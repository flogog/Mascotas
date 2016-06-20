package com.diegog.mascotas.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by flogog on 6/19/16.
 */
public class MascotaPageAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mascotaFragments;

    public MascotaPageAdapter(FragmentManager fm, ArrayList<Fragment> fragments){
        super(fm);
        this.mascotaFragments = fragments;
    }
    @Override
    public Fragment getItem(int position) {
        return mascotaFragments.get(position);
    }

    @Override
    public int getCount() {
        return mascotaFragments.size();
    }
}
