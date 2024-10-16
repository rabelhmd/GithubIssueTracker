package com.example.githubissuetracker.models;

import com.example.githubissuetracker.models.issueListItem.IssueListItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResult {

    @SerializedName("items")
    private List<IssueListItem> items;

    public List<IssueListItem> getItems() {
        return items;
    }
}

