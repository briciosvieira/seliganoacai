package com.seliganoacai.acai.JWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = request.getHeader(JwtUtils.AUTHORIZATION);
        System.out.println("Token recebido no cabeçalho: " + token);


        if ( token == null || !token.contains(JwtUtils.BEARER)){
            System.out.println("Token é nulo ou não contem bearer");
            return ;
        }

        if ( !JwtUtils.isTokenValid(token)){
            System.out.println("Token expirado");
            filterChain.doFilter(request,response);
            return ;
        }

        String username = JwtUtils.getUsernameFromToken(token);
        toAuthentication(request, username);
        filterChain.doFilter(request, response);

    }

    private void toAuthentication(HttpServletRequest request, String username){
        UserDetails userDetails = service.loadUserByUsername(username);

        var authenticationToken = UsernamePasswordAuthenticationToken.authenticated(userDetails, null, userDetails.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
