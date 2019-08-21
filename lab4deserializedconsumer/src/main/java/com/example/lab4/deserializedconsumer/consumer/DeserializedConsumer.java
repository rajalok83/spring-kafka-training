package com.example.lab4.deserializedconsumer.consumer;


import com.example.lab4.deserializedconsumer.model.Employee;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class DeserializedConsumer {
    @KafkaListener(topics = {"serializedproducer"})
    public void consume(Employee emp){
        System.out.println("Message is "+emp.toString());
    }
}