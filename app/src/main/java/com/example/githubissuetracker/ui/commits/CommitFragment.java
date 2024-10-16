package com.example.githubissuetracker.ui.commits;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.githubissuetracker.databinding.FragmentCommitBinding;
import com.example.githubissuetracker.models.issueListItem.IssueListItem;

import java.util.List;
public class CommitFragment extends Fragment {

    private IssueAdapter issueAdapter;
    private FragmentCommitBinding binding;
    private CommitViewModel commitViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCommitBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        setupViewModel();
        setupClickListeners();
    }

    private void setupRecyclerView() {
        binding.recyclerViewIssues.setLayoutManager(new LinearLayoutManager(getContext()));
        issueAdapter = new IssueAdapter(new IssueAdapterCallback() {
            @Override
            public void loadNextPage() {
                commitViewModel.fetchNextPage();
            }

            @Override
            public void showDetailsView(IssueListItem item) {

            }
        });
        binding.recyclerViewIssues.setAdapter(issueAdapter);
        binding.recyclerViewIssues.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    private void setupViewModel() {
        commitViewModel = new ViewModelProvider(this).get(CommitViewModel.class);

        commitViewModel.getIssuesLiveData().observe(getViewLifecycleOwner(), new Observer<List<IssueListItem>>() {
            @Override
            public void onChanged(List<IssueListItem> issueListItems) {
                issueAdapter.setItems(issueListItems);
            }
        });

        commitViewModel.getErrorMessage().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                handleErrorMessage(s);
            }
        });

        commitViewModel.getIsLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                handleLoadingIndicator(aBoolean);
            }
        });
    }

    private void handleErrorMessage(String errorMessage) {
        if (errorMessage == null) {
            binding.errorView.setVisibility(View.GONE);
            binding.retryButton.setVisibility(View.GONE);
        } else {
            binding.errorView.setText(errorMessage);
            binding.errorView.setVisibility(View.VISIBLE);
            binding.retryButton.setVisibility(View.VISIBLE);
        }
    }

    private void handleLoadingIndicator(Boolean isLoading) {
        binding.loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    private void setupClickListeners() {
        binding.retryButton.setOnClickListener(v -> commitViewModel.onRetryClicked());

        binding.backButton.setOnClickListener(v -> {
            binding.searchView.setVisibility(View.GONE);
            binding.searchField.setQuery("", false);
            commitViewModel.clearSearchQuery();
        });

        binding.searchIcon.setOnClickListener(v -> toggleSearchViewVisibility());

        binding.searchField.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                commitViewModel.searchIssues(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void toggleSearchViewVisibility() {
        if (binding.searchView.getVisibility() == View.VISIBLE) {
            binding.searchView.setVisibility(View.GONE);
        } else {
            binding.searchView.setVisibility(View.VISIBLE);
            binding.searchField.onActionViewExpanded();
            binding.searchView.requestFocus();
        }
    }
}
