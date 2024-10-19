package com.rabelhmd.githubissuetracker.repository;

import com.rabelhmd.githubissuetracker.models.UserProfile;
import com.rabelhmd.githubissuetracker.service.NetworkCallback;

public interface UserRepository {
    void fetchUserProfile(String userName, NetworkCallback<UserProfile> networkCallback);
}
