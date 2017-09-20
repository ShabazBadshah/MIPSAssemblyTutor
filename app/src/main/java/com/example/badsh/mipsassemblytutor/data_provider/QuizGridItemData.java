package com.example.badsh.mipsassemblytutor.data_provider;

import com.example.badsh.mipsassemblytutor.activities.AddingBinary;
import com.example.badsh.mipsassemblytutor.activities.ArithmeticShift;
import com.example.badsh.mipsassemblytutor.activities.BinaryToDecimal;
import com.example.badsh.mipsassemblytutor.activities.BitwiseOperators;
import com.example.badsh.mipsassemblytutor.activities.DecimalToBinary;
import com.example.badsh.mipsassemblytutor.activities.LogicalShift;
import com.example.badsh.mipsassemblytutor.activities.TwosComplement;
import com.example.badsh.mipsassemblytutor.models.QuizGridItem;

/**
 * Created by Shabaz Badshah on 9/19/2017.
 */

public class QuizGridItemData {

    private static String[] sNameOfQuizCategories = {
            "Adding Binary",
            "Convert Decimal to Binary",
            "Convert Binary to Decimal",
            "Twos Complement",
            "Logical Shift",
            "Arithmetic Shift",
            "Bitwise Operators"
    };

    // The activity that will be started when the corresponding grid item is clicked, 1-1 with sNameOfQuizCategories
    private static Class[] sQuizActivityClasses = {
            AddingBinary.class,
            DecimalToBinary.class,
            BinaryToDecimal.class,
            TwosComplement.class,
            LogicalShift.class,
            ArithmeticShift.class,
            BitwiseOperators.class
    };

    private static String[] sQuizPrimaryColorsInHex = {
            "#cc6055",
            "#d35519",
            "#f5af41",
            "#8e44ad",
            "#5cace2",
            "#27ae60",
            "#1fbc9c"
    };

    public static QuizGridItem[] initializeAndGetQuizzes() {
        int amountQuizzes = sNameOfQuizCategories.length;
        QuizGridItem[] quizGridItems = new QuizGridItem[amountQuizzes];

        for (int i = 0; i < amountQuizzes; i++) {
            QuizGridItem quizGridItemToBuild = new QuizGridItem(sQuizActivityClasses[i]);
            quizGridItemToBuild.setNameOfQuiz(sNameOfQuizCategories[i]);
            quizGridItemToBuild.setQuizPrimaryColor(sQuizPrimaryColorsInHex[i]);

            quizGridItems[i] = quizGridItemToBuild;
        }
        return quizGridItems;
    }


}
