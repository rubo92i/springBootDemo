package com.example.demo.controller;

import com.example.demo.model.Car;
import org.apache.commons.io.FileUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/interconnect")
public class TestController {


    @GetMapping("/test1")
    public ResponseEntity test() throws IOException {
        Car car = new Car();
        car.setId(15);
        car.setModel("sdfnskd");
        car.setVendor("sdgfsdgfs");
        car.setVin("sf45sd154145");
        car.setYear(2020);
        byte[] data = SerializationUtils.serialize(car);
        new FileOutputStream("test").write(data);
        return ResponseEntity.ok().build();

    }


    @GetMapping("/test2")
    public ResponseEntity test2() throws IOException {
        byte [] data = FileUtils.readFileToByteArray(new File("test"));
        Car car = (Car) SerializationUtils.deserialize(data);
        return ResponseEntity.ok(car);

    }
}
