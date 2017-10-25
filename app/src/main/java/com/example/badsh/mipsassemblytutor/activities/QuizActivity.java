package com.example.badsh.mipsassemblytutor.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.badsh.mipsassemblytutor.MainActivity;
import com.example.badsh.mipsassemblytutor.R;
import com.example.badsh.mipsassemblytutor.data_provider.UserStats;
import com.example.badsh.mipsassemblytutor.fragments.AddingBinaryFragment;
import com.example.badsh.mipsassemblytutor.fragments.BinaryInputFragment;
import com.example.badsh.mipsassemblytutor.fragments.DecimalInputFragment;
import com.example.badsh.mipsassemblytutor.fragments.MIPSSelectCorrectCommand;

import static com.example.badsh.mipsassemblytutor.R.id.quitQuiz;
import static com.example.badsh.mipsassemblytutor.R.id.requestHint;

/**
 * Created by Shabaz Badshah on 9/19/2017.
 */

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private final int QUIZ_VIEW_HOLDER_ID = R.id.question_container;
    private final int QUIZ_ACTIVITY_LAYOUT = R.layout.activity_quiz_activity;
    private final Class NEXT_ACTIVITY_TO_START = QuizCompleteActivity.class;
    private Class mAssociatedQuizActivity;

    private FragmentManager mFragmentManager;
    private Fragment mFragmentToSwitchTo;

    private Intent mQuizCompleteActivityIntent;

    private TextView mCurrentQuesNumTv;
    private TextView mAmountHintsTv;

    private ImageView mQuitQuizIv;
    private ImageView mRequestHintIv;

    private Chronometer mTimeElapsedInQuizView;
    private long totalElapsedTimeInSec;

    private Button mConfirmAnswerBtn;

    private long mTimeWhenStopped = 0;
    private int mAmountHintsLeft = 2;
    private int mCurrentQuesNum = 1;
    private int mTotalAmountQues = 5;
    private int mNumOfCorrectAns = 0;

    private String mQuizDarkPrimaryColor;
    private String mQuizPrimaryColor;

    private boolean mCorrectAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(QUIZ_ACTIVITY_LAYOUT);

        mFragmentManager = getSupportFragmentManager();

        getQuizIntentData();
        initQuizFragment();
        initViews();
        setCountsAndColors();
        initClickListeners();

        mTimeElapsedInQuizView.start();
    }

    private void initViews() {
        mQuitQuizIv = (ImageView) findViewById(quitQuiz);
        mRequestHintIv = (ImageView) findViewById(requestHint);

        mAmountHintsTv = (TextView) findViewById(R.id.numOfHintsLeftCount);
        mCurrentQuesNumTv = (TextView) findViewById(R.id.amountQuestionsLeftCount);

        mConfirmAnswerBtn = (Button) findViewById(R.id.confirmAnswerBtn);

        mTimeElapsedInQuizView = (Chronometer) findViewById(R.id.amountOfTimeElapsed);
    }

    private void initQuizFragment() {

        if (mAssociatedQuizActivity.equals(BinaryInputFragment.class)) {
            mFragmentToSwitchTo = new BinaryInputFragment();
        }
        else if (mAssociatedQuizActivity.equals(DecimalInputFragment.class)) {
            mFragmentToSwitchTo = new DecimalInputFragment();
        }
        else if (mAssociatedQuizActivity.equals(AddingBinaryFragment.class)) {
            mFragmentToSwitchTo = new AddingBinaryFragment();
        }
        else if (mAssociatedQuizActivity.equals(MIPSSelectCorrectCommand.class)) {
            mFragmentToSwitchTo = new MIPSSelectCorrectCommand();
        }

        if (mAssociatedQuizActivity != null && mFragmentToSwitchTo != null) {
            mFragmentManager.beginTransaction()
                    .add(QUIZ_VIEW_HOLDER_ID, mFragmentToSwitchTo)
                    .commit();
        } else {
            Context goToHomeContext = getApplicationContext();
            Intent goToHomeIntent = new Intent(goToHomeContext, MainActivity.class);
            goToHomeContext.startActivity(goToHomeIntent);
            finish();
        }
    }

    private void displayNewQuestion() {

        if (mFragmentToSwitchTo instanceof DecimalInputFragment) {
            ((DecimalInputFragment) mFragmentToSwitchTo).generateAndSetNewQuestion();
        } else if (mFragmentToSwitchTo instanceof BinaryInputFragment) {
            ((BinaryInputFragment) mFragmentToSwitchTo).generateAndSetNewQuestion();
        } else if (mFragmentToSwitchTo instanceof AddingBinaryFragment) {
            ((AddingBinaryFragment) mFragmentToSwitchTo).generateAndSetNewQuestion();
        } else if (mFragmentToSwitchTo instanceof MIPSSelectCorrectCommand) {
            ((MIPSSelectCorrectCommand) mFragmentToSwitchTo).generateAndSetNewQuestion();
        }

    }

    private void getQuizIntentData() {
        mQuizDarkPrimaryColor = this.getIntent().getExtras().get("mQuizDarkPrimaryColor").toString();
        mQuizPrimaryColor = this.getIntent().getExtras().get("mQuizPrimaryColor").toString();

        mAssociatedQuizActivity = (Class) this.getIntent().getExtras().get("mAssociatedQuizActivity");
    }

    private void setCountsAndColors() {
        mConfirmAnswerBtn.setBackgroundColor(Color.parseColor(mQuizDarkPrimaryColor));

        mAmountHintsTv.setText(String.valueOf(mAmountHintsLeft));

        String questionCountDisplayFormat = String.format("%d/%d", mCurrentQuesNum, mTotalAmountQues);
        mCurrentQuesNumTv.setText(questionCountDisplayFormat);
    }

    private void initClickListeners() {
        mQuitQuizIv.setOnClickListener(this);
        mRequestHintIv.setOnClickListener(this);

        mConfirmAnswerBtn.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        resumeQuizTimer();
    }

    @Override
    public void onPause() {
        super.onPause();
        pauseQuizTimer();
    }

    @Override
    public void onBackPressed() {
        pauseQuizTimer();
        promptUserForConfirmation("Are you sure you want to quit the quiz?", "Quit", R.id.quitQuiz);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.quitQuiz:
                onBackPressed();
                break;
            case R.id.requestHint:
                if (mAmountHintsLeft <= 0) Toast.makeText(this, "You have no hints left", Toast.LENGTH_SHORT).show();
                else promptUserForConfirmation("Do you want to use a hint?", "Hint", R.id.requestHint);
                break;
            case R.id.confirmAnswerBtn:
                // Check ths user's answer
                if (mFragmentToSwitchTo instanceof DecimalInputFragment) {
                    mCorrectAnswer = ((DecimalInputFragment) mFragmentToSwitchTo).checkAnswer();
                } else if (mFragmentToSwitchTo instanceof BinaryInputFragment) {
                    mCorrectAnswer = ((BinaryInputFragment) mFragmentToSwitchTo).checkAnswer();
                } else if (mFragmentToSwitchTo instanceof AddingBinaryFragment) {
                    mCorrectAnswer = ((AddingBinaryFragment) mFragmentToSwitchTo).checkAnswer();
                } else if (mFragmentToSwitchTo instanceof MIPSSelectCorrectCommand) {
                    mCorrectAnswer = ((MIPSSelectCorrectCommand) mFragmentToSwitchTo).checkAnswer();
                }

                if (mCorrectAnswer) mNumOfCorrectAns++;
                Toast.makeText(getApplicationContext(), String.valueOf(mCorrectAnswer), Toast.LENGTH_SHORT).show();
                incrementQuestionNumber();

                if (mCurrentQuesNum > mTotalAmountQues) finishGame();
                else displayNewQuestion();

        }
    }

    private void pauseQuizTimer(){
        mTimeElapsedInQuizView.stop();
        mTimeWhenStopped = mTimeElapsedInQuizView.getBase() - SystemClock.elapsedRealtime();
    }

    private void resumeQuizTimer() {
        mTimeElapsedInQuizView.setBase(SystemClock.elapsedRealtime() + mTimeWhenStopped);
        mTimeWhenStopped = 0;
        mTimeElapsedInQuizView.start();
    }

    private void promptUserForConfirmation(String messageToDisplayOnPrompt, String titleOfPrompt, final int itemClicked) {

        new AlertDialog.Builder(this)
                .setTitle(titleOfPrompt)
                .setMessage(messageToDisplayOnPrompt)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        switch (itemClicked) {
                            default:
                                break;
                            case R.id.requestHint:
                                decrementHintCount();
                                break;
                            case R.id.quitQuiz:
                                finish(); // Finishes the current activity and goes to the previous one
                                break;
                        }
                    }})
                .setNegativeButton(R.string.no, null)
                .show();
    }

    private void decrementHintCount() {
        if (mAmountHintsLeft > 0) {
            mAmountHintsLeft--;
            mAmountHintsTv.setText(String.valueOf(mAmountHintsLeft));
        }
    }

    private void incrementQuestionNumber() {
            mCurrentQuesNum++;
            if (mCurrentQuesNum <= mTotalAmountQues) {
                String questionCountDisplayFormat = String.format("%d/%d", mCurrentQuesNum, mTotalAmountQues);
                mCurrentQuesNumTv.setText(questionCountDisplayFormat);
            }
    }

    private void sendGameDataViaIntent() {
        mQuizCompleteActivityIntent = new Intent(this, NEXT_ACTIVITY_TO_START);

        String userScoreFormat = String.format("%d/%d", mNumOfCorrectAns, mTotalAmountQues);
        mQuizCompleteActivityIntent.putExtra("userScore", userScoreFormat);

        mQuizCompleteActivityIntent.putExtra("mQuizPrimaryColor", mQuizPrimaryColor);
        mQuizCompleteActivityIntent.putExtra("mQuizDarkPrimaryColor", mQuizDarkPrimaryColor);

        totalElapsedTimeInSec = SystemClock.elapsedRealtime() - mTimeElapsedInQuizView.getBase();
        mQuizCompleteActivityIntent.putExtra("userTime", String.valueOf(totalElapsedTimeInSec/1000));
    }

    private void finishGame() {
        sendGameDataViaIntent();
        updateUserStats();
        getApplicationContext().startActivity(mQuizCompleteActivityIntent);
        finish();
    }

    private void updateUserStats() {
        UserStats userStats = MainActivity.getUserStats();

        userStats.updateUserStat("Highest Accuracy", String.format("%.2f", (float)mNumOfCorrectAns/(float)mTotalAmountQues));
        userStats.updateUserStat("Best Time", String.valueOf(totalElapsedTimeInSec));
        userStats.updateUserStat("Questions Answered", String.valueOf(mTotalAmountQues));
        userStats.updateUserStat("Quizzes Finished", String.valueOf(1));
    }

}

