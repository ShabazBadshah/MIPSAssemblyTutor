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
    private String mQuizPrimaryColorHex;
    private String mQuizDarkPrimaryColorHex;
    private int mQuizIconImageId;
    private String mAssociatedQuizActivity;

    public QuizGridItem(Context activityContext, String nameOfQuiz, String quizPrimaryColorHex,
                        String quizDarkPrimaryColorHex, int iconImageId, String activityToStart) {
        this.mActivityContext = activityContext;
        this.mNameOfQuiz = nameOfQuiz;
        this.mQuizPrimaryColorHex = quizPrimaryColorHex;
        this.mQuizDarkPrimaryColorHex = quizDarkPrimaryColorHex;
        this.mQuizIconImageId = iconImageId;
        this.mAssociatedQuizActivity = activityToStart;
    }

    public int getQuizImageId() { return this.mQuizIconImageId; }
    public String getPrimaryColor() {
       return this.mQuizPrimaryColorHex;
    }
    public String getDarkPrimaryColor() {
        return this.mQuizDarkPrimaryColorHex;
    }
    public String getQuizName() {
       return this.mNameOfQuiz;
    }
    public String getAssociatedQuizActivity() { return this.mAssociatedQuizActivity; }

    @Override
    public String toString() {
        return "QuizGridItem{" +
                "mActivityContext=" + mActivityContext +
                ", mNameOfQuiz='" + mNameOfQuiz + '\'' +
                ", mQuizPrimaryColorHex='" + mQuizPrimaryColorHex + '\'' +
                ", mQuizDarkPrimaryColorHex='" + mQuizDarkPrimaryColorHex + '\'' +
                ", mQuizIconImageId=" + mQuizIconImageId +
                ", mAssociatedQuizActivity='" + mAssociatedQuizActivity + '\'' +
                '}';
    }


    // Parcables boilerplate, allows easier and more performance efficient transfer and use of objects between
    // activitieas (http://www.vogella.com/tutorials/AndroidParcelable/article.html)

    protected QuizGridItem(Parcel in) {
        mNameOfQuiz = in.readString();
        mQuizPrimaryColorHex = in.readString();
        mQuizDarkPrimaryColorHex = in.readString();
        mQuizIconImageId = in.readInt();
        mAssociatedQuizActivity = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mNameOfQuiz);
        dest.writeString(mQuizPrimaryColorHex);
        dest.writeString(mQuizDarkPrimaryColorHex);
        dest.writeInt(mQuizIconImageId);
        dest.writeString(mAssociatedQuizActivity);
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
}
