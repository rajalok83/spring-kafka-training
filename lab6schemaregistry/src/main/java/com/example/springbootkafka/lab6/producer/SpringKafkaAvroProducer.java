package com.example.springbootkafka.lab6.producer;

import com.example.springbootkafka.lab6.avro.ClickRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SpringKafkaAvroProducer {
    @Autowired
    KafkaTemplate<String, ClickRecord> kafkaTemplate;
    @Value ("${spring.app.topic}")
    String topicName;
    public void sendMsg(ClickRecord  cr){
        kafkaTemplate.send(topicName, cr.getSessionId().toString(), cr);
    }
}
