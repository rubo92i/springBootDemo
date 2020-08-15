package com.example.demo.controller;

import com.example.demo.model.Car;
import com.example.demo.model.exceptions.DuplicateDataException;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/car")
public class CarController {

    @Autowired
    private CarService carService;


    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(path = "/test1", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Car> getAllByBody() {
        return carService.getAll();
    }


    // @Secured("ROLE_role1")
    // @PreAuthorize("hasRole('ROLE_role1')")
    //@RolesAllowed(value = {"ROLE_role1", "ROLE_role2"})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll() {
        return ResponseEntity.ok(carService.getAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable int id) {
        return ResponseEntity.of(carService.findById(id));
    }

    @PostMapping(/*spasum a back@ */consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@Valid @RequestBody Car car) throws DuplicateDataException {
        carService.save(car);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@PathVariable int id, @Valid @RequestBody Car car) throws DuplicateDataException {
        car.setId(id);
        return ResponseEntity.ok(carService.update(car));
    }


}
