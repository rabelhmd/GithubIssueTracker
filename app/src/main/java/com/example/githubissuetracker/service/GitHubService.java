package com.example.githubissuetracker.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import com.example.githubissuetracker.models.Issue;
import com.example.githubissuetracker.models.SearchResult;


public class GitHubService {

    private static final String BASE_URL = "https://api.github.com";
    private OkHttpClient client;
    private Gson gson;

    public GitHubService() {
        client = new OkHttpClient();
        gson = new Gson();
    }

    // Singleton instance
    private static final GitHubService instance = new GitHubService();

    public static GitHubService getInstance() {
        return instance;
    }

    // Fetch issues from a repository
    public void fetchIssues(String repo, int page, int perPage, final Callback callback) {
        String url = BASE_URL + "/repos/" + repo + "/issues?per_page=" + perPage + "&page=" + page;

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    callback.onFailure(call, new IOException("Unexpected code " + response));
                    return;
                }

                String jsonData = response.body().string();

                // Print JSON for debugging
                System.out.println("Response JSON: " + jsonData);

                try {
                    Issue[] issues = gson.fromJson(jsonData, Issue[].class);
                    callback.onSuccess(issues);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    callback.onFailure(call, new IOException("Failed to parse JSON", e));
                }
            }
        });
    }

    public void searchIssues(String repo, String query, final Callback callback) {
        String encodedQuery = query.replace(" ", "%20");
        String urlString = BASE_URL + "/search/issues?q=" + encodedQuery + "+repo:" + repo;

        Request request = new Request.Builder()
                .url(urlString)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    callback.onFailure(call, new IOException("Unexpected code " + response));
                    return;
                }

                String jsonData = response.body().string();

                try {
                    SearchResult searchResult = gson.fromJson(jsonData, SearchResult.class);
                    callback.onSuccess(searchResult.getItems());
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    callback.onFailure(call, new IOException("Failed to parse JSON", e));
                }
            }
        });
    }

    public interface Callback {
        void onSuccess(Issue[] issues);
        void onFailure(Call call, IOException e);
    }
}


