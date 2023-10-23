package com.example.apipostgress.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apipostgress.models.auth.AuthResponse;
import com.example.apipostgress.models.auth.LoginRequest;
import com.example.apipostgress.models.auth.RegisterRequest;
import com.example.apipostgress.services.AuthServices;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  private AuthServices authServices;
  
  // Post users
  @PostMapping(value = "/register")
  public ResponseEntity<AuthResponse> create(@RequestBody RegisterRequest model) {
    return  ResponseEntity.ok(authServices.register(model));
  }

  // Auth method
  @PostMapping(value = "/login")
  public ResponseEntity<AuthResponse> isAuthenticated(@RequestBody LoginRequest model) {
    return  ResponseEntity.ok(authServices.login(model));
  }
}
