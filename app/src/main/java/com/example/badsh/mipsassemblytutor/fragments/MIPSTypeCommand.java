package com.example.badsh.mipsassemblytutor.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.badsh.mipsassemblytutor.R;
import com.example.badsh.mipsassemblytutor.models.MIPSQuestion;
import com.example.badsh.mipsassemblytutor.question_engine.SubstitutionGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MIPSTypeCommand extends Fragment implements View.OnClickListener {

    private TextView questionField;
    private MIPSQuestion generatedQuestion;
    private MIPSQuestion generatedQuestionForAnswer;
    private String questionTemplate;
    private SubstitutionGenerator substitutionGenerator = new SubstitutionGenerator();
    private static ArrayList<MIPSQuestion> questionsAlreadyGenerated = new ArrayList<>();
    private static Random randNumGenerator = new Random();
    private static String[] commands = new String[] {"add", "sub"};

    private View mQuizView;

    private TextView userAnswerTv;

    private ArrayList<CheckBox> checkBoxes = new ArrayList<>(16);
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
        userAnswerTv.setText("");
        generateAndSetNewQuestion();
    }

    public void generateAndSetNewQuestion(int amountToGenerate) {
        userAnswerTv.setText("");
        checkBoxes.clear();

        String currentCommand = "type-cmd";
        questionTemplate = substitutionGenerator.pickRandomQuestion(currentCommand);
        generatedQuestion = new MIPSQuestion(questionTemplate, substitutionGenerator);
        String questionGenerated = generatedQuestion.getQuestion();

        String [] answers = generatedQuestion.getAnswer().split(" ");

        for(int i=0; i<answers.length; i++){
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setText(answers[i]);
            checkBox.setTag(Integer.valueOf(i));
            checkBox.setOnClickListener(this);
            checkBoxes.add(checkBox);
        }

        Collections.shuffle(checkBoxes);

        for (CheckBox checkBox: checkBoxes) {
            parentLayout.addView(checkBox);
        }

        questionField.setText(questionGenerated);
    }

    public void generateAndSetNewQuestion() {
        generateAndSetNewQuestion(2);
    }

    @Override
    public void onClick(View v) {
        int cbId = (Integer)v.getTag();
        if (checkBoxes.get(cbId).isChecked()) {
            userAnswerTv.append(" " + checkBoxes.get(cbId).getText());
        } else {
            String userAnswer = userAnswerTv.getText().toString();
            userAnswer = userAnswer.replace(checkBoxes.get(cbId).getText(), "");
            userAnswerTv.setText(userAnswer);
        }
    }

    public boolean checkAnswer() {
        parentLayout.removeAllViews();
        String userAnswer = userAnswerTv.getText().toString();
        return userAnswer.trim().equals(generatedQuestion.getAnswer());
    }


}
