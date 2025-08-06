package com.gonie.shoppingmall.service;

import com.gonie.shoppingmall.model.User;
import com.gonie.shoppingmall.customExceptions.*;
import com.gonie.shoppingmall.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;


    public String cryptPassword(String rawPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(rawPassword);
    }

    public User registerUser(String username, String password) {

        User existingUser = repo.findByUsername(username);
        if (existingUser != null) {

            throw new UsernameAlreadyExistsException("Username already exists");
        }
        String hashedPassword = cryptPassword(password);
        User newUser = new User(username, hashedPassword, "user");
        return repo.save(newUser);
    }

    public User loginUser(String username, String password) {
        User existingUser = repo.findByUsername(username);
        if (existingUser == null) {
            throw new UsernameNoExistsException("Username is not Exist");

        }else{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (!encoder.matches(password, existingUser.getPassword())){
                throw new BadCredentialsException("Invalid password");
            }
            return existingUser;
        }
    }
}


