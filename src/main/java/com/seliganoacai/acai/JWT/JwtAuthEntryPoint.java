package com.seliganoacai.acai.JWT;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;


import java.io.IOException;
import java.util.logging.Logger;

public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    private static final Logger logger = Logger.getLogger(JwtAuthEntryPoint.class.getName());
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

       logger.severe("Error EntryPoint: "+authException.getMessage());

    }
}
