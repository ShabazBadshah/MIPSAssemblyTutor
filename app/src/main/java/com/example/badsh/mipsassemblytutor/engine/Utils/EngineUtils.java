package com.example.badsh.mipsassemblytutor.engine.Utils;

import java.util.Random;

public class EngineUtils {

    public static Random randNumGen = new Random();

    private static final int MAX_NUM_TO_GENERATE = 32;

    public static String convertDecimalToBinary(int numToConvert) { return Integer.toBinaryString(numToConvert); }
    public static int convertBinaryToDecimal(String binaryNumString) { return Integer.parseInt(binaryNumString, 2); }

    public static int generateRandomDecimalNumber(boolean isPositive) {
        int generatedNum = randNumGen.nextInt(MAX_NUM_TO_GENERATE);

        if (isPositive) return generatedNum;
        else return -1 * generatedNum;
    }

    // Generates a random number between 0 and maxNumToGenerate
    public static int generateRandomDecimalNumber(boolean isPositive, int maxNumToGenerate) {
        int generatedNum = randNumGen.nextInt(maxNumToGenerate);

        if (isPositive) return generatedNum;
        else return -1 * generatedNum;
    }

    public static String addBinaryStrings(String binaryString1, String binaryString2) {

        int firstNum = Integer.parseInt(binaryString1, 2);
        int secondNum = Integer.parseInt(binaryString2, 2);

        int sum = firstNum + secondNum;
        return Integer.toBinaryString(sum);
    }

    public static String segmentBinaryStringNPieces(int amountToSplit, String machineInstruction) {
        String equalSplit[] = machineInstruction.split("(?<=\\G.{" + amountToSplit + "})");
        StringBuilder sb = new StringBuilder();

        for (String binaryString: equalSplit) {
            sb.append(binaryString).append(" ");
        }

        return sb.toString();
    }

    public static String leftPadBinaryString (int amountToPad, String binaryStringToPad) {
        return String.format("%0" + (amountToPad - binaryStringToPad.length()) + "d%s", 0,
                binaryStringToPad);
    }
}
