package com.example.apipostgress.models.users;

import java.io.Serializable;
import java.util.List;

import com.example.apipostgress.models.posts.PostModel;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserModel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "username")
  private String username;

  @Column(name = "photo")
  private String photo;

  @Column(name = "cover")
  private String cover;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<PostModel> posts;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
          name = "user_followers",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "follower_id")
  )
  private List<UserModel> followers;
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }

  public String getPhoto() {
    return photo;
  }
  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public String getCover() {
    return cover;
  }
  public void setCover(String cover) {
    this.cover = cover;
  }

  public List<PostModel> getPosts() {
    return posts;
  }
  public void setPosts(List<PostModel> posts) {
    this.posts = posts;
  }

  public List<UserModel> getFollowers() {
    return followers;
  }
  public void setFollowers(List<UserModel> followers) {
    this.followers = followers;
  }
}
