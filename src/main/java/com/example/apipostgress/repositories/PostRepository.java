package com.example.apipostgress.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.apipostgress.models.posts.PostModel;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostModel, Long> { 
  List<PostModel> findAll();
  void deleteById(Long id);
}
