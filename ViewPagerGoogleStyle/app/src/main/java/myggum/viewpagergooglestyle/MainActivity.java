package myggum.viewpagergooglestyle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    PagerAdapter adapter;
    FloatingActionButton Fab1;
    FloatingActionButton Fab2;
    FloatingActionButton Fab3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        adapter = new PagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        Fab1 = (FloatingActionButton) findViewById(R.id.FAB1);
        Fab2 = (FloatingActionButton) findViewById(R.id.FAB2);
        Fab3 = (FloatingActionButton) findViewById(R.id.FAB3);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //드래그 하는 동안 계속 호출.
            }
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    Fab1.show();
                    Fab2.hide();
                    Fab3.hide();
                } else if (position == 1) {
                    Fab1.hide();
                    Fab2.show();
                    Fab3.hide();
                } else if (position == 2) {
                    Fab1.hide();
                    Fab2.hide();
                    Fab3.show();
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                //Swipe(0-->1)
             /*   switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        Fab1.hide();
                        Fab2.hide();
                        Fab3.hide();
                        break;
                }*/
            }
        });
    }
}
