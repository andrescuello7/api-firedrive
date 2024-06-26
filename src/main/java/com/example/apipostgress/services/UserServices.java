package com.example.apipostgress.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.apipostgress.interfaces.IUserServices;
import com.example.apipostgress.models.users.UserModel;
import com.example.apipostgress.repositories.UserRepository;
import com.example.apipostgress.utils.auth.AuthModel;

import jakarta.transaction.Transactional;

@Service
public class UserServices implements IUserServices {
  @Autowired
  private UserRepository userRepository;

  @Override
  @Transactional
  public List<UserModel> findAll() {
    return userRepository.findAll();
  }

  @Override
  @Transactional
  public UserModel findById(Long id) {
    return userRepository.findById(id).orElse(null);
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }

  @Override
  @Transactional
  public UserModel findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  @Transactional
  public UserModel save(UserModel model) {
    String encryptedPass = new BCryptPasswordEncoder().encode(model.getPassword());
    model.setPassword(encryptedPass);
    return userRepository.save(model);
  }

  @Override
  @Transactional
  public UserModel update(UserModel model) {
    return userRepository.save(model);
  }

  @Transactional
  public Boolean isAuthenticated(AuthModel auth) {
    UserModel model = userRepository.findByEmail(auth.getEmail());
    if (auth.getEmail() != model.getEmail() && auth.getPassword() != model.getPassword()) {
      return false;
    }
    return true;
  }

  @Override
  @Transactional
  public UserModel addFollower(Long id) {
    UserModel model = userRepository.findById(id).orElse(null);
    return model;
  }
}
