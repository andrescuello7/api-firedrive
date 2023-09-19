package com.example.apipostgress.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.apipostgress.models.PostModel;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostModel, Long> {
  List<PostModel> findAll();
  PostModel save(PostModel model);
}
