package com.test.appscrip.triviaapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TestViewModel extends AndroidViewModel {
    private TestRepository repository;
    private LiveData<List<Test>> allNotes;

    public TestViewModel(@NonNull Application application) {
        super(application);
        repository = new TestRepository(application);
        allNotes = repository.getAlldata();
    }

    public void insert(Test note) {
        repository.insert(note);
    }

    public void update(Test test) {
        repository.update(test);
    }

    public LiveData<List<Test>> getAllData() {
        return allNotes;
    }
}