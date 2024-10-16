package com.example.githubissuetracker.repository;

import com.example.githubissuetracker.models.UserProfile;
import com.example.githubissuetracker.service.ApiClient;
import com.example.githubissuetracker.service.GitHubApiService;
import com.example.githubissuetracker.service.NetworkCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepositoryImpl implements UserRepository {
    GitHubApiService apiService;

    public UserRepositoryImpl() {
        apiService = new ApiClient().getGitHubService();
    }

    @Override
    public void fetchUser(String userName, NetworkCallback<UserProfile> networkCallback) {
        apiService.fetchUser(userName).enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if(response.isSuccessful() && response.body() != null) {
                    networkCallback.onSuccess(response.body());
                }else {
                    networkCallback.onFailure(new Exception("Failed to fetch issues"));
                }
            }
            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                networkCallback.onFailure(new Exception("Failed to fetch issues"));
            }
        });
    }
}
