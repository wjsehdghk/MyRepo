package com.example.administrator.viewpagerfrag;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.administrator.viewpagerfrag.Adapter.MyPagerAdapter;




public class MainActivity extends AppCompatActivity {
    MyPagerAdapter myPagerAdapter;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        myPagerAdapter=new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);

    }
}
