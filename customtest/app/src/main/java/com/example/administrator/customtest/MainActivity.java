package com.example.administrator.customtest;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CustomTest button;
    FrameLayout Container;
    float oldXvalue;
    float oldYvalue;
    CustomText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Container = (FrameLayout) findViewById(R.id.container);
        text = new CustomText(this);

        //button = new CustomTest(this); // 디폴트 넓이와 높이값이 wrap_content로 지정.
        //내부컨텐츠의 값에 따라 넓이와 높이를 지정.
        //어떠한 뷰를 상속하는냐에 따라 Wrap_content의 크기는 달라진다.
        //따라서 부모뷰가 wrap_content 일때는 자식뷰(커스텀뷰)가 wrap_content일때 아예 크거나 안보일수 있으므로 명시적으로 지정해주는 것이 좋다.
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(300, 300);
        //FrameLayout.LayoutParams.MATCH_PARENT,
        // FrameLayout.LayoutParams.WRAP_CONTENT);
        //button.setLayoutParams(params);
        text.setBackgroundColor(Color.RED);
        Container.addView(text, params);
        //button.setOnTouchListener(button);


      /*  button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int width = ((ViewGroup) view.getParent()).getWidth() - view.getWidth();
                Log.v("넓이값", " " + width);
                //부모 뷰의 넓이에서 자신의 뷰 넓이를 뺀 넓이 값.
                int height = ((ViewGroup) view.getParent()).getHeight() - view.getHeight();
                Log.v("높이값", " " + height);
                //부모 뷰의 높이에서 자신의 뷰 높이를 뺀 높이 값.
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    oldXvalue = motionEvent.getX();
                    oldYvalue = motionEvent.getY();
                    Log.v(" Tag1 ", +oldXvalue + " " + oldYvalue);
                    //터치한 현재 뷰의 x값과 y값을 oldXvalue,oldYvalue에 저장.
                } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    view.setX(motionEvent.getRawX() - oldXvalue - 100);
                    //getRawX() -->  디바이스 전체 크기에서의 절대적인 위치를 가져온다.
                    view.setY(motionEvent.getRawY() - (oldYvalue + view.getHeight()) + 100);
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
        });
    }*/
    }
}

