package com.example.start1_project1.fram;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.start1_project1.R;
import com.example.start1_project1.bean.ArtWanAndroidData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 */
public class LikeBFragment extends Fragment {


    private View view;
    private WebView mMyweb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        EventBus.getDefault().unregister(this);
        View inflate = inflater.inflate(R.layout.fragment_like_b, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mMyweb = (WebView) inflate.findViewById(R.id.myweb);
        mMyweb.setWebChromeClient(new WebChromeClient());
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getData(ArtWanAndroidData.DataBean.DatasBean r){
        if (r!=null){
            Log.i("tag", "url:=====> "+r.getLink());
            mMyweb.loadUrl(r.getLink());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
