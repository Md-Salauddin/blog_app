package com.example.blog.repo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.blog.db.BlogDB;
import com.example.blog.db.PostDao;
import com.example.blog.model.Post;

import java.util.List;

public class PostRepo {

    private BlogDB blogDB;
    private PostDao postDao;
    private LiveData<List<Post>> listLiveData;

    public PostRepo(Application application) {
        blogDB = BlogDB.getDatabase(application);
        this.postDao = blogDB.postDao();
        this.listLiveData = postDao.getPosts();
    }

    public void insertPost(Post post) {
        blogDB.databaseWriteExecutor.execute(() -> {
            postDao.insertPost(post);
        });
    }

    public void updatePost(Post post) {
        blogDB.databaseWriteExecutor.execute(() -> {
            postDao.updatePost(post);
        });
    }

    public LiveData<List<Post>> getListLiveData() {
        return listLiveData;
    }
}
