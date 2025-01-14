package com.seliganoacai.acai.JWT;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class JwtToken {

    private String Token;


    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
