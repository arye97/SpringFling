package com.example.springfling.Controller;

import com.example.springfling.Model.User;
import com.example.springfling.Payload.Request.UserRegisterRequest;
import com.example.springfling.Repository.UserRepository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User registerNewUser(@Validated @RequestBody UserRegisterRequest userData) {
        System.out.println(userData.getLastName());
        User newUser = new User(userData);
        userRepository.save(newUser);
        return newUser;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}