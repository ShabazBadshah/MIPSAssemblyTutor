package com.example.badsh.mipsassemblytutor.data_provider;

import com.example.badsh.mipsassemblytutor.R;
import com.example.badsh.mipsassemblytutor.activities.QuizActivity;
import com.example.badsh.mipsassemblytutor.fragments.AddingBinaryFragment;
import com.example.badsh.mipsassemblytutor.fragments.BinaryInputFragment;
import com.example.badsh.mipsassemblytutor.fragments.DecimalInputFragment;
import com.example.badsh.mipsassemblytutor.fragments.MachineCodeInputFragment;
import com.example.badsh.mipsassemblytutor.fragments.MipsComputeCommandFragment;
import com.example.badsh.mipsassemblytutor.fragments.TypeMipsCommandFragment;
import com.example.badsh.mipsassemblytutor.models.QuizGridItem;

/**
 * Created by Shabaz Badshah on 9/19/2017.
 */

public class QuizGridItemDataProvider {

    private static final Class ACTIVITY_TO_LAUNCH = QuizActivity.class;

    private static String[] sNameOfQuizCategories = {
            "Convert Binary to Decimal",
            "Convert Decimal to Binary",
            "Adding Binary",
            "Compute the MIPS Command",
            "Convert MIPS to Machine Code",
            "Type the MIPS Command"
    };

    private static Class[] sAssociatedQuizActivity = {
            DecimalInputFragment.class,
            BinaryInputFragment.class,
            AddingBinaryFragment.class,
            MipsComputeCommandFragment.class,
            MachineCodeInputFragment.class,
            TypeMipsCommandFragment.class
    };

    private static String[] sQuizPrimaryColorsInHex = {
            "#5399c6",
            "#27ae60",
            "#cc6055",
            "#8e44ad",
            "#e67e22",
            "#7f8c8d"
    };

    private static String[] sQuizDarkPrimaryColorsInHex = {
            "#5cace2",
            "#57d68d",
            "#ea6f63",
            "#af7ac4",
            "#f5af41",
            "#95a5a6",
    };

    private static int[] sQuizImageIds = {
            R.drawable.ic_dectobin,
            R.drawable.ic_bintodec,
            R.drawable.ic_action_name,
            R.drawable.ic_compmips,
            R.drawable.ic_mipstobin,
            R.drawable.ic_bitop
    };

    public static QuizGridItem[] initializeAndGetQuizzes() {
        int amountQuizzes = sAssociatedQuizActivity.length;
        QuizGridItem[] quizGridItems = new QuizGridItem[amountQuizzes];

        for (int i = 0; i < amountQuizzes; i++) {
            QuizGridItem quizGridItemToBuild = new QuizGridItem(ACTIVITY_TO_LAUNCH);
            quizGridItemToBuild.setNameOfQuiz(sNameOfQuizCategories[i]);
            quizGridItemToBuild.setQuizPrimaryColor(sQuizPrimaryColorsInHex[i]);
            quizGridItemToBuild.setQuizDarkPrimaryColor(sQuizDarkPrimaryColorsInHex[i]);
            quizGridItemToBuild.setQuizImage(sQuizImageIds[i]);

            quizGridItemToBuild.setQuizActivityToStart(sAssociatedQuizActivity[i]);

            quizGridItems[i] = quizGridItemToBuild;
        }
        return quizGridItems;
    }


}
