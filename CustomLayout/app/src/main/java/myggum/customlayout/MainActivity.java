package myggum.customlayout;

import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
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
    HorizontalScrollView colorview;
    TextView textView3;
    Typeface typeface;
    View currentFocus;
    LinearLayout Sizelayout;
    TextView sizeText;
    SeekBar sizebar;
    Button button;
    Animation newsanimation;
    Animation newsanimation2;
    LinearLayout textVelocity;
    TextView textvelocity;
    SeekBar velocitybar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customLayoutList = new ArrayList<>();
        setContentView(R.layout.activity_main);
        newsanimation = AnimationUtils.loadAnimation(this, R.anim.news);
        newsanimation2 = AnimationUtils.loadAnimation(this, R.anim.news2);
        settingtextattr();
        adapter = new CaptionAdapter(getBaseContext(), attrs);
        fontview = (HorizontalScrollView) findViewById(R.id.font);
        colorview = (HorizontalScrollView) findViewById(R.id.bold);
        textView3 = (TextView) findViewById(R.id.textcolor);
        Sizelayout = (LinearLayout) findViewById(R.id.color);
        textVelocity = (LinearLayout) findViewById(R.id.textvelocity);
        sizeText = (TextView) findViewById(R.id.sizetext);
        sizebar = (SeekBar) findViewById(R.id.sizeseekbar);
        captionattrview = (RecyclerView) findViewById(R.id.captionattr);
        captionattrview.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false));
        captionattrview.setItemAnimator(new DefaultItemAnimator());
        captionattrview.setHasFixedSize(true);
        container = (FrameLayout) findViewById(R.id.container);
        button = (Button) findViewById(R.id.button);
        textvelocity = (TextView) findViewById(R.id.velocitytext);
        velocitybar = (SeekBar) findViewById(R.id.velocityseekbar);

        //시크바를 눌렀을때의 이벤트
        sizebar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentFocus = getCurrentFocus();
                if (currentFocus instanceof CustomLayout) {
                    seekBar.setEnabled(true);
                    if (progress < 13) {
                        progress = 12;
                        ((CustomLayout) currentFocus).textView.setTextSize(progress);
                        sizeText.setText("" + progress + "pt");
                        sizebar.setProgress(progress);
                    }
                    ((CustomLayout) currentFocus).textView.setTextSize(progress);
                    Log.d("sizetest", "" + ((CustomLayout) currentFocus).textView.getTextSize() / 2);
                    sizeText.setText("" + progress + "pt");
                } else {
                    sizeText.setText("" + progress + "pt");
                    seekBar.setEnabled(false);
                    Toast.makeText(getBaseContext(), "자막을 선택하세요", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        velocitybar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentFocus = getCurrentFocus();
                if (currentFocus instanceof CustomLayout) {
                    seekBar.setEnabled(true);
                    if (progress < 1) {
                        progress = 1;
                        newsanimation.setDuration((progress * 1000));
                        newsanimation2.setDuration((progress * 1000));
                        textvelocity.setText("자막 속도 " + progress);
                        velocitybar.setProgress(progress);
                    }
                    newsanimation.setDuration((progress * 1000));
                    newsanimation2.setDuration((progress * 1000));
                    textvelocity.setText("자막 속도 " + progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                currentFocus.startAnimation(newsanimation);
                newsanimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        currentFocus.startAnimation(newsanimation2);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
            }
        });
        captionattrview.setAdapter(adapter);
        adapter.setOnItemClickListener(new CaptionAdapter.OnitemClickListener() {
            @Override
            public void onItemClick(View itemview, int position) {
                index = position;
                if (index == 0) {
                    fontview.setVisibility(View.VISIBLE);
                    colorview.setVisibility(View.INVISIBLE);
                    Sizelayout.setVisibility(View.INVISIBLE);
                    textView3.setVisibility(View.INVISIBLE);
                    textVelocity.setVisibility(View.INVISIBLE);
                } else if (index == 1) {
                    fontview.setVisibility(View.INVISIBLE);
                    colorview.setVisibility(View.VISIBLE);
                    Sizelayout.setVisibility(View.INVISIBLE);
                    textView3.setVisibility(View.INVISIBLE);
                    textVelocity.setVisibility(View.INVISIBLE);
                } else if (index == 2) {
                    fontview.setVisibility(View.INVISIBLE);
                    colorview.setVisibility(View.INVISIBLE);
                    Sizelayout.setVisibility(View.VISIBLE);
                    textView3.setVisibility(View.INVISIBLE);
                    textVelocity.setVisibility(View.INVISIBLE);
                } else if (index == 3) {
                    fontview.setVisibility(View.INVISIBLE);
                    colorview.setVisibility(View.INVISIBLE);
                    Sizelayout.setVisibility(View.INVISIBLE);
                    textView3.setVisibility(View.VISIBLE);
                    textVelocity.setVisibility(View.INVISIBLE);
                } else if (index == 4) {
                    fontview.setVisibility(View.INVISIBLE);
                    colorview.setVisibility(View.INVISIBLE);
                    Sizelayout.setVisibility(View.INVISIBLE);
                    textView3.setVisibility(View.INVISIBLE);
                    textVelocity.setVisibility(View.VISIBLE);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                customLayout = new CustomLayout(getBaseContext());
                customLayout.setFocusableInTouchMode(true);
                //커스텀 자막 생성.
                customLayout.setId(num);
                //커스텀 자막에 ID번호 부여.
                customLayoutList.add(customLayout);
                //자막 리스트에 커스텀 자막 추가.
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(300, 150);
                customLayout.setOnTouchListener(customLayout);
                container.addView(customLayoutList.get(num), params);

                customLayoutList.get(num).textView.setText("자막을 입력해주세요. " + num);
                customLayoutList.get(num).requestFocus();
                //포커스 얻기
                sizebar.setProgress((int) customLayoutList.get(num).textView.getTextSize() / 2);
                sizeText.setText("" + (int) customLayoutList.get(num).textView.getTextSize() / 2 + "pt");
                customLayoutList.get(num).bringToFront();
                num++;
                //24
                // Log.d("focustest", " "+(int)customLayout.textView.getTextSize() );
                //48
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
        currentFocus = getCurrentFocus();
        //현재 포커스 받는 뷰 얻어온다.
        Log.d("focus", " " + currentFocus);
        if (currentFocus instanceof CustomLayout) {
            switch (id) {
                case R.id.barungothic:
                    typeface = Typeface.createFromAsset(getAssets(), "fonts/NanumBarunGothic.ttf");
                    ((CustomLayout) currentFocus).textView.setTypeface(typeface);
                    break;
                case R.id.gothic:
                    typeface = Typeface.createFromAsset(getAssets(), "fonts/NanumGothic.ttf");
                    ((CustomLayout) currentFocus).textView.setTypeface(typeface);
                    break;
                case R.id.myungjo:
                    typeface = Typeface.createFromAsset(getAssets(), "fonts/NanumMyeongjo.ttf");
                    ((CustomLayout) currentFocus).textView.setTypeface(typeface);
                    break;
                case R.id.brush:
                    typeface = Typeface.createFromAsset(getAssets(), "fonts/NanumPen.ttf");
                    ((CustomLayout) currentFocus).textView.setTypeface(typeface);
                    break;
                case R.id.pen:
                    typeface = Typeface.createFromAsset(getAssets(), "fonts/nanumbrush.ttf");
                    ((CustomLayout) currentFocus).textView.setTypeface(typeface);
                    break;
            }
        } else Toast.makeText(getBaseContext(), "자막을 선택하세요", Toast.LENGTH_SHORT).show();
    }

    public void onClick2(View v) {
        int id = v.getId();
        currentFocus = getCurrentFocus();
        Log.d("focus", " " + currentFocus);
        if (currentFocus instanceof CustomLayout) {
            switch (id) {
                case R.id.RED:
                    ((CustomLayout) currentFocus).textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.Red));
                    break;
                case R.id.Pink:
                    ((CustomLayout) currentFocus).textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.Pink));
                    break;
                case R.id.Purple:
                    ((CustomLayout) currentFocus).textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.Purple));
                    break;
                case R.id.Indigo:
                    ((CustomLayout) currentFocus).textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.Indigo));
                    break;
                case R.id.Blue:
                    ((CustomLayout) currentFocus).textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.Blue));
                    break;
                case R.id.Cyan:
                    ((CustomLayout) currentFocus).textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.Cyan));
                    break;
                case R.id.Teal:
                    ((CustomLayout) currentFocus).textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.Teal));
                    break;
                case R.id.Green:
                    ((CustomLayout) currentFocus).textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.Green));
                    break;
                case R.id.Lime:
                    ((CustomLayout) currentFocus).textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.Lime));
                    break;
                case R.id.Yellow:
                    ((CustomLayout) currentFocus).textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.Yellow));
                    break;
                case R.id.Amber:
                    ((CustomLayout) currentFocus).textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.Amber));
                    break;
                case R.id.Orange:
                    ((CustomLayout) currentFocus).textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.Orange));
                    break;
                case R.id.DeepOrange:
                    ((CustomLayout) currentFocus).textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.DeepOrange));
                    break;
                case R.id.Brown:
                    ((CustomLayout) currentFocus).textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.Brown));
                    break;
                case R.id.Grey:
                    ((CustomLayout) currentFocus).textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.Grey));
                    break;
                case R.id.Black:
                    ((CustomLayout) currentFocus).textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.Black));
                    break;
                case R.id.White:
                    ((CustomLayout) currentFocus).textView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.White));
                    break;
            }
        } else Toast.makeText(getBaseContext(), "자막을 선택하세요", Toast.LENGTH_SHORT).show();
    }
}

