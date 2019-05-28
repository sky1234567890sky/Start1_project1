package com.example.start1_project1.fram;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.start1_project1.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeBFragment extends Fragment {


    private View view;
    private RecyclerView mHomebLv;
    private SmartRefreshLayout mHomebSrl;

    public HomeBFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home_b, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mHomebLv = (RecyclerView) inflate.findViewById(R.id.homeb_lv);

        mHomebSrl = (SmartRefreshLayout) inflate.findViewById(R.id.homeb_srl);
    }
}
