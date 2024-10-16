package com.example.githubissuetracker.ui.commits;

import com.example.githubissuetracker.models.issueListItem.IssueListItem;

public interface IssueAdapterCallback {
    void loadNextPage();
    void showDetailsView(IssueListItem item);
}
