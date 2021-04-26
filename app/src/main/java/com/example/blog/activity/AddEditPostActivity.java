package com.example.blog.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.blog.R;
import com.example.blog.adapter.CategoryAdapter;
import com.example.blog.databinding.ActivityAddPostBinding;
import com.example.blog.databinding.DialogCategoryBinding;
import com.example.blog.model.Post;

import java.util.ArrayList;
import java.util.List;

import static com.example.blog.utils.ConstantFile.ADD_POST_PARCELABLE_CODE;
import static com.example.blog.utils.ConstantFile.POST_ID_CODE;
import static com.example.blog.utils.ConstantFile.getAuthor;
import static com.example.blog.utils.ConstantFile.getCategories;

public class AddEditPostActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityAddPostBinding activityAddPostBinding;
    private CategoryAdapter categoryAdapter;
    private List<String> categories;
    private int id;
    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddPostBinding = ActivityAddPostBinding.inflate(getLayoutInflater());
        setContentView(activityAddPostBinding.getRoot());

        parseIntent();

        activityAddPostBinding.edtCategories.setOnClickListener(this);
        activityAddPostBinding.btnSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSave) {
            String title = activityAddPostBinding.edtTitle.getText().toString();
            String description = activityAddPostBinding.edtDescription.getText().toString();
            String categoryName = activityAddPostBinding.edtCategories.getText().toString();

            if (title.trim().isEmpty() || description.trim().isEmpty() || categoryName.trim().isEmpty()) {
                Toast.makeText(this, "Please insert data", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                Intent intent = new Intent();
                if (id != -1) {
                    intent.putExtra(ADD_POST_PARCELABLE_CODE, new Post(title, description, post.getCoverPhotoUrl(), categories, getAuthor()));
                    intent.putExtra(POST_ID_CODE, id);
                }
                else
                    intent.putExtra(ADD_POST_PARCELABLE_CODE, new Post(title, description, "", categories, getAuthor()));

                setResult(RESULT_OK, intent);
                finish();
            }
        }
        else if (v.getId() == R.id.edtCategories) {
            new ViewDialog().showDialog(AddEditPostActivity.this);
        }
    }

    public class ViewDialog implements CategoryAdapter.GetCategoryListener {


        public void showDialog(Activity activity){
            categories = new ArrayList<>();

            Dialog dialog = new Dialog(activity, android.R.style.Theme_Light_NoTitleBar_Fullscreen);

            DialogCategoryBinding binding = DialogCategoryBinding.inflate(getLayoutInflater());
            dialog.setContentView(binding.getRoot());

            categoryAdapter = new CategoryAdapter(activity, getCategories(), this);
            binding.rvCategory.setAdapter(categoryAdapter);

            binding.btnDialogClose.setOnClickListener(v -> {
                dialog.dismiss();
            });

            binding.btnDialogSave.setOnClickListener(v -> {
                activityAddPostBinding.setString(categories.toString());
                dialog.dismiss();
            });

            dialog.show();

        }

        @Override
        public void getCategory(String categoryName, boolean isChecked) {
            if (isChecked)
                categories.add(categoryName);
            else {
                for (String name : getCategories()) {
                    if (name.contains(categoryName))
                        categories.remove(categoryName);
                }
            }
        }
    }

    public void parseIntent() {
        Intent intent = getIntent();
        id = intent.getIntExtra(POST_ID_CODE, -1);
        if (id != -1 && intent.hasExtra(POST_ID_CODE)) {
            categories = new ArrayList<>();
            post = intent.getParcelableExtra(ADD_POST_PARCELABLE_CODE);
            categories.addAll(post.getCategories());
            activityAddPostBinding.edtTitle.setText(post.getTitle());
            activityAddPostBinding.edtDescription.setText(post.getDescription());
            activityAddPostBinding.setString(post.getCategories().toString());
            activityAddPostBinding.imageView.setVisibility(View.GONE);
        }
    }

}