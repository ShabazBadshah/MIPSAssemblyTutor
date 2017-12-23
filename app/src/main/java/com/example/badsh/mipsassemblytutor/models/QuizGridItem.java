package com.example.badsh.mipsassemblytutor.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shabaz Badshah on 9/16/2017.
 */

public class QuizGridItem implements Parcelable {

    private Context mActivityContext;

    private String mNameOfQuiz;
    private String mQuizPrimaryColor;
    private String mQuizDarkPrimaryColor;
    private int mQuizIconImageId;
    private String mAssociatedQuizActivity;

    public QuizGridItem(Context activityContext, String nameOfQuiz, String quizPrimaryColorHex,
                        String quizDarkPrimaryColorHex, int iconImageId, String activityToStart) {
        this.mActivityContext = activityContext;
        this.mNameOfQuiz = nameOfQuiz;
        this.mQuizPrimaryColor = quizPrimaryColorHex;
        this.mQuizDarkPrimaryColor = quizDarkPrimaryColorHex;
        this.mQuizIconImageId = iconImageId;
        this.mAssociatedQuizActivity = activityToStart;
    }

    public int getQuizImageId() { return this.mQuizIconImageId; }
    public String getPrimaryColor() {
       return this.mQuizPrimaryColor;
    }
    public String getDarkPrimaryColor() {
        return this.mQuizDarkPrimaryColor;
    }
    public String getQuizName() {
       return this.mNameOfQuiz;
    }
    public Context getActivityContext() { return this.mActivityContext; }
    public String getAssociatedQuizActivity() { return this.mAssociatedQuizActivity; }

    protected QuizGridItem(Parcel in) {
        mNameOfQuiz = in.readString();
        mQuizPrimaryColor = in.readString();
        mQuizDarkPrimaryColor = in.readString();
        mQuizIconImageId = in.readInt();
        mAssociatedQuizActivity = in.readString();
    }

    @Override
    public String toString() {
        return "QuizGridItem{" +
                "mActivityContext=" + mActivityContext +
                ", mNameOfQuiz='" + mNameOfQuiz + '\'' +
                ", mQuizPrimaryColor='" + mQuizPrimaryColor + '\'' +
                ", mQuizDarkPrimaryColor='" + mQuizDarkPrimaryColor + '\'' +
                ", mQuizIconImageId=" + mQuizIconImageId +
                ", mAssociatedQuizActivity='" + mAssociatedQuizActivity + '\'' +
                '}';
    }

    public static final Creator<QuizGridItem> CREATOR = new Creator<QuizGridItem>() {
        @Override
        public QuizGridItem createFromParcel(Parcel in) {
            return new QuizGridItem(in);
        }

        @Override
        public QuizGridItem[] newArray(int size) {
            return new QuizGridItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mNameOfQuiz);
        dest.writeString(mQuizPrimaryColor);
        dest.writeString(mQuizDarkPrimaryColor);
        dest.writeInt(mQuizIconImageId);
        dest.writeString(mAssociatedQuizActivity);
    }
}
