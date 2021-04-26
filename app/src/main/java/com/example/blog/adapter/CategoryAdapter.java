package com.example.blog.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blog.databinding.ItemCategoryBinding;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<String> list;
    private Activity activity;
    private GetCategoryListener getCategoryListener;

    public CategoryAdapter(Activity activity, List<String> list, GetCategoryListener getCategoryListener) {
        this.list = list;
        this.activity = activity;
        this.getCategoryListener = getCategoryListener;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemCategoryBinding itemCategoryBinding = ItemCategoryBinding.inflate(layoutInflater, parent, false);
        return new CategoryAdapter.ViewHolder(itemCategoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        String category = list.get(position);
        holder.itemCategoryBinding.setString(category);
        holder.itemCategoryBinding.executePendingBindings();

        holder.itemCategoryBinding.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    getCategoryListener.getCategory(list.get(position), true);
                else
                    getCategoryListener.getCategory(list.get(position), false);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemCategoryBinding itemCategoryBinding;

        public ViewHolder(ItemCategoryBinding itemCategoryBinding) {
            super(itemCategoryBinding.getRoot());
            this.itemCategoryBinding = itemCategoryBinding;
        }

    }

    public interface GetCategoryListener {
        void getCategory(String categoryName, boolean isChecked);
    }

}
