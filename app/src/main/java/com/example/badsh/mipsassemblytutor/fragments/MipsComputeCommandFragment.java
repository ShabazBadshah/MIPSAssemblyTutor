package com.example.badsh.mipsassemblytutor.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.badsh.mipsassemblytutor.R;
import com.example.badsh.mipsassemblytutor.data_provider.QuizDataProvider;
import com.example.badsh.mipsassemblytutor.models.MipsCommand;

import java.util.Random;

public class MipsComputeCommandFragment extends Fragment implements View.OnClickListener {

    private TextView questionField;

    private MipsCommand generatedQuestion;

    private RadioGroup optionHolder;
    private View mQuizView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mQuizView = inflater.inflate(R.layout.fragment_radio_option_select, container, false);

        initViews();
        return mQuizView;
    }

    private void initViews() {

        questionField = (TextView) mQuizView.findViewById(R.id.questionPrompt);
        optionHolder = (RadioGroup) mQuizView.findViewById(R.id.radio_select_group);
        generateAndSetNewQuestion();
    }

    public void generateAndSetNewQuestion(int amountToGenerate) {
        optionHolder.clearCheck(); // Clears all checked buttons

        generatedQuestion = QuizDataProvider.getRandomFunction().getAssociatedCommand();
        String category = generatedQuestion.getCommandFunctionString();
        questionField.setText(generatedQuestion.getCommandQuestion());

        Random randNumGenerator = new Random();
        int whereToPlaceAnswer = randNumGenerator.nextInt(amountToGenerate);

        RadioButton optionBtn;

//        Toast.makeText(this.getContext(), generatedQuestion.getCommandAnswer(), Toast.LENGTH_SHORT).show();

        for (int i = 0; i < amountToGenerate; i++) {

            if (optionHolder.getChildAt(i) instanceof RadioButton) {
                int optionButtonId = getResources().getIdentifier("option" + String.valueOf(i + 1), "id",
                        getActivity().getPackageName());
                optionBtn = (RadioButton) mQuizView.findViewById(optionButtonId);

                if (i == whereToPlaceAnswer) {
                    optionBtn.setText(generatedQuestion.getCommandAnswer());
                } else {
                    optionBtn.setText(QuizDataProvider.getSpecificFunction(category).getCommandAnswer());
                }
            }
        }
    }

    public void generateAndSetNewQuestion() {
        generateAndSetNewQuestion(optionHolder.getChildCount());
    }

    @Override
    public void onClick(View v) {}

    public boolean checkAnswer() {
        int selectedAnswer = optionHolder.getCheckedRadioButtonId();
        // Change checkAnswer to return an int instead of a boolean
        if (selectedAnswer == -1) {
            return false;
        }
        RadioButton selectedAnswerRB = (RadioButton) mQuizView.findViewById(selectedAnswer);
        return selectedAnswerRB.getText().equals(generatedQuestion.getCommandAnswer());
    }

    public String getQuestionAnswer() {
        return String.valueOf(generatedQuestion.getCommandAnswer());
    }

}
