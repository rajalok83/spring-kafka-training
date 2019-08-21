package com.example.springbootkafka.lab1.simpleproducer.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class SimpleProducer {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleProducer.class);

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    @Value("${app.topic.simple}")
    private String topic;

    public void send(String message, boolean isAsync) {
        LOG.info("sending message='{}' to topic='{}'", message, topic);
        if (isAsync) {
            ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(topic, message);
            future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
                @Override
                public void onSuccess(SendResult<Integer, String> result) {
                    System.out.println("Sent message=[" + message +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                }

                @Override
                public void onFailure(Throwable ex) {
                    System.out.println("Unable to send message=["
                            + message + "] due to : " + ex.getMessage());
                }
            });
        } else kafkaTemplate.send(topic, message);
    }

}