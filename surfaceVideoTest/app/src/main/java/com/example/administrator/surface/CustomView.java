package com.example.administrator.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.VideoView;

/**
 * Created by Administrator on 2016-09-22.
 */
public class CustomView extends RelativeLayout implements View.OnTouchListener {
    int oldX;
    int oldY;
    View v;
    Button button;
   // VideoView videoView;

    public CustomView(Context context) {
        super(context);
        init();

    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void init(){
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v= inflater.inflate(R.layout.video,this,false);
        addView(v);
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        v.setFocusableInTouchMode(true);
        v.requestFocus();
        int width = ((ViewGroup) v.getParent()).getWidth() - v.getWidth();
        // Log.v("부모레이아웃에서 자식 뷰를 뺀 넓이값", " " + width);
        int xxxxx = ((ViewGroup) v.getParent()).getWidth();
        Log.v("부모레이아웃 넓이", " " + xxxxx);
        int yyyyy = v.getWidth();
        // Log.v("뷰의 넓이값", " " + yyyyy);
        //부모 뷰의 넓이에서 자신의 뷰 넓이를 뺀 넓이 값.
        int height = ((ViewGroup) v.getParent()).getHeight() - v.getHeight();
        //Log.v("부모레이아웃에서 자식뷰를 뺀 높이값", " " + height);
        //부모 뷰의 높이에서 자신의 뷰 높이를 뺀 높이 값.
        int xxxxx11 = ((ViewGroup) v.getParent()).getHeight();
        Log.v("부모레이아웃 높이", " " + xxxxx11);
        int yyyyy22 = v.getHeight();
        //  Log.v("뷰의 높이값", " " + yyyyy22);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // oldX = (int) motionEvent.getX();
            //oldY = (int) motionEvent.getY();
            oldX = (int) (v.getX() - event.getRawX());
            oldY = (int) (v.getY() - event.getRawY());
            Log.v(" Tag1 ", +oldX + " " + oldY);
            //터치한 현재 뷰의 x값과 y값을 oldX,oldY에 저장.
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            //view.setX(motionEvent.getRawX() - oldX - 100);
            // view.setY(motionEvent.getRawY() - (oldY + view.getHeight() + 500));
            v.setY(event.getRawY() + oldY);
            v.setX(event.getRawX() + oldX);
            float xx = event.getRawX();
            float yy = event.getRawY();
            int x1 = (int) v.getX();
            int y1 = (int) v.getY();
            Log.d("TAG!", +xx + "   " + yy);
            Log.d("뷰", +x1 + "   " + y1);
            //getRawX() -->  디바이스 전체 크기에서의 절대적인 위치를 가져온다.
            //뷰를 터치하고 이동을 시작할때 뷰의 위치값을 셋팅.
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (v.getX() > width && v.getY() > height) {
                v.setX(width);
                v.setY(height);
                float x1 = v.getX();
                float y1 = v.getY();
                Log.d("뷰 x, y값 ", "x값" + x1 + "    ,    y값 " + y1 + "");
                //1. 오케이
            } else if (v.getX() < 0 && v.getY() > height) {
                v.setX(0);
                v.setY(height);
                float x1 = v.getX();
                float y1 = v.getY();
                Log.d("뷰 x, y ", "x값 " + x1 + ",y값 " + y1 + "");
                //2. okay
            } else if (v.getX() > width && v.getY() < 0) {
                v.setX(width);
                v.setY(0);
                float x1 = v.getX();
                float y1 = v.getY();
                Log.d("뷰 x, y값 ", "x값" + x1 + "    ,    y값 " + y1 + "");
                //3. okay
            } else if (v.getX() < 0 && v.getY() < 0) {
                v.setX(0);
                v.setY(0);
                float x1 = v.getX();
                float y1 = v.getY();
                Log.d("뷰 x, y값 ", "x값" + x1 + "    ,    y값 " + y1 + "");
                //4. okay
            } else if (v.getX() < 0 || v.getX() > width) {
                if (v.getX() < 0) {
                    v.setX(0);
                    v.setY(event.getRawY() + oldY);
                    // view.setY(motionEvent.getRawY() - oldY - view.getHeight());
                    float x1 = v.getX();
                    float y1 = v.getY();
                    Log.d("뷰 x, y값 ", "x값" + x1 + "    ,    y값 " + y1 + "");
                } else {
                    v.setX(width);
                    v.setY(event.getRawY() + oldY);
                    //view.setY(motionEvent.getRawY() - oldY - view.getHeight());
                    float x1 = v.getX();
                    float y1 = v.getY();
                    Log.d("뷰 x, y값 ", "x값" + x1 + "    ,    y값 " + y1 + "");
                }
            } else if (v.getY() < 0 || v.getY() > height) {
                if (v.getY() < 0) {
                    //view.setX(motionEvent.getRawX() - oldX);
                    v.setX(event.getRawX() + oldX);
                    v.setY(0);
                    float x1 = v.getX();
                    float y1 = v.getY();
                    Log.d("뷰 x, y값 ", "x값" + x1 + "    ,    y값 " + y1 + "");
                } else {
                    v.setX(event.getRawX() + oldX);
                    v.setY(height);
                    float x1 = v.getX();
                    float y1 = v.getY();
                    Log.d("뷰 x, y값 ", "x값" + x1 + "    ,    y값 " + y1 + "");
                }
            }
        }
        return true;
    }
}
