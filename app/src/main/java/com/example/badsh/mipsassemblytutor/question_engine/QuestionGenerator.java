package com.example.badsh.mipsassemblytutor.question_engine;

import java.util.Random;

/**
 * Created by Shabaz Badshah on 9/26/2017.
 */

public class QuestionGenerator {
    private static Random mRandNumGenerator = new Random();

    public static String convertDecimalToBinary(int numToConvert) {
        return Integer.toBinaryString(numToConvert);
    }

    public static int convertBinaryToDecimal(String binaryNumString) {
        return Integer.parseInt(binaryNumString, 2);
    }

    public static int generateRandDecimalNum (int maxNumToGenerate) {
        //min value 0
        return mRandNumGenerator.nextInt(maxNumToGenerate + 1);
    }

}
