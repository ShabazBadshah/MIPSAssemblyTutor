package com.example.badsh.mipsassemblytutor.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.badsh.mipsassemblytutor.R;
import com.example.badsh.mipsassemblytutor.engine.Utils.EngineUtils;
import com.example.badsh.mipsassemblytutor.interfaces.QuizPlayModule;

public class AddingBinaryFragment extends QuizPlayModule implements View.OnClickListener {

    private View mQuizView;

    private TextView mFirstBinaryStringTv;
    private TextView mSecondBinaryStringTv;
    private EditText mAnswerField;

    private Button mZeroBtn;
    private Button mOneBtn;
    private Button mBackspaceBtn;

    private String mFirstBinaryString;
    private String mSecondBinaryString;

    private String questionAnswer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mQuizView = inflater.inflate(R.layout.fragment_add_binary, container, false);

        initViews();
        initClickListeners();

        return mQuizView;
    }

    protected void initViews() {
        mFirstBinaryStringTv = (TextView) mQuizView.findViewById(R.id.firstNumField);
        mSecondBinaryStringTv = (TextView) mQuizView.findViewById(R.id.secondNumField);

        mAnswerField = (EditText) mQuizView.findViewById(R.id.answerField);
        mAnswerField.setShowSoftInputOnFocus(false);

        mZeroBtn = (Button) mQuizView.findViewById(R.id.zeroBtn);
        mOneBtn = (Button) mQuizView.findViewById(R.id.oneBtn);
        mBackspaceBtn = (Button) mQuizView.findViewById(R.id.backspaceBtn);

        generateAndSetNewQuestion();
    }

    public void generateAndSetNewQuestion() {
        int mDecimalNum = EngineUtils.generateRandomDecimalNumber(true, 16);
        mFirstBinaryString = EngineUtils.convertDecimalToBinary(mDecimalNum);
        mDecimalNum = EngineUtils.generateRandomDecimalNumber(true, 8);
        mSecondBinaryString = EngineUtils.convertDecimalToBinary(mDecimalNum);

        mFirstBinaryStringTv.setText("0b" + mFirstBinaryString);
        mSecondBinaryStringTv.setText("0b" + mSecondBinaryString);
        mAnswerField.setText("");

        questionAnswer = EngineUtils.addBinaryStrings(mFirstBinaryString, mSecondBinaryString);
    }

    protected void initClickListeners() {
        mZeroBtn.setOnClickListener(this);
        mOneBtn.setOnClickListener(this);
        mBackspaceBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.zeroBtn:
                mAnswerField.append("0");
                break;
            case R.id.oneBtn:
                mAnswerField.append("1");
                break;
            case R.id.backspaceBtn:
                if (mAnswerField.length() >= 1) {

                    String userAnswer = mAnswerField.getText().toString();

                    mAnswerField.setText("");
                    mAnswerField.append(userAnswer.substring(0, userAnswer.length() - 1));
                }
                break;
        }
    }

    public boolean checkAnswer() {
        String userAnswer = mAnswerField.getText().toString();
        return userAnswer.equals(questionAnswer);
    }

    public String getQuestionAnswer() {
        return this.questionAnswer;
    }

}
