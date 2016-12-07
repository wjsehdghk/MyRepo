package myggum.customlayout;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import myggum.customlayout.Adapter.CaptionAdapter;
import myggum.customlayout.Model.TextAttr;


public class MainActivity extends AppCompatActivity {

    FrameLayout container;
    List<CustomLayout> customLayoutList;
    int num = 0;
    CustomLayout customLayout;
    RecyclerView captionattrview;
    CaptionAdapter adapter;
    List<TextAttr> attrs = new ArrayList<>();
    int index;
    HorizontalScrollView fontview;
    TextView textView2;
    TextView textView3;
    Typeface typeface;
    View currentFocus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customLayoutList = new ArrayList<>();
        setContentView(R.layout.activity_main);
        currentFocus = getCurrentFocus();
        Log.d("focus"," " + currentFocus);
        settingtextattr();
        adapter = new CaptionAdapter(getBaseContext(), attrs);
        fontview = (HorizontalScrollView) findViewById(R.id.font);
        textView2 = (TextView) findViewById(R.id.bold);
        textView3 = (TextView) findViewById(R.id.textcolor);
        captionattrview = (RecyclerView) findViewById(R.id.captionattr);
        captionattrview.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false));
        captionattrview.setItemAnimator(new DefaultItemAnimator());
        captionattrview.setHasFixedSize(true);
        container = (FrameLayout) findViewById(R.id.container);
        Button button = (Button) findViewById(R.id.button);
        Button button1 = (Button) findViewById(R.id.button1);
        captionattrview.setAdapter(adapter);
        adapter.setOnItemClickListener(new CaptionAdapter.OnitemClickListener() {
            @Override
            public void onItemClick(View itemview, int position) {
                index = position;
                if (index == 0) {
                    fontview.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                    textView3.setVisibility(View.INVISIBLE);
                } else if (index == 1) {
                    fontview.setVisibility(View.INVISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView3.setVisibility(View.INVISIBLE);
                } else if (index == 2) {
                    fontview.setVisibility(View.INVISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                    textView3.setVisibility(View.VISIBLE);
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customLayout = new CustomLayout(getBaseContext());
                customLayout.setFocusableInTouchMode(true);
                //커스텀 자막 생성.
                customLayout.setId(num);
                //커스텀 자막에 ID번호 부여.
                customLayoutList.add(customLayout);
                //커스텀 자막 리스트에 추가.



                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(300, 200);
                customLayout.setOnTouchListener(customLayout);
                container.addView(customLayout, params);
                customLayout.textView.setText("자막을 입력해주세요." + num);
                num++;
                customLayout.requestFocus();
                currentFocus = getCurrentFocus();
                Log.d("focus"," " + currentFocus);
            }
        });



        currentFocus = getCurrentFocus();
        Log.d("focus"," " + currentFocus);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = getCurrentFocus();
                if (v instanceof TextView) {
                    ((TextView) v).setTextColor(Color.BLUE);
                } else if (v instanceof CustomLayout) {
                    ((CustomLayout) v).textView.setTextColor(Color.BLUE);
                }
            }
        });
    }

    public void settingtextattr() {
        int[] setting = new int[]{
                R.drawable.font,
                R.drawable.color,
                R.drawable.size,
                R.drawable.bold,
                R.drawable.velocity,
                R.drawable.effect
        };
        TextAttr textAttr = new TextAttr(setting[0], "폰트");
        attrs.add(textAttr);
        textAttr = new TextAttr(setting[1], "글자색");
        attrs.add(textAttr);
        textAttr = new TextAttr(setting[2], "글자크기");
        attrs.add(textAttr);
        textAttr = new TextAttr(setting[3], "굵기");
        attrs.add(textAttr);
        textAttr = new TextAttr(setting[4], "자막속도");
        attrs.add(textAttr);
        textAttr = new TextAttr(setting[5], "자막효과");
        attrs.add(textAttr);
    }

    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.barungothic:
                typeface = Typeface.createFromAsset(getAssets(), "fonts/NanumBarunGothic.ttf");
                Toast.makeText(getBaseContext(), "바른고딕", Toast.LENGTH_SHORT).show();
                if (currentFocus instanceof CustomLayout) {

                }
                break;
            case R.id.gothic:
                typeface = Typeface.createFromAsset(getAssets(), "fonts/NanumGothic.ttf");
               // customLayoutList.get(0).textView.setTypeface(typeface);
                Toast.makeText(getBaseContext(), "고딕", Toast.LENGTH_SHORT).show();
                break;
            case R.id.myungjo:
                typeface = Typeface.createFromAsset(getAssets(), "fonts/NanumMyeongjo.ttf");
                Toast.makeText(getBaseContext(), "나눔명조", Toast.LENGTH_SHORT).show();
               // customLayoutList.get(0).textView.setTypeface(typeface);
                break;
            case R.id.brush:
                typeface = Typeface.createFromAsset(getAssets(), "fonts/NanumPen.ttf");
              //  customLayoutList.get(0).textView.setTypeface(typeface);
                Toast.makeText(getBaseContext(), "손글씨펜", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pen:
                typeface = Typeface.createFromAsset(getAssets(), "fonts/nanumbrush.ttf");
                Toast.makeText(getBaseContext(), "손글씨 붓", Toast.LENGTH_SHORT).show();
               // customLayoutList.get(0).textView.setTypeface(typeface);
                break;
        }
    }
}

