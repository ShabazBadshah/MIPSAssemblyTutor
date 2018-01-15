package com.example.badsh.mipsassemblytutor.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.badsh.mipsassemblytutor.R;

/**
 * Created by badsh on 1/14/2018.
 */

public class QuizMenuItemDivider extends RecyclerView.ItemDecoration {
    private Drawable mDivider;

    private final int leftMarginDp = 64;

    public QuizMenuItemDivider(Context context) {
        mDivider = ContextCompat.getDrawable(context,R.drawable.grid_item_divider);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            int left = parent.getPaddingLeft() + leftMarginDp;
            int right = parent.getWidth() - parent.getPaddingRight();


            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}