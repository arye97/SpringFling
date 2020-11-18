package com.example.springfling.Payload.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {
    @JsonProperty("Token")
    private String token;

    @JsonProperty("userId")
    private Long userId;

    public LoginResponse(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public Long getUserId() {
        return userId;
    }
}
