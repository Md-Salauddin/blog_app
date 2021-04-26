package com.example.blog.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blog.activity.AddEditPostActivity;
import com.example.blog.databinding.DialogPostViewBinding;
import com.example.blog.databinding.ItemPostBinding;
import com.example.blog.model.Post;

import java.util.List;

import static com.example.blog.utils.ConstantFile.ADD_POST_PARCELABLE_CODE;
import static com.example.blog.utils.ConstantFile.EDIT_POST_CODE;
import static com.example.blog.utils.ConstantFile.POST_ID_CODE;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{

    private Activity activity;
    private List<Post> posts;

    public PostAdapter(Activity activity, List<Post> posts) {
        this.activity = activity;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPostBinding itemPostBinding = ItemPostBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(itemPostBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.itemPostBinding.setPost(post);
        holder.itemPostBinding.executePendingBindings();

        holder.itemPostBinding.imgPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ViewDialog(post).showDialog(activity);
            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemPostBinding itemPostBinding;
        public ViewHolder(ItemPostBinding itemPostBinding) {
            super(itemPostBinding.getRoot());
            this.itemPostBinding = itemPostBinding;
        }
    }


    public class ViewDialog  {

        private Post post;

        public ViewDialog(Post post) {
            this.post = post;
        }

        public void showDialog(Activity activity){

            Dialog dialog = new Dialog(activity, android.R.style.Theme_Light_NoTitleBar_Fullscreen);

            DialogPostViewBinding binding = DialogPostViewBinding.inflate(activity.getLayoutInflater());
            dialog.setContentView(binding.getRoot());
            binding.setPost(post);

            binding.imgBack.setOnClickListener(v -> {
                dialog.dismiss();
            });

            binding.fabEdit.setOnClickListener(v -> {
                activity.startActivityForResult(new Intent(activity, AddEditPostActivity.class)
                        .putExtra(POST_ID_CODE, post.getId())
                        .putExtra(ADD_POST_PARCELABLE_CODE, post), EDIT_POST_CODE);
                dialog.dismiss();
            });

            dialog.show();

        }

    }

}
