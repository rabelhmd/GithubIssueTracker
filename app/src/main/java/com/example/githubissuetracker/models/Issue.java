package com.example.githubissuetracker.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import com.example.githubissuetracker.models.issueListItem.User;


public class Issue {

    @SerializedName("id")
    private long id;  // Changed from int to long

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("user")
    private User user;

    @SerializedName("state")
    private String state;

    // Constructor
    public Issue(long id, String title, String body, Date createdAt, User user, String state) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
        this.user = user;
        this.state = state;
    }

    public Issue() {

    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

