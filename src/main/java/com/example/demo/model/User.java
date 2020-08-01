package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   // @NotBlank
    private String name;

    //@NotBlank
    private String surname;

    private String code;

   // @NotBlank(message = "Username must not be blank")
    @Column(name = "username", nullable = false, unique = true)
    private String username;


  //  @NotBlank(message = "Password must not be blank")
  //  @Size(min = 6, message = "Password must be more then 6 characters")
    private String password;

    @Column(name = "status", nullable = false)
    private int status;


    @OneToOne
    private Card card;

}
