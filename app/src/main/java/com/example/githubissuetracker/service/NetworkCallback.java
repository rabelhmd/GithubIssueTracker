package com.example.githubissuetracker.service;

public interface NetworkCallback<T> {
    void onSuccess(T data);
    void onFailure(Exception e);
}

