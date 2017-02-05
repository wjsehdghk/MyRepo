package myggum.viewpagergooglestyle;

import android.content.Context;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by Administrator on 2016-12-19.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Tab1", "Tab2", "Tab3" };
    private Context context;
    public PagerAdapter(FragmentManager fm, Context context){
        super(fm);
        this.context = context;
    }
    @Override
    public Fragment getItem(int position) {

        return PageFragment.newInstance(position);
    }
    @Override
    public CharSequence getPageTitle(int position) {

        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

}
