package com.example.springfling.Controller;

import com.example.springfling.Model.User;
import com.example.springfling.Payload.Request.UserLoginRequest;
import com.example.springfling.Payload.Request.UserRegisterRequest;
import com.example.springfling.Payload.Response.LoginResponse;
import com.example.springfling.Payload.Response.UserProfileResponse;
import com.example.springfling.Repository.UserRepository;
import com.example.springfling.Service.UserService;
import com.example.springfling.Validator.UserValidator;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



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

        User checkUser = userRepository.findByEmail(userData.getEmail());
        if (checkUser != null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This email has already been registered!");
        }

        User newUser = new User(userData);
        userValidator.validate(newUser);
        userRepository.save(newUser);
        return userService.login(newUser.getEmail(), userData.getPassword());
    }

    /**
     * For the user to sign in to the platform
     * @param userData the JSON object to read data from
     * @return a login response containing the user token and id for use in FE
     */
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

    /**
     * Returns the specific users information based on their userId
     * @param userId the id of the specific user
     * @return the user's information as in UserProfileResponse
     */
    @GetMapping("/user/{userId}")
    public UserProfileResponse getUserProfile(@PathVariable Long userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
        }
        return new UserProfileResponse(user);
    }

}