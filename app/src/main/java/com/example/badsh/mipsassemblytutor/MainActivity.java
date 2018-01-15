package com.example.badsh.mipsassemblytutor;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.badsh.mipsassemblytutor.activities.QuizActivity;
import com.example.badsh.mipsassemblytutor.activities.UserStatsActivity;
import com.example.badsh.mipsassemblytutor.adapaters.QuizSelectionRvAdapter;
import com.example.badsh.mipsassemblytutor.data_provider.QuizMenuItemDataProvider;
import com.example.badsh.mipsassemblytutor.data_provider.UserStatsDataHandler;
import com.example.badsh.mipsassemblytutor.models.QuizGridItem;
import com.example.badsh.mipsassemblytutor.views.QuizMenuItemDivider;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final Class USER_STATS_ACTIVITY = UserStatsActivity.class;
    private static final Class QUIZ_PLAY_ACTIVITY = QuizActivity.class;
    private static final int MAIN_ACTIVITY_LAYOUT_ID = R.layout.activity_main;
 
    private static UserStatsDataHandler mUserStatsDataProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(MAIN_ACTIVITY_LAYOUT_ID);

        mUserStatsDataProvider = new UserStatsDataHandler(getApplicationContext());
        final QuizGridItem[] quizModulesData = QuizMenuItemDataProvider.initializeQuizData(getApplicationContext());

        initToolbar();
        initRecyclerView(quizModulesData);

        // Loads a random quiz module if the user clicks the random quiz button
        Button randomQuizBtn = (Button) findViewById(R.id.practiceAllQsBtn);
        randomQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random mRandNumGenerator = new Random();
                startQuizActivity(quizModulesData[mRandNumGenerator.nextInt(quizModulesData.length)]);
            }
        });
    }

    public static UserStatsDataHandler getUserStatsDataProvider() {
        return mUserStatsDataProvider;
    }

    private void initRecyclerView(QuizGridItem[] quizModulesData) {
        RecyclerView mQuizGridRv = (RecyclerView) findViewById(R.id.generic_recyclerview);

        mQuizGridRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mQuizGridRv.setHasFixedSize(true); //An optimization for datasets that don't change often

        QuizSelectionRvAdapter quizSelectionRvAdapter =
                new QuizSelectionRvAdapter(getApplicationContext(), quizModulesData);

        mQuizGridRv.setAdapter(quizSelectionRvAdapter);
        mQuizGridRv.addItemDecoration(new QuizMenuItemDivider(this));

    }

    private void initToolbar() {
        ConstraintLayout mToolbar = (ConstraintLayout) findViewById(R.id.main_menu_toolbar);
        Button userStatsBtn = (Button) mToolbar.findViewById(R.id.user_profile_btn);

        userStatsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getApplicationContext().startActivity(
                        new Intent(getApplicationContext(), USER_STATS_ACTIVITY));
            }
        });
    }

    public void startQuizActivity(QuizGridItem quizGridItem) {
        Intent intentToStartQuiz = new Intent(getApplicationContext(), QUIZ_PLAY_ACTIVITY);
        intentToStartQuiz.putExtra("quizMeta", quizGridItem);
        getApplicationContext().startActivity(intentToStartQuiz);
    }
}
