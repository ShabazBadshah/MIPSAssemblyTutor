package com.example.badsh.mipsassemblytutor.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.badsh.mipsassemblytutor.R;

import static android.R.string.no;
import static com.example.badsh.mipsassemblytutor.R.id.quitQuiz;
import static com.example.badsh.mipsassemblytutor.R.id.requestHint;

/**
 * Created by Shabaz Badshah on 9/19/2017.
 */

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView amountHintsView;
    private Chronometer quizElapsedTimerView;
    private TextView currentQuestionNumView;
    private RelativeLayout questionContainerView;
    private Button confirmAnswerBtn;
    private int amountUserHintsLeft = 2;
    private int currentUserQuestionNum = 1;
    private int totalAmountQuestions = 1;
    private long timeWhenStopped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_activity);

        initToolbarsAndQuizBG();
        initHintsAndQuestionNumCounts();
        initQuizTimer();
        initClickListeners();
    }

    private void initToolbarsAndQuizBG() {

        String quizDarkPrimaryColor = this.getIntent().getExtras().get("mQuizDarkPrimaryColor").toString();
        String quizPrimaryColor = this.getIntent().getExtras().get("mQuizPrimaryColor").toString();

        confirmAnswerBtn = (Button) findViewById(R.id.confirmAnswerBtn);
        confirmAnswerBtn.setBackgroundColor(Color.parseColor(quizDarkPrimaryColor));
    }

    private void initQuizTimer() {
        quizElapsedTimerView = (Chronometer) findViewById(R.id.amountOfTimeElapsed);
        quizElapsedTimerView.start();
    }

    private void initHintsAndQuestionNumCounts() {
        amountHintsView = (TextView) findViewById(R.id.numOfHintsLeftCount);
        currentQuestionNumView = (TextView) findViewById(R.id.amountQuestionsLeftCount);

        amountHintsView.setText(String.valueOf(amountUserHintsLeft));

        String questionCountDisplayFormat = String.format("%d/%d", currentUserQuestionNum, totalAmountQuestions);
        currentQuestionNumView.setText(questionCountDisplayFormat);
    }

    private void initClickListeners() {
        ImageView quitQuizImage = (ImageView) findViewById(quitQuiz);
        quitQuizImage.setOnClickListener(this);

        ImageView requestHintImage = (ImageView) findViewById(requestHint);
        requestHintImage.setOnClickListener(this);

        confirmAnswerBtn.setOnClickListener(this);
    }

    public void onResume() {
        super.onResume();
        quizElapsedTimerView.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.quitQuiz:
                onBackPressed();
                break;
            case R.id.requestHint:
                pauseQuizTimer();
                if (amountUserHintsLeft <= 0) {
                    resumeQuizTimer();
                    Toast.makeText(this, "You do not have any hints left", Toast.LENGTH_SHORT).show();
                } else {
                    promptUserForConfirmation("Do you want to use a hint?", "Hint", R.id.requestHint);
                }
                break;
            case R.id.confirmAnswerBtn:
                if (currentUserQuestionNum >= totalAmountQuestions) {
                    finishGame();
                } else {
                    incrementQuestionNumber();
                }
        }
    }

    @Override
    public void onBackPressed() {
        pauseQuizTimer();
        promptUserForConfirmation("Are you sure you want to quit the quiz?", "Quit", R.id.quitQuiz);
    }

    private void pauseQuizTimer(){
        quizElapsedTimerView.stop();
        timeWhenStopped = quizElapsedTimerView.getBase() - SystemClock.elapsedRealtime();
    }

    private void resumeQuizTimer() {
        quizElapsedTimerView.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
        timeWhenStopped = 0;
        quizElapsedTimerView.start();
    }

    private void promptUserForConfirmation(String messageToDisplayOnPrompt, String titleOfPrompt, final int itemClicked) {

        new AlertDialog.Builder(this)
                .setTitle(titleOfPrompt)
                .setMessage(messageToDisplayOnPrompt)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        switch (itemClicked) {
                            default:
                                break;
                            case R.id.requestHint:
                                decrementHintCount();
                                resumeQuizTimer();
                                break;
                            case R.id.quitQuiz:
                                finish(); // Finishes the current activity and goes to the previous one
                                break;
                        }
                    }})
                .setNegativeButton(no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resumeQuizTimer();
                    }
                })
                .show();
    }

    private void decrementHintCount() {
        if (amountUserHintsLeft > 0) {
            amountUserHintsLeft--;
            amountHintsView.setText(String.valueOf(amountUserHintsLeft));
        }
    }

    private void incrementQuestionNumber() {
        if (currentUserQuestionNum < totalAmountQuestions) {
            currentUserQuestionNum++;
            String questionCountDisplayFormat = String.format("%d/%d", currentUserQuestionNum, totalAmountQuestions);
            currentQuestionNumView.setText(questionCountDisplayFormat);
        }
    }

    private void finishGame() {
        Intent quizCompleteIntent = new Intent(getApplicationContext(), QuizCompleteActivity.class);
        String userScoreFormat = String.format("%d/%d", 3, totalAmountQuestions);
        quizCompleteIntent.putExtra("userScore", userScoreFormat);

        long totalElapsedTimeInSec = SystemClock.elapsedRealtime() - quizElapsedTimerView.getBase();
        quizCompleteIntent.putExtra("userTime", String.valueOf(totalElapsedTimeInSec/1000));

        getApplicationContext().startActivity(quizCompleteIntent);
        finish();
    }

}

