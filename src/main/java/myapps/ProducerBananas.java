package myapps;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProducerBananas {
	
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
		
		int[] counts = {5, 3, 67, 78, 93, 108, 98, 75, 79, 84, 15, 23, 45};

		for(int count : counts) {
			Fruit myFruit = new Fruit(Fruit.BANANA, count);
			JsonNode jsonNode = objectMapper.valueToTree(myFruit);
			ProducerRecord<String, JsonNode> record = new ProducerRecord<String, JsonNode>("streams-fruits-input", jsonNode);
			producer.send(record);
		}

		producer.close();
	}
}
