package com.example.badsh.mipsassemblytutor.models;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

/**
 * Created by Shabaz Badshah on 9/16/2017.
 */

public class QuizGridItem {
    private Context mParentActivityContext;
    private Class mQuizActivityToStart;
    private Class mAssociatedQuizActivity;

    private int mQuizPrimaryColor;
    private int mQuizDarkPrimaryColor;

    private String mNameOfQuiz;

    private int mQuizImageId;

    public QuizGridItem(Class quizActivityToStart) {
        this.mQuizActivityToStart = quizActivityToStart;
    }

    public void startQuizActivity() {
        Intent intentToStartQuiz = new Intent(mParentActivityContext, mQuizActivityToStart);
        // https://stackoverflow.com/questions/6539879/how-to-convert-a-color-integer-to-a-hex-string-in-android
        String quizPrimaryColor = String.format("#%06X", (0xFFFFFF & mQuizPrimaryColor));
        String quizDarkPrimaryColor = String.format("#%06X", (0xFFFFFF & mQuizPrimaryColor));

        intentToStartQuiz.putExtra("mQuizPrimaryColor", quizPrimaryColor);
        intentToStartQuiz.putExtra("mQuizDarkPrimaryColor", quizDarkPrimaryColor);
        intentToStartQuiz.putExtra("mAssociatedQuizActivity", mAssociatedQuizActivity);
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

    public void setQuizImage(int imageId) {
        this.mQuizImageId = imageId;
    }

    public int getQuizImageId() {
        return this.mQuizImageId;
    }

    public void setQuizActivityToStart (Class quizToStart) {
        this.mAssociatedQuizActivity = quizToStart;
    }

    public Class getQuizAssociatedQuizActivity() { return this.mAssociatedQuizActivity; }

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
