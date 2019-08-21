package com.example.springbootkafka.lab1.simpleproducer;

import com.example.springbootkafka.lab1.simpleproducer.producer.SimpleProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@SpringBootApplication
public class SimpleproducerApplication implements CommandLineRunner {
    boolean isAsync=true;
    @Autowired
    private SimpleProducer sender;



    public static void main(String[] args) {
        SpringApplication.run(SimpleproducerApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        sender.send("Spring Kafka and Spring Boot Configuration Example", isAsync);



    }
}
