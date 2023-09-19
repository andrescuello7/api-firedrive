package com.example.apipostgress.interfaces;

import java.util.List;

import com.example.apipostgress.models.AuthModel;
import com.example.apipostgress.models.UserModel;

public interface IUserServices {
  public List<UserModel> findAll();
  public UserModel findById(Long id);
  public void deleteById(Long id);
  public UserModel save(UserModel model);
  public Boolean isAuthenticated(AuthModel auth);
}
