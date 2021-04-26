package com.example.blog.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.blog.R;
import com.example.blog.adapter.PostAdapter;
import com.example.blog.databinding.ActivityMainBinding;
import com.example.blog.model.Post;
import com.example.blog.viewmodel.PostListViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.blog.utils.ConstantFile.ADD_POST_CODE;
import static com.example.blog.utils.ConstantFile.ADD_POST_PARCELABLE_CODE;
import static com.example.blog.utils.ConstantFile.EDIT_POST_CODE;
import static com.example.blog.utils.ConstantFile.POST_ID_CODE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding activityMainBinding;
    private PostListViewModel postListViewModel;
    private PostAdapter postAdapter;
    private List<Post> postList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        postAdapter = new PostAdapter(this, postList);
        activityMainBinding.rvPost.setAdapter(postAdapter);
        
        postListViewModel = ViewModelProviders.of(this).get(PostListViewModel.class);
        postListViewModel.getListLiveData().observe(this, posts -> {
            if (posts != null) {
                postList.clear();
                postList.addAll(posts);
                activityMainBinding.rvPost.setAdapter(postAdapter);
            }
        });

        activityMainBinding.fab.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab)
            startActivityForResult(new Intent(this, AddEditPostActivity.class), ADD_POST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_POST_CODE && resultCode == RESULT_OK) {
            Post post = data.getParcelableExtra(ADD_POST_PARCELABLE_CODE);
            postListViewModel.insert(post);
            Toast.makeText(this, "Post saved", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == EDIT_POST_CODE && resultCode == RESULT_OK) {
            int id  = data.getIntExtra(POST_ID_CODE, -1);
            if (id == -1)
                return;
            Post post = data.getParcelableExtra(ADD_POST_PARCELABLE_CODE);
            post.setId(id);
            postListViewModel.update(post);
            Toast.makeText(this, "Post updated", Toast.LENGTH_SHORT).show();
        }

    }



}