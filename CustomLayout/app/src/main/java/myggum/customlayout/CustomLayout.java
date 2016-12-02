package myggum.customlayout;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by Administrator on 2016-10-20.
 */


public class CustomLayout extends RelativeLayout implements View.OnTouchListener {
    float oldXvalue;
    float oldYvalue;
    AppCompatEditText editText;
    View v;
    Button button;
    Button button1;
    ViewGroup viewgroup;
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
        editText = (AppCompatEditText) findViewById(R.id.edit_query);
        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button2);
    }

    public void setAttr() {
        //editText.setText("자막을 입력해");
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                viewgroup = (ViewGroup) view.getParent();
                viewgroup.removeAllViews();
            }
        });
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "hhhhhhhh", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int width = ((ViewGroup) view.getParent()).getWidth() - view.getWidth();
        Log.v("넓이값", " " + width);
        //부모 뷰의 넓이에서 자신의 뷰 넓이를 뺀 넓이 값.
        int height = ((ViewGroup) view.getParent()).getHeight() - view.getHeight();
        Log.v("높이값", " " + height);
        //부모 뷰의 높이에서 자신의 뷰 높이를 뺀 높이 값.
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            oldXvalue = (int) motionEvent.getX();
            oldYvalue = (int) motionEvent.getY();
            Log.v(" Tag1 ", +oldXvalue + " " + oldYvalue);
            //터치한 현재 뷰의 x값과 y값을 oldXvalue,oldYvalue에 저장.
        } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
            view.setX(motionEvent.getRawX() - oldXvalue);
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
}
