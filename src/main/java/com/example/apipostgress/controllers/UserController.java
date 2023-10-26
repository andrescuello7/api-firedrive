package com.example.apipostgress.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apipostgress.models.users.AuthModel;
import com.example.apipostgress.models.users.UserModel;
import com.example.apipostgress.services.UserServices;


@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
  @Autowired
  private UserServices userServices;
  
  // Get all users
  @GetMapping(value = "/users")
  public ResponseEntity<Object> get() {
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      List<UserModel> response = userServices.findAll();
      return new ResponseEntity<Object>(response, HttpStatus.OK);
    } catch (Exception e) {
      map.put(null, e.getMessage());
      return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // Get user by ID
  @GetMapping(value = "/user/{id}")
  public ResponseEntity<Object> findById(@PathVariable Long id) {
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      UserModel response = userServices.findById(id);
      return new ResponseEntity<Object>(response, HttpStatus.OK);
    } catch (Exception e) {
      map.put(null, e.getMessage());
      return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // Post users
  @PostMapping(value = "/users")
  public ResponseEntity<Object> create(@RequestBody UserModel user) {
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      UserModel response = userServices.save(user);
      return new ResponseEntity<Object>(response, HttpStatus.OK);
    } catch (Exception e) {
      map.put(null, e.getMessage());
      return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // Auth method
  @PutMapping(value = "/users")
  public ResponseEntity<Object> updateUser(@RequestBody UserModel user) {
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      UserModel response = userServices.save(user);
      return new ResponseEntity<Object>(response, HttpStatus.OK);
    } catch (Exception e) {
      map.put(null, e.getMessage());
      return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  // Delete user by ID
  @DeleteMapping(value = "/user/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      userServices.deleteById(id);
      return new ResponseEntity<Object>(true, HttpStatus.OK);
    } catch (Exception e) {
      map.put(null, e.getMessage());
      return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
