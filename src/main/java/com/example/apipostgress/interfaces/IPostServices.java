package com.example.apipostgress.interfaces;

import java.util.List;
import java.util.Optional;

import com.example.apipostgress.models.posts.PostModel;
import com.example.apipostgress.models.posts.PostWithCommentsModel;

public interface IPostServices {
  public List<PostModel> findAll();
  public List<PostWithCommentsModel> findAllPostsWithComments();
  public void deleteById(Long id);
  public Optional<PostModel> findById(Long id);
  public PostModel save(PostModel model);
}
