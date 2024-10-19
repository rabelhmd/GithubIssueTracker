package com.rabelhmd.githubissuetracker.ui.issues;

import com.rabelhmd.githubissuetracker.models.issueListItem.IssueListItem;

public interface IssueAdapterCallback {
    void loadNextPage();
    void showDetailsView(IssueListItem item);
}
