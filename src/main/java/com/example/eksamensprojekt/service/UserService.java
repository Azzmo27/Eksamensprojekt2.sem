package com.example.eksamensprojekt.service;

import com.example.eksamensprojekt.model.User;
import com.example.eksamensprojekt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void createUser(User user) {
        userRepository.createUser(user);
    }

    public Boolean verifyUserLogin(String username, String userPassword) {
        return userRepository.verifyUserLogin(username, userPassword);
    }

    public int getUserId(String username, String userPassword) {
        return userRepository.getUserId(username, userPassword);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
