package com.example.badsh.mipsassemblytutor.data_provider;

import com.example.badsh.mipsassemblytutor.activities.QuizActivity;
import com.example.badsh.mipsassemblytutor.models.QuizGridItem;

/**
 * Created by Shabaz Badshah on 9/19/2017.
 */

public class QuizGridItemData {

    private static final Class ACTIVITY_TO_LAUNCH = QuizActivity.class;

    private static String[] sNameOfQuizCategories = {
            "Adding Binary",
            "Convert Decimal to Binary",
            "Convert Binary to Decimal",
            "Twos Complement",
            "Logical Shift",
            "Arithmetic Shift",
            "Bitwise Operators"
    };

    private static String[] sQuizPrimaryColorsInHex = {
            "#5399c6",
            "#27ae60",
            "#cc6055",
            "#8e44ad",
            "#e67e22",
            "#7f8c8d",
            "#eff3f3"
    };

    private static String[] sQuizDarkPrimaryColorsInHex = {
            "#5cace2",
            "#57d68d",
            "#ea6f63",
            "#af7ac4",
            "#f5af41",
            "#95a5a6",
            "#bdc3c7"
    };

    public static QuizGridItem[] initializeAndGetQuizzes() {
        int amountQuizzes = sNameOfQuizCategories.length;
        QuizGridItem[] quizGridItems = new QuizGridItem[amountQuizzes];

        for (int i = 0; i < amountQuizzes; i++) {
            QuizGridItem quizGridItemToBuild = new QuizGridItem(ACTIVITY_TO_LAUNCH);
            quizGridItemToBuild.setNameOfQuiz(sNameOfQuizCategories[i]);
            quizGridItemToBuild.setQuizPrimaryColor(sQuizPrimaryColorsInHex[i]);
            quizGridItemToBuild.setQuizDarkPrimaryColor(sQuizDarkPrimaryColorsInHex[i]);

            quizGridItems[i] = quizGridItemToBuild;
        }
        return quizGridItems;
    }


}
