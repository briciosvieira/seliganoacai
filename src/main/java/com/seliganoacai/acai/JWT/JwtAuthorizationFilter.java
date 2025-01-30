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
    private JwtUserService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String token = request.getHeader(JwtUtils.AUTHORIZATION);

        System.out.println("Token é :"+token);

        if (token == null || !token.contains(JwtUtils.BEARER)) {
            System.out.println("Token é nulo ou não contém Bearer");
            filterChain.doFilter(request, response);
            return;
        }

        String cleanToken = token.trim();
        if (!JwtUtils.isTokenValid(cleanToken)) {
            System.out.println("Token expirado ou inválido");
            filterChain.doFilter(request, response);
            return;
        }

        String username = JwtUtils.getUsernameFromToken(cleanToken);
        if (username == null) {
            System.out.println("Erro: Usuário não encontrado no token.");
            filterChain.doFilter(request, response);
            return;
        }

        toAuthentication(request, username);
        filterChain.doFilter(request, response);
    }

    private void toAuthentication(HttpServletRequest request, String username) {
        UserDetails userDetails = jwtService.loadUserByUsername(username);

        var authenticationToken = UsernamePasswordAuthenticationToken
                .authenticated(userDetails, null, userDetails.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
