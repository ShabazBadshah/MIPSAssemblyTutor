package com.example.badsh.mipsassemblytutor.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.badsh.mipsassemblytutor.R;
import com.example.badsh.mipsassemblytutor.data_provider.QuizDataProvider;
import com.example.badsh.mipsassemblytutor.models.MipsCommand;
import com.example.badsh.mipsassemblytutor.models.Register;

import java.util.ArrayList;
import java.util.Collections;

public class TypeMipsCommandFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private TextView questionField;
    private MipsCommand generatedQuestion;
    private MipsCommand getGeneratedQuestionForAnswer;

    private View mQuizView;

    private TextView userAnswerTv;

    private ArrayList<ToggleButton> checkBoxes = new ArrayList<>(16);
    private LinearLayout parentLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mQuizView = inflater.inflate(R.layout.fragment_type_command_select, container, false);
        parentLayout = (LinearLayout) mQuizView.findViewById(R.id.checkBoxHolder);

        initViews();
        return mQuizView;
    }

    private void initViews() {

        questionField = (TextView) mQuizView.findViewById(R.id.questionPrompt);
        userAnswerTv = (TextView) mQuizView.findViewById(R.id.userCommandTv);
        userAnswerTv.setText(null);
        generateAndSetNewQuestion();
    }

    public void generateAndSetNewQuestion(int amountToGenerate) {
        userAnswerTv.setText(null);
        checkBoxes.clear();

        parentLayout.removeAllViews();

        generatedQuestion = QuizDataProvider.getRandomImmediateCommand().getAssociatedCommand();
        getGeneratedQuestionForAnswer = QuizDataProvider.getRandomImmediateCommand().getAssociatedCommand();
        String category = generatedQuestion.getCommandFunctionString();

        String [] answers = generatedQuestion.getCommandGeneratedInstruction().split(" ");
        String [] secondAnswers = getGeneratedQuestionForAnswer.getCommandGeneratedInstruction().split(" ");



        for(int i = 0; i < answers.length; i++){
            ToggleButton button = new ToggleButton(getContext());
            button.setTransformationMethod(null);

            try {
                Integer.parseInt(answers[i]);
                answers[i] = answers[i].trim();
            } catch (NumberFormatException e) {
                // ignore num if not a string
            }

            if (i != answers.length) {
                answers[i] = answers[i] + " ";
            }

            button.setText(answers[i]);
            button.setTextOn(answers[i]);
            button.setTextOff(answers[i]);
            button.setTag(Integer.valueOf(i));
            button.setOnCheckedChangeListener(this);
            checkBoxes.add(button);
        }

        Collections.shuffle(checkBoxes);

        for (ToggleButton checkBox: checkBoxes) {
            parentLayout.addView(checkBox);
        }

        ArrayList<Register> registers = generatedQuestion.getCommandRegisters();
        Register answerRegister = registers.get(registers.size() - 1);

        String question = "Use the following words to create a command that has the following answer where\n" + "Final register: " + answerRegister.getCompleteRegisterName() + "=" + answerRegister.getStoredValue() + " and\n" +
                "Immediate register: " + registers.get(0).getCompleteRegisterName() + "=" + registers.get(0).getStoredValue();
        questionField.setText(question);

        Toast.makeText(getContext(), generatedQuestion.getCommandGeneratedInstruction(), Toast.LENGTH_SHORT).show();
    }

    public void generateAndSetNewQuestion() {
        generateAndSetNewQuestion(2);
    }

    @Override
    public void onClick(View v) {}

    public boolean checkAnswer() {
        String userAnswer = userAnswerTv.getText().toString().toLowerCase().trim();
        return userAnswer.trim().toLowerCase().equals(generatedQuestion.getCommandGeneratedInstruction().toLowerCase().trim());
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked) {
            userAnswerTv.append(buttonView.getText());
        } else if (!isChecked) {
            String userAnswer = userAnswerTv.getText().toString();
            userAnswer = userAnswer.replace(buttonView.getText(), "");
            userAnswerTv.setText(userAnswer);
            Toast.makeText(this.getContext(), String.valueOf(userAnswerTv.getText().toString().length()), Toast.LENGTH_SHORT).show();

        }
    }

    public String getQuestionAnswer() {
        return generatedQuestion.getCommandGeneratedInstruction();
    }
}
