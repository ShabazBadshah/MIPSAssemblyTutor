package com.example.badsh.mipsassemblytutor.models;

/**
 * Created by Shabaz Badshah on 10/24/2017.
 */

import com.example.badsh.mipsassemblytutor.question_engine.SubstitutionGenerator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MIPSQuestion {

    private String question;
    private String answer;
    private SubstitutionGenerator substitutionGenerator;
    private ArrayList<String> substitutions = new ArrayList<String>();

    public MIPSQuestion(String questionTemplate, SubstitutionGenerator substitutionGenerator) {

        this.substitutionGenerator = substitutionGenerator;

        String[] questionSplit = questionTemplate.split(":");

        this.getAllSubstitutions(questionSplit[2]); // All the substitutions to be made

        this.question = questionSplit[0];
        this.answer = questionSplit[1].trim();

        this.parseAnswer();

    }

    private void getAllSubstitutions(String questionSubstitutions) {

        String[] substitutionsSplit = questionSubstitutions.split(" ");

        for (String substitution: substitutionsSplit) {
            if (!substitution.isEmpty()) {  // Ignore spaces
                substitutions.add(substitution);
            }
        }
    }


    private void parseAnswer() {

        for (int i = 0; i < substitutions.size(); i++) {

            String register;
            String value;
            int amountSubstitutionsToGenerate = 0;
            int currentAmount = 0;
            Matcher m;

            switch(substitutions.get(i)) {

                case "register":
                    register = substitutionGenerator.getSubstitution("register");

                    this.question = this.question.replace("{register}", register);
                    this.answer = this.answer.replace("{register}", register);

                case "value":
                    value = substitutionGenerator.getSubstitution("value");

                    this.question = this.question.replace("{value}", value);
                    this.answer = this.answer.replace("{value}", value);

                case "valueMax":
                    Pattern valueNumberPattern = Pattern.compile("value\\d+");
                    m = valueNumberPattern.matcher(this.question);

                    while (m.find()) {
                        currentAmount = Character.getNumericValue(m.group(0).charAt(m.group(0).length() - 1));
                        if (currentAmount >= amountSubstitutionsToGenerate) { amountSubstitutionsToGenerate= currentAmount; }
                    }
                    generateNSubstitutions("value", amountSubstitutionsToGenerate);

                case "registerMax":

                    Pattern registerNumberPattern = Pattern.compile("register\\d+");
                    m = registerNumberPattern.matcher(this.question);

                    while (m.find()) {
                        currentAmount = Character.getNumericValue(m.group(0).charAt(m.group(0).length() - 1));
                        if (currentAmount >= amountSubstitutionsToGenerate) { amountSubstitutionsToGenerate = currentAmount; }
                    }
                    generateNSubstitutions("register", amountSubstitutionsToGenerate);

                case "final_register":
                    register = substitutionGenerator.getSubstitution("register");
                    this.question = this.question.replace("{final_register}", register);
                    this.answer = this.answer.replace("{final_register}", register);

                case "final_value-new_value":
                    int final_value = Integer.valueOf(substitutionGenerator.getSubstitution("value"));
                    int new_value = Integer.valueOf(substitutionGenerator.getSubstitution("value"));

                    if (final_value < new_value) {
                        int temp = final_value;
                        final_value = new_value;
                        new_value = temp;
                    }

                    int addiAnswer = final_value - new_value;

                    this.question = this.question.replace("{final_value}", String.valueOf(final_value));
                    this.question = this.question.replace("{new_value}", String.valueOf(new_value));
                    this.answer = this.answer.replace("{final_value-new_value}", String.valueOf(addiAnswer));
            }
        }

    }

    private void generateNSubstitutions(String substitution, int amountSubstitutionsToGenerate) {

        String substitutionGenerated;

        for (int j = 1; j < amountSubstitutionsToGenerate + 1; j++) { //Value numbering starts at 1
            substitutionGenerated = substitutionGenerator.getSubstitution(substitution);
            String substitutionNumberConcat = substitution + String.valueOf(j);
            this.question = this.question.replace("{" + substitutionNumberConcat + "}", substitutionGenerated);
            this.answer = this.answer.replace("{" + substitutionNumberConcat + "}", substitutionGenerated);
        }
    }

    public String getQuestion() { return this.question; }
    public String getAnswer() { return this.answer; }

}
