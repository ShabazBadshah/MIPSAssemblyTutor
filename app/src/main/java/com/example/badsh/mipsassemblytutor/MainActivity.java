package com.example.badsh.mipsassemblytutor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.badsh.mipsassemblytutor.activities.UserStatsActivity;
import com.example.badsh.mipsassemblytutor.adapaters.RecyclerViewAdapter;
import com.example.badsh.mipsassemblytutor.data_provider.QuizGridItemData;
import com.example.badsh.mipsassemblytutor.data_provider.UserStats;
import com.example.badsh.mipsassemblytutor.models.QuizGridItem;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private View mToolbar;
    private static UserStats userStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userStats = new UserStats(this);
        initToolbar();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.generic_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true); //An optimization for datasets that don't change often

        final QuizGridItem[] listOfQuizCategories = QuizGridItemData.initializeAndGetQuizzes();
        for (QuizGridItem quizItem: listOfQuizCategories) { // Sets the context
            quizItem.setContext(this);
        }

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, listOfQuizCategories);
        recyclerView.setAdapter(recyclerViewAdapter);

        Button randomQuizBtn = (Button) findViewById(R.id.practiceAllQsBtn);
        randomQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                int randomQuestion = rand.nextInt(3);
                Log.v("a", String.valueOf(randomQuestion));
                listOfQuizCategories[randomQuestion].startQuizActivity();
            }
        });
    }

    public static UserStats getUserStats() {
        return userStats;
    }

    private void initToolbar() {
        mToolbar = findViewById(R.id.main_menu_toolbar);

        Button userStatsBtn = (Button) mToolbar.findViewById(R.id.user_profile_btn);
        userStatsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getApplicationContext().startActivity(
                        new Intent(getApplicationContext(), UserStatsActivity.class));
            }
        });
    }
}
