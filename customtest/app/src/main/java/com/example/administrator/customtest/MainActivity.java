package com.example.administrator.customtest;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.customtest.Adapter.ViewPagerAdapter;
import com.example.administrator.customtest.CustomView.CustomTest;
import com.example.administrator.customtest.FragmentPage.OneFragment;
import com.example.administrator.customtest.FragmentPage.TwoFragment;

public class MainActivity extends AppCompatActivity {
    CustomTest button;
    RelativeLayout Container;
    float oldXvalue;
    float oldYvalue;
    TabLayout tabLayout;
    ViewPager viewPager;
    Context context;
    int[] tabicons={
            R.drawable.background,
            R.drawable.event
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Container = (RelativeLayout) findViewById(R.id.container);
        viewPager = (ViewPager)findViewById(R.id.viewpage);
        setupViewPager(viewPager);
        tabLayout = (TabLayout)findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
       // button = new CustomTest(this); // 디폴트 넓이와 높이값이 wrap_content로 지정.
        //내부컨텐츠의 값에 따라 넓이와 높이를 지정.
        //어떠한 뷰를 상속하는냐에 따라 Wrap_content의 크기는 달라진다.
        //따라서 부모뷰가 wrap_content 일때는 자식뷰(커스텀뷰)가 wrap_content일때 아예 크거나 안보일수 있으므로 명시적으로 지정해주는 것이 좋다.
       // RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(300, 300);
        //FrameLayout.LayoutParams.MATCH_PARENT,
        // FrameLayout.LayoutParams.WRAP_CONTENT);
        //button.setLayoutParams(params);
//        Container.addView(button, params);
       // button.setOnTouchListener(button);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Toast.makeText(getBaseContext(),"aaa: " + position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(getBaseContext(),"postion: " + position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //Toast.makeText(getBaseContext(),"bbb: " ,Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setupTabIcons(){
        tabLayout.getTabAt(0).setIcon(tabicons[0]);
        tabLayout.getTabAt(1).setIcon(tabicons[1]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "배경이미지");
        adapter.addFragment(new TwoFragment(), "자막");
        viewPager.setAdapter(adapter);
    }
}


