package com.example.badsh.mipsassemblytutor.fragments;

import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.badsh.mipsassemblytutor.R;
import com.example.badsh.mipsassemblytutor.question_engine.QuestionGenerator;

public class DecimalInputFragment extends Fragment implements View.OnClickListener {

    QuestionGenerator mQuestionGenerator = new QuestionGenerator();

    private View mQuizView;

    private TextView mBinaryNumTv;
    private EditText mAnswerField;

    private Button[] mNumpadBtnsList = new Button[10];
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
        mQuizView = inflater.inflate(R.layout.fragment_decimal_input, container, false);

        initViews();
        initNumpad();

        return mQuizView;
    }

    private void initViews() {
        mBinaryNumTv = (TextView) mQuizView.findViewById(R.id.randGeneratedNumOne);
        mAnswerField = (EditText) mQuizView.findViewById(R.id.answerField);
        mAnswerField.setShowSoftInputOnFocus(false);

        generateAndSetNewQuestion();
    }

    public void generateAndSetNewQuestion() {
        mDecimalNum = mQuestionGenerator.generateRandDecimalNum(20);
        mBinaryNum = mQuestionGenerator.convertDecimalToBinary(mDecimalNum);

        mBinaryNumTv.setText(mBinaryNum);
        mAnswerField.setText("");
    }

    private void initNumpad() {

        mBackspaceBtn = (Button) mQuizView.findViewById(R.id.decimalInputBackSpaceBtn);
        mBackspaceBtn.setOnClickListener(this);

        mNumpadBtnsList[0] = (Button) mQuizView.findViewById(R.id.zeroBtn);
        mNumpadBtnsList[1] = (Button) mQuizView.findViewById(R.id.oneBtn);
        mNumpadBtnsList[2] = (Button) mQuizView.findViewById(R.id.twoBtn);
        mNumpadBtnsList[3] = (Button) mQuizView.findViewById(R.id.threeBtn);
        mNumpadBtnsList[4] = (Button) mQuizView.findViewById(R.id.fourBtn);
        mNumpadBtnsList[5] = (Button) mQuizView.findViewById(R.id.fiveBtn);
        mNumpadBtnsList[6] = (Button) mQuizView.findViewById(R.id.sixBtn);
        mNumpadBtnsList[7] = (Button) mQuizView.findViewById(R.id.sevenBtn);
        mNumpadBtnsList[8] = (Button) mQuizView.findViewById(R.id.eightBtn);
        mNumpadBtnsList[9] = (Button) mQuizView.findViewById(R.id.nineBtn);

        // Set listeners
        for (int i = 0; i < mNumpadBtnsList.length; i++) mNumpadBtnsList[i].setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.decimalInputBackSpaceBtn) {

            String userAnswer = mAnswerField.getText().toString();

            if (userAnswer.length() >= 1) {
                mAnswerField.setText("");
                mAnswerField.append(userAnswer.substring(0, userAnswer.length() - 1));
            }
        }
        else if (v.getTag().equals("numpad_input")) {
            Button buttonPressed = (Button)v;
            mAnswerField.append(buttonPressed.getText());
        }
    }

    public boolean checkAnswer() {
        int userAnswer = 0;
        try {
            userAnswer = Integer.valueOf(mAnswerField.getText().toString());
        } catch (NumberFormatException e) {
            return false;
        }

        int correctAnswer = mQuestionGenerator.convertBinaryToDecimal(mBinaryNum);
        return userAnswer == correctAnswer;
    }

}
