package com.example.apipostgress.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apipostgress.models.CommentModel;
import com.example.apipostgress.models.PostModel;
import com.example.apipostgress.services.CommentServices;
import com.example.apipostgress.services.PostServices;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CommentController {
  @Autowired
  private CommentServices commentServices;
  @Autowired
  private PostServices postServices;

  @GetMapping(value = "/comments")
  public ResponseEntity<Object> findAll() {
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      List<CommentModel> comments = commentServices.findAll();
      return new ResponseEntity<>(comments, HttpStatus.OK);
    } catch (Exception e) {
      map.put(null, e.getMessage());
      return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping(value = "/comment/{id}")
  public ResponseEntity<Object> findById(@PathVariable Long id) {
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      CommentModel response = commentServices.findById(id);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      map.put(null, e.getMessage());
      return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping(value = "/comment/post/{id}")
  public ResponseEntity<Object> findPostById(@PathVariable Long id) {
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      List<CommentModel> findPostId = commentServices.findPostId(id);
      return new ResponseEntity<>(findPostId, HttpStatus.OK);
    } catch (Exception e) {
      map.put(null, e.getMessage());
      return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping(value = "/comment")
  public ResponseEntity<Object> create(@RequestBody CommentModel comment) {
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      Optional<PostModel> postOptional = postServices.findById(comment.getPostId());
      if (postOptional.isPresent()) {
        CommentModel saveComment = commentServices.save(comment);
        return new ResponseEntity<>(saveComment, HttpStatus.OK);
      } else {
        return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      map.put(null, e.getMessage());
      return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
