package com.example.githubissuetracker;

import android.os.Bundle;

import com.example.githubissuetracker.models.Issue;
import com.example.githubissuetracker.service.GitHubService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.githubissuetracker.databinding.ActivityMainBinding;

import java.io.IOException;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

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

        fetchIssue();
        fetchQuery("App");
    }

    private  void fetchQuery(String query) {
        System.out.println("Issue Query: " + query);

        GitHubService service = new GitHubService();
        service.searchIssues("flutter/flutter", query, new GitHubService.Callback() {
            @Override
            public void onSuccess(Issue[] issues) {
                System.out.println("Query Total: " + issues.length);
                for (Issue issue : issues) {
                    System.out.println("Query Title: " + issue.getTitle());
                    //System.out.println("Created by: " + issue.getUser().getLogin());
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();  // Handle the error
            }
        });

    }

    private void fetchIssue() {

        GitHubService service = GitHubService.getInstance();
        service.fetchIssues("flutter/flutter", 1, 30, new GitHubService.Callback() {
            @Override
            public void onSuccess(Issue[] issues) {
                // Handle the array of issues (e.g., update UI)
                System.out.println("Total Issues: " + issues.length);
                for (Issue issue : issues) {
                    System.out.println("Issue Title: " + issue.getTitle());
//                    System.out.println("Author: " + issue.getUser().getLogin());
//                    System.out.println("Avatar URL: " + issue.getUser().getAvatarUrl());
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();  // Handle the error
            }
        });
    }
}