package com.example.apipostgress.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.apipostgress.models.CommentModel;

@Repository
public interface CommentRepository extends JpaRepository<CommentModel, Long> {
  CommentModel save(CommentModel model);
}
