package com.example.apipostgress.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apipostgress.interfaces.IFollowerServices;
import com.example.apipostgress.models.users.UserModel;
import com.example.apipostgress.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class FollowerServices implements IFollowerServices {
  @Autowired
  private UserRepository userRepository;

  @Override
  @Transactional
  public UserModel findById(Long id) {
    return userRepository.findById(id).orElse(null);
  }
  
  @Override
  @Transactional
  public UserModel addFollower(Long userId, Long followerId) {
    UserModel user = userRepository.findById(userId).orElse(null);
    UserModel model = userRepository.findById(followerId).orElse(null);
    if (model != null) {
      user.getFollowers().add(model);
    }
    return user;
  }
}
