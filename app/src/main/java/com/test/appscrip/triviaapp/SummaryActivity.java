package com.test.appscrip.triviaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SummaryActivity extends Activity {

    public static final String NAME = "name";
    public static final String DATETIME = "datetime";
    public static final String QUESTION1 = "question1";
    Button finish;
    Button history;
    TextView summary;
    String finalSummary;
    String databaseString;
    Test test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary);
        summary = (TextView) findViewById(R.id.summary);
        finish = (Button) findViewById(R.id.finish);
        history = (Button) findViewById(R.id.history);


        if (getIntent().hasExtra(DATETIME)) {
            String date = getIntent().getStringExtra(DATETIME);
            databaseString = "Game : " + test.getId() + date;
        }
        if (getIntent().hasExtra(NAME)) {
            String name = getIntent().getStringExtra(NAME);
            finalSummary = "Hello " + name + ": \n\n" + "Here are the selected answers: " ;
        }

        if (getIntent().hasExtra(QUESTION1)) {
            String question = getIntent().getStringExtra(QUESTION1);
            finalSummary = finalSummary + question;
        }



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
