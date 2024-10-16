package com.example.githubissuetracker.ui.userprofile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.githubissuetracker.R;
import com.example.githubissuetracker.databinding.FragmentUserProfileBinding;
import com.example.githubissuetracker.models.UserProfile;

public class UserProfileFragment extends Fragment {

    private FragmentUserProfileBinding binding;
    private UserProfileViewModel userProfileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewModel();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupViewModel() {
        userProfileViewModel = new ViewModelProvider(this).get(UserProfileViewModel.class);

        userProfileViewModel.getUserProfile().observe(getViewLifecycleOwner(), new Observer<UserProfile>() {
            @Override
            public void onChanged(UserProfile userProfile) {
                if (userProfile == null) return;
                setUserProfile(userProfile);
            }
        });

        userProfileViewModel.getErrorMessage().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                handleErrorMessage(s);
            }
        });

        userProfileViewModel.getIsLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                handleLoadingIndicator(aBoolean);
            }
        });
    }

    private void setUserProfile(UserProfile userProfile) {
        String avatarUrl = userProfile.getAvatarUrl();
        if(avatarUrl != null) {
            Glide.with(requireContext()) // Use 'requireContext()' in fragments
                    .load(avatarUrl) // Load the avatar URL
                    .placeholder(R.drawable.avatar_placeholder) // Optional: Add a placeholder while the image loads
                    .error(R.drawable.avatar_placeholder) // Optional: Add an error image if the loading fails
                    .into(binding.profileImage); // Set the image into the ImageView
        }

        String userName = userProfile.getName();
        if (userName != null) {
            binding.username.setText(userName);
        }
        String userHandle = userProfile.getLogin();
        if(userHandle != null) {
            binding.userHandle.setText(userHandle);
        }
        String userBio = userProfile.getBio();
        if(userBio != null) {
            binding.userBio.setText(userBio);
        }
        String publicRepos = "Public Repos: " + userProfile.getPublicRepos();
        String publicGists = "Public Gists: " + userProfile.getPublicGists();
        String followers = "@Followers: " + userProfile.getFollowers();
        binding.publicRepos.setText(publicRepos);
        binding.publicGists.setText(publicGists);
        binding.privateRepos.setText(followers);
    }

    private void handleErrorMessage(String errorMessage) {
//        if (errorMessage == null) {
//            binding.errorView.setVisibility(View.GONE);
//            binding.retryButton.setVisibility(View.GONE);
//        } else {
//            binding.errorView.setText(errorMessage);
//            binding.errorView.setVisibility(View.VISIBLE);
//            binding.retryButton.setVisibility(View.VISIBLE);
//        }
        Log.d("TEST: ", "handleErrorMessage " + errorMessage);
    }

    private void handleLoadingIndicator(Boolean isLoading) {
//        binding.loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        Log.d("TEST:", "handleLoadingIndicator: " + isLoading);
    }
}