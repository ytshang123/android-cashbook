package com.syt.Notepad.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


//  使用FragmentPagerAdapter 实现多Fragment页面的横向滑动

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private FragmentManager fm;
    private List<Fragment> list;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.fm = fm;
        this.list = list;
    }

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {
        Fragment fg = list.get(arg0);
        return fg;
    }

    @Override
    public int getCount() {
        if (list != null)
            return list.size();
        return 0;
    }

}
