package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.exceptions.AccessDeniedException;
import com.example.demo.model.exceptions.DuplicateDataException;
import com.example.demo.model.exceptions.NotFoundException;
import com.example.demo.model.exceptions.UnverifiedException;

public interface UserService {



    void register(User user) throws DuplicateDataException;

    User login(String username, String password) throws NotFoundException, UnverifiedException;

    User changePassword(String username, String password, String newPassword) throws NotFoundException, AccessDeniedException;

    void sendCode(String username) throws NotFoundException;

    void recoverPassword(String username, String code, String password) throws NotFoundException, AccessDeniedException;

    void verify(String username, String code) throws NotFoundException, AccessDeniedException;

    void makeAdmin(int userId) throws NotFoundException;

}
