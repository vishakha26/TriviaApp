package com.test.appscrip.triviaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class SummaryActivity extends AppCompatActivity {

    public static final String NAME = "name";
    public static final String DATETIME = "datetime";
    public static final String QUESTION1 = "question1";
    Button finish;
    Button history;
    TextView summary;
    String finalSummary;
    String databaseString;
    Test test;
    String name1, date1, question;
    TestViewModel testViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary);
        summary = (TextView) findViewById(R.id.summary);
        finish = (Button) findViewById(R.id.finish);
        history = (Button) findViewById(R.id.history);

        //catch intent
        if (getIntent().hasExtra(DATETIME)) {
            String date = getIntent().getStringExtra(DATETIME);
            date1 = "Game : " + date;
        }
        if (getIntent().hasExtra(NAME)) {
            String name = getIntent().getStringExtra(NAME);
            finalSummary = "Hello " + name + ": \n" + "Here are the selected answers: " ;
            name1 = "Name : " +name;
        }

        if (getIntent().hasExtra(QUESTION1)) {
            question = getIntent().getStringExtra(QUESTION1);
            finalSummary = finalSummary + question;
        }

        //save data
        test = new Test(name1, date1, question);
        testViewModel = new ViewModelProvider(this).get(TestViewModel.class);
        testViewModel.getAllData().observe(this, new Observer<List<Test>>() {
            @Override
            public void onChanged(@Nullable List<Test> tests) {
            }
        });

        testViewModel.insert(test);

        summary.setText(finalSummary);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TestActivity.class);
                startActivity(i);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

}
