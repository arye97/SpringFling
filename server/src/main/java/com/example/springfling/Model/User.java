package com.example.springfling.Model;

import com.example.springfling.Payload.Request.UserRegisterRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.validation.constraints.NotNull;


import javax.persistence.*;

@Entity
public class User {

//    //Using BCRYPT for passwords
//    private static PasswordEncoder encoder = new BCryptPasswordEncoder();

    //private String token;

    private static Log log = LogFactory.getLog(User.class);

    static final private int FIELD_LEN = 45;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    @JsonProperty("id")
    private Long userId;

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

    public User(UserRegisterRequest userData) {
        this.setFirstName(userData.getFirstName());
        this.setLastName(userData.getLastName());
        this.setEmail(userData.getEmail());
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
}