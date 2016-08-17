package com.example.administrator.tabggum.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-08-17.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragementTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
    public void addFragment(Fragment fragment,String title){
        mFragmentList.add(fragment);
        mFragementTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragementTitleList.get(position);
    }
}
