package com.example.apipostgress.interfaces;

import java.util.List;

import com.example.apipostgress.models.posts.CommentModel;

public interface ICommentServices {
  public List<CommentModel> findAll();
  public CommentModel findById(Long id);
  public CommentModel save(CommentModel model);
}
