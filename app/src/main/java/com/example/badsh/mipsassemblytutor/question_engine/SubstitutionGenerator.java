package com.example.badsh.mipsassemblytutor.question_engine;

/**
 * Created by Shabaz Badshah on 10/24/2017.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SubstitutionGenerator {

    private static Map<String, String[]> questionTemplates = new HashMap<String, String[]>();
    private static ArrayList<String> registers = new ArrayList<String> (Arrays.asList(new String[] {
            "$r1", "$r2", "$r3", "$r4", "$r5", "$r6", "$r7", "$r8", "$r9", "$r10", "$r11", "$r12", "$r13", "$r14", "$r15"
    }));

    private static Random randNumGenerator = new Random();
    private static int amountRegisters = registers.size();
    private static int maxValue = 100;

    public SubstitutionGenerator() {

		/*
		 * Question format:
		 *
		 * 		1. questions
		 * 		2. answer
		 * 		3. substitutions required
		 */

        questionTemplates.put("add", new String[] {

                "{register1}={value1} and {register2}={value2}. Add the two together and store in {register3}:"
                        + " add {register3}, {register1}, {register2}:"
                        + " registerMax valueMax",

                "What does the following line do? add {final_register}, {register1}, {register2}:"
                        + " adds the value from {register2} with {register1} and stores them in {final_register}:"
                        + " registerMax final_register",

                "{register}={value}, which command sets {register} to 0?:"
                        + " addi {register}, -{value}:"
                        + " register value",

                "{register}={value}, how do you add {new_value} to make {register} now hold the new value of {final_value}?: "
                        + "addi {register}, {final_value-new_value}: "
                        + "register value new_value final_value final_value-new_value"
        });

        questionTemplates.put("sub", new String [] {
                "{register}={value}, how do you subtract {new_value} to make it {final_value}?:"
                        + " sub {register}, {register}, -{final_value-new_value}:"
                        + " register new_value final_value final_value-new_value",

                "What does the following line do? sub {final_register}, {register1}, {register2}:"
                        + " subtracts the value from {register2} with {register1} and stores them in {final_register}:"
                        + " registerMax final_register",
        });

        questionTemplates.put("cmd", new String [] {
                "{register}={value}, how do you subtract {new_value} to make it {final_value}?:"
                        + " sub {register}, {register}, -{final_value-new_value}:"
                        + " register new_value final_value final_value-new_value",

                "{register}={value}, how do you add {new_value} to make {register} now hold the new value of {final_value}?: "
                        + "addi {register}, {final_value-new_value}: "
                        + "register value new_value final_value final_value-new_value",

                "{register}={value}, which command sets {register} to 0?:"
                        + " addi {register}, -{value}:"
                        + " register value",

                "{register1}={value1} and {register2}={value2}. Add the two together and store in {register3}:"
                        + " add {register3}, {register1}, {register2}:"
                        + " registerMax valueMax"
        });

        questionTemplates.put("type-cmd", new String [] {
                "{register}={value}, Use subtraction to make the value in {register}={final_value}?:"
                        + " sub {register}, {register}, -{final_value-new_value}:"
                        + " register new_value final_value final_value-new_value",

                "{register}={value}, Add {new_value} to {register}, to make {register}={final_value}?: "
                        + "addi {register}, {final_value-new_value}: "
                        + "register value new_value final_value final_value-new_value",

                "{register}={value}, use add to set {register} to 0:"
                        + " addi {register}, -{value}:"
                        + " register value",

                "{register1}={value1} and {register2}={value2}. Add the two together and store in {register3}:"
                        + " add {register3}, {register1}, {register2}:"
                        + " registerMax valueMax"
        });

    }

    public static String pickRandomQuestion(String category) {
        String[] listOfQuestions = questionTemplates.get(category); //Gets all the questions associated with a particular category
        return listOfQuestions[randNumGenerator.nextInt(listOfQuestions.length)];
    }

    public String getSubstitution(String substitution) {

        switch(substitution) {
            case "value":
                return String.valueOf(randNumGenerator.nextInt(maxValue));
            case "register":
                Collections.shuffle(registers); // Shuffling registers list to try to pick a unique register each time
                return registers.get(randNumGenerator.nextInt(amountRegisters));
        }

        return "ERROR";
    }

}
