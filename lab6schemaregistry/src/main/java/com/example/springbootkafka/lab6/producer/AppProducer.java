package com.example.springbootkafka.lab6.producer;

import com.example.springbootkafka.lab6.avro.ClickRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class AppProducer implements CommandLineRunner {
    @Autowired
    SpringKafkaAvroProducer skap;
    public static void main(String[] args) {
        SpringApplication.run(AppProducer.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        Scanner in = new Scanner(System.in);
        String s;
        do {
            ClickRecord cr = new ClickRecord();
            System.out.println("Give Session ID(type bye to exit):");
            s = in.nextLine();
            if(s.equalsIgnoreCase("bye"))
                break;
            else
                cr.setSessionId(s);
            System.out.println("Give Browser(type bye to exit):");
            s = in.nextLine();
            if(s.equalsIgnoreCase("bye"))
                break;
            else
                cr.setBrowser(s);
            System.out.println("Give Campaign(type bye to exit):");
            s = in.nextLine();
            if(s.equalsIgnoreCase("bye"))
                break;
            else
                cr.setCampaign(s);
            System.out.println("Give Channel(type bye to exit):");
            s = in.nextLine();
            if(s.equalsIgnoreCase("bye"))
                break;
            else
                cr.setChannel(s);
            System.out.println("Give Referrer(type bye to exit):");
            s = in.nextLine();
            if(s.equalsIgnoreCase("bye"))
                break;
            else
                cr.setReferrer(s);
            System.out.println("Give IP(type bye to exit):");
            s = in.nextLine();
            if(s.equalsIgnoreCase("bye"))
                break;
            else
                cr.setIp(s);
            skap.sendMsg(cr);
        }while(!s.equalsIgnoreCase("bye"));
    }
}
