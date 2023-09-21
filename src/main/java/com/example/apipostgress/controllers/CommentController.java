package com.example.apipostgress.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
  private PostServices postServices;

  @PostMapping(value = "/comment/{id}")
  public ResponseEntity<Object> create(@PathVariable Long id, @RequestBody CommentModel comment) {
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      CommentModel saveComment = commentServices.save(comment);
      PostModel post = postServices.findById(id);
      List<CommentModel> listComments = Collections.singletonList(saveComment);
      post.setComments(listComments);
      return new ResponseEntity<Object>(post, HttpStatus.OK);
    } catch (Exception e) {
      map.put(null, e.getMessage());
      return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
