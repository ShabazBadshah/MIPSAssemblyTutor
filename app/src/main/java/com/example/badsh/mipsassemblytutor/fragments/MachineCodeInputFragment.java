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
import com.example.badsh.mipsassemblytutor.data_provider.QuizDataProvider;
import com.example.badsh.mipsassemblytutor.models.MipsCommand;

public class MachineCodeInputFragment extends Fragment implements View.OnClickListener {

    private View mQuizView;

    private TextView mDecimalNumTv;
    private EditText mAnswerField;

    private MipsCommand generatedQuestion;

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
        mQuizView = inflater.inflate(R.layout.fragment_machine_code_input, container, false);

        initViews();
        initClickListeners();

        return mQuizView;
    }

    private void initViews() {
        mDecimalNumTv = (TextView) mQuizView.findViewById(R.id.userCommandTv);

        mAnswerField = (EditText) mQuizView.findViewById(R.id.answerField);
        mAnswerField.setInputType(InputType.TYPE_NULL);

        mZeroBtn = (Button) mQuizView.findViewById(R.id.zeroInputBtn);
        mOneBtn = (Button) mQuizView.findViewById(R.id.oneInputBtn);
        mBackspaceBtn = (Button) mQuizView.findViewById(R.id.backspaceBtn);

        generateAndSetNewQuestion();
    }

    public void generateAndSetNewQuestion() {
        generatedQuestion = QuizDataProvider.getRandomFunction().getAssociatedCommand();
        mDecimalNumTv.setText(generatedQuestion.getCommandGeneratedInstruction());
        mAnswerField.setText(null);

//        Toast.makeText(getContext(), generatedQuestion.getCommandMachineInstruction(), Toast.LENGTH_LONG).show();
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
        String userAnswer = mAnswerField.getText().toString().trim();
        return userAnswer.equals(generatedQuestion.getCommandMachineInstruction().trim());
    }

    public String getQuestionAnswer() {
        return generatedQuestion.getCommandMachineInstruction().trim();
    }

}
