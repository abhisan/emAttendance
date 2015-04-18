package com.em.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.em.fragments.PlaceholderFragment;
import com.em.vo.Student;

import java.util.List;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    List<Student> data;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setDataProvider(List<Student> data) {
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        return PlaceholderFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position).getStudentName();
    }
}