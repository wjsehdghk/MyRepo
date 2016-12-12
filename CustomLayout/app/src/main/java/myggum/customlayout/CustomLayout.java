package myggum.customlayout;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-10-20.
 */

public class CustomLayout extends RelativeLayout implements View.OnTouchListener {
    int oldX;
    int oldY;
    TextView textView;
    View v;
    Button button;
    Button button1;
    int id;



    List<CustomLayout> customLayoutList;





    public CustomLayout(Context context) {
        super(context);
        init();
        setAttr();
    }

    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        setAttr();
    }

    public void init() {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.customlayout, this, false);
        addView(v);

        customLayoutList= new ArrayList<>();
        //부모 뷰그룹에 레이아웃 xml파일을 넣되, 바로 사용하지 않겟다는것이다.
        //addview를 통해 부모에 넣어야 한다.
        //inflater.inflate(R.layout.customlayout, this,true);
        // v=inflater.inflate(R.layout.customlayout, this);
        // 바로 지정가능. 디폴트는 true
        // 여기서 addview를 하면 에러가 난다.
        // 뷰객체를 받아오지 않아도 가능하다.
        // v = inflater.inflate(R.layout.customlayout, null);
        // addView(v);
        //부모 뷰그룹의 파라미터 정보가 업기때문에 경고가 발생한다.
        //addview를 해줘야 한다.
        //꽉찬 화면으로 나온다.
        //시스템이 직접 계산하여 크기를 정해준다.
        textView = (TextView) findViewById(R.id.edit_query);
        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button2);
    }
    public void setAttr() {
        button.setOnClickListener(new OnClickListener() {
            @Override

            public void onClick(View view) {

                removeAllViews();



            }
        });
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        int width = ((ViewGroup) view.getParent()).getWidth() - view.getWidth();
        // Log.v("부모레이아웃에서 자식 뷰를 뺀 넓이값", " " + width);
        int xxxxx = ((ViewGroup) view.getParent()).getWidth();
        Log.v("부모레이아웃 넓이", " " + xxxxx);
        int yyyyy = view.getWidth();
        // Log.v("뷰의 넓이값", " " + yyyyy);
        //부모 뷰의 넓이에서 자신의 뷰 넓이를 뺀 넓이 값.
        int height = ((ViewGroup) view.getParent()).getHeight() - view.getHeight();
        //Log.v("부모레이아웃에서 자식뷰를 뺀 높이값", " " + height);
        //부모 뷰의 높이에서 자신의 뷰 높이를 뺀 높이 값.
        int xxxxx11 = ((ViewGroup) view.getParent()).getHeight();
        Log.v("부모레이아웃 높이", " " + xxxxx11);
        int yyyyy22 = view.getHeight();
        //  Log.v("뷰의 높이값", " " + yyyyy22);
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            // oldX = (int) motionEvent.getX();
            //oldY = (int) motionEvent.getY();
            oldX = (int) (view.getX() - motionEvent.getRawX());
            oldY = (int) (view.getY() - motionEvent.getRawY());
            Log.v(" Tag1 ", +oldX + " " + oldY);
            //터치한 현재 뷰의 x값과 y값을 oldX,oldY에 저장.
        } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
            //view.setX(motionEvent.getRawX() - oldX - 100);
            // view.setY(motionEvent.getRawY() - (oldY + view.getHeight() + 500));
            view.setY(motionEvent.getRawY() + oldY);
            view.setX(motionEvent.getRawX() + oldX);
            float xx = motionEvent.getRawX();
            float yy = motionEvent.getRawY();
            int x1 = (int) view.getX();
            int y1 = (int) view.getY();
            Log.d("TAG!", +xx + "   " + yy);
            Log.d("뷰", +x1 + "   " + y1);
            //getRawX() -->  디바이스 전체 크기에서의 절대적인 위치를 가져온다.
            //뷰를 터치하고 이동을 시작할때 뷰의 위치값을 셋팅.
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            if (view.getX() > width && view.getY() > height) {
                view.setX(width);
                view.setY(height);
                float x1 = view.getX();
                float y1 = view.getY();
                Log.d("뷰 x, y값 ", "x값" + x1 + "    ,    y값 " + y1 + "");
                //1. 오케이
            } else if (view.getX() < 0 && view.getY() > height) {
                view.setX(0);
                view.setY(height);
                float x1 = view.getX();
                float y1 = view.getY();
                Log.d("뷰 x, y ", "x값 " + x1 + ",y값 " + y1 + "");
                //2. okay
            } else if (view.getX() > width && view.getY() < 0) {
                view.setX(width);
                view.setY(0);
                float x1 = view.getX();
                float y1 = view.getY();
                Log.d("뷰 x, y값 ", "x값" + x1 + "    ,    y값 " + y1 + "");
                //3. okay
            } else if (view.getX() < 0 && view.getY() < 0) {
                view.setX(0);
                view.setY(0);
                float x1 = view.getX();
                float y1 = view.getY();
                Log.d("뷰 x, y값 ", "x값" + x1 + "    ,    y값 " + y1 + "");
                //4. okay
            } else if (view.getX() < 0 || view.getX() > width) {
                if (view.getX() < 0) {
                    view.setX(0);
                    view.setY(motionEvent.getRawY() + oldY);
                    // view.setY(motionEvent.getRawY() - oldY - view.getHeight());
                    float x1 = view.getX();
                    float y1 = view.getY();
                    Log.d("뷰 x, y값 ", "x값" + x1 + "    ,    y값 " + y1 + "");
                } else {
                    view.setX(width);
                    view.setY(motionEvent.getRawY() + oldY);
                    //view.setY(motionEvent.getRawY() - oldY - view.getHeight());
                    float x1 = view.getX();
                    float y1 = view.getY();
                    Log.d("뷰 x, y값 ", "x값" + x1 + "    ,    y값 " + y1 + "");
                }
            } else if (view.getY() < 0 || view.getY() > height) {
                if (view.getY() < 0) {
                    //view.setX(motionEvent.getRawX() - oldX);
                    view.setX(motionEvent.getRawX() + oldX);
                    view.setY(0);
                    float x1 = view.getX();
                    float y1 = view.getY();
                    Log.d("뷰 x, y값 ", "x값" + x1 + "    ,    y값 " + y1 + "");
                } else {
                    view.setX(motionEvent.getRawX() + oldX);
                    view.setY(height);
                    float x1 = view.getX();
                    float y1 = view.getY();
                    Log.d("뷰 x, y값 ", "x값" + x1 + "    ,    y값 " + y1 + "");
                }
            }
        }
        return true;
    }
}
