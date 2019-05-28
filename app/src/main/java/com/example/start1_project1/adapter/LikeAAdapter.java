package com.example.start1_project1.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.start1_project1.R;
import com.example.start1_project1.bean.ArtWanAndroidData;

import java.util.ArrayList;

/**
 * Created by 华为 on 2019/5/28.
 */

public class LikeAAdapter extends RecyclerView.Adapter<LikeAAdapter.ViewHolder>{
    private final FragmentActivity activity;
    private final ArrayList<ArtWanAndroidData.DataBean.DatasBean> list;

    public LikeAAdapter(FragmentActivity activity, ArrayList<ArtWanAndroidData.DataBean.DatasBean> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(activity, R.layout.likea_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ArtWanAndroidData.DataBean.DatasBean r = list.get(position);
        holder.likeaTv.setText(r.getAuthor());
        Glide.with(activity).load(r.getEnvelopePic()).placeholder(R.mipmap.ic_launcher).into(holder.likeaImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a!=null){
                    a.b(r,position);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView likeaImg;
        private TextView likeaTv;
        public ViewHolder(View itemView) {
            super(itemView);


            likeaImg = (ImageView) itemView.findViewById(R.id.likea_img);
            likeaTv = (TextView) itemView.findViewById(R.id.likea_tv);

        }
    }

    A a;
    public interface A{
        void b(ArtWanAndroidData.DataBean.DatasBean r,int p);
    }

    public void setA(A a) {
        this.a = a;
    }
}
