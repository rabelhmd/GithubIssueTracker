package com.example.githubissuetracker.models;

import com.google.gson.annotations.SerializedName;

class User {
    @SerializedName("login")
    private String login;

    @SerializedName("avatar_url")
    private String avatarUrl;

    // Constructor
    public User(String login, String avatarUrl) {
        this.login = login;
        this.avatarUrl = avatarUrl;
    }

    // Getters and setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
