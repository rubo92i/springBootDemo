package com.example.demo.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SpamGenerator {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    @PostConstruct
    public void startSpam() {

        for (int i = 0 ; i <= 10; i++){
            new Thread(this::execute).start();
        }

    }


    private void execute(){
        long l = 0;
        while (true) {
            l++;
            rabbitTemplate.convertAndSend("que01", "Message with number : " + l);
        }
    }

}
