package com.example.apipostgress.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apipostgress.models.users.UserModel;
import com.example.apipostgress.services.FollowerServices;
import com.example.apipostgress.services.UserServices;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api")
public class FollowingController {
  @Autowired
  private FollowerServices followerServices;
  @Autowired
  private UserServices userServices;

  // Get all users
  @GetMapping(value = "/user/follower/{id}")
  public ResponseEntity<Object> get(@PathVariable Long id) {
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      UserModel response = followerServices.findById(id);
      return new ResponseEntity<Object>(response, HttpStatus.OK);
    } catch (Exception e) {
      map.put(null, e.getMessage());
      return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // Post users
  @PostMapping(value = "/user/follower/{id}")
  public ResponseEntity<Object> create(@PathVariable Long id) {
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String email = authentication.getPrincipal().toString();
      UserModel userModel = userServices.findByEmail(email);
      UserModel response = followerServices.addFollower(userModel.getId(), id);
      return new ResponseEntity<Object>(response, HttpStatus.OK);
    } catch (Exception e) {
      map.put(null, e.getMessage());
      return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
