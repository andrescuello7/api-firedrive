package com.example.apipostgress.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apipostgress.interfaces.ICommentServices;
import com.example.apipostgress.models.posts.CommentModel;
import com.example.apipostgress.repositories.CommentRepository;

import jakarta.transaction.Transactional;

@Service
public class CommentServices implements ICommentServices {
  @Autowired
  private CommentRepository commentRepository;

  @Override
  @Transactional
  public CommentModel save(CommentModel model) {
    return commentRepository.save(model);
  }

  @Override
  public List<CommentModel> findAll() {
    return commentRepository.findAll();
  }

  @Override
  public CommentModel findById(Long id) {
    return commentRepository.findById(id).orElse(null);
  }
}
