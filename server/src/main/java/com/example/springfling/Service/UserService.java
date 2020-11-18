package com.example.springfling.Service;

import com.example.springfling.Model.User;
import com.example.springfling.Payload.Response.LoginResponse;
import com.example.springfling.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;

@Service("userService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final String CHAR_LIST =
            "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Login helper function. Generates a random String value for token, to be stored in the database.
     * @return Random String for Token.
     */
    private String generateNewToken() {
        int length = 30;
        StringBuilder strBuffer = new StringBuilder(length);
        SecureRandom secureRandom = new SecureRandom();
        for(int i = 0; i < length; i++)
            strBuffer.append(CHAR_LIST.charAt(secureRandom.nextInt(CHAR_LIST.length())));
        return strBuffer.toString();
    }

    /**
     * Generates a token and stores token in repository if valid email and password
     * @param email user's email to login
     * @param password user's password to login
     * @return the token generated or ResponseStatusException is thrown
     */
    public LoginResponse login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user.getEmail() == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Could not find user with email " + email);
        }
        if (user.checkPassword(password)) {
            String token = generateNewToken();
            user.setToken(token);
            user.setTokenTime();
            userRepository.save(user);
            return new LoginResponse(token, user.getId());
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login unsuccessful, please enter a valid password");
    }

    /**
     * Find user which contains token and remove it
     * @param token a user's token
     */
    public void logout(String token) {
        User user = userRepository.findByToken(token);
        if (user != null) {
            user.setToken(null);
            userRepository.save(user);
        }
    }
}
