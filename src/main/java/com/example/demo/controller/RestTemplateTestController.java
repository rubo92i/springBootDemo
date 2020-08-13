package com.example.demo.controller;

import com.example.demo.model.Card;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.Executor;

@Log4j2
@RestController
@RequestMapping("/test")
public class RestTemplateTestController {


    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/1")
    public ResponseEntity getCards() {
        List<Card> cards = restTemplate.getForObject("http://localhost:8080/api/cards", List.class);
        System.out.println(cards);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/2")
    public ResponseEntity addCards() {

        Card card = new Card();
        card.setNumber("1212535244");
        card.setHolder("dfsfsd");
        card.setCvv("987");
        card.setBalance(2000);

          try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("OriginModule", "demo2");
            httpHeaders.add("Authorization","Basic "+ Base64.encodeBase64String("admin:admin_password".getBytes()));

            HttpEntity<Card> httpEntity = new HttpEntity<>(card, httpHeaders);
            ResponseEntity<Card> cardResponseEntity = restTemplate.exchange("http://localhost:8080/api/cards", HttpMethod.POST, httpEntity, Card.class);

            log.info(card.getNumber());
            return cardResponseEntity;

        } catch (RestClientResponseException exception) {
            log.error(exception.getMessage());
            return ResponseEntity
                    .status(exception.getRawStatusCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(exception.getResponseBodyAsString());
        }


    }
}
