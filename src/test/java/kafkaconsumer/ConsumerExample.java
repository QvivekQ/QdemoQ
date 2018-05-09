package kafkaconsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerExample {
	public static KafkaConsumer<String, String> consumer = null;

	public static void main(String[] args) {

		// consumer properties
		Properties props = new Properties();
		props.put("bootstrap.servers", "172.18.0.2:9092");
		props.put("group.id", "REQUEST_RESPONSE_JOB_GROUP");

		// using auto commit
		props.put("enable.auto.commit", "true");

		// string inputs and outputs
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		// kafka consumer object
		consumer = new KafkaConsumer<String, String>(props);

		// subscribe to topic
		consumer.subscribe(Arrays.asList("REST_PROC_REQ_1"));

		// infinite poll loop
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records)
				System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
		}

	}

}