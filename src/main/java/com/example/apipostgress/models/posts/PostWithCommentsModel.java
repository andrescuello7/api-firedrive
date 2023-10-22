package com.example.apipostgress.models.posts;

import java.util.List;

public class PostWithCommentsModel {
    private PostModel post;
    private List<CommentModel> comments;

    public PostWithCommentsModel(PostModel post, List<CommentModel> comments) {
        this.post = post;
        this.comments = comments;
    }

    public PostModel getPost() {
        return post;
    }

    public void setPost(PostModel post) {
        this.post = post;
    }

    public List<CommentModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }
}