package com.example.accountbook.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.accountbook.fragment.BillFragment;

public class BillPagerAdapter extends FragmentPagerAdapter {
    private final int mYear;

    public BillPagerAdapter(@NonNull FragmentManager fm, int year) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mYear=year;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        //2035-09
        int month = position+1;
        String monthStr = String.format("%02d",month);
        String yearMonth = mYear+"-"+monthStr;
        return BillFragment.newInstance(yearMonth);
    }

    @Override
    public int getCount() {
        //一共12个月
        return 12;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return (position+1)+"月份";
    }
}
