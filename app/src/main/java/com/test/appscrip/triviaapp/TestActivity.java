package com.test.appscrip.triviaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends Activity {

    QuestionAnswer questionBank = new QuestionAnswer();
    TextView mQuestion;
    CheckBox choice1;
    CheckBox choice2;
    CheckBox choice3;
    CheckBox choice4;
    Button nextQuestion;
    Button summary;
    int mQuestionNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);

        if(savedInstanceState!=null) {
            mQuestionNumber = savedInstanceState.getInt("IndexKey");
        } else {
            mQuestionNumber = 0;
        }

        mQuestion = (TextView) findViewById(R.id.question);
        choice1 = (CheckBox) findViewById(R.id.checkBox);
        choice2 = (CheckBox) findViewById(R.id.checkBox2);
        choice3 = (CheckBox) findViewById(R.id.checkBox3);
        choice4 = (CheckBox) findViewById(R.id.checkBox4);
        nextQuestion = (Button) findViewById(R.id.nextquestion);
        summary = (Button) findViewById(R.id.summary);

        if(mQuestionNumber<questionBank.getCount())
            updateQuestion();

        choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Choice 1 Selected", Toast.LENGTH_SHORT).show();
            }
        });
        choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Choice 2 Selected", Toast.LENGTH_SHORT).show();
            }
        });
        choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Choice 3 Selected", Toast.LENGTH_SHORT).show();
            }
        });
        choice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Choice 4 Selected", Toast.LENGTH_SHORT).show();
            }
        });
        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mQuestionNumber<questionBank.getCount()) {
                    updateQuestion();
                }
            }
        });

        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SummaryActivity.class);
                startActivity(i);
            }
        });
    }

    private void updateQuestion() {
        mQuestion.setText(questionBank.getQuestion(mQuestionNumber));
        choice1.setText(questionBank.getChoice1(mQuestionNumber));
        choice2.setText(questionBank.getChoice2(mQuestionNumber));
        choice3.setText(questionBank.getChoice3(mQuestionNumber));
        choice4.setText(questionBank.getChoice4(mQuestionNumber));

        Log.d("QuestionNumber", String.valueOf(mQuestionNumber));

        mQuestionNumber++;

        if(mQuestionNumber>=questionBank.getCount()) {
            summary.setVisibility(View.VISIBLE);
            nextQuestion.setVisibility(View.INVISIBLE);
            mQuestionNumber = questionBank.getCount()-1;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        //TODO save question state on rotate
        outState.putInt("IndexKey", mQuestionNumber);
        Log.d("QuestionNumberstate", String.valueOf(mQuestionNumber));
    }
}
