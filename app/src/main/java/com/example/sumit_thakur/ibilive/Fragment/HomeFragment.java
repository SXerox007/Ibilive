package com.example.sumit_thakur.ibilive.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sumit_thakur.ibilive.Constants.Constants;
import com.example.sumit_thakur.ibilive.R;


/**
 * Home Fragment
 */
public class HomeFragment extends Fragment implements Constants {
    private ViewPager viewPager;
    private EditText etSearchBar;
    private ImageView ivInfo;
    private TextView tvDiscover;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        init(view);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(final int position) {
                switch (position) {
                    case 0:

                        etSearchBar.setVisibility(View.VISIBLE);
                        ivInfo.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        etSearchBar.setVisibility(View.VISIBLE);
                        ivInfo.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        etSearchBar.setVisibility(View.VISIBLE);
                        ivInfo.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        etSearchBar.setVisibility(View.GONE);
                        ivInfo.setVisibility(View.GONE);
                        break;
                    case 4:
                        etSearchBar.setVisibility(View.GONE);
                        ivInfo.setVisibility(View.GONE);
                        break;
                    default:
                        etSearchBar.setVisibility(View.VISIBLE);
                        ivInfo.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });


        FragmentStatePagerAdapter fragmentStatePagerAdapter = new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public Fragment getItem(final int position) {
                if (position == 3) {
                    Log.e("error", "sucess0");
                    return new DiscoverFragment(REQUEST);
                } else if (position == 1) {
                    return new MapFragment();
                } else if (position == 4) {
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

    /**
     * initilization
     *
     * @param view view
     */

    private void init(final View view) {
        viewPager = (ViewPager) view.findViewById(R.id.vpSwipe);
        etSearchBar = (EditText) view.findViewById(R.id.tvInfo);
        ivInfo = (ImageView) view.findViewById(R.id.ivInfo);
        tvDiscover = (TextView) view.findViewById(R.id.tvDiscover);
    }

    @Override
    public void extra() {

    }
}
