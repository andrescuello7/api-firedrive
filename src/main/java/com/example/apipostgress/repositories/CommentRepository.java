package com.example.apipostgress.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.apipostgress.models.CommentModel;

@Repository
public interface CommentRepository extends JpaRepository<CommentModel, Long> {
    public List<CommentModel> findAll();
    public Optional<CommentModel> findById(Long id);
}
