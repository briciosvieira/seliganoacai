package com.seliganoacai.acai.JWT;

import com.seliganoacai.acai.webConfig.dto.userLogin.UserLoginDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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

        try{

            var autenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
            authenticationManager.authenticate(autenticationToken);

            JwtToken token = service.getTokenAutenticated(dto.getUsername());

            return ResponseEntity.ok(token);

        } catch (AuthenticationException e) {

        }
        return ResponseEntity.badRequest().body(new Object());
    }
}
