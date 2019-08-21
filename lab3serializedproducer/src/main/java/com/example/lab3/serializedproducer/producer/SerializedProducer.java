package com.example.lab3.serializedproducer.producer;

import com.example.lab3.serializedproducer.model.Employee;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SerializedProducer {
    @Value("${app.topic.serialized}")
    String topic;
    @Autowired
    KafkaTemplate<Integer, Employee> kafkaTemplate;

    public void send(Employee emp){
        kafkaTemplate.send(topic, emp);
    }

}
