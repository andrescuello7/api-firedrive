package com.example.apipostgress.interfaces;

import java.util.List;

import com.example.apipostgress.models.PostModel;

public interface IPostServices {
  public List<PostModel> findAll();
  public void deleteById(Long id);
  public PostModel save(PostModel model);
}
