package com.example.blog.api;

import com.example.blog.model.Blogs;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.example.blog.utils.ConstantFile.BLOG_LIST;


public interface APIInterface {

    @GET(BLOG_LIST)
    Call<Blogs> getPosts();

}
