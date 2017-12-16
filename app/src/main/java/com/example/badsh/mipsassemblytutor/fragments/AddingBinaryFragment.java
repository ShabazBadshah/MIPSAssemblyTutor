package com.example.badsh.mipsassemblytutor.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.badsh.mipsassemblytutor.R;
import com.example.badsh.mipsassemblytutor.engine.Utils.EngineUtils;

public class AddingBinaryFragment extends Fragment implements View.OnClickListener {

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

    private void initViews() {
        mFirstBinaryStringTv = (TextView) mQuizView.findViewById(R.id.userCommandTv);
        mSecondBinaryStringTv = (TextView) mQuizView.findViewById(R.id.randGeneratedNumTwo);

        mAnswerField = (EditText) mQuizView.findViewById(R.id.answerField);
        mAnswerField.setInputType(InputType.TYPE_NULL);

        mZeroBtn = (Button) mQuizView.findViewById(R.id.zeroInputBtn);
        mOneBtn = (Button) mQuizView.findViewById(R.id.oneInputBtn);
        mBackspaceBtn = (Button) mQuizView.findViewById(R.id.backspaceBtn);

        generateAndSetNewQuestion();
    }

    public void generateAndSetNewQuestion() {
        int mDecimalNum = EngineUtils.generateRandomDecimalNumber(true, 16);
        mFirstBinaryString = EngineUtils.convertDecimalToBinary(mDecimalNum);
        mDecimalNum = EngineUtils.generateRandomDecimalNumber(true, 8);
        mSecondBinaryString = EngineUtils.convertDecimalToBinary(mDecimalNum);

        mFirstBinaryStringTv.setText(null);
        mSecondBinaryStringTv.setText(null);
        mFirstBinaryStringTv.setText(mFirstBinaryString);
        mSecondBinaryStringTv.setText(mSecondBinaryString);
        mAnswerField.setText(null);

        questionAnswer = EngineUtils.addBinaryStrings(mFirstBinaryString, mSecondBinaryString);

//        Toast.makeText(getContext(), questionAnswer, Toast.LENGTH_SHORT).show();
    }

    private void initClickListeners() {
        mZeroBtn.setOnClickListener(this);
        mOneBtn.setOnClickListener(this);
        mBackspaceBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.zeroInputBtn:
                mAnswerField.setText(mAnswerField.getText().append("0"));
                break;
            case R.id.oneInputBtn:
                mAnswerField.setText(mAnswerField.getText().append("1"));
                break;
            case R.id.backspaceBtn:
                String userAnswer = mAnswerField.getText().toString();
                if (userAnswer.length() >= 1) {
                    mAnswerField.setText(null);
                    userAnswer = userAnswer.substring(0, userAnswer.length() - 1);
                    mAnswerField.setText(userAnswer);
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
