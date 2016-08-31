package com.example.dell.woof.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dell.woof.fragments.ExploreFragment;
import com.example.dell.woof.fragments.NearByFragment;

/**
 * Created by Dell on 09-08-2016.
 */
public class TabsFragmentPagerAdapter extends FragmentPagerAdapter {

    public TabsFragmentPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return new NearByFragment();
        if (position == 1)
            return new ExploreFragment();
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
