package com.example.githubissuetracker.repository;

import com.example.githubissuetracker.models.UserProfile;
import com.example.githubissuetracker.service.NetworkCallback;

public interface UserRepository {
    void fetchUser(String userName, NetworkCallback<UserProfile> networkCallback);
}
