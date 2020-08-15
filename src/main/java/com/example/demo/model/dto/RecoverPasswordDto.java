package com.example.demo.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RecoverPasswordDto {


    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String code;

}
