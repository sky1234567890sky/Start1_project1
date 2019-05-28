package com.example.start1_project1.fram;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.start1_project1.R;
import com.example.start1_project1.adapter.HomeFuliAdapter;
import com.example.start1_project1.bean.ArtDataFuli;
import com.example.start1_project1.server.MyServer;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
public class HomeAFragment extends Fragment implements OnRefreshListener, OnLoadMoreListener {

    private View view;
    private RecyclerView mHomeaLv;
    private SmartRefreshLayout mHomeaSrl;
    private ArrayList<ArtDataFuli.DataBean> list;
    private HomeFuliAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home_a, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {

        Retrofit rf = new Retrofit.Builder()
                .baseUrl(MyServer.fuliPath)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        rf.create(MyServer.class).getFuliData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArtDataFuli>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArtDataFuli artDataFuli) {
                        Log.i("tag", "onNext: "+artDataFuli.toString());
//                        Toast.makeText(getActivity(), ""+, Toast.LENGTH_SHORT).show();
                            list.addAll(artDataFuli.getData());
                            adapter.notifyDataSetChanged();
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
        mHomeaLv = (RecyclerView) inflate.findViewById(R.id.homea_lv);
        mHomeaSrl = (SmartRefreshLayout) inflate.findViewById(R.id.homea_srl);

        list = new ArrayList<>();
        mHomeaLv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mHomeaLv.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        adapter = new HomeFuliAdapter(getActivity(), list);
        mHomeaLv.setAdapter(adapter);

        mHomeaSrl.setOnRefreshListener(this);
        mHomeaSrl.setOnLoadMoreListener(this);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }
}
