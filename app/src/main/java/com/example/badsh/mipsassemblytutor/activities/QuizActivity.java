package com.example.badsh.mipsassemblytutor.activities;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Shabaz Badshah on 9/19/2017.
 */

public class QuizActivity extends AppCompatActivity {

    public void initActionBarAndActivity() {
        // https://stackoverflow.com/questions/12070744/add-back-button-to-action-bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        String nameOfQuiz = getIntent().getStringExtra("mNameOfQuiz");
        actionBar.setTitle(nameOfQuiz);

        final View rootView = findViewById(android.R.id.content);
        String quizPrimaryColor = getIntent().getStringExtra("mQuizPrimaryColor");
        Toast.makeText(this, quizPrimaryColor, Toast.LENGTH_SHORT).show();
        rootView.setBackgroundColor(Color.parseColor(quizPrimaryColor));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Home is the android builtin back button in actionbar
            case android.R.id.home: // Goes back
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

