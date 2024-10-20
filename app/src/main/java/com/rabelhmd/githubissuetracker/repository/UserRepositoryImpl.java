package com.rabelhmd.githubissuetracker.repository;

import com.rabelhmd.githubissuetracker.models.UserProfile;
import com.rabelhmd.githubissuetracker.service.ApiClient;
import com.rabelhmd.githubissuetracker.service.GitHubApiService;
import com.rabelhmd.githubissuetracker.service.NetworkCallback;

import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepositoryImpl implements UserRepository {
    GitHubApiService apiService;

    public UserRepositoryImpl() {
        apiService = new ApiClient().getGitHubService();
    }

    @Override
    public void fetchUserProfile(String userName, NetworkCallback<UserProfile> networkCallback) {
        apiService.fetchUser(userName).enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallback.onSuccess(response.body());
                } else {
                    networkCallback.onFailure(new Exception("Failed to fetch UserProfile"));
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                if (t instanceof UnknownHostException) {
                    networkCallback.onFailure(new IllegalStateException());
                } else
                    networkCallback.onFailure(new Exception("Failed to fetch UserProfile"));
            }
        });
    }
}
