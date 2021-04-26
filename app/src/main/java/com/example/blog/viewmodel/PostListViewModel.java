package com.example.blog.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blog.api.APIClient;
import com.example.blog.model.Blogs;
import com.example.blog.model.Post;
import com.example.blog.repo.PostRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostListViewModel extends AndroidViewModel {

    private LiveData<List<Post>> listLiveData;
    private PostRepo postRepo;

    public PostListViewModel(Application application) {
        super(application);
        this.postRepo = new PostRepo(application);
        this.listLiveData = postRepo.getListLiveData();
    }

    public void insert(Post post) {
        postRepo.insertPost(post);
    }

    public void update(Post post) {
        postRepo.updatePost(post);
    }

    public LiveData<List<Post>> getListLiveData() {
        return listLiveData;
    }

}
