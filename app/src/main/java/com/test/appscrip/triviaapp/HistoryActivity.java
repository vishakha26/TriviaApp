package com.test.appscrip.triviaapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private TestViewModel testViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        testViewModel = new ViewModelProvider(this).get(TestViewModel.class);
        testViewModel.getAllNotes().observe(this, new Observer<List<Test>>() {
            @Override
            public void onChanged(@Nullable List<Test> notes) {
                //update RecyclerView
                Toast.makeText(HistoryActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
            }
        });
    }
}