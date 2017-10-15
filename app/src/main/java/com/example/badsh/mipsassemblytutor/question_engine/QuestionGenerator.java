package com.example.badsh.mipsassemblytutor.question_engine;

import java.util.Random;

/**
 * Created by Shabaz Badshah on 9/26/2017.
 */

public class QuestionGenerator {
    private static Random mRandNumGenerator = new Random();

    public String convertDecimalToBinary(int numToConvert) {
        return Integer.toBinaryString(numToConvert);
    }

    public int convertBinaryToDecimal(String binaryNumString) {
        return Integer.parseInt(binaryNumString, 2);
    }

    public String addBinaryNumbers(String binaryString1, String binaryString2) {

        int firstNum = Integer.parseInt(binaryString1, 2);
        int secondNum = Integer.parseInt(binaryString2, 2);

        int sum = firstNum + secondNum;
        return Integer.toBinaryString(sum);
    }

    public int generateRandDecimalNum (int maxNumToGenerate) {
        //min value 0
        return mRandNumGenerator.nextInt(maxNumToGenerate + 1);
    }

}
