package com.rabelhmd.githubissuetracker.ui.commits;

import com.rabelhmd.githubissuetracker.models.issueListItem.IssueListItem;

public interface IssueAdapterCallback {
    void loadNextPage();
    void showDetailsView(IssueListItem item);
}
