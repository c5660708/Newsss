package com.skyia.tablelayouttest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skyia_ccf on 2017/12/28.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mTabTitles = new ArrayList<>();

    public MyPagerAdapter(FragmentManager fm){
        super(fm);
    }

    public void addFragment(Fragment fragment,String title){
        mFragments.add(fragment);
        mTabTitles.add(title);
    }

    public void removeFragment(Fragment fragment,String title){
        mFragments.remove(fragment);
        mTabTitles.remove(title);
    }
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }


    @Override
    public CharSequence getPageTitle(int position){
        return mTabTitles.get(position);
    }

}
