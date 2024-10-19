package com.rabelhmd.githubissuetracker.models;

import com.rabelhmd.githubissuetracker.models.issueListItem.IssueListItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResult {

    @SerializedName("items")
    private List<IssueListItem> items;

    public List<IssueListItem> getItems() {
        return items;
    }
}

