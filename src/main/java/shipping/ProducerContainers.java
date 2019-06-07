package shipping;

import java.time.LocalDateTime;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProducerContainers {
	
	public static void main(String[] args) {
		Properties producerProps = new Properties();
		producerProps.put("bootstrap.servers", "localhost:9092");
		producerProps.put("acks", "all");
		producerProps.put("delivery.timeout.wait", "30000");
		producerProps.put("batch.size", 16384);
		producerProps.put("linger.ms", 1);
		producerProps.put("buffer.memory", 33554432);
		producerProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producerProps.put("value.serializer", "org.apache.kafka.connect.json.JsonSerializer");
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		KafkaProducer<String, JsonNode> producer = new KafkaProducer<>(producerProps);
		
		int[] tempCs = {32, 35, 76, 25, 85, 24, 39, 89, 105, 143, 34, 27, 26, 93, 26, 30, 76};
		String[] shipID = {"ShipA", "ShipB", "ShipC"};
		String[] containerId = {"Container1", "Container2", "Container3"};
		
		for(int tempC : tempCs) {
			int shipNumber = ThreadLocalRandom.current().nextInt(0, 3);
			int containerNumber = ThreadLocalRandom.current().nextInt(0, 3);
			ContainerMessage myContainer = new ContainerMessage(containerId[containerNumber], tempC, 0, 0.0, 0, 0.0, 0.0, 0, shipID[shipNumber], LocalDateTime.now().toString());
			JsonNode jsonNode = objectMapper.valueToTree(myContainer);
			System.out.println("sending ...");
			ProducerRecord<String, JsonNode> record = new ProducerRecord<String, JsonNode>("streams-shipping-input", jsonNode);
			producer.send(record);
		}

		producer.close();
	}
}
