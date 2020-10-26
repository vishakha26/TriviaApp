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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestActivity extends Activity {

    public static final String NAME = "name";
    public static final String DATETIME = "datetime";
    public static final String QUESTION1 = "question1";

    QuestionAnswer questionBank = new QuestionAnswer();
    TextView mQuestion;
    CheckBox choice1;
    CheckBox choice2;
    CheckBox choice3;
    CheckBox choice4;
    Button nextQuestion;
    Button summary;
    int mQuestionNumber = 0;
    String name = "";
    private static final String TAG = "TestActivityTag";
    String ansSelected ;

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

        if(mQuestionNumber<questionBank.getCount()){
            updateText();
            updateQuestion();
        }

        //catch intent
        if (getIntent().hasExtra("name")) {
            name = getIntent().getStringExtra("name");
            Log.d(TAG, name);
        }

        Calendar mcurrentDate = Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("dd MMMM HH:mm");
        String date = month_date.format(mcurrentDate.getTime());

        ansSelected = "\n";

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mQuestionNumber<questionBank.getCount()) {
                    updateText();
                    mQuestionNumber++;
                    updateQuestion();
                }
//                saveData();
            }
        });

        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateText();
                Log.d(TAG, "ans is " + ansSelected);
                Intent i = new Intent(getApplicationContext(), SummaryActivity.class);
                i.putExtra(NAME,name);
                i.putExtra(DATETIME,date);
                i.putExtra(QUESTION1, ansSelected);
                startActivity(i);
            }
        });

    }

    private void saveData() {
        if(ansSelected.isEmpty()) {
            ansSelected = "Nothing Selected";
        }
        Intent i = new Intent();
        i.putExtra(NAME, name);
        i.putExtra(DATETIME, "27 Oct 2020 2:00am");
        i.putExtra(QUESTION1, questionBank.getQuestion(mQuestionNumber) + "\n" + ansSelected);
        setResult(RESULT_OK, i);
        finish();
    }

    private void updateQuestion() {

        mQuestion.setText(questionBank.getQuestion(mQuestionNumber));
        choice1.setText(questionBank.getChoice1(mQuestionNumber));
        choice2.setText(questionBank.getChoice2(mQuestionNumber));
        choice3.setText(questionBank.getChoice3(mQuestionNumber));
        choice4.setText(questionBank.getChoice4(mQuestionNumber));


        Log.d(TAG, String.valueOf(mQuestionNumber));

        //Show Summary Button at last question
        if(mQuestionNumber==questionBank.getCount()-1) {
            summary.setVisibility(View.VISIBLE);
            nextQuestion.setVisibility(View.INVISIBLE);
        }

    }

    private void updateText() {

        if(ansSelected != null)
            ansSelected = ansSelected + "\n\n" + questionBank.getQuestion(mQuestionNumber) + "\n";

        if(choice1.isChecked())
            ansSelected = ansSelected + " " +choice1.getText().toString() +",";
        if(choice2.isChecked())
            ansSelected = ansSelected + " " +choice2.getText().toString() + ",";
        if(choice3.isChecked())
            ansSelected = ansSelected + " " +choice3.getText().toString() + ",";
        if(choice4.isChecked())
            ansSelected = ansSelected + " " +choice4.getText().toString() + ",";

        //to remove extra comma at the end
        if(ansSelected != null)
            ansSelected = ansSelected.substring(0,ansSelected.length()-1);
        Log.d(TAG, "ans is " + ansSelected);
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        //TODO save question state on rotate

        // save question index
        outState.putInt("IndexKey", mQuestionNumber);
        Log.d(TAG, String.valueOf(mQuestionNumber));
    }
}
