package com.example.apipostgress.services;

import java.util.ArrayList;
import java.util.List;

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

  @Override
  public List<CommentModel> findAll() {
    return commentRepository.findAll();
  }

  @Override
  public CommentModel findById(Long id) {
    return commentRepository.findById(id).orElse(null);
  }

  @Override
  public List<CommentModel> findPostId(Long id) {
    List<CommentModel> comments = commentRepository.findAll();
    System.out.println(comments);
    List<CommentModel> commentsPostId = new ArrayList<CommentModel>();
    for (CommentModel commentModel : comments) {
      if (commentModel.getPostId() == id) {
        commentsPostId.add(commentModel);
      }
    }
    System.out.printf("commentsID" + commentsPostId);
    return commentsPostId;
  }
}
