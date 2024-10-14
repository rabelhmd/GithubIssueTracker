package com.example.githubissuetracker.models;

import com.google.gson.annotations.SerializedName;

public class SearchResult {

    @SerializedName("items")
    private Issue[] items;

    public Issue[] getItems() {
        return items;
    }

    public void setItems(Issue[] items) {
        this.items = items;
    }
}

