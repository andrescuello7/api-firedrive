package com.example.apipostgress.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apipostgress.interfaces.ICommentServices;
import com.example.apipostgress.models.CommentModel;
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
}
