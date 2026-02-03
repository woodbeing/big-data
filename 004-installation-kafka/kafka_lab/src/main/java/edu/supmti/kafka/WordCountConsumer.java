package edu.supmti.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class WordCountConsumer {
    public static void main(String[] args) throws Exception {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9094");
        props.put("group.id", "wordcount-group");
        props.put("enable.auto.commit", "true");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Arrays.asList("WordCount-Topic"));

        Map<String, Integer> wordCounts = new HashMap<>();

        System.out.println("Attente de messages sur le topic " + "WordCount-Topic" + "...");

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {

                String[] words = record.value().toLowerCase().split("\\W+");

                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                    }
                }

                System.out.println("Fréquences actuelles : " + wordCounts);
            }
        }
    }
}