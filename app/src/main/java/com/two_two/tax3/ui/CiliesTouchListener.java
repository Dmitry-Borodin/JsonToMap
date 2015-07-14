package com.two_two.tax3.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Dmitry on 13.07.2015.
 */
public class CiliesTouchListener implements RecyclerView.OnItemTouchListener {
    private ClickListener mListener;
    private GestureDetector mGestureDetector;

    public interface ClickListener {
        void onItemClick(View view, int position);
    }

    public CiliesTouchListener(Context context, ClickListener mListener) {
        this.mListener = mListener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, rv.getChildPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }
}
