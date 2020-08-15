package com.example.demo.service.impl;

import com.example.demo.model.Authority;
import com.example.demo.model.User;
import com.example.demo.model.exceptions.AccessDeniedException;
import com.example.demo.model.exceptions.DuplicateDataException;
import com.example.demo.model.exceptions.NotFoundException;
import com.example.demo.model.exceptions.UnverifiedException;
import com.example.demo.model.lsp.UserStatus;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.Generator;
import com.example.demo.util.MailSenderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static com.example.demo.util.Messages.*;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private MailSenderClient mailSenderClient;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    @Transactional
    public void register(User user) throws DuplicateDataException {
        User duplicate = userRepository.getByUsername(user.getUsername());
        DuplicateDataException.check(duplicate != null, DUPLICATE_USER_MESSAGE);

        Authority authority = authorityRepository.getById(1);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCode(Generator.getRandomDigits(5));
        user.setStatus(UserStatus.UNVERIFIED);
        user.setAuthorities(Collections.singletonList(authority));

        userRepository.save(user);
        mailSenderClient.sendSimpleMessage(user.getUsername(), "Verification", "Your code is " + user.getCode());
    }


    @Override
    public User login(String username, String password) throws NotFoundException, UnverifiedException {
        User user = userRepository.getByUsername(username);
        NotFoundException.check(user == null || !passwordEncoder.matches(password, user.getPassword()), INVALID_CREDENTIALS_MESSAGE);
        UnverifiedException.check(user.getStatus() != UserStatus.ACTIVE, UNVERIFIED_MESSAGE);
        return user;

    }

    @Override
    public User changePassword(String username, String password, String newPassword) throws NotFoundException, AccessDeniedException {
        User user = userRepository.getByUsername(username);
        NotFoundException.check(user == null, USER_NOT_EXIST_MESSAGE);
        AccessDeniedException.check(!user.getPassword().equals(passwordEncoder.encode(password)), WRONG_PASSWORD_MESSAGE);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional
    public void sendCode(String username) throws NotFoundException {
        User user = userRepository.getByUsername(username);
        NotFoundException.check(user == null, USER_NOT_EXIST_MESSAGE);
        user.setCode(Generator.getRandomDigits(5));
        userRepository.save(user);
        mailSenderClient.sendSimpleMessage(user.getUsername(), "ACCESS CODE", "Your code is " + user.getCode());
    }

    @Override
    public void recoverPassword(String username, String code, String password) throws NotFoundException, AccessDeniedException {
        User user = userRepository.getByUsername(username);
        NotFoundException.check(user == null, USER_NOT_EXIST_MESSAGE);
        AccessDeniedException.check(!user.getCode().equals(code), WRONG_CODE_MESSAGE);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public void verify(String username, String code) throws NotFoundException, AccessDeniedException {
        User user = userRepository.getByUsername(username);
        NotFoundException.check(user == null, USER_NOT_EXIST_MESSAGE);
        AccessDeniedException.check(!user.getCode().equals(code), WRONG_CODE_MESSAGE);
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }

    @Override
    public void makeAdmin(int userId) throws NotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_EXIST_MESSAGE));
        Authority authority = authorityRepository.getById(2);
        user.getAuthorities().add(authority);
        userRepository.save(user);
    }
}
