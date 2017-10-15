package com.example.badsh.mipsassemblytutor.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.badsh.mipsassemblytutor.MainActivity;
import com.example.badsh.mipsassemblytutor.R;
import com.example.badsh.mipsassemblytutor.adapaters.RecyclerViewListAdapter;
import com.example.badsh.mipsassemblytutor.data_provider.UserStats;

public class UserStatsActivity extends AppCompatActivity {

    private static UserStats userStat;
    private View mToolbar;
    private RecyclerView mUserStatRv;
    private static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_stats);
        mContext = getApplicationContext();

        mUserStatRv = (RecyclerView) findViewById(R.id.user_stats_rv);
        mUserStatRv.setLayoutManager(new LinearLayoutManager(this));

        String userStatTitles[] = userStat.getAllKeys();
        String userStatValues[] = userStat.getAllValues();

        RecyclerViewListAdapter userStatsAdapter = new RecyclerViewListAdapter(getApplicationContext(),
                userStatTitles, userStatValues);

        mUserStatRv.setAdapter(userStatsAdapter);

        initToolbar();
    }

    private void initToolbar() {

        mToolbar = findViewById(R.id.user_profile_toolbar);

        Button userStatsBtn = (Button) mToolbar.findViewById(R.id.backBtn);
        userStatsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, MainActivity.class));
            }
        });
    }
}
