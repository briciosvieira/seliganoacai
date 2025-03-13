package com.seliganoacai.acai.JWT;

import com.seliganoacai.acai.exception.UserNotFoundException;
import com.seliganoacai.acai.webConfig.configException.CustomExceptionHandler;
import com.seliganoacai.acai.webConfig.dto.userLogin.UserLoginDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acai/v1")

public class JwtAuthenticatorController {

    @Autowired
    private JwtUserService service;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<Object> autenticationUser(@RequestBody @Valid UserLoginDto dto, HttpServletRequest request){
         Logger logger = LoggerFactory.getLogger(JwtAuthenticatorController.class);
        try{

            var autenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
            authenticationManager.authenticate(autenticationToken);

            JwtToken token = service.getTokenAutenticated(dto.getUsername());

            return ResponseEntity.ok(token);

        } catch (InternalAuthenticationServiceException e) {
            throw new UserNotFoundException("Login ou senha incorreta!");

        }
    }
}
