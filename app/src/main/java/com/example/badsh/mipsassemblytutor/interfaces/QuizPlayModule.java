package com.example.badsh.mipsassemblytutor.interfaces;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by badsh on 12/23/2017.
 */

public abstract class QuizPlayModule extends Fragment implements View.OnClickListener {

        protected abstract void initViews();
        public abstract void generateAndSetNewQuestion();
        protected abstract void initClickListeners();

        public abstract void onClick(View v);
        public abstract boolean checkAnswer();
        public abstract String getQuestionAnswer();

}
