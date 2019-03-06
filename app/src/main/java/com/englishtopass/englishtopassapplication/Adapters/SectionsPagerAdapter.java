package com.englishtopass.englishtopassapplication.Adapters;

import android.util.Log;

import com.englishtopass.englishtopassapplication.TabbedFragments.ListeningTabbedFragment;
import com.englishtopass.englishtopassapplication.TabbedFragments.ReadingTabbedFragment;
import com.englishtopass.englishtopassapplication.TabbedFragments.UoETabbedFragment;
import com.englishtopass.englishtopassapplication.TabbedFragments.WritingTabbedFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


/**
 *
 * THIS IS THE ADAPTER WILL SET THE FRAGMENTS ON THE HOME PAGE
 * GET ITEM IS CALLED WHEN THE VIEW NEEDS A FRAGMENT, WILL ONLY CALL ONCE FOR EACH FRAGMENT
 * WILL ALSO CREATE THE FRAGMENT THATS NEXT TO SELECTED FRAGMENT
 *
 */


public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "SectionsPagerAdapter";

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position) {

            case 0:

                fragment = UoETabbedFragment.newInstance();
                break;

            case 1:

                fragment = ListeningTabbedFragment.newInstance();
                break;

            case 2:

                fragment = WritingTabbedFragment.newInstance();
                break;

            case 3:

                fragment = ReadingTabbedFragment.newInstance();
                break;

            default:
                Log.d(TAG, "getItem: add error");
        }

        return fragment;

    }


    @Override
    public int getCount() {
        return 4;
    }
}
