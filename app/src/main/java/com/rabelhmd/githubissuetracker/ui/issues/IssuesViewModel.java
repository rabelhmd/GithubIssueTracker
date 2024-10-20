package com.rabelhmd.githubissuetracker.ui.issues;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rabelhmd.githubissuetracker.models.issueListItem.IssueListItem;
import com.rabelhmd.githubissuetracker.repository.IssueListRepository;
import com.rabelhmd.githubissuetracker.repository.IssueListRepositoryImpl;
import com.rabelhmd.githubissuetracker.service.NetworkCallback;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class IssuesViewModel extends ViewModel {

    private final MutableLiveData<List<IssueListItem>> issuesLiveData = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>(null);
    private final MutableLiveData<Boolean> showEmptyView = new MutableLiveData<>(null);
    private String searchQuery;

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Boolean> getShowEmptyView() {
        return showEmptyView;
    }

    public LiveData<List<IssueListItem>> getIssuesLiveData() {
        return issuesLiveData;
    }

    private final IssueListRepository repository;
    private int pageCount;
    private boolean reachedEndOfList = false;
    private final int pageSize = 30;

    public IssuesViewModel() {
        pageCount = 1;
        reachedEndOfList = false;
        repository = new IssueListRepositoryImpl();
        fetchNextPage();
    }

    public void fetchNextPage() {
        if (Boolean.TRUE.equals(isLoading.getValue()) || reachedEndOfList) {
            return;
        }
        showEmptyView.setValue(false);
        isLoading.setValue(true);
        errorMessage.setValue(null);
        repository.fetchIssues("flutter", "flutter", pageCount, pageSize, new NetworkCallback<List<IssueListItem>>() {
            @Override
            public void onSuccess(List<IssueListItem> data) {
                isLoading.setValue(false);
                errorMessage.setValue(null);
                pageCount += 1;
                updateIssueListLiveData(data);
            }
            @Override
            public void onFailure(Exception e) {
                isLoading.setValue(false);
                if(e instanceof IllegalStateException) {
                    errorMessage.setValue("No Internet!");
                } else {
                    errorMessage.setValue("Unable to fetch issues");
                }
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
        if (Boolean.TRUE.equals(isLoading.getValue())) {
            return;
        }

        String encodedQuery = URLEncoder.encode(q);
        final String query = encodedQuery + "+repo:flutter/flutter";
        if (searchQuery == null || !searchQuery.equals(q)) {
            searchQuery = q;
            pageCount = 1;
            reachedEndOfList = false;
            issuesLiveData.postValue(new ArrayList<>());
        } else if (reachedEndOfList) {
            return;
        }
        showEmptyView.setValue(false);
        isLoading.setValue(true);
        repository.searchIssues(query, pageSize, pageCount, new NetworkCallback<List<IssueListItem>>() {
            @Override
            public void onSuccess(List<IssueListItem> data) {
                isLoading.postValue(false);
                pageCount += 1;
                updateIssueListLiveData(data);
            }
            @Override
            public void onFailure(Exception e) {
                isLoading.postValue(false);
                if(e instanceof IllegalStateException) {
                    errorMessage.postValue("No Internet!");
                } else {
                    errorMessage.postValue("Unable to fetch issues");
                }
            }
        });
    }

    public void clearSearchQuery() {
        this.pageCount = 1;
        reachedEndOfList = false;
        this.searchQuery = null;
        issuesLiveData.postValue(new ArrayList<>());
        fetchNextPage();
    }

    private void updateIssueListLiveData(List<IssueListItem> data) {
        if (data == null || data.isEmpty()) {
            reachedEndOfList = true;
        }

        List<IssueListItem> filteredList = getFilteredList(data);
        if (filteredList.isEmpty() && (issuesLiveData.getValue() == null || issuesLiveData.getValue().isEmpty())) {
            showEmptyView.setValue(true);
            return;
        }

        if (pageCount == 2) {
            issuesLiveData.setValue(filteredList);
        } else {
            List<IssueListItem> currentIssues = issuesLiveData.getValue();
            if (currentIssues == null) {
                currentIssues = new ArrayList<>();
            }
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
