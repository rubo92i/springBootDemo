package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@Size(min = 2,message = "Name must be more then 2 characters")
    private String name;

    //@Size(min = 2,message = "Description must be more then 2 characters")
    private String description;

    @Column(name = "user_id", nullable = false)
    private int userId;



}
