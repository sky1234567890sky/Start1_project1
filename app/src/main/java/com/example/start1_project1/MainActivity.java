package com.example.start1_project1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.start1_project1.fragment.StartAFragment;
import com.example.start1_project1.fragment.StartBFragment;

public class MainActivity extends AppCompatActivity {
    private Toolbar mTol;
    private FrameLayout mFl;
    private LinearLayout mLl;
    private NavigationView mNa;
    private DrawerLayout mDl;
    private StartAFragment startAFragment;
    private StartBFragment startBFragment;
    //苏克阳 H1810A
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        initFragment();
    }

    private void initFragment() {
        startAFragment = new StartAFragment();
        startBFragment = new StartBFragment();

        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl, startAFragment);
        transaction.commit();
//        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                switch (tab.getPosition()) {
//                    case R.id.astart:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fl, startAFragment);
//                        break;
//                    case R.id.blike:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fl, startBFragment);
//                        break;
//                }
//            }
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
        mNa.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setCheckable(true);
                switch (item.getItemId()) {
                    case R.id.astart:
                        mTol.setTitle("A");
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl, startAFragment).commit();
                        break;
                    case R.id.blike:
                        mTol.setTitle("B");
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl, startBFragment).commit();
                        break;
                }
                //关闭策划
                mDl.closeDrawer(Gravity.LEFT);
                return false;
            }
        });
    }
    private void initView() {
        mTol = (Toolbar) findViewById(R.id.tol);
        mFl = (FrameLayout) findViewById(R.id.fl);
        mLl = (LinearLayout) findViewById(R.id.ll);
        mNa = (NavigationView) findViewById(R.id.na);
        mDl = (DrawerLayout) findViewById(R.id.dl);

        mTol.setTitle("首页");
        mNa.setItemIconTintList(null);
        setSupportActionBar(mTol);
        //侧滑开关
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDl, mTol, R.string.srl_content_empty, R.string.srl_content_empty);
        mDl.addDrawerListener(toggle);
        toggle.syncState();

        //侧滑点击监听
        mDl.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                mLl.setX(mNa.getWidth()*slideOffset);
            }
            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }
}
