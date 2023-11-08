package com.example.apipostgress.interfaces;

import java.util.List;
import java.util.Optional;

import com.example.apipostgress.models.posts.PostModel;

public interface IPostServices {
  public List<PostModel> findAll();
  public void deleteById(Long id);
  public Optional<PostModel> findById(Long id);
  public PostModel save(PostModel model);
  public PostModel update(PostModel model);
}
