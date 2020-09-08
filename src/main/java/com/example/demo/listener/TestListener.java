package com.example.demo.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class TestListener {


    @RabbitListener(queuesToDeclare = @Queue(name = "que01"))
    public void listen(String string) {
        log.info("Received message with content : {}", string);
    }

}
