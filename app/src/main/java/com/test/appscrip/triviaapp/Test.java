package com.test.appscrip.triviaapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "test_history_table")
public class Test {
    //primary key
    @PrimaryKey(autoGenerate = true)
    private int id;

    //Other columns
    private String name;
    private String datetime;
    private String question;
    private String answers;

    public Test(String name, String datetime, String question, String answers) {
        this.name = name;
        this.datetime = datetime;
        this.question = question;
        this.answers = answers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswers() {
        return answers;
    }
}
