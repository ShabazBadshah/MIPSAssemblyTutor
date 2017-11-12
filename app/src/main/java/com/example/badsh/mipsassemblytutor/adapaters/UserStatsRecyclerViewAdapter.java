package com.example.badsh.mipsassemblytutor.adapaters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.badsh.mipsassemblytutor.R;

import static com.example.badsh.mipsassemblytutor.R.id.statTitle;

/**
 * Created by Shabaz Badshah on 9/16/2017.
 */

public class UserStatsRecyclerViewAdapter extends RecyclerView.Adapter<UserStatsRecyclerViewAdapter.UserStatViewHolder> {

    private String[] mStatTitles;
    private String[] mStatValues;
    private LayoutInflater mLayoutInflator;

    public UserStatsRecyclerViewAdapter(Context parentContext, String[] statTitles, String[] statValues) {
        this.mLayoutInflator = LayoutInflater.from(parentContext);
        this.mStatTitles = statTitles;
        this.mStatValues = statValues;
    }

    // Inflates the View for a grid item when needed
    @Override
    public UserStatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View gridItemToInflate = mLayoutInflator.inflate(R.layout.user_stats_row, parent, false);
        UserStatViewHolder userStatViewHolder = new UserStatViewHolder(gridItemToInflate);
        return userStatViewHolder;
    }

    // Binds data to all the widgets and views the holder
    @Override
    public void onBindViewHolder(UserStatViewHolder userStatViewHolder, int position) {
        String statTitle = mStatTitles[position];
        String statValue = mStatValues[position] ;

        userStatViewHolder.statTitleTv.setText(statTitle);
        userStatViewHolder.statValueTv.setText(statValue);
    }

    @Override
    public int getItemCount() {
        return mStatTitles.length;
    }

    // Stores and recycles Views as needed
    public class UserStatViewHolder extends RecyclerView.ViewHolder {

        public TextView statTitleTv;
        public TextView statValueTv;

        public UserStatViewHolder(View itemView) {
            super(itemView);
            statTitleTv = (TextView) itemView.findViewById(statTitle);
            statValueTv = (TextView) itemView.findViewById(R.id.statValue);
        }

    }


}