package com.example.apipostgress.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apipostgress.models.auth.AuthResponse;
import com.example.apipostgress.models.auth.LoginRequest;
import com.example.apipostgress.models.auth.RegisterRequest;
import com.example.apipostgress.models.users.UserModel;
import com.example.apipostgress.repositories.UserRepository;


@Service
public class AuthServices {
  @Autowired
  private UserRepository userRepository;
  // @Autowired
  // private JwtService jwtService;

  public AuthResponse register(RegisterRequest request) {
    UserModel user = new UserModel();
      user.setEmail(request.getEmail());
      user.setPassword(request.getPassword());
    userRepository.save(user);
    AuthResponse token = new AuthResponse();
    // token.setToken(jwtService.getToken());
    return token;
  }
  public AuthResponse login(LoginRequest request) {
    return null;
  }
}
