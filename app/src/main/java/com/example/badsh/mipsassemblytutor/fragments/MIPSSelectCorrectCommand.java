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
import com.example.badsh.mipsassemblytutor.models.MIPSQuestion;
import com.example.badsh.mipsassemblytutor.question_engine.SubstitutionGenerator;

import java.util.ArrayList;
import java.util.Random;

public class MIPSSelectCorrectCommand extends Fragment implements View.OnClickListener {

    private TextView questionField;
    private MIPSQuestion generatedQuestion;
    private MIPSQuestion generatedQuestionForAnswer;
    private String questionTemplate;
    private SubstitutionGenerator substitutionGenerator = new SubstitutionGenerator();
    private static ArrayList<MIPSQuestion> questionsAlreadyGenerated = new ArrayList<>();
    private RadioGroup optionHolder;
    private static Random randNumGenerator = new Random();
    private static String[] commands = new String[] {"add", "sub"};

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

        Random rn = new Random();
        int range = 1 + 1;
        int randomNum =  rn.nextInt(range) + 0;
        //String currentCommand = commands[randomNum];
        String currentCommand = "cmd";
        questionTemplate = substitutionGenerator.pickRandomQuestion(currentCommand);
        generatedQuestion = new MIPSQuestion(questionTemplate, substitutionGenerator);
        questionField.setText(generatedQuestion.getQuestion());

        RadioButton optionBtn;

        int whereToPlaceAnswer = randNumGenerator.nextInt(amountToGenerate);

        for (int i = 0; i < amountToGenerate; i++) {

            if (optionHolder.getChildAt(i) instanceof RadioButton) {
                int optionButtonId = getResources().getIdentifier("option" + String.valueOf(i + 1), "id",
                        getActivity().getPackageName());
                optionBtn = (RadioButton) mQuizView.findViewById(optionButtonId);

                // Generate a new question if we don't have any we can use
                if (questionsAlreadyGenerated.isEmpty()) {
                    // Gets the question
                    questionTemplate = substitutionGenerator.pickRandomQuestion(currentCommand);
                    generatedQuestionForAnswer = new MIPSQuestion(questionTemplate, substitutionGenerator);
                    questionsAlreadyGenerated.add(generatedQuestionForAnswer);
                }

                // Pick a question from the list of questions you used to generate answers for the current question, no need to create new ones until you need them
                generatedQuestionForAnswer = questionsAlreadyGenerated.remove(questionsAlreadyGenerated.size() - 1);

                if (i == whereToPlaceAnswer) {
                    optionBtn.setText(generatedQuestion.getAnswer());
                } else {
                    optionBtn.setText(generatedQuestionForAnswer.getAnswer());
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
        return selectedAnswerRB.getText() == generatedQuestion.getAnswer();
    }


}
