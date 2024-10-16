package com.example.githubissuetracker.service;


import com.example.githubissuetracker.models.issueListItem.IssueListItem;
import com.example.githubissuetracker.models.SearchResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubApiService {
    @GET("/repos/{user}/{repo}/issues")
    Call<List<IssueListItem>> listIssues(@Path("user") String user, @Path("repo") String repo, @Query("perPage") int perPage, @Query("page") int page);

    @GET("search/issues")
    Call<SearchResult> searchIssues(@Query("q") String query, @Query("per_page") int perPage, @Query("page") int page);
}
