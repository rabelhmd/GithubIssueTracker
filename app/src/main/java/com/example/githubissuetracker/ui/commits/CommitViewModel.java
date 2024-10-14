package com.example.githubissuetracker.ui.commits;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CommitViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CommitViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Commit Fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}