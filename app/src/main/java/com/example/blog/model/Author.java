package com.example.blog.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "author")
public class Author implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String name;
    @SerializedName("avatar") private String avatarUrl;
    private String profession;

    public Author(int id, String name, String avatarUrl, String profession) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.profession = profession;
    }

    protected Author(Parcel in) {
        id = in.readInt();
        name = in.readString();
        avatarUrl = in.readString();
        profession = in.readString();
    }

    public static final Creator<Author> CREATOR = new Creator<Author>() {
        @Override
        public Author createFromParcel(Parcel in) {
            return new Author(in);
        }

        @Override
        public Author[] newArray(int size) {
            return new Author[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getProfession() {
        return profession;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(avatarUrl);
        dest.writeString(profession);
    }
}
