package com.example.badsh.mipsassemblytutor.data_provider;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Shabaz Badshah on 10/14/2017.
 */

public class UserStatsDataHandler {

    private static HashMap<String, String> sUserStats = new HashMap<>();
    private static Context sCurrActivityContext;

    public UserStatsDataHandler(Context context) {
        sCurrActivityContext = context;

        boolean loadedData = loadUserStats();

        if (loadedData == false) {
            sUserStats.put("Highest Accuracy", "0");
            sUserStats.put("Best Time (Seconds)", "0");
            sUserStats.put("Questions Answered", "0");
            sUserStats.put("Quizzes Finished", "0");
        }
    }

    public static void updateUserStat(String key, String value) {
        String statValue;
        if ((statValue = sUserStats.get(key)) != null) {

            if (key.equals("Questions Answered") || key.equals("Quizzes Finished")) {
                int oldValue = Integer.valueOf(statValue);
                int valueToBeAdded = Integer.valueOf(value);
                sUserStats.put(key, String.valueOf(oldValue + valueToBeAdded));
            }

            if (key.equals("Highest Accuracy")) {
                float oldAccuracy = Float.valueOf(statValue);
                float newAccuracy = Float.valueOf(value);
                if (newAccuracy > oldAccuracy) {
                    sUserStats.put(key, String.valueOf(newAccuracy));
                }
            }

            if (key.equals("Best Time (Seconds)")) {
                long previousTime = Long.valueOf(statValue);
                long newTime = Long.valueOf(value);
                if (newTime > previousTime) {
                    sUserStats.put(key, String.valueOf(newTime/1000));
                }
            }
            saveUserData();
        }

    }

    private static void saveUserData() {
        SharedPreferences keyValues = sCurrActivityContext.getSharedPreferences("SavedUserStats", Context.MODE_PRIVATE);
        if (keyValues == null) return;
        SharedPreferences.Editor keyValuesEditor = keyValues.edit();

        for (String key : sUserStats.keySet()) {
            keyValuesEditor.putString(key, sUserStats.get(key));
        }
        keyValuesEditor.commit();
    }

    public static boolean loadUserStats() {
        SharedPreferences pref = sCurrActivityContext.getSharedPreferences("SavedUserStats", Context.MODE_PRIVATE);
        if (pref == null) return false;
        if (sUserStats.size() == 0) return false;
        sUserStats = (HashMap<String, String>) pref.getAll();

        return true;
    }

    public static String[] getAllKeys() {
        String keys[] = new String[sUserStats.size()];
        Object allKeys[] = sUserStats.keySet().toArray();

        for (int i = 0; i < allKeys.length; i++) {
            keys[i] = allKeys[i].toString();
        }

        return keys;
    }

    public static String[] getAllValues() {
        String values[] = new String[sUserStats.size()];
        Object allValues[] = sUserStats.values().toArray();

        for (int i = 0; i < allValues.length; i++) {
            values[i] = allValues[i].toString();
        }

        return values;
    }
}
