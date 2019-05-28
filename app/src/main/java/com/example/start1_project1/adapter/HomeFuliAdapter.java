package com.example.start1_project1.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.start1_project1.R;
import com.example.start1_project1.bean.ArtDataFuli;

import java.util.ArrayList;

/**
 * Created by 华为 on 2019/5/28.
 */

public class HomeFuliAdapter extends RecyclerView.Adapter<HomeFuliAdapter.ViewHolder>{
    private final FragmentActivity activity;
    private final ArrayList<ArtDataFuli.DataBean> list;

    public HomeFuliAdapter(FragmentActivity activity, ArrayList<ArtDataFuli.DataBean> list) {

        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(activity, R.layout.homea_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArtDataFuli.DataBean r = list.get(position);
        Glide.with(activity).load(r.getThumbnail()).placeholder(R.mipmap.ic_launcher).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);


            img = (ImageView) itemView.findViewById(R.id.img);

        }
    }
}
