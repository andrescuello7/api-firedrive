package com.example.apipostgress.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apipostgress.models.posts.PostModel;
import com.example.apipostgress.services.PostServices;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PostController {
  @Autowired
  private PostServices postServices;

  //Get all posts 
  @GetMapping(value = "/posts")
  public ResponseEntity<Object> get() {
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      List<PostModel> response = postServices.findAll();
      return new ResponseEntity<Object>(response, HttpStatus.OK);
    } catch (Exception e) {
      map.put(null, e.getMessage());
      return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  //Get by ID
  @GetMapping(value = "/post/{id}")
  public ResponseEntity<Object> get(@PathVariable Long id) {
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      Optional<PostModel> response = postServices.findById(id);
      if (response.isPresent()) {
        return new ResponseEntity<Object>(response, HttpStatus.OK);
      } else {
        return new ResponseEntity<Object>("Error in find post by ID", HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      map.put(null, e.getMessage());
      return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  //Post Posts
  @PostMapping(value = "/post")
  public ResponseEntity<Object> create(@RequestBody PostModel post) {
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      PostModel response = postServices.save(post);
      return new ResponseEntity<Object>(response, HttpStatus.OK);
    } catch (Exception e) {
      map.put(null, e.getMessage());
      return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  //Delete Posts
  @DeleteMapping(value = "/post/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      postServices.deleteById(id);
      return new ResponseEntity<Object>(true, HttpStatus.OK);
    } catch (Exception e) {
      map.put(null, e.getMessage());
      return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
