package com.example.blog.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;
import com.example.blog.R;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "post")
public class Post implements Parcelable {
    /*
    *
        {
			"id": 1,
			"title": "Lorem Ipsum is simply dummy text of the printing and typesetting industry",
			"description": "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of de Finibus Bonorum",
			"cover_photo": "https://i.picsum.photos/id/579/200/300.jpg?hmac=9MD8EV4Jl9EqKLkTj5kyNdBUKQWyHk2m4pE4UCBGc8Q",
			"categories": [
				"Business",
				"Lifestyle"
			],
			"author": {
				"id": 1,
				"name": "John Doe",
				"avatar": "https://i.pravatar.cc/250",
				"profession": "Content Writer"
			}
		}
	*/
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String title;
    private String description;
    @SerializedName("cover_photo") private String coverPhotoUrl;
    private List<String> categories;
    private Author author;

    public Post(String title, String description, String coverPhotoUrl, List<String> categories, Author author) {
        this.title = title;
        this.description = description;
        this.coverPhotoUrl = coverPhotoUrl;
        this.categories = categories;
        this.author = author;
    }

    protected Post(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        coverPhotoUrl = in.readString();
        categories = in.createStringArrayList();
        author = in.readParcelable(Author.class.getClassLoader());
    }


    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCoverPhotoUrl(String coverPhotoUrl) {
        this.coverPhotoUrl = coverPhotoUrl;
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

    public String getCoverPhotoUrl() {
        return coverPhotoUrl;
    }

    public List<String> getCategories() {
        return categories;
    }

    public Author getAuthor() {
        return author;
    }

    @BindingAdapter("android:loadCoverImage")
    public static void loadCoverImage(ImageView imageView, String coverPhotoUrl) {
        if(coverPhotoUrl != null) {
            Glide.with(imageView)
                    .load(coverPhotoUrl)
                    .placeholder(R.drawable.default_)
                    .into(imageView);
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }


   @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(coverPhotoUrl);
        dest.writeStringList(categories);
        dest.writeParcelable(author, flags);
    }

}
