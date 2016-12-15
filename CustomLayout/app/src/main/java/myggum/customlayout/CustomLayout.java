package myggum.customlayout;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.icu.text.LocaleDisplayNames;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
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
    private float this_orgX = -1, this_orgY = -1;
    private float scale_orgX = -1, scale_orgY = -1;
    private double scale_orgWidth = -1, scale_orgHeight = -1;
    private double centerX, centerY;
    TextView textView;
    private final static int SELF_SIZE_DP = 100;
    View v;
    Button button;
    Button button1;
    int number;
    List<CustomLayout> customLayoutList;
    private GestureDetector mGesture;
    private OnDoubleClickListener onDoubleClickListener;
    interface OnDoubleClickListener {
        void onDoubleClick(View view);
    }
    public void setOnDoubleClickListener(OnDoubleClickListener onDoubleClickListener) {
        this.onDoubleClickListener = onDoubleClickListener;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public CustomLayout(Context context) {
        super(context);
        init();
        mGesture = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if (onDoubleClickListener != null) {
                    onDoubleClickListener.onDoubleClick(CustomLayout.this);
                }
                return true;
            }
        });
    }
    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        mGesture = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if (onDoubleClickListener != null) {
                    onDoubleClickListener.onDoubleClick(CustomLayout.this);
                }
                return true;
            }
        });
    }
    public void init() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.customlayout, this, false);
        addView(v);
        customLayoutList = new ArrayList<>();
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
        button1.setTag("scale");
        button1.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        this_orgX = CustomLayout.this.getX();
                        this_orgY = CustomLayout.this.getY();
                        //커스텀 뷰의 현재 x,y값
                        Log.d("getXY : ", " " + this_orgX + " " + this_orgY);
                        scale_orgX = event.getRawX();
                        scale_orgY = event.getRawY();
                        // 터치 한 곳의 절대 x , y 값
                        Log.d("getRawXY : ", " " + scale_orgX + " " + scale_orgY);
                        scale_orgWidth = CustomLayout.this.getLayoutParams().width;
                        scale_orgHeight = CustomLayout.this.getLayoutParams().height;
                        //커스텀 뷰의 넓이, 높이 값.
                        Log.d("customviewwidthheight:", " " + scale_orgWidth + " " + scale_orgHeight);
                        centerX = CustomLayout.this.getX() + ((View) CustomLayout.this.getParent()).getX() +
                                (float) CustomLayout.this.getWidth() / 2;
                        Log.d("centerX :", " " + centerX);
                        Log.d("getparentx", " " + ((View) CustomLayout.this.getParent()).getX());
                        int result = 0;
                        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
                        Log.d("resourceId", " " + resourceId);
                        if (resourceId > 0) {
                            result = getResources().getDimensionPixelSize(resourceId);
                        }
                        double statusBarHeight = result;
                        Log.d("statusBarheight", " " + statusBarHeight);
                        //50
                        centerY = CustomLayout.this.getY() + ((View) CustomLayout.this.getParent()).getY() +
                                statusBarHeight + (float) CustomLayout.this.getHeight() / 2;
                        Log.d("centerY : ", " " + centerY);
                        Log.d("getparenty", " " + ((View) CustomLayout.this.getParent()).getY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        double length1 = getLength(centerX, centerY, scale_orgX, scale_orgY);
                        Log.d("length1", " " + length1);
                        double length2 = getLength(centerX, centerY, event.getRawX(), event.getRawY());
                        Log.d("length2", " " + length2);
                        int size = convertDpToPixel(SELF_SIZE_DP, getContext());
                        Log.d("size", " " + size);
                        //200
                        View customparentView = (View)CustomLayout.this.getParent();
                        int parentwid=customparentView.getWidth();
                        if (length2 > length1) {
                            //scale up
                            double offsetX = Math.abs(event.getRawX() - scale_orgX);
                            int numtest2 = (int) offsetX;
                            double offsetY = Math.abs(event.getRawY() - scale_orgY);
                            int numtest1 = (int) offsetY;
                            Log.d("numtest", " " + numtest1 + " " + numtest2);
                            double offset = Math.max(offsetX, offsetY);
                            Log.d("offset", " " + offset);
                            offset = Math.round(offset);
                            Log.d("offset2", " " + offset);
                            CustomLayout.this.getLayoutParams().width += offset;
                            if(CustomLayout.this.getLayoutParams().width > parentwid){
                                CustomLayout.this.getLayoutParams().width = parentwid;
                            }
                            CustomLayout.this.getLayoutParams().height += offset;
                            if(CustomLayout.this.getLayoutParams().height > 300){
                                CustomLayout.this.getLayoutParams().height = 300;
                            }
                            onScaling(true);
                        }
                        //scale down
                        else if (length2 < length1
                                && CustomLayout.this.getLayoutParams().width > size / 2
                                && CustomLayout.this.getLayoutParams().height > size / 2)
                        {
                            double offsetX = Math.abs(event.getRawX() - scale_orgX);
                            double offsetY = Math.abs(event.getRawY() - scale_orgY);
                            double offset = Math.max(offsetX, offsetY);
                            offset = Math.round(offset);
                            CustomLayout.this.getLayoutParams().width -= offset;
                            CustomLayout.this.getLayoutParams().height -= offset;
                            onScaling(false);
                        }
                        scale_orgX = event.getRawX();
                        scale_orgY = event.getRawY();
                        postInvalidate();
                        requestLayout();
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    protected void onScaling(boolean scaleUp) {
    }

    private static int convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int) px;
    }

    private double getLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        mGesture.onTouchEvent(motionEvent);
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
