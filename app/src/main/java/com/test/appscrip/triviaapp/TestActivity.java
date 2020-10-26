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

    public static final String INDEX = "IndexKey";
    public static final String QUESANS = "Question";
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

        //check if there is any saved state
        if(savedInstanceState!=null) {
            mQuestionNumber = savedInstanceState.getInt(INDEX);
            ansSelected = savedInstanceState.getString(QUESANS);
        } else {
            mQuestionNumber = 0;
            ansSelected = "";
        }

        mQuestion = (TextView) findViewById(R.id.question);
        choice1 = (CheckBox) findViewById(R.id.checkBox);
        choice2 = (CheckBox) findViewById(R.id.checkBox2);
        choice3 = (CheckBox) findViewById(R.id.checkBox3);
        choice4 = (CheckBox) findViewById(R.id.checkBox4);
        nextQuestion = (Button) findViewById(R.id.nextquestion);
        summary = (Button) findViewById(R.id.summary);

        if(mQuestionNumber<questionBank.getCount()){
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

        choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if question has single answer
                if(questionBank.getMultiOrSingle(mQuestionNumber) == 0)
                    //uncheck all other boxes
                    onCheckboxClicked(choice1);
            }
        });
        choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionBank.getMultiOrSingle(mQuestionNumber) == 0)
                    onCheckboxClicked(choice2);
            }
        });
        choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionBank.getMultiOrSingle(mQuestionNumber) == 0)
                    onCheckboxClicked(choice3);
            }
        });
        choice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionBank.getMultiOrSingle(mQuestionNumber) == 0)
                    onCheckboxClicked(choice4);
            }
        });


        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mQuestionNumber<questionBank.getCount()) {
                    updateText();
                    mQuestionNumber++;
                    updateQuestion();
                }
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

    public void onCheckboxClicked(View view) {

        switch (view.getId()) {

            case R.id.checkBox:

                choice2.setChecked(false);
                choice3.setChecked(false);
                choice4.setChecked(false);
                break;

            case R.id.checkBox2:

                choice1.setChecked(false);
                choice3.setChecked(false);
                choice4.setChecked(false);
                break;

            case R.id.checkBox3:

                choice1.setChecked(false);
                choice2.setChecked(false);
                choice4.setChecked(false);
                break;

            case R.id.checkBox4:

                choice1.setChecked(false);
                choice2.setChecked(false);
                choice3.setChecked(false);
                break;
        }
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
            ansSelected = ansSelected + "\n" + questionBank.getQuestion(mQuestionNumber) + "\n" ;

        if(questionBank.getMultiOrSingle(mQuestionNumber) == 0)
            ansSelected = ansSelected + "Answer : ";
        else
            ansSelected = ansSelected + "Answers : ";

        //uncheck checked box for next question
        if(choice1.isChecked()) {
            ansSelected = ansSelected + " " + choice1.getText().toString() + ",";
            choice1.setChecked(false);
        }
        if(choice2.isChecked()) {
            ansSelected = ansSelected + " " +choice2.getText().toString() + ",";
            choice2.setChecked(false);
        }
        if(choice3.isChecked()) {
            ansSelected = ansSelected + " " +choice3.getText().toString() + ",";
            choice3.setChecked(false);
        }
        if(choice4.isChecked()) {
            ansSelected = ansSelected + " " +choice4.getText().toString() + ",";
            choice4.setChecked(false);
        }

        //to remove extra comma at the end
        if(ansSelected != null)
            ansSelected = ansSelected.substring(0,ansSelected.length()-1);
        Log.d(TAG, "ans is " + ansSelected);
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        // save question index and question answer string
        outState.putInt(INDEX, mQuestionNumber);
        outState.putString(QUESANS, ansSelected);
        Log.d(TAG, String.valueOf(mQuestionNumber));
    }
}
