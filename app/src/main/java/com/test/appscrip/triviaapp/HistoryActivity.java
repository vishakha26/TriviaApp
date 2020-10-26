package com.test.appscrip.triviaapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private TestViewModel testViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        RecyclerView listrecyclerview = (RecyclerView) findViewById(R.id.history_recyclerview);
        listrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        listrecyclerview.setHasFixedSize(true);
        final TestAdapter adapter = new TestAdapter();
        listrecyclerview.setAdapter(adapter);

        testViewModel = new ViewModelProvider(this).get(TestViewModel.class);
        testViewModel.getAllData().observe(this, new Observer<List<Test>>() {
            @Override
            public void onChanged(@Nullable List<Test> tests) {
                Log.d("TestAdapterItems", "Data is updating");
                adapter.setNotes(tests);
            }
        });
    }
}