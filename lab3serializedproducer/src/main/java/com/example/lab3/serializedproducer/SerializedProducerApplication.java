package com.example.lab3.serializedproducer;

import com.example.lab3.serializedproducer.model.Employee;
import com.example.lab3.serializedproducer.producer.SerializedProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SerializedProducerApplication implements CommandLineRunner {

    @Autowired
    SerializedProducer sp;

    public static void main(String[] args) {
        SpringApplication.run(SerializedProducerApplication.class, args);
    }
    @Override
    public void run(String... strings) throws Exception {
        Employee emp = new Employee(1, 25, "abc", "xyz");

        sp.send(emp);

    }

}
