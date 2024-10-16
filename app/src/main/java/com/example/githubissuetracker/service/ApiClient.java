package com.example.githubissuetracker.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public GitHubApiService getGitHubService() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
//            .baseUrl("http://10.0.2.2:3001/")
                .addConverterFactory(GsonConverterFactory.create())
            .build();
        return retrofit.create(GitHubApiService.class);
    }
}


