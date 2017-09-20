package com.example.badsh.mipsassemblytutor.adapaters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.badsh.mipsassemblytutor.R;
import com.example.badsh.mipsassemblytutor.models.QuizGridItem;

/**
 * Created by Shabaz Badshah on 9/16/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.QuizCategoryViewHolder> {

    private QuizGridItem[] mListOfQuizCategories;
    private LayoutInflater mLayoutInflator;
    private Context mParentContext;

    public RecyclerViewAdapter(Context parentContext, QuizGridItem[] listOfQuizzesToAdd) {
        this.mLayoutInflator = LayoutInflater.from(parentContext);
        this.mListOfQuizCategories = listOfQuizzesToAdd;
        this.mParentContext = parentContext;
    }

    // Inflates the View for a grid item when needed
    @Override
    public QuizCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View gridItemToInflate = mLayoutInflator.inflate(R.layout.homescreen_griditem, parent, false);
        QuizCategoryViewHolder quizCategoryViewHolder = new QuizCategoryViewHolder(gridItemToInflate);
        return quizCategoryViewHolder;
    }

    // Binds data to all the widgets and views the holder
    @Override
    public void onBindViewHolder(QuizCategoryViewHolder quizViewHolder, int position) {
        QuizGridItem currentQuizGridItem = mListOfQuizCategories[position];

        String mNameOfQuiz = currentQuizGridItem.getNameOfQuiz();
        quizViewHolder.categoryTextView.setText(mNameOfQuiz);

        int quizPrimaryColor = currentQuizGridItem.getPrimaryColor();
        quizViewHolder.categoryTextView.setBackgroundColor(quizPrimaryColor);

        quizViewHolder.backgroundImageView.setBackgroundColor(quizPrimaryColor);
    }

    @Override
    public int getItemCount() {
        return mListOfQuizCategories.length;
    }

    // Stores and recycles Views as needed
    public class QuizCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView categoryTextView;
        public ImageView backgroundImageView;

        public QuizCategoryViewHolder(View itemView) {
            super(itemView);
            categoryTextView = (TextView) itemView.findViewById(R.id.homescreen_griditem_category_title);
            backgroundImageView = (ImageView) itemView.findViewById(R.id.homescreen_griditem_category_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            QuizGridItem gridQuizItemClickedOn = mListOfQuizCategories[getAdapterPosition()];
            gridQuizItemClickedOn.onClick(mParentContext);
        }
    }


}