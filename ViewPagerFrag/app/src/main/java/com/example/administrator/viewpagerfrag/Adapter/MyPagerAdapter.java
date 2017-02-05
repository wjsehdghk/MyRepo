package com.example.administrator.viewpagerfrag.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.viewpagerfrag.FragmentIn.FirstFragment;
import com.example.administrator.viewpagerfrag.FragmentIn.SecondFragment;

/**
 * Created by Administrator on 2016-08-12.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    public static int num=3;
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return FirstFragment.newInstance(0,"page # 1");
            case 1:
                return FirstFragment.newInstance(1,"page # 2");
            case 2:
                return SecondFragment.newInstance(2,"page # 3");
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return num;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return "page " + position;
    }
}
