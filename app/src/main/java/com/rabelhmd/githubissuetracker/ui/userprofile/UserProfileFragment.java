package com.rabelhmd.githubissuetracker.ui.userprofile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.rabelhmd.githubissuetracker.R;
import com.rabelhmd.githubissuetracker.databinding.FragmentUserProfileBinding;
import com.rabelhmd.githubissuetracker.models.UserProfile;

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
        setupClickListeners();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupViewModel() {
        userProfileViewModel = new ViewModelProvider(this).get(UserProfileViewModel.class);

        userProfileViewModel.getUserProfileLiveData().observe(getViewLifecycleOwner(), new Observer<UserProfile>() {
            @Override
            public void onChanged(UserProfile userProfile) {
                setUserProfile(userProfile);
            }
        });

        userProfileViewModel.getIsLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                handleLoadingIndicator(aBoolean);
            }
        });

        userProfileViewModel.getErrorMessage().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                handleErrorMessage(s);
            }
        });
    }

    private void setUserProfile(@Nullable UserProfile userProfile) {
        if (userProfile == null) return;
        binding.commonView.getRoot().setVisibility(View.GONE);
        binding.contentView.setVisibility(View.VISIBLE);

        String avatarUrl = userProfile.getAvatarUrl();
        if(avatarUrl != null) {
            Glide.with(requireContext())
                    .load(avatarUrl)
                    .circleCrop()
                    .placeholder(R.drawable.avatar_placeholder)
                    .error(R.drawable.avatar_placeholder)
                    .into(binding.profileImage);
        }

        String userName = userProfile.getName();
        binding.username.setText(userName);
        String userHandle = userProfile.getLogin();
        if(userHandle != null) {
            binding.userHandle.setText(String.format("@%s", userHandle));
        } else {
            binding.userHandle.setText("");
        }

        String userBio = userProfile.getBio();
        if(userBio != null) {
            binding.userBio.setText(requireContext().getString(R.string.bio, userBio));
        } else {
            binding.userBio.setText("");
        }

        binding.publicRepos.setText(requireContext().getString(R.string.public_repos, userProfile.getPublicRepos()));
        binding.publicGists.setText(requireContext().getString(R.string.public_gists, userProfile.getPublicGists()));
        binding.followers.setText(requireContext().getString(R.string.followers, userProfile.getFollowers()));
    }

    private void handleErrorMessage(@Nullable String errorMessage) {
        if(errorMessage == null) {
            binding.contentView.setVisibility(View.VISIBLE);
            binding.commonView.loadingView.setVisibility(View.GONE);
//            binding.commonView.getRoot().setVisibility(View.VISIBLE);
        } else {
            binding.contentView.setVisibility(View.GONE);
            binding.commonView.getRoot().setVisibility(View.VISIBLE);
            binding.commonView.errorView.setVisibility(View.VISIBLE);
            binding.commonView.retryButton.setVisibility(View.VISIBLE);
            binding.commonView.loadingView.setVisibility(View.GONE);
            binding.commonView.errorView.setText(getString(R.string.unable_to_fetch, errorMessage));
        }
    }

    private void handleLoadingIndicator(@Nullable Boolean isLoading) {
        if(isLoading == null) return;
        binding.contentView.setVisibility(View.GONE);
        if (isLoading) {
            binding.commonView.getRoot().setVisibility(View.VISIBLE);
            binding.commonView.errorView.setVisibility(View.GONE);
            binding.commonView.retryButton.setVisibility(View.GONE);
            binding.commonView.loadingView.setVisibility(View.VISIBLE);
        } else {
            binding.commonView.getRoot().setVisibility(View.VISIBLE);
            binding.commonView.loadingView.setVisibility(View.GONE);
        }
    }

    private void setupClickListeners() {
        binding.commonView.retryButton.setOnClickListener(v -> userProfileViewModel.onRetryClicked());
    }
}