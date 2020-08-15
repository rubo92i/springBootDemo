package com.example.demo.config;

import com.example.demo.model.User;
import com.example.demo.model.lsp.UserStatus;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user = userRepository.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }

        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new LockedException("User is unverified");
        }


        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }
}
