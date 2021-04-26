package com.example.blog.utils;

import com.example.blog.model.Author;

import java.util.ArrayList;
import java.util.List;

public class ConstantFile {
    public static final String BASE_URL = "https://my-json-server.typicode.com/sohel-cse/";
    public static final String BLOG_LIST = "simple-blog-api/db";

    public static final int ADD_POST_CODE = 9999;
    public static final int EDIT_POST_CODE = 9998;
    public static final String POST_ID_CODE = "POST_ID";
    public static final String ADD_POST_PARCELABLE_CODE = "POST_DATA";

    // return author
    public static Author getAuthor() {
        return new Author(1, "John Doe", "https://i.pravatar.cc/250", "Content Writer");
    }

    // return categories
    public static List<String> getCategories() {
        List<String> list = new ArrayList<>();
        list.add("Business");
        list.add("Lifestyle");
        list.add("Entertainment");
        list.add("Productivity");
        return list;
    }

}
