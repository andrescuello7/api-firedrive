package com.example.apipostgress.interfaces;

import java.util.List;

import com.example.apipostgress.models.users.AuthModel;
import com.example.apipostgress.models.users.UserModel;

public interface IUserServices {
  public List<UserModel> findAll();
  public UserModel findById(Long id);
  public UserModel findByEmail(String email);
  public void deleteById(Long id);
  public UserModel save(UserModel model);
  public Boolean isAuthenticated(AuthModel auth);
}
