package com.example.apipostgress.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.apipostgress.models.UserModel;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
  List<UserModel> findAll();
  void deleteById(Long id);
  UserModel findByEmail(String email);
}
