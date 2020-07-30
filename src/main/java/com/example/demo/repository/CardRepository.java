package com.example.demo.repository;


import com.example.demo.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    Card getById(int id);

    Card getByNumber(String number);

    boolean existsByNumber(String number);

    boolean existsByNumberAndIdNot(String number, int id);

}


