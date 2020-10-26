package com.test.appscrip.triviaapp;

public class QuestionAnswer {
    private String mQuestions [] = {
            "Who is the best cricketer in the world?",
            "What are the colors in the Indian national flag? Select all:",
            };

    private String mChoices [][] = {
            {"Sachin Tendulkar", "Virat Kohli", "Adam Gilchirst", "Jacques Kallis"},
            {"White", "Yellow", "Orange", "Green"}
    };

    // 0 for single choice, 1 for multiple choice answers
    private int multiOrSingle[] = {0,1};

    public int getCount() {
        return mQuestions.length;
    }

//Todo select only one in question number one

    public String getQuestion(int a) {
        String question = mQuestions[a];
        return question;
    }


    public String getChoice1(int a) {
        String choice0 = mChoices[a][0];
        return choice0;
    }


    public String getChoice2(int a) {
        String choice1 = mChoices[a][1];
        return choice1;
    }

    public String getChoice3(int a) {
        String choice2 = mChoices[a][2];
        return choice2;
    }

    public String getChoice4(int a) {
        String choice2 = mChoices[a][3];
        return choice2;
    }

    public int getMultiOrSingle(int a) {
        return multiOrSingle[a];
    }
}
