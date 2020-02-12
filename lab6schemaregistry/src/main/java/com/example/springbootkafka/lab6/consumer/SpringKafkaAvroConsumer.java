package com.example.springbootkafka.lab6.consumer;

import com.example.springbootkafka.lab6.avro.ClickRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class SpringKafkaAvroConsumer {
    @KafkaListener(topics = {"avrotopic"})
    public  void onMessage(ClickRecord clickRecord, Acknowledgment ack){
        System.out.println(clickRecord.toString());
        ack.acknowledge();
    }
}
