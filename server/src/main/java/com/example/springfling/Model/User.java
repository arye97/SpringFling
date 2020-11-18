package com.example.springfling.Model;

import com.example.springfling.Payload.Request.UserRegisterRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotNull;


import javax.persistence.*;
import java.util.Date;

@Entity
public class User {

    //Using BCRYPT for passwords
    private static PasswordEncoder encoder = new BCryptPasswordEncoder();

    private static int tokenDecayTime = 30000 * 30; // 30 minutes (30 sec * 30 mins = 15 mins)
    static final private int FIELD_LEN = 45;

    private String token;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    @JsonProperty("id")
    private Long userId;

    @Column(name = "token_time")
    private Date tokenTime;

    @NotNull(message = "Please provide a first name")
    @Column(name = "first_name", length = FIELD_LEN, nullable = false)
    @JsonProperty("firstname")
    private String firstName;

    @NotNull(message = "Please provide a last name")
    @Column(name = "last_name", length = FIELD_LEN, nullable = false)
    @JsonProperty("lastname")
    private String lastName;

    @NotNull(message = "Please provide an email")
    @Column(name = "email", length = FIELD_LEN, nullable = false)
    @JsonProperty("email")
    private String email;

    @NotNull(message = "Please provide a password")
    @Column(name = "password", nullable = false)
    @JsonProperty("password")
    private String password;

    public User(UserRegisterRequest userData) {
        this.setFirstName(userData.getFirstName());
        this.setLastName(userData.getLastName());
        this.setEmail(userData.getEmail());
        this.setPassword(userData.getPassword());
    }


    public User() {}

    public Long getId() {
        return userId;
    }

    public void setId(Long id) {
        this.userId = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setTokenTime() {
        this.tokenTime = new Date();
    }

    public boolean isTimedOut() {
        ///time calculated in milliseconds
        if (this.tokenTime != null) {
            Date now = new Date();
            long diff = now.getTime() - tokenTime.getTime();
            return diff >= tokenDecayTime || diff < 0;
        } else return true; //Default to the user being timed out
    }

    public boolean checkPassword(String password) {
        return encoder.matches(password, this.password);
    }

    public void setPassword(String password) {
        if (password == null) {
            this.password = null;
        } else {
            this.password = encoder.encode(password);
        }
    }
}