package com.example.badsh.mipsassemblytutor.models;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import com.example.badsh.mipsassemblytutor.MainActivity;

/**
 * Created by Shabaz Badshah on 9/16/2017.
 */

public class QuizGridItem {
    private String mNameOfQuiz;
    private int mQuizPrimaryColor;
    private int mQuizDarkPrimaryColor;
    private Class mQuizActivityToStart;
    private Context mParentActivityContext;

    public QuizGridItem(Class quizActivityToStart) {
        this.mQuizActivityToStart = quizActivityToStart;
    }

    public void startQuizActivity() {
        Intent intentToStartQuiz = new Intent(mParentActivityContext, MainActivity.class);
        // https://stackoverflow.com/questions/6539879/how-to-convert-a-color-integer-to-a-hex-string-in-android
        String quizPrimaryColor = String.format("#%06X", (0xFFFFFF & mQuizPrimaryColor));
        String quizDarkPrimaryColor = String.format("#%06X", (0xFFFFFF & mQuizPrimaryColor));

        intentToStartQuiz.putExtra("mQuizPrimaryColor", quizPrimaryColor);
        intentToStartQuiz.putExtra("mQuizDarkPrimaryColor", quizDarkPrimaryColor);
        mParentActivityContext.startActivity(intentToStartQuiz);
    }

    public void setContext(Context parentActivityContext) {
        this.mParentActivityContext = parentActivityContext;
    }

    public void setQuizPrimaryColor(String colorToSet) {
        this.mQuizPrimaryColor = Color.parseColor(colorToSet);
    }

    public void setQuizDarkPrimaryColor(String darkColorToSet) {
        this.mQuizDarkPrimaryColor = Color.parseColor(darkColorToSet);
    }

    public void setNameOfQuiz(String nameToSet) {
        this.mNameOfQuiz = nameToSet;
    }

    public int getPrimaryColor() {
        return this.mQuizPrimaryColor;
    }

    public int getDarkPrimaryColor() {
        return this.mQuizDarkPrimaryColor;
    }

    public String getNameOfQuiz() {
        return this.mNameOfQuiz;
    }

    public String toString() {
        return "Clicked on: " + mNameOfQuiz;
    }
}