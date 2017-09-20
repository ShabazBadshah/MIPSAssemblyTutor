package com.example.badsh.mipsassemblytutor.models;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.Toast;

/**
 * Created by Shabaz Badshah on 9/16/2017.
 */

public class QuizGridItem {
    private String mNameOfQuiz;
    private int mQuizPrimaryColor;
    private Class mQuizActivityToStart;

    private Context mParentActivityContext;

    public QuizGridItem(Class quizActivityToStart) {
       this.mQuizActivityToStart = quizActivityToStart;
    }

    private void startQuizActivity() {
        Intent intentToStartQuiz = new Intent(mParentActivityContext, mQuizActivityToStart);
        intentToStartQuiz.putExtra("mNameOfQuiz", mNameOfQuiz);

        // https://stackoverflow.com/questions/6539879/how-to-convert-a-color-integer-to-a-hex-string-in-android
        String quizPrimaryColor = String.format("#%06X", (0xFFFFFF & mQuizPrimaryColor));
        intentToStartQuiz.putExtra("mQuizPrimaryColor", quizPrimaryColor);
        mParentActivityContext.startActivity(intentToStartQuiz);
    }

    public void setContext(Context parentActivityContext) {
       this.mParentActivityContext = parentActivityContext;
    }

    public void setQuizPrimaryColor(String colorToSet) {
       this.mQuizPrimaryColor = Color.parseColor(colorToSet);
    }

    public void setNameOfQuiz(String nameToSet) {
       this.mNameOfQuiz = nameToSet;
    }

    public int getPrimaryColor() {
       return this.mQuizPrimaryColor;
    }

    public void onClick(Context parentContext) {
        Toast.makeText(parentContext, this.toString(), Toast.LENGTH_SHORT).show();
        startQuizActivity();
    }

    public String getNameOfQuiz() {
       return this.mNameOfQuiz;
    }

    public String toString() {
        return "Clicked on: " + mNameOfQuiz;
    }
}
