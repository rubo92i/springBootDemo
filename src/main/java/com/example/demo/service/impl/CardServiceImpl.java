package com.example.demo.service.impl;


import com.example.demo.model.Card;
import com.example.demo.model.exceptions.DuplicateDataException;
import com.example.demo.repository.CardRepository;
import com.example.demo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {


    @Autowired
    private CardRepository cardRepository;


    @Override
    public void add(Card card) throws DuplicateDataException {
        DuplicateDataException.check(cardRepository.existsByNumber(card.getNumber()),"duplicate.card.number");
        cardRepository.save(card);
    }


    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();
    }


    @Override
    public Optional<Card> getById(int id) {
        return cardRepository.findById(id);
    }

    @Override
    @Async("taskExecutorFirst")
    public void deleteById(int id) {
        cardRepository.deleteById(id);
    }

    @Override
    public Card getByNumber(String number) {
        return cardRepository.getByNumber(number);
    }


    @Override
    @Transactional
    @Async("taskExecutorSecond")
    public void update(Card card) throws DuplicateDataException {
        DuplicateDataException.check(cardRepository.existsByNumberAndIdNot(card.getNumber(),card.getId()),"duplicate.card");
        Card fromDb = cardRepository.getById(card.getId());
        fromDb.setBalance(card.getBalance());
        fromDb.setCvv(card.getCvv());
        fromDb.setHolder(card.getHolder());
        fromDb.setNumber(card.getNumber());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
