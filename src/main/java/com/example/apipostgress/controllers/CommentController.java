package com.example.apipostgress.controllers;

import java.util.HashMap;
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
      Optional<PostModel> postOptional = postServices.findById(id);
      if (postOptional.isPresent()) {
        CommentModel saveComment = commentServices.save(comment);
        //TODO i18n: add comment
        // PostModel post = postOptional.get();
        // List<CommentModel> listComments = Collections.singletonList(saveComment);
        // post.setComments(listComments);
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
