package com.example.apipostgress.interfaces;

import com.example.apipostgress.models.users.UserModel;

public interface IFollowerServices {
  public UserModel addFollower(Long userId, Long followerId);
  public UserModel findById(Long id);
}
