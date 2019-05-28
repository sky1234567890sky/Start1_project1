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
import com.example.start1_project1.fram.LikeAFragment;
import com.example.start1_project1.fram.LikeBFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class StartBFragment extends Fragment {


    private View view;
    private TabLayout tab;
    private FrameLayout startFl;
    private LikeAFragment likeAFragment;
    private LikeBFragment likeBFragment;

    public StartBFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_start_b, container, false);
        initView(inflate);
        intiViews();
        return inflate;
    }

    private void intiViews() {
        tab.addTab(tab.newTab().setIcon(R.mipmap.ic_launcher).setText("首页A"));
        tab.addTab(tab.newTab().setIcon(R.mipmap.ic_launcher).setText("收藏A"));

        likeAFragment = new LikeAFragment();
        likeBFragment = new LikeBFragment();

        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.likea_fl, likeAFragment);
        fragmentTransaction.commit();


        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        getChildFragmentManager().beginTransaction().replace(R.id.likea_fl, likeAFragment).commit();
                        break;
                    case 1:
                        getChildFragmentManager().beginTransaction().replace(R.id.likea_fl, likeBFragment).commit();
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
        tab = (TabLayout) inflate.findViewById(R.id.likea_tab);
        startFl = (FrameLayout) inflate.findViewById(R.id.likea_fl);
    }
}
