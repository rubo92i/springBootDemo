package com.example.demo.controller;

import com.example.demo.model.exceptions.NotFoundException;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private UserService userService;


    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/{id}/make-admin")
    public ResponseEntity makeAdmin(@PathVariable("id") int userId) throws NotFoundException {
        userService.makeAdmin(userId);
        return ResponseEntity.ok().build();
    }
}
