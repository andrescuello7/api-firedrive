package com.example.apipostgress.services.Dto;

import com.example.apipostgress.models.posts.PostModel;
import com.example.apipostgress.models.users.UserModel;

public class PostResponseDTO {
    private PostModel posts;
    private UserModel user;
    
    public PostModel getPosts() {
        return posts;
    }
    public void setPosts(PostModel posts) {
        this.posts = posts;
    }
    public UserModel getUser() {
        return user;
    }
    public void setUser(UserModel user) {
        this.user = user;
    }
}
