package com.example.blog.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.blog.model.Author;

import java.util.List;

@Entity(tableName = "post")
public class PostModel {
    
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String title;
    private String description;
    private Author author;
    private List<String> categories;

    public PostModel(String title, String description, List<String> categories, Author author) {
        this.title = title;
        this.description = description;
        this.categories = categories;
        this.author = author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getCategories() {
        return categories;
    }

    public Author getAuthor() {
        return author;
    }


    @Override
    public String toString() {
        return "PostModel{" +
                "title='" + title + '\'' +
                ", author=" + author +
                ", categories=" + categories +
                '}';
    }
}
