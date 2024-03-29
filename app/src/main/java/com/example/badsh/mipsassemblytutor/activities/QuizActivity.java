package com.example.badsh.mipsassemblytutor.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.badsh.mipsassemblytutor.MainActivity;
import com.example.badsh.mipsassemblytutor.R;
import com.example.badsh.mipsassemblytutor.data_provider.UserStatsDataHandler;
import com.example.badsh.mipsassemblytutor.fragments.AddingBinaryFragment;
import com.example.badsh.mipsassemblytutor.fragments.BinaryInputFragment;
import com.example.badsh.mipsassemblytutor.fragments.DecimalInputFragment;
import com.example.badsh.mipsassemblytutor.fragments.MachineCodeInputFragment;
import com.example.badsh.mipsassemblytutor.fragments.MipsComputeCommandFragment;
import com.example.badsh.mipsassemblytutor.fragments.TypeMipsCommandFragment;
import com.example.badsh.mipsassemblytutor.models.QuizGridItem;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Shabaz Badshah on 9/19/2017.
 */

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private final int QUIZ_FRAGMENT_HOLDER = R.id.question_container;
    private final int QUIZ_ACTIVITY_LAYOUT_ID = R.layout.activity_quiz_activity;

    private final Class QUIZ_COMPLETE_ACTIVITY = QuizCompleteActivity.class;

    private  QuizGridItem quizMeta;
    private final String PATH_TO_FRAGMENTS_PKG = "com.example.badsh.mipsassemblytutor.fragments.";
    private String mAssociatedQuizActivity = "";

    private static FragmentManager sFragmentManager;
    private Fragment mFragmentToSwitchTo;

    private Intent mQuizCompleteActivityIntent;

    private TextView mScoreCountTv;
    private TextView mScoreCountInsTv;

    private ImageView mAnswerStatusIv;

    private ViewGroup mScoreContainer;

    private ImageButton mConfirmAnswerBtn;
    private ImageButton mQuitQuizBtn;

    private int mCurrentQuesNum = 1;
    private int mTotalAmountQues = 5;
    private int mNumOfCorrectAns = 0;

    private boolean visible = false;

    private String mQuizDarkPrimaryColor;
    private String mQuizPrimaryColor;

    private boolean mCorrectAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(QUIZ_ACTIVITY_LAYOUT_ID);

        sFragmentManager = getSupportFragmentManager();

        getQuizIntentData();
        try {
            initQuizFragment();
        } catch (Exception e) {
        }
        initViews();
        setCountsAndColors();
        initClickListeners();
    }

    private void initViews() {

        mScoreContainer = (ViewGroup) findViewById(R.id.scoreContainer);

        mScoreCountTv = (TextView) findViewById(R.id.scoreTv);
        mScoreCountInsTv = (TextView) findViewById(R.id.scoreTvInstruction);
        mConfirmAnswerBtn = (ImageButton) findViewById(R.id.confirmAnswerBtn);
        mQuitQuizBtn = (ImageButton) findViewById(R.id.quitQuizBtn);
        mAnswerStatusIv = (ImageView) findViewById(R.id.questionAnswerStatus);

        mAnswerStatusIv.setVisibility(View.GONE);
    }

    private void initQuizFragment() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Log.v("meta", mQuizDarkPrimaryColor + ", " + mQuizPrimaryColor + ", " + mAssociatedQuizActivity);

        mFragmentToSwitchTo = (Fragment) Class.forName(mAssociatedQuizActivity).newInstance();

        if (mAssociatedQuizActivity != null && mFragmentToSwitchTo != null) {
            sFragmentManager.beginTransaction()
                    .add(QUIZ_FRAGMENT_HOLDER, mFragmentToSwitchTo)
                    .commit();
        }
        else {
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
        } else if (mFragmentToSwitchTo instanceof MipsComputeCommandFragment) {
            ((MipsComputeCommandFragment) mFragmentToSwitchTo).generateAndSetNewQuestion();
        }
        else if (mFragmentToSwitchTo instanceof TypeMipsCommandFragment) {
            ((TypeMipsCommandFragment) mFragmentToSwitchTo).generateAndSetNewQuestion();
        }
        else if (mFragmentToSwitchTo instanceof MachineCodeInputFragment) {
            ((MachineCodeInputFragment) mFragmentToSwitchTo).generateAndSetNewQuestion();
        }

    }

    private void getQuizIntentData() {
        quizMeta  = this.getIntent().getExtras().getParcelable("quizMeta");
        mQuizDarkPrimaryColor = quizMeta.getDarkPrimaryColor();
        mQuizPrimaryColor = quizMeta.getPrimaryColor();

        // Need to provide absolute path to class in src dir to create a class instance
        mAssociatedQuizActivity = mAssociatedQuizActivity.concat(PATH_TO_FRAGMENTS_PKG)
                .concat(quizMeta.getAssociatedQuizActivity());
    }

    private void setCountsAndColors() {
        mQuitQuizBtn.setBackgroundColor(Color.parseColor(mQuizDarkPrimaryColor));
        mConfirmAnswerBtn.setBackgroundColor(Color.parseColor(mQuizPrimaryColor));
        mScoreCountInsTv.setBackgroundColor(Color.parseColor(mQuizDarkPrimaryColor));
        mScoreCountTv.setBackgroundColor(Color.parseColor(mQuizDarkPrimaryColor));
    }

    private void initClickListeners() {
        mQuitQuizBtn.setOnClickListener(this);
        mConfirmAnswerBtn.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        promptUserForConfirmation("Are you sure you want to quit the quiz?", "Quit", R.id.quitQuiz);
    }

    @Override
    public void onClick(View v) {
        String status = "CORRECT!";
        String answer = "";
        switch (v.getId()) {
            case R.id.quitQuizBtn:
                onBackPressed();
                break;
            case R.id.confirmAnswerBtn:
                // Check ths user's answer
                if (mFragmentToSwitchTo instanceof DecimalInputFragment) {
                    mCorrectAnswer = ((DecimalInputFragment) mFragmentToSwitchTo).checkAnswer();
                    answer = ((DecimalInputFragment) mFragmentToSwitchTo).getQuestionAnswer();
                } else if (mFragmentToSwitchTo instanceof BinaryInputFragment) {
                    mCorrectAnswer = ((BinaryInputFragment) mFragmentToSwitchTo).checkAnswer();
                    answer = ((BinaryInputFragment) mFragmentToSwitchTo).getQuestionAnswer();
                } else if (mFragmentToSwitchTo instanceof AddingBinaryFragment) {
                    mCorrectAnswer = ((AddingBinaryFragment) mFragmentToSwitchTo).checkAnswer();
                    answer = ((AddingBinaryFragment) mFragmentToSwitchTo).getQuestionAnswer();
                } else if (mFragmentToSwitchTo instanceof MipsComputeCommandFragment) {
                    mCorrectAnswer = ((MipsComputeCommandFragment) mFragmentToSwitchTo).checkAnswer();
                    answer = ((MipsComputeCommandFragment) mFragmentToSwitchTo).getQuestionAnswer();
                }
                else if (mFragmentToSwitchTo instanceof TypeMipsCommandFragment) {
                    mCorrectAnswer = ((TypeMipsCommandFragment) mFragmentToSwitchTo).checkAnswer();
                    answer = ((TypeMipsCommandFragment) mFragmentToSwitchTo).getQuestionAnswer();
                }
                else if (mFragmentToSwitchTo instanceof MachineCodeInputFragment) {
                    mCorrectAnswer = ((MachineCodeInputFragment) mFragmentToSwitchTo).checkAnswer();
                    answer = ((MachineCodeInputFragment) mFragmentToSwitchTo).getQuestionAnswer();
                }

                TextView tv = new TextView(v.getContext());
                tv.setTextColor(Color.BLACK);
                if (mCorrectAnswer) {
                    status = "CORRECT!\n";
                    tv.setBackgroundColor(getResources().getColor(R.color.correct_answer_color));
                    mNumOfCorrectAns++;
                    mScoreCountTv.setText(String.valueOf(mNumOfCorrectAns));
                } else {
                    tv.setBackgroundColor(getResources().getColor(R.color.incorrect_answer_color));
                    status = "INCORRECT\n";
                }

                mCurrentQuesNum++;

                if (mCurrentQuesNum > mTotalAmountQues) finishGame();
                else {
                    displayNewQuestion();
                }

        }
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
                            case R.id.quitQuiz:
                                finish(); // Finishes the current activity and goes to the previous one
                                break;
                        }
                    }})
                .setNegativeButton(R.string.no, null)
                .show();
    }

    private void sendGameDataViaIntent() {
        mQuizCompleteActivityIntent = new Intent(this, QUIZ_COMPLETE_ACTIVITY);

        String userScoreFormat = String.format("%d/%d", mNumOfCorrectAns, mTotalAmountQues);
        mQuizCompleteActivityIntent.putExtra("userScore", userScoreFormat);

        mQuizCompleteActivityIntent.putExtra("mQuizPrimaryColor", mQuizPrimaryColor);
        mQuizCompleteActivityIntent.putExtra("mQuizDarkPrimaryColor", mQuizDarkPrimaryColor);
    }

    private void finishGame() {
        sendGameDataViaIntent();
        updateUserStats();
        getApplicationContext().startActivity(mQuizCompleteActivityIntent);
        finish();
    }

    private void updateUserStats() {
        UserStatsDataHandler userStatsDataHandler = MainActivity.getUserStatsDataProvider();

        userStatsDataHandler.updateUserStat("Highest Accuracy", String.format("%.2f", (float) mNumOfCorrectAns / (float) mTotalAmountQues));
        userStatsDataHandler.updateUserStat("Questions Answered", String.valueOf(mTotalAmountQues));
        userStatsDataHandler.updateUserStat("Quizzes Finished", String.valueOf(1));
    }
}

