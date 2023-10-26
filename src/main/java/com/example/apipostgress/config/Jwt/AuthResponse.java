package com.example.apipostgress.config.Jwt;

public class AuthResponse {
    private String token;
    private String message;
    
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
