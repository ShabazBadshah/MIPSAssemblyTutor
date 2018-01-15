package com.example.badsh.mipsassemblytutor.data_provider;

import android.content.Context;

import com.example.badsh.mipsassemblytutor.R;
import com.example.badsh.mipsassemblytutor.models.QuizGridItem;

/**
 * Created by Shabaz Badshah on 9/19/2017.
 */

public class QuizGridItemDataProvider {

    private static final String[] QUIZ_MODULE_NAMES = {
            "Convert Binary to Decimal",
            "Convert Decimal to Binary",
            "Adding Binary",
            "Compute the MIPS Command",
            "Convert MIPS to Machine Code",
            "Type the MIPS Command"
    };

    private static final String[] QUIZ_MODULE_ACTIVITIES = {
            "DecimalInputFragment",
            "BinaryInputFragment",
            "AddingBinaryFragment",
            "MipsComputeCommandFragment",
            "MachineCodeInputFragment",
            "TypeMipsCommandFragment"
    };

    private static final String[] QUIZ_PRIMARY_COLORS_IN_HEX = {
            "#5399c6",
            "#27ae60",
            "#cc6055",
            "#8e44ad",
            "#e67e22",
            "#7f8c8d"
    };

    private static final String[] QUIZ_DARK_PRIMARY_COLORS_IN_HEX = {
            "#5cace2",
            "#57d68d",
            "#ea6f63",
            "#af7ac4",
            "#f5af41",
            "#95a5a6",
    };

    private static final int[] QUIZ_MODULE_GRID_ICONS = {
            R.drawable.icon_bin_to_dec,
            R.drawable.icon_dec_to_bin,
            R.drawable.icon_adding_bin,
            R.drawable.icon_compute_mips,
            R.drawable.icon_mips_to_machine,
            R.drawable.icon_type_mips
    };

    public static QuizGridItem[] initializeQuizData(Context mParentActivityContext) {
        int amountQuizzes = QUIZ_MODULE_ACTIVITIES.length;
        QuizGridItem[] quizGridItems = new QuizGridItem[amountQuizzes];

        for (int i = 0; i < amountQuizzes; i++) {
            QuizGridItem quizGridItemToBuild = new QuizGridItem(
                    mParentActivityContext,
                    QUIZ_MODULE_NAMES[i],
                    QUIZ_PRIMARY_COLORS_IN_HEX[i],
                    QUIZ_DARK_PRIMARY_COLORS_IN_HEX[i],
                    QUIZ_MODULE_GRID_ICONS[i],
                    QUIZ_MODULE_ACTIVITIES[i]);

            quizGridItems[i] = quizGridItemToBuild;
        }
        return quizGridItems;
    }


}
