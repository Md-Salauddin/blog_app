package com.example.blog.api;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.blog.utils.ConstantFile.BASE_URL;

public class APIClient {

    private static APIClient apiClient;
    private Retrofit retrofit;

    private APIClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized APIClient getInstance() {
        if (apiClient == null )
            apiClient = new APIClient();
        return apiClient;
    }

    public APIInterface getApi() {
        return retrofit.create(APIInterface.class);
    }


}
