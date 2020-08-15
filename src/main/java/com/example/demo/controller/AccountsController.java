package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.dto.RecoverPasswordDto;
import com.example.demo.model.dto.UsernameCodeDto;
import com.example.demo.model.exceptions.AccessDeniedException;
import com.example.demo.model.exceptions.DuplicateDataException;
import com.example.demo.model.exceptions.NotFoundException;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountsController {


    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user) throws DuplicateDataException {
        userService.register(user);
        return ResponseEntity.ok(user);
    }


    @PostMapping("/forget-password")
    public ResponseEntity forgetPassword(@RequestParam String username) throws NotFoundException {
        userService.sendCode(username);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/recover-password")
    public ResponseEntity recoverPassword(@RequestBody @Valid RecoverPasswordDto dto) throws NotFoundException, AccessDeniedException {
        userService.recoverPassword(dto.getUsername(), dto.getCode(), dto.getPassword());
        return ResponseEntity.ok().build();
    }


    @PostMapping("/resend")
    public ResponseEntity resend(@RequestParam String username) throws NotFoundException {
        userService.sendCode(username);
        return ResponseEntity.ok().build();
    }



    @PostMapping(path = "/verify")
    public ResponseEntity verify(@RequestBody UsernameCodeDto dto) throws NotFoundException, AccessDeniedException {
        userService.verify(dto.getUsername(), dto.getCode());
        return ResponseEntity.ok().build();

    }


}
