    package com.example.badsh.mipsassemblytutor.activities;

    import android.content.Context;
    import android.content.Intent;
    import android.graphics.Color;
    import android.os.Bundle;
    import android.support.v7.app.AppCompatActivity;
    import android.view.View;
    import android.widget.Button;
    import android.widget.TextView;

    import com.example.badsh.mipsassemblytutor.MainActivity;
    import com.example.badsh.mipsassemblytutor.R;

    public class QuizCompleteActivity extends AppCompatActivity implements View.OnClickListener {

        String quizPrimaryColor;
        String quizDarkPrimaryColor;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_quiz_complete);
            initClickListeners();
            initUserScoreAndTime();
        }

        public void onBackPressed() {
            goToHomeActivity();
        }

        private void initUserScoreAndTime() {
            String userScore = getIntent().getExtras().getString("userScore");
            String userTime = getIntent().getExtras().getString("userTime");

            TextView userScoreTV = (TextView) findViewById(R.id.userQuizScore);
            TextView userTimeTV = (TextView) findViewById(R.id.userQuizTime);

            userScoreTV.setText(userScore);
            userScoreTV.setTextColor(Color.parseColor(quizPrimaryColor));

            long totalTimeInSeconds = Integer.valueOf(userTime);
            long hours = totalTimeInSeconds / 3600;
            long minutes = (totalTimeInSeconds % 3600) / 60;
            long seconds = totalTimeInSeconds % 60;

            String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);

            userTimeTV.setText(timeString);
            userTimeTV.setTextColor(Color.parseColor(quizPrimaryColor));
        }

        private void initClickListeners() {
            Button goHomeBtn = (Button) findViewById(R.id.goHomeBtn);

            quizPrimaryColor = getIntent().getExtras().getString("mQuizPrimaryColor");
            quizDarkPrimaryColor = getIntent().getExtras().getString("mQuizDarkPrimaryColor");

            goHomeBtn.setBackgroundColor(Color.parseColor(quizPrimaryColor));

            goHomeBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.goHomeBtn) goToHomeActivity();
        }

        private void goToHomeActivity() {
            Context goToHomeContext = getApplicationContext();
            Intent goToHomeIntent = new Intent(goToHomeContext, MainActivity.class);
            goToHomeContext.startActivity(goToHomeIntent);
            finish();
        }
    }
