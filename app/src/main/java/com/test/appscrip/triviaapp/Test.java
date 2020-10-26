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
    private String question1;

    public Test(String name, String datetime, String question1) {
        this.name = name;
        this.datetime = datetime;
        this.question1 = question1;
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

    public String getQuestion1() {
        return question1;
    }

}
