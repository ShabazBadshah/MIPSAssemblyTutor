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
import android.widget.Toast;

import com.example.badsh.mipsassemblytutor.R;
import com.example.badsh.mipsassemblytutor.question_engine.QuestionGenerator;

public class BinaryInputFragment extends Fragment implements View.OnClickListener {

    QuestionGenerator mQuestionGenerator = new QuestionGenerator();

    private View mQuizView;

    private TextView mDecimalNumTv;
    private EditText mAnswerField;

    private String mUserAnswer = "";

    private Button mZeroBtn;
    private Button mOneBtn;
    private Button mBackspaceBtn;

    private int mDecimalNum;
    private String mBinaryNum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDecimalNum = mQuestionGenerator.generateRandDecimalNum(20);
        mBinaryNum = mQuestionGenerator.convertDecimalToBinary(mDecimalNum);
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
        mDecimalNumTv = (TextView) mQuizView.findViewById(R.id.randGeneratedNum);
        mDecimalNumTv.setText(Integer.toString(mDecimalNum));

        mAnswerField = (EditText) mQuizView.findViewById(R.id.answerField);
        mAnswerField.setInputType(InputType.TYPE_NULL);
        mAnswerField.setText("Enter answer");

        Toast.makeText(getContext(), mBinaryNum, Toast.LENGTH_SHORT).show();

        mZeroBtn = (Button) mQuizView.findViewById(R.id.zeroInputBtn);
        mOneBtn = (Button) mQuizView.findViewById(R.id.oneInputBtn);
        mBackspaceBtn = (Button) mQuizView.findViewById(R.id.backspaceBtn);
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
                mUserAnswer += "0";
                mAnswerField.setText(mUserAnswer);
                break;
            case R.id.oneInputBtn:
                mUserAnswer += "1";
                mAnswerField.setText(mUserAnswer);
                break;
            case R.id.backspaceBtn:
                if (mUserAnswer.length() >= 1) {
                    mUserAnswer = mUserAnswer.substring(0, mUserAnswer.length() - 1);
                    mAnswerField.setText(mUserAnswer);
                }
                break;
        }
    }

    public boolean checkAnswer() {
        return mUserAnswer.equals(mBinaryNum);
    }

}
