package com.example.apipostgress.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apipostgress.interfaces.IPostServices;
import com.example.apipostgress.models.posts.CommentModel;
import com.example.apipostgress.models.posts.PostModel;
import com.example.apipostgress.models.posts.PostWithCommentsModel;
import com.example.apipostgress.repositories.CommentRepository;
import com.example.apipostgress.repositories.PostRepository;

import jakarta.transaction.Transactional;

@Service
public class PostServices implements IPostServices {
  @Autowired
  private PostRepository postRepository;
  @Autowired
  private CommentRepository commentRepository;

  @Override
  @Transactional
  public List<PostModel> findAll() {
    List<PostModel> postsList = postRepository.findAll();
    return postsList;
  }

  @Override
  @Transactional
  public List<PostWithCommentsModel> findAllPostsWithComments() {
    List<PostModel> posts = postRepository.findAll();
    List<CommentModel> comments = commentRepository.findAll();

    List<PostWithCommentsModel> postsWithComments = new ArrayList<>();

    for (PostModel post : posts) {
      List<CommentModel> postComments = new ArrayList<>();
      for (CommentModel commentModel : comments) {
        if (commentModel.getPost().getId() == post.getId()) {
          postComments.add(commentModel);
        }
      }
      PostWithCommentsModel postWithComments = new PostWithCommentsModel(post, postComments);
      postsWithComments.add(postWithComments);
    }

    return postsWithComments;
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    List<CommentModel> comments = commentRepository.findAll();

    for (CommentModel commentModel : comments) {
      if (commentModel.getPost().getId() == id) {
        commentRepository.deleteById(commentModel.getId());;
      }
    }
    postRepository.deleteById(id);
  }

  @Override
  @Transactional
  public Optional<PostModel> findById(Long id) {
    return postRepository.findById(id);
  }

  @Override
  @Transactional
  public PostModel save(PostModel model) {
    return postRepository.save(model);
  }
}
