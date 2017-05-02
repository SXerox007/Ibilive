package com.example.sumit_thakur.ibilive.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sumit_thakur.ibilive.Constants.Constants;
import com.example.sumit_thakur.ibilive.R;


/**
 * Home Fragment
 */
public class HomeFragment extends Fragment implements Constants {
    private ViewPager viewPager;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.vpSwipe);
        FragmentStatePagerAdapter fragmentStatePagerAdapter = new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public Fragment getItem(final int position) {
                if (position == 1) {
                    return new MapFragment();
                } else if (position == 4) {
                    Log.e("hello", "sucess0");
                    return new DiscoverFragment(MY_NETWORK);
                } else if (position == 2) {
                    return new DiscoverFragment(MY_POSTS);
                } else {
                    return new DiscoverFragment(DISCOVER);
                }
            }
        };
        viewPager.setAdapter(fragmentStatePagerAdapter);
        return view;
    }

    @Override
    public void extra() {

    }
}
