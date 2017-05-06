package com.example.sumit_thakur.ibilive;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sumit_thakur.ibilive.BaseActivity.BaseActivity;
import com.example.sumit_thakur.ibilive.Fragment.DiscoverFragment;
import com.example.sumit_thakur.ibilive.Fragment.HomeFragment;

/**
 * Main Activity
 */
public class DiscoverActivity extends BaseActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    private DrawerLayout drawerLayout;
    private ImageView ivSideMenu;
    private TextView tvTitle, tvDiscover, tvMap, tvPosts, tvRequests, tvMyNetwork;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        init();
        setData();
        ivSideMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new HomeFragment();
        fragmentTransaction.replace(R.id.flHomeActivity, fragment);
        fragmentTransaction.commit();

        tvDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                tvDiscover.setBackgroundResource(R.color.colorPrimaryDark);
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragment = new DiscoverFragment(DISCOVER);
                fragmentTransaction.replace(R.id.flHomeActivity, fragment);
                fragmentTransaction.commit();
            }
        });
    }

    /**
     * Initilization
     */
    private void init() {
        ivSideMenu = (ImageView) findViewById(R.id.ivDrawer);
        tvTitle = (TextView) findViewById(R.id.toolbar_top_title);
        drawerLayout = (DrawerLayout) findViewById(R.id.dlUserDrawer);
        tvDiscover = (TextView) findViewById(R.id.tvDiscover);
        tvMap = (TextView) findViewById(R.id.tvMap);
        tvPosts = (TextView) findViewById(R.id.tvMyPost);
        tvRequests = (TextView) findViewById(R.id.tvRequest);
        tvMyNetwork = (TextView) findViewById(R.id.tvMyNetwork);
    }

    /**
     * set Data
     */
    private void setData() {
        ivSideMenu.setImageResource(R.drawable.burger);
        tvTitle.setText("Drawable");
    }

}
