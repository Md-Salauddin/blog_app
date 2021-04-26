package com.example.blog.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Blogs {

    @SerializedName("blogs")
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

}
