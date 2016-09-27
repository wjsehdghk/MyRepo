package com.example.administrator.customtest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2016-09-27.
 */
public class CustomText extends TextView implements View.OnTouchListener,ScaleGestureDetector.OnScaleGestureListener {
    ScaleGestureDetector mScaleDetector =
            new ScaleGestureDetector(getContext(), this);

    public CustomText(Context context) {
        super(context);
    }

    public CustomText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (mScaleDetector.onTouchEvent(motionEvent))
            return true;
        return super.onTouchEvent(motionEvent);
    }
}
