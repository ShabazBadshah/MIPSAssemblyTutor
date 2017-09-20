package com.example.badsh.mipsassemblytutor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.badsh.mipsassemblytutor.adapaters.RecyclerViewAdapter;
import com.example.badsh.mipsassemblytutor.data_provider.QuizGridItemData;
import com.example.badsh.mipsassemblytutor.models.QuizGridItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.generic_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true); //An optimization for datasets that don't change often

        QuizGridItem[] listOfQuizCategories = QuizGridItemData.initializeAndGetQuizzes();
        for (QuizGridItem quizItem: listOfQuizCategories) { // Sets the context
            quizItem.setContext(this);
        }

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, listOfQuizCategories);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
