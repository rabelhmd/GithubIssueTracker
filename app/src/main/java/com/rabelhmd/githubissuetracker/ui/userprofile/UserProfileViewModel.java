package com.rabelhmd.githubissuetracker.ui.userprofile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rabelhmd.githubissuetracker.models.UserProfile;
import com.rabelhmd.githubissuetracker.repository.UserRepository;
import com.rabelhmd.githubissuetracker.repository.UserRepositoryImpl;
import com.rabelhmd.githubissuetracker.service.NetworkCallback;


public class UserProfileViewModel extends ViewModel {
    private final MutableLiveData<UserProfile> userProfileLiveData = new MutableLiveData<>(null);
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(null);
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>(null);

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<UserProfile> getUserProfileLiveData() {
        return userProfileLiveData;
    }

    private final UserRepository repository;

    public UserProfileViewModel() {
        repository = new UserRepositoryImpl();
        fetchUserProfile();
    }

    public void fetchUserProfile() {
       onLoadingState();
        repository.fetchUserProfile("rabelhmd", new NetworkCallback<UserProfile>() {
            @Override
            public void onSuccess(UserProfile userProfile) {
                userProfileLiveData.setValue(userProfile);
            }
            @Override
            public void onFailure(Exception e) {
                if(e instanceof IllegalStateException) {
                    onFailureState("No Internet!");
                } else {
                    onFailureState(e.getLocalizedMessage());
                }
            }
        });
    }

    public void onRetryClicked() {
        fetchUserProfile();
    }

    private void onLoadingState() {
        isLoading.setValue(true);
    }

    private void onFailureState(String message) {
        isLoading.setValue(false);
        errorMessage.setValue(message);
    }
}