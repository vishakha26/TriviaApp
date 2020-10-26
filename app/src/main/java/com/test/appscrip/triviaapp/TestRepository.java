package com.test.appscrip.triviaapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TestRepository {
    private TestDao testDao;
    private LiveData<List<Test>> alldata;

    public TestRepository(Application application) {
        TestDatabse database = TestDatabse.getInstance(application);
        testDao = database.testDao();
        alldata = testDao.getAllData();
    }

    public void insert(Test test) {
        new InsertNoteAsyncTask(testDao).execute(test);
    }

    public void update(Test test) {
        new UpdateNoteAsyncTask(testDao).execute(test);
    }

    public LiveData<List<Test>> getAlldata() {
        return alldata;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Test, Void, Void> {
        private TestDao testDao;

        //constructor to pass test dao
        private InsertNoteAsyncTask(TestDao testDao) {
            this.testDao = testDao;
        }
        @Override
        protected Void doInBackground(Test... notes) {
            testDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Test, Void, Void> {
        private TestDao testDao;

        //constructor to pass testdao
        private UpdateNoteAsyncTask(TestDao testDao) {
            this.testDao = testDao;
        }
        @Override
        protected Void doInBackground(Test... notes) {
            testDao.update(notes[0]);
            return null;
        }
    }
}
