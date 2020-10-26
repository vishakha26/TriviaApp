package com.test.appscrip.triviaapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TestDao {

    @Insert
    void insert(Test test);

    @Query("SELECT * from test_history_table ORDER BY id DESC")
    LiveData<List<Test>> getAllData();
}
