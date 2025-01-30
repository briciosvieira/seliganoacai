package com.seliganoacai.acai.JWT;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class JwtToken {

    private String Token;


    public JwtToken(String token) {
        Token = token;
    }

    public JwtToken() {
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
