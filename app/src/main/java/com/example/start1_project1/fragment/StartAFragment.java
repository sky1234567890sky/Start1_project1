package com.example.start1_project1.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.start1_project1.R;
import com.example.start1_project1.fram.HomeAFragment;
import com.example.start1_project1.fram.LikeAFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartAFragment extends Fragment {


    private View view;
    private TabLayout mTab;
    private FrameLayout mFl;
    private HomeAFragment homeAFragment;
    private LikeAFragment likeAFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_start_a, container, false);
        initView(inflate);
        initViews();
        return inflate;
    }

    private void initViews() {
        mTab.addTab(mTab.newTab().setIcon(R.mipmap.ic_launcher).setText("首页A"));
        mTab.addTab(mTab.newTab().setIcon(R.mipmap.ic_launcher).setText("收藏A"));

        homeAFragment = new HomeAFragment();
        likeAFragment = new LikeAFragment();

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.start_fl, homeAFragment);
        fragmentTransaction.commit();

        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        getChildFragmentManager().beginTransaction().replace(R.id.start_fl, homeAFragment).commit();
                        break;
                    case 1:
                        getChildFragmentManager().beginTransaction().replace(R.id.start_fl, likeAFragment).commit();
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initView(View inflate) {
        mTab = (TabLayout) inflate.findViewById(R.id.tab);
        mFl = (FrameLayout) inflate.findViewById(R.id.start_fl);
    }
}
