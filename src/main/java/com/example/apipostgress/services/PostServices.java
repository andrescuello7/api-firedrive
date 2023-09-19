package com.example.apipostgress.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apipostgress.interfaces.IPostServices;
import com.example.apipostgress.models.PostModel;
import com.example.apipostgress.repositories.PostRepository;

import jakarta.transaction.Transactional;

@Service
public class PostServices implements IPostServices {
  @Autowired
  private PostRepository postRepository;

  @Override
  @Transactional
  public List<PostModel> findAll() {
    return postRepository.findAll();
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    postRepository.deleteById(id);
  }

  @Override
  @Transactional
  public PostModel save(PostModel model) {
    return postRepository.save(model);
  }
}
