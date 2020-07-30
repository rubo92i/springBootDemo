package com.example.demo.service;

import com.example.demo.model.Car;
import com.example.demo.model.exceptions.DuplicateDataException;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> getAll();

    Optional<Car> findById(int id);

    Car save(Car car) throws DuplicateDataException;

    Car update(Car car) throws DuplicateDataException;


}
