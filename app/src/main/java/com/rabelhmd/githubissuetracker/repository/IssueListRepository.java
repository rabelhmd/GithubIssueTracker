package com.rabelhmd.githubissuetracker.repository;

import com.rabelhmd.githubissuetracker.models.issueListItem.IssueListItem;
import com.rabelhmd.githubissuetracker.service.NetworkCallback;

import java.util.List;

public interface IssueListRepository {
    void fetchIssues(String user, String repo, int perPage, int page, NetworkCallback<List<IssueListItem>> networkCallback);
    void searchIssues(String query, int perPage, int page, NetworkCallback<List<IssueListItem>> networkCallback);
}
