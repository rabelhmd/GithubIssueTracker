package com.rabelhmd.githubissuetracker.repository;

import androidx.annotation.NonNull;

import com.rabelhmd.githubissuetracker.models.SearchResult;
import com.rabelhmd.githubissuetracker.models.issueListItem.IssueListItem;
import com.rabelhmd.githubissuetracker.service.ApiClient;
import com.rabelhmd.githubissuetracker.service.GitHubApiService;
import com.rabelhmd.githubissuetracker.service.NetworkCallback;

import java.net.SocketException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IssueListRepositoryImpl implements IssueListRepository {
    GitHubApiService apiService;
    public IssueListRepositoryImpl() {
        apiService = new ApiClient().getGitHubService();
    }
    @Override
    public void fetchIssues(String user, String repo, int perPage, int page, NetworkCallback<List<IssueListItem>> networkCallback) {
        apiService.listIssues(user,repo,perPage,page).enqueue(new Callback<List<IssueListItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<IssueListItem>> call, @NonNull Response<List<IssueListItem>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    networkCallback.onSuccess(response.body());
                }else {
                    networkCallback.onFailure(new Exception("Failed to fetch issues"));
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<IssueListItem>> call, @NonNull Throwable t) {
                networkCallback.onFailure(new Exception("Failed to fetch issues"));
            }
        });
    }

    @Override
    public void searchIssues(String query, int perPage, int page, NetworkCallback<List<IssueListItem>> networkCallback) {
        apiService.searchIssues(query, perPage, page).enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                if(response.isSuccessful() && response.body() != null) {
                    networkCallback.onSuccess(response.body().getItems());
                }else {
                    networkCallback.onFailure(new Exception("Failed to fetch issues"));
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                networkCallback.onFailure(new Exception("Failed to fetch issues"));
            }
        });
    }
}

