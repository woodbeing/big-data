package edu.supmti.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;
import java.util.Scanner;

public class WordCountProducer {
public static void main(String[] args) throws Exception {

        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9093");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Saisissez du texte (Ctrl+C pour quitter) :");
        while (scanner.hasNextLine()) {
            System.out.print("> ");
            String line = scanner.nextLine();    
            producer.send(new ProducerRecord<>("WordCount-Topic", line, line));
        }

        producer.close();
        scanner.close();
    }
}