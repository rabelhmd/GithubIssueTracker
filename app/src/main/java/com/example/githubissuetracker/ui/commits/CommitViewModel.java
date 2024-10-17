package com.example.githubissuetracker.ui.commits;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.githubissuetracker.models.issueListItem.IssueListItem;
import com.example.githubissuetracker.repository.IssueListRepository;
import com.example.githubissuetracker.repository.IssueListRepositoryImpl;
import com.example.githubissuetracker.service.NetworkCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CommitViewModel extends ViewModel {

    private final MutableLiveData<List<IssueListItem>> issuesLiveData = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>(null);
    private String searchQuery;

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<List<IssueListItem>> getIssuesLiveData() {
        return issuesLiveData;
    }

    private final IssueListRepository repository;

    public CommitViewModel() {
        repository = new IssueListRepositoryImpl();
        fetchNextPage();
    }

    public void fetchNextPage() {
        if (Boolean.TRUE.equals(isLoading.getValue())) {
            return;
        }
        isLoading.setValue(true);
        errorMessage.setValue(null);
        int issuesCount = issuesLiveData.getValue() == null ? 1 : issuesLiveData.getValue().size();
        int pageSize = 30;
        int page = issuesCount / pageSize + 1;
        repository.fetchIssues("flutter", "flutter", page, pageSize, new NetworkCallback<List<IssueListItem>>() {
            @Override
            public void onSuccess(List<IssueListItem> data) {
                isLoading.setValue(false);
                errorMessage.setValue(null);
                updateIssueListLiveData(page, data);
            }

            @Override
            public void onFailure(Exception e) {
                isLoading.setValue(false);
                errorMessage.setValue("Unable to fetch issues");
            }
        });
    }

    public void onRetryClicked() {
        if (searchQuery == null || searchQuery.isEmpty()) {
            fetchNextPage();
        } else {
            searchIssues(searchQuery);
        }
    }

    public void searchIssues(String q) {

        String encodedQuery = q.replace(" ", "%20");
        final String query = encodedQuery + "+repo:flutter/flutter";
        int issuesCount = issuesLiveData.getValue() == null ? 1 : issuesLiveData.getValue().size();
        int pageSize = 30;
        int page = issuesCount / pageSize + 1;
        errorMessage.postValue(null);
        issuesLiveData.postValue(new ArrayList<>());
        if (searchQuery == null || !searchQuery.equals(q)) {
            searchQuery = q;
            page = 1;
        }
        isLoading.postValue(true);
        int finalPage = page;
        repository.searchIssues(query, pageSize, page, new NetworkCallback<List<IssueListItem>>() {
            @Override
            public void onSuccess(List<IssueListItem> data) {
                isLoading.postValue(false);
                errorMessage.postValue(null);
                updateIssueListLiveData(finalPage, data);
            }

            @Override
            public void onFailure(Exception e) {
                isLoading.postValue(false);
                errorMessage.postValue("Unable to fetch issues");
            }
        });
    }

    public void clearSearchQuery() {
        this.searchQuery = null;
        issuesLiveData.postValue(new ArrayList<>());
        fetchNextPage();
    }

    private void updateIssueListLiveData(int forPageNo, List<IssueListItem> data) {
        List<IssueListItem> filteredList = getFilteredList(data);
        if (forPageNo == 1) {
            issuesLiveData.setValue(filteredList);
        } else {
            List<IssueListItem> currentIssues = issuesLiveData.getValue();
            currentIssues.addAll(filteredList);
            issuesLiveData.setValue(currentIssues);
        }
    }

    private List<IssueListItem> getFilteredList(List<IssueListItem> listItems) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return listItems.stream().filter(issue -> !issue.getTitle().toLowerCase().contains("flutter")).collect(Collectors.toList());
        } else {
            List<IssueListItem> filteredList = new ArrayList<>();
            for (IssueListItem issue : listItems) {
                if (!issue.getTitle().toLowerCase().contains("flutter")) {
                    filteredList.add(issue);
                }
            }
            return filteredList;
        }
    }
}
