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

public class AddingBinaryFragment extends Fragment implements View.OnClickListener {

    QuestionGenerator mQuestionGenerator = new QuestionGenerator();

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
        int mDecimalNum = mQuestionGenerator.generateRandDecimalNum(16);
        mFirstBinaryString = mQuestionGenerator.convertDecimalToBinary(mDecimalNum);
        mDecimalNum = mQuestionGenerator.generateRandDecimalNum(8);
        mSecondBinaryString = mQuestionGenerator.convertDecimalToBinary(mDecimalNum);

        mFirstBinaryStringTv.setText("");
        mSecondBinaryStringTv.setText("");
        mFirstBinaryStringTv.setText(mFirstBinaryString);
        mSecondBinaryStringTv.setText(mSecondBinaryString);
        mAnswerField.setText("");

        questionAnswer = mQuestionGenerator.addBinaryNumbers(mFirstBinaryString, mSecondBinaryString);

        Toast.makeText(getContext(), questionAnswer, Toast.LENGTH_SHORT).show();
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
                mAnswerField.append("0");
                break;
            case R.id.oneInputBtn:
                mAnswerField.append("1");
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
        return userAnswer.equals(questionAnswer);

    }

}
