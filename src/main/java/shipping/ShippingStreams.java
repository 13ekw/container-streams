package shipping;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import serialization.JsonPOJODeserializer;
import serialization.JsonPOJOSerializer;

public class ShippingStreams {
	
	public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "shippingstream");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9092");
	
        final StreamsBuilder builder = new StreamsBuilder();
        
        Map<String, Object> serdeProps = new HashMap<>();
        
        final Serializer<ContainerMessage> containerSerializer = new JsonPOJOSerializer<>();
        serdeProps.put("JsonPOJOClass", ContainerMessage.class);
        containerSerializer.configure(serdeProps, false);
        
        final Deserializer<ContainerMessage> containerDeserializer = new JsonPOJODeserializer<>();
        serdeProps.put("JsonPOJOClass", ContainerMessage.class);
        containerDeserializer.configure(serdeProps, false);
        
        final Serde<ContainerMessage> containerSerde  = Serdes.serdeFrom(containerSerializer, containerDeserializer);

        final Serializer<ProblemMessage> problemSerializer = new JsonPOJOSerializer<>();
        serdeProps.put("JsonPOJOClass", ProblemMessage.class);
        problemSerializer.configure(serdeProps, false);
        
        final Deserializer<ProblemMessage> problemDeserializer = new JsonPOJODeserializer<>();
        serdeProps.put("JsonPOJOClass", ProblemMessage.class);
        problemDeserializer.configure(serdeProps, false);
        
        final Serde<ProblemMessage> problemSerde  = Serdes.serdeFrom(problemSerializer, problemDeserializer);
        
        KStream<String, ContainerMessage> source = builder.stream("bluewaterContainer", Consumed.with(Serdes.String(), containerSerde));
        
        @SuppressWarnings("unchecked")
		KStream<String, ContainerMessage>[] branches = source
			.selectKey(
        		(key, container) -> container.getShipID()
        	)
        	.branch(
        		(key, container) -> container.getTempC() > 75 && container.getTempC() <= 150,
        		(key, container) -> container.getTempC() > 150 
        );
        
        branches[0].mapValues(container -> new ProblemMessage(container.getContainerId(), container.getTempC(), container.getTimestampMillis(), "WARNING temperature approaching danger level")) 
        	.to("streams-shipping-warning-output", Produced.with(Serdes.String(), problemSerde));
        
        branches[1].mapValues(container -> new ProblemMessage(container.getContainerId(), container.getTempC(), container.getTimestampMillis(), "DANGER temperature has exceeded recommended level")) 
        	.to("streams-shipping-danger-output", Produced.with(Serdes.String(), problemSerde));
        
        final Topology topology = builder.build();
        System.out.println(topology.describe());
        
        final KafkaStreams streams = new KafkaStreams(topology, props);
        final CountDownLatch latch = new CountDownLatch(1);
        
        Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
        	@Override
        	public void run() {
	        	streams.close();
	        	latch.countDown();
	        }
	    });
        
        try {
        	streams.start();
        	latch.await();
        } catch (Throwable e) {
        	System.exit(1);
        }
        
        System.exit(0);
	}
}
