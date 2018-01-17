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

public class BinaryInputFragment extends Fragment implements View.OnClickListener {

    private View mQuizView;

    private TextView mDecimalNumTv;
    private EditText mAnswerField;

    private Button mZeroBtn;
    private Button mOneBtn;
    private Button mBackspaceBtn;

    private int mDecimalNum;
    private String mBinaryNum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mQuizView = inflater.inflate(R.layout.fragment_binary_input, container, false);

        initViews();
        initClickListeners();

        return mQuizView;
    }

    private void initViews() {
        mDecimalNumTv = (TextView) mQuizView.findViewById(R.id.userCommandTv);

        mAnswerField = (EditText) mQuizView.findViewById(R.id.answerField);
        mAnswerField.setInputType(InputType.TYPE_NULL);

        mZeroBtn = (Button) mQuizView.findViewById(R.id.zeroBtn);
        mOneBtn = (Button) mQuizView.findViewById(R.id.oneBtn);
        mBackspaceBtn = (Button) mQuizView.findViewById(R.id.backspaceBtn);

        generateAndSetNewQuestion();
    }

    public void generateAndSetNewQuestion() {
        mDecimalNum = EngineUtils.generateRandomDecimalNumber(true, 16);
        mBinaryNum = EngineUtils.convertDecimalToBinary(mDecimalNum);

        mDecimalNumTv.setText(Integer.toString(mDecimalNum));
        mAnswerField.setText("");
    }

    private void initClickListeners() {
        mZeroBtn.setOnClickListener(this);
        mOneBtn.setOnClickListener(this);
        mBackspaceBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.zeroBtn:
                mAnswerField.setText(mAnswerField.getText().append("0"));
                break;
            case R.id.oneBtn:
                mAnswerField.setText(mAnswerField.getText().append("1"));
                break;
            case R.id.backspaceBtn:
                String userAnswer = mAnswerField.getText().toString();
                if (userAnswer.length() >= 1) {
                    mAnswerField.setText("");
                    userAnswer = userAnswer.substring(0, userAnswer.length() - 1);
                    mAnswerField.setText(userAnswer);
                }
                break;
        }
    }

    public boolean checkAnswer() {
        String userAnswer = mAnswerField.getText().toString();
        return userAnswer.equals(mBinaryNum);
    }

    public String getQuestionAnswer() {
        return this.mBinaryNum;
    }

}
