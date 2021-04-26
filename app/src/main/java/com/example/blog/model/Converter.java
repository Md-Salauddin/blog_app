package com.example.blog.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converter {

    @TypeConverter // note this annotation
    public String fromList(List<String> categoryName) {
        if (categoryName == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        String json = gson.toJson(categoryName, type);
        return json;
    }

    @TypeConverter // note this annotation
    public List<String> toList(String optionValuesString) {
        if (optionValuesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        List<String> categoryName = gson.fromJson(optionValuesString, type);
        return categoryName;
    }

    @TypeConverter // note this annotation
    public String fromAuthor(Author author) {
        if (author == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Author>() {
        }.getType();
        String json = gson.toJson(author, type);
        return json;
    }

    @TypeConverter // note this annotation
    public Author toAuthor(String authorString) {
        if (authorString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Author>() {
        }.getType();
        Author author = gson.fromJson(authorString, type);
        return author;
    }

}
