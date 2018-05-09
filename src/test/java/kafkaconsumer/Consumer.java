package kafkaconsumer;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

public class Consumer {
	
    private static Scanner in;

    public static void main(String[] argv)throws Exception{
      
        in = new Scanner(System.in);
        String topicName="REST_PROC_REQ_1";
        String topicName1 ="PANEL_EVENTS_1";
        String groupId = "REQUEST_RESPONSE_JOB_GROUP";
        String var;


        ConsumerThread consumerRunnable = new ConsumerThread(topicName,groupId);
        consumerRunnable.start();
       
    }

    private static class ConsumerThread extends Thread{
        private String topicName;
        private String groupId;
        private KafkaConsumer<String,String> kafkaConsumer0;
        private KafkaConsumer<String,String> kafkaConsumer1;
        private KafkaConsumer<String,String> kafkaConsumer2;

        
        public ConsumerThread(String topicName, String groupId){
            this.topicName = topicName;
            this.groupId = groupId;
        }
        public void run() {
            Properties configProperties = new Properties();
            configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.18.0.2:9092");
            configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArrayDeserializer");
            configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
            configProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, "simple");

            //Figure out where to start processing messages from
            kafkaConsumer0 = new KafkaConsumer<String, String>(configProperties);
            kafkaConsumer0.subscribe(Arrays.asList(topicName));
            kafkaConsumer1 = new KafkaConsumer<String, String>(configProperties);
            kafkaConsumer1.subscribe(Arrays.asList(topicName));
            kafkaConsumer2 = new KafkaConsumer<String, String>(configProperties);
            kafkaConsumer2.subscribe(Arrays.asList(topicName));
            
            //Start processing messages
            try {
                while (true) {
                    ConsumerRecords<String, String> records0 = kafkaConsumer0.poll(100);
                    ConsumerRecords<String, String> records1 = kafkaConsumer1.poll(100);
                    ConsumerRecords<String, String> records2 = kafkaConsumer2.poll(100);

					for (ConsumerRecord<String, String> record0 : records0)
                        System.out.println(record0.value());
					for (ConsumerRecord<String, String> record1 : records1)
                        System.out.println(record1.value());
					for (ConsumerRecord<String, String> record2 : records2)
                        System.out.println(record2.value());
                }
            }catch(WakeupException ex){
                System.out.println("Exception caught " + ex.getMessage());
            }finally{
                kafkaConsumer0.close();
                kafkaConsumer1.close();
                kafkaConsumer2.close();
                System.out.println("After closing KafkaConsumer");
            }
        }
       
    }
}