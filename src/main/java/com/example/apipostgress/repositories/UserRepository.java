package com.example.apipostgress.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.apipostgress.models.users.UserModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
  Optional<UserModel> findOneByUsername(String username);
  List<UserModel> findAll();
  void deleteById(Long id);
  UserModel findByEmail(String email);
}
