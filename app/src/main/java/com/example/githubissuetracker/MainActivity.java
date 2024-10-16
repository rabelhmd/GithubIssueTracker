package com.example.githubissuetracker;

import android.os.Bundle;
import android.util.Log;

import com.example.githubissuetracker.models.Issue;
import com.example.githubissuetracker.models.issueListItem.IssueListItem;
import com.example.githubissuetracker.repository.IssueListRepository;
import com.example.githubissuetracker.repository.IssueListRepositoryImpl;
import com.example.githubissuetracker.service.NetworkCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.githubissuetracker.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private IssueListRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_commit, R.id.navigation_user_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        repository = new IssueListRepositoryImpl();
        //fetchNextPage();
        searchIssues("App");
    }

    public void fetchNextPage() {
        int issuesCount = 1;
        int pageSize = 30;
        int page = issuesCount / pageSize + 1;
        repository.fetchIssues("flutter", "flutter", page, pageSize, new NetworkCallback<List<IssueListItem>>() {
            @Override
            public void onSuccess(List<IssueListItem> data) {
                Log.d("fetchNextPage: Success", "" + data.size());
                for(IssueListItem issueListItem: data) {
                    Log.d("Item ID: " + issueListItem.getId(), "title: " + issueListItem.getTitle());
                }
            }
            @Override
            public void onFailure(Exception e) {
                Log.d("fetchNextPage: ERROR", "" + e.getMessage());
            }
        });
    }

    public void searchIssues(String q) {

        String encodedQuery = q.replace(" ", "%20");
        final String query = encodedQuery + "+repo:flutter/flutter";
        int pageSize = 30;
        int page = 1;

        repository.searchIssues(query, pageSize, page, new NetworkCallback<List<IssueListItem>>() {
            @Override
            public void onSuccess(List<IssueListItem> data) {
                Log.d("searchIssues: Q: ", "" + data.size());
                for(IssueListItem issueListItem: data) {
                    Log.d("Item ID: " + issueListItem.getId(), "title: " + issueListItem.getTitle());
                }
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("searchIssues: ERROR", "" + e.getMessage());
            }
        });
    }

}