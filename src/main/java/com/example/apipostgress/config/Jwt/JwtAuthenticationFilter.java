package com.example.apipostgress.config.Jwt;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.apipostgress.utils.TokenUtils;
import com.example.apipostgress.utils.auth.AuthCredentials;
import com.example.apipostgress.utils.auth.UserDetailsImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        AuthCredentials credentials = new AuthCredentials();

        try {
            credentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(), credentials.getPassword(), Collections.emptyList());

        return getAuthenticationManager().authenticate(usernamePAT);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain, Authentication authResult) throws IOException, ServletException {

        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authResult.getPrincipal();
        String token = TokenUtils.createAccessToken(userDetailsImpl.getUsername(), userDetailsImpl.getEmail());
        
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken("Bearer " + token);
        authResponse.setMessage("Authentication successful");

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(authResponse);
        
        response.addHeader("Authorization", "Bearer " + token);
        response.getWriter().flush();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(body);
        response.getWriter().flush();
        super.successfulAuthentication(request, response, filterChain, authResult);
    }
}
