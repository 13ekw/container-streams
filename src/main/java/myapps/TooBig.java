package myapps;

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

public class TooBig {
	
	public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-toobig");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	
        final StreamsBuilder builder = new StreamsBuilder();
        
        Map<String, Object> serdeProps = new HashMap<>();
        
        final Serializer<Fruit> fruitSerializer = new JsonPOJOSerializer<>();
        serdeProps.put("JsonPOJOClass", Fruit.class);
        fruitSerializer.configure(serdeProps, false);
        
        final Deserializer<Fruit> fruitDeserializer = new JsonPOJODeserializer<>();
        serdeProps.put("JsonPOJOClass", Fruit.class);
        fruitDeserializer.configure(serdeProps, false);
        
        final Serde<Fruit> fruitSerde  = Serdes.serdeFrom(fruitSerializer, fruitDeserializer);

        
        final Serializer<FruitAvalanche> avalancheSerializer = new JsonPOJOSerializer<>();
        serdeProps.put("JsonPOJOClass", Fruit.class);
        avalancheSerializer.configure(serdeProps, false);
        
        final Deserializer<FruitAvalanche> avalancheDeserializer = new JsonPOJODeserializer<>();
        serdeProps.put("JsonPOJOClass", FruitAvalanche.class);
        avalancheDeserializer.configure(serdeProps, false);
        
        final Serde<FruitAvalanche> avalancheSerde  = Serdes.serdeFrom(avalancheSerializer, avalancheDeserializer);
        
        KStream<String, Fruit> source = builder.stream("streams-fruits-input", Consumed.with(Serdes.String(), fruitSerde));
        
        source.selectKey((key, fruit) -> fruit.getType());
        
        @SuppressWarnings("unchecked")
        KStream<String, Fruit>[] branches = source.branch(
        		(key, fruit) -> fruit.getCount() > 75 && fruit.getCount() < 90,
        		(key, fruit) -> fruit.getCount() > 90 
        );
        
        branches[0].mapValues(fruit -> new FruitAvalanche(fruit.getCount() - 75))
        	.to("streams-overflow-warning-output", Produced.with(Serdes.String(), avalancheSerde));
        
        branches[1].mapValues(fruit -> new FruitAvalanche(fruit.getCount() - 75)) 
        	.to("streams-overflow-danger-output", Produced.with(Serdes.String(), avalancheSerde));
        
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