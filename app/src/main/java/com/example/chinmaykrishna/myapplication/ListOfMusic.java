package com.example.chinmaykrishna.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Created by chinmaykrishna on 04/08/15.
 */
public class ListOfMusic extends FragmentActivity {
    private ViewPager viewPager;
    private MusicListFragmentStatePagerAdapter musicFragmentPager;
    private static  final int NUMBER_OF_FRAGMENTS=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        viewPager=(ViewPager) findViewById(R.id.view_pager);
        musicFragmentPager=new MusicListFragmentStatePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(musicFragmentPager);
    }

    private class MusicListFragmentStatePagerAdapter extends FragmentStatePagerAdapter{

        public MusicListFragmentStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch(position) {
                case 0:
                    return new Firstfragment();
                case 1:
                    return new Secondfragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUMBER_OF_FRAGMENTS;
        }
    }
}
