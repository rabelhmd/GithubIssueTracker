package com.rabelhmd.githubissuetracker.ui.issues;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rabelhmd.githubissuetracker.R;
import com.rabelhmd.githubissuetracker.databinding.FragmentCommitBinding;
import com.rabelhmd.githubissuetracker.models.issueListItem.IssueListItem;
import com.rabelhmd.githubissuetracker.ui.markdown.IssueDetailsViewer;

import java.util.List;
public class IssuesFragment extends Fragment {

    private IssueAdapter issueAdapter;
    private FragmentCommitBinding binding;
    private IssuesViewModel commitViewModel;

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
                commitViewModel.onRetryClicked();
                //commitViewModel.fetchNextPage();
            }

            @Override
            public void showDetailsView(IssueListItem item) {
                Intent intent = new Intent(getContext(), IssueDetailsViewer.class);
                intent.putExtra("issue_details_info", item);
                requireContext().startActivity(intent);
            }
        });
        binding.recyclerViewIssues.setAdapter(issueAdapter);
        binding.recyclerViewIssues.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    private void setupViewModel() {
        commitViewModel = new ViewModelProvider(this).get(IssuesViewModel.class);

        commitViewModel.getShowEmptyView().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean s) {
                if(s == null) return;

                if(s) {
                    binding.commonView.getRoot().setVisibility(View.VISIBLE);
                    binding.commonView.errorView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);  // Hide top drawable
                    binding.commonView.errorView.setText(getResources().getString(R.string.no_data_found));
                    binding.commonView.errorView.setVisibility(View.VISIBLE);
                    binding.commonView.retryButton.setVisibility(View.GONE);
                }
                else {
//                    binding.commonView.getRoot().setVisibility(View.VISIBLE);
                    binding.commonView.errorView.setVisibility(View.GONE);
                    binding.commonView.retryButton.setVisibility(View.GONE);
                }
            }
        });

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
            binding.commonView.errorView.setVisibility(View.GONE);
            binding.commonView.retryButton.setVisibility(View.GONE);
        } else {
            binding.commonView.getRoot().setVisibility(View.VISIBLE);
            binding.commonView.errorView.setVisibility(View.VISIBLE);
            binding.commonView.retryButton.setVisibility(View.VISIBLE);
            binding.commonView.errorView.setText(getString(R.string.unable_to_fetch, errorMessage));
        }
    }

    private void handleLoadingIndicator(Boolean isLoading) {
        binding.commonView.loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    private void setupClickListeners() {
        binding.commonView.retryButton.setOnClickListener(v -> commitViewModel.onRetryClicked());

        binding.backButton.setOnClickListener(v -> {
            binding.searchView.setVisibility(View.GONE);
            binding.searchField.setQuery("", false);
            commitViewModel.clearSearchQuery();
        });

        binding.searchIcon.setOnClickListener(v -> toggleSearchViewVisibility());

        binding.searchField.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                toggleKeyboard(false);
                commitViewModel.searchIssues(query.trim());
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
            toggleKeyboard(true);
            binding.searchView.setVisibility(View.VISIBLE);
            binding.searchField.onActionViewExpanded();
            binding.searchView.requestFocus();
        }
    }

    private void toggleKeyboard(Boolean isEnable) {
        InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = requireActivity().getCurrentFocus();
        if (view != null) {
            if(isEnable) {
                imm.showSoftInputFromInputMethod(view.getWindowToken(), 0);
            } else {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
}
