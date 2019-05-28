package com.example.start1_project1.fram;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.start1_project1.R;
import com.example.start1_project1.adapter.LikeAAdapter;
import com.example.start1_project1.bean.ArtWanAndroidData;
import com.example.start1_project1.server.MyServer;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class LikeAFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener {


    private View view;
    private RecyclerView mHomeaLv;
    private SmartRefreshLayout mHomeaSrl;
    private ArrayList<ArtWanAndroidData.DataBean.DatasBean> list;
    private LikeAAdapter adapter;
    private int page = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_like_a, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {

        Retrofit rf = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(MyServer.wanandroid)
                .build();

        rf.create(MyServer.class)
                .getwanandroid(page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArtWanAndroidData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(ArtWanAndroidData artWanAndroidData) {
                        Log.i("tag","安卓数据"+artWanAndroidData.toString());
                        list.addAll(artWanAndroidData.getData().getDatas());

                        adapter.notifyDataSetChanged();
                        mHomeaSrl.finishRefresh();
                        mHomeaSrl.finishLoadMore();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initView(View inflate) {
        mHomeaLv = (RecyclerView) inflate.findViewById(R.id.likea_lv);
        mHomeaSrl = (SmartRefreshLayout) inflate.findViewById(R.id.like_srl);

        list = new ArrayList<ArtWanAndroidData.DataBean.DatasBean>();
//        mHomeaLv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mHomeaLv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHomeaLv.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        adapter = new LikeAAdapter(getActivity(), list);
        mHomeaLv.setAdapter(adapter);

        adapter.setA(new LikeAAdapter.A() {
            @Override
            public void b(ArtWanAndroidData.DataBean.DatasBean r, int p) {
                EventBus.getDefault().postSticky(r);
            }
        });
        mHomeaSrl.setOnRefreshListener(this);
        mHomeaSrl.setOnLoadMoreListener(this);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page=0;
        list.clear();
        initData();
    }
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page++;
        initData();
    }
}
