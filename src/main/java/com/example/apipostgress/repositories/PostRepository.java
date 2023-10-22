package com.example.apipostgress.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.apipostgress.models.posts.PostModel;
import com.example.apipostgress.models.posts.PostWithCommentsModel;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostModel, Long> { 
  @Query("SELECT p.id AS postId, p.description AS postDescription, p.photo AS postPhoto, c.id AS commentId, c.description AS commentDescription FROM PostModel p LEFT JOIN p.comments c")
  List<PostWithCommentsModel> findAllPostsWithComments();
  List<PostModel> findAll();
  void deleteById(Long id);
}
