package com.test.appscrip.triviaapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Test.class, version = 1)
public abstract class TestDatabse extends RoomDatabase {

    //DB instance to turn class into singleton
    private static TestDatabse instance;

    public abstract TestDao testDao();

    //syncronized to maintain consistency
    public static synchronized TestDatabse getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TestDatabse.class,"test_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    //run db tasks in another thread
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private TestDao testDao;
        private PopulateDbAsyncTask(TestDatabse db) {
            testDao = db.testDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
