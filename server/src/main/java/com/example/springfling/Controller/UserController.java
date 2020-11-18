package com.example.springfling.Controller;

import com.example.springfling.Model.User;
import com.example.springfling.Payload.Request.UserLoginRequest;
import com.example.springfling.Payload.Request.UserRegisterRequest;
import com.example.springfling.Payload.Response.LoginResponse;
import com.example.springfling.Repository.UserRepository;
import com.example.springfling.Service.UserService;
import com.example.springfling.Validator.UserValidator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
public class UserController {

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserValidator userValidator,
                          UserService userService) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
        this.userService = userService;
    }

    @PostMapping("/register")
    public LoginResponse registerNewUser(@Validated @RequestBody UserRegisterRequest userData) {

        User newUser = new User(userData);
        userValidator.validate(newUser);
        userRepository.save(newUser);
        return userService.login(newUser.getEmail(), userData.getPassword());
    }

    @PostMapping("/login")
    public LoginResponse login(@Validated @RequestBody UserLoginRequest userData) {
        return userService.login(userData.getEmail(), userData.getPassword());
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Token");
        if (token != null) {
          userService.logout(token);
          response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

}