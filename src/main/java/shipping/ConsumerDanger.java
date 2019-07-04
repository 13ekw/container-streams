package shipping;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.Serdes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConsumerDanger {
	public static void main(String[] args) {
		Properties consumerProps = new Properties();
		consumerProps.put("bootstrap.servers", "kafka1:9092");
		consumerProps.put("group.id", "mygroup");
		consumerProps.put("enable.auto.commit", "true");
		consumerProps.put("auto.commit.interval.ms", "1000");
		consumerProps.put("session.timeout.ms", "30000");
		consumerProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		consumerProps.put("value.deserializer", "org.apache.kafka.connect.json.JsonDeserializer");

		KafkaConsumer<String, JsonNode> consumer = new KafkaConsumer<String, JsonNode>(consumerProps);
		
		try {
			consumer.subscribe(Arrays.asList("streams-shipping-danger-output"));
			
			ObjectMapper mapper = new ObjectMapper();
			
			while (true) {
				ConsumerRecords<String, JsonNode> records = consumer.poll(100);
				for (ConsumerRecord<String, JsonNode> record : records) {
					JsonNode jsonNode = record.value();
					System.out.println(record.key()+ "-" + mapper.treeToValue(jsonNode, ProblemMessage.class));
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			consumer.close();
		}
	}
}








