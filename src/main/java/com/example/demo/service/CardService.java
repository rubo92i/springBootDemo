package com.example.demo.service;


import com.example.demo.model.Card;
import com.example.demo.model.exceptions.DuplicateDataException;

import java.util.List;
import java.util.Optional;

public interface CardService {
    void add(Card card) throws DuplicateDataException;

    List<Card> findAll();

    Optional<Card> getById(int id) ;

    void deleteById(int id);

    Card getByNumber(String number);

    void update(Card card) throws DuplicateDataException;
}
