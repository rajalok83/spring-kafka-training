package com.example.springbootkafka.lab2.simpleconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SimpleConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleConsumer.class);

    @Value("${app.topic.simple}")
    private String topic;

    @KafkaListener(topics = {"simpleproducer"})
    public void consume(String msg){
        System.out.println("Message is "+msg);
    }
}