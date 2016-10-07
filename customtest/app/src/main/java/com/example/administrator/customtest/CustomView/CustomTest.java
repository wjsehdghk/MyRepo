package com.example.administrator.customtest.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


/**
 * Created by Administrator on 2016-09-21.
 */

public class CustomTest extends AppCompatButton implements View.OnTouchListener {

    public int stuff = 0;
    int oldXvalue;
    int oldYvalue;




    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int width = ((ViewGroup) view.getParent()).getWidth() - view.getWidth();
        Log.v("넓이값", " " + width);
        //부모 뷰의 넓이에서 자신의 뷰 넓이를 뺀 넓이 값.
        int height = ((ViewGroup) view.getParent()).getHeight() - view.getHeight();
        Log.v("높이값", " " + height);
        //부모 뷰의 높이에서 자신의 뷰 높이를 뺀 높이 값.
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            oldXvalue =(int) motionEvent.getX();
            oldYvalue = (int)motionEvent.getY();
            Log.v(" Tag1 ", +oldXvalue + " " + oldYvalue);
            //터치한 현재 뷰의 x값과 y값을 oldXvalue,oldYvalue에 저장.
        } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
            view.setX(motionEvent.getRawX() -oldXvalue);
            //getRawX() -->  디바이스 전체 크기에서의 절대적인 위치를 가져온다.
            view.setY(motionEvent.getRawY() - (oldYvalue + view.getHeight()));
            //뷰를 터치하고 이동을 시작할때 뷰의 위치값을 셋팅.
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

            if (view.getX() > width && view.getY() > height) {
                view.setX(width);
                view.setY(height);

            } else if (view.getX() < 0 && view.getY() > height) {
                view.setX(0);
                view.setY(height);
            } else if (view.getX() > width && view.getY() < 0) {
                view.setX(width);
                view.setY(0);
            } else if (view.getX() < 0 && view.getY() < 0) {
                view.setX(0);
                view.setY(0);
            } else if (view.getX() < 0 || view.getX() > width) {
                if (view.getX() < 0) {
                    view.setX(0);
                    view.setY(motionEvent.getRawY() - oldYvalue - view.getHeight());
                } else {
                    view.setX(width);
                    view.setY(motionEvent.getRawY() - oldYvalue - view.getHeight());
                }
            } else if (view.getY() < 0 || view.getY() > height) {
                if (view.getY() < 0) {
                    view.setX(motionEvent.getRawX() - oldXvalue);
                    view.setY(0);
                } else {
                    view.setX(motionEvent.getRawX() - oldXvalue);
                    view.setY(height);
                }
            }
        }
        return true;
    }

    public CustomTest(Context context) {
        super(context);
        setOnTouchListener(this);
    }

    public CustomTest(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /*public interface OnViewTouchListener{
         boolean onViewTouch(View view, MotionEvent motionEvent);

    }

    private static OnViewTouchListener viewTouchListener;

    public void setOnViewTouchListener(OnViewTouchListener listener){

        this.viewTouchListener=listener;

    }
*/
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        Log.v("CustomView-density", "" + metrics.density);
        //모드
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = 0;
        switch (widthMode) {
            case MeasureSpec.UNSPECIFIED:
                widthSize = widthMeasureSpec;
                break;
            case MeasureSpec.AT_MOST:
                widthSize = 100;  //부모 뷰가 wrap_content일때 자식뷰의 크기를 정한다.
                break;
            case MeasureSpec.EXACTLY:
                widthSize = MeasureSpec.getSize(widthMeasureSpec);
                break;
        }

        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = 0;
        switch (heightMode) {
            case MeasureSpec.UNSPECIFIED:
                heightSize = heightMeasureSpec;
                break;
            case MeasureSpec.AT_MOST:
                heightSize = 100;  //부모 뷰가 wrap_content 일때, 자식뷰의 크기를 정한다.
                break;
            case MeasureSpec.EXACTLY: //부모 뷰가 match_parent일때, 크기를 부모만큼 지정한다.
                heightSize = MeasureSpec.getSize(widthMeasureSpec);
                break;
        }
        printMode("width mode : ", widthMode);
        printMode("height mode : ", heightMode);

        //측정된 폭과 높이
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        int height = View.MeasureSpec.getSize(heightMeasureSpec);
        Log.v("CustomView-onMeasure", "width : " + width + " height : " + height);
        //setMeasuredDimension(widthSize,heightSize); //자바 코드에서 직접 설정하지 않으면 여기서 명시적으로 지정한다.
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.v("CustomView-onLayout", "rect : (x, y, w, h) : " + left + " " + top + " " + (right - left) + " " + (bottom - top));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int left = getLeft();
        int top = getTop();
        int width = getWidth();
        int height = getHeight();
        int mwidth = getMeasuredWidth();
        int mheight = getMeasuredHeight();
        Log.v("CustomView-onDraw", "rect : (x, y, w, h, mw, mh) : " + left + " " + top + " " + width + " " + height + " " + mwidth + " " + mheight);
    }

    private void printMode(String tag, int mode) {
        switch (mode) {
            case MeasureSpec.AT_MOST:
                Log.v("CustomView-MeasureSpec", tag + " AT_MOST");
                break;
            case MeasureSpec.EXACTLY:
                Log.v("CustomView-MeasureSpec", tag + " EXACTLY");
                break;
            case MeasureSpec.UNSPECIFIED:
                Log.v("CustomView-MeasureSpec", tag + " UNSPECIFIED");
                break;
        }
    }

}
