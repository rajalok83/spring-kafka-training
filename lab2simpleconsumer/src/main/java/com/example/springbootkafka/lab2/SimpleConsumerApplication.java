package com.example.springbootkafka.lab2;

import com.example.springbootkafka.lab2.simpleconsumer.SimpleConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleConsumerApplication.class, args);
    }

}
