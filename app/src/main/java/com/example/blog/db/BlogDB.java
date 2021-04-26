package com.example.blog.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.blog.api.APIClient;
import com.example.blog.model.Author;
import com.example.blog.model.Blogs;
import com.example.blog.model.Converter;
import com.example.blog.model.Post;
import com.example.blog.repo.PostRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Database(entities = {Post.class}, version = 1, exportSchema = false)
@TypeConverters(Converter.class)
public abstract class BlogDB extends RoomDatabase {

    public abstract PostDao postDao();

    private static volatile BlogDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static BlogDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BlogDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BlogDB.class, "blog_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private PostDao postDao;
        private BlogDB db;

        private PopulateDbAsyncTask(BlogDB db) {
            postDao = db.postDao();
            this.db = db;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Call<Blogs> call = APIClient
                    .getInstance()
                    .getApi()
                    .getPosts();

            call.enqueue(new retrofit2.Callback<Blogs>() {
                @Override
                public void onResponse(Call<Blogs> call, Response<Blogs> response) {
                    if (response.isSuccessful() && response != null) {
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                for (Post post : response.body().getPosts()) {
                                    postDao.insertPost(new Post(post.getTitle(),
                                            post.getDescription(),
                                            post.getCoverPhotoUrl(),
                                            post.getCategories(),
                                            post.getAuthor()));
                                }
                            }
                        });
                    }

                }

                @Override
                public void onFailure(Call<Blogs> call, Throwable t) {
                }
            });
            return null;
        }
    }

}
