package com.example.blog.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.blog.model.Post;

import java.util.List;

@Dao
public interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPost(Post post);

    @Update
    void updatePost(Post post);

    @Query("SELECT * FROM post")
    LiveData<List<Post>> getPosts();
}
