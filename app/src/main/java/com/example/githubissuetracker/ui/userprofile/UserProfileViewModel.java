package com.example.githubissuetracker.ui.userprofile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.githubissuetracker.models.UserProfile;
import com.example.githubissuetracker.repository.UserRepository;
import com.example.githubissuetracker.repository.UserRepositoryImpl;
import com.example.githubissuetracker.service.NetworkCallback;


public class UserProfileViewModel extends ViewModel {
    private final MutableLiveData<UserProfile> mUserProfile = new MutableLiveData<>(null);
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>(null);

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<UserProfile> getUserProfile() {
        return mUserProfile;
    }

    private final UserRepository repository;

    public UserProfileViewModel() {
        repository = new UserRepositoryImpl();
        fetchUserProfile();
    }

    public void fetchUserProfile() {
        onLoadingState();

        repository.fetchUser("rabelhmd", new NetworkCallback<UserProfile>() {
            @Override
            public void onSuccess(UserProfile userProfile) {
                onSuccessState();
                mUserProfile.setValue(userProfile);
            }
            @Override
            public void onFailure(Exception e) {
                Log.d("fetchUser", "Failed: " + e.getMessage());
                onFailureState(e.getLocalizedMessage());
            }
        });
    }

    private void onLoadingState() {
        isLoading.setValue(true);
        errorMessage.setValue(null);
    }

    private void onSuccessState() {
        isLoading.setValue(false);
        errorMessage.setValue(null);
    }

    private void onFailureState(String message) {
        isLoading.setValue(false);
        errorMessage.setValue(message);
    }
}