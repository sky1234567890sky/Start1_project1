package com.example.start1_project1.server;

import com.example.start1_project1.bean.ArtDataFuli;
import com.example.start1_project1.bean.ArtWanAndroidData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by 华为 on 2019/5/27.
 */

public interface MyServer {

//    1、知乎：
//    http://news-at.zhihu.com/api/4/sections
//            2)最新日报
//    news/latest
//    5)专栏日报
//            sections
//            7热门日报
//    news/hot

    String fuliPath="http://news-at.zhihu.com/api/4/";
    @GET("sections")//福利
    Observable<ArtDataFuli>getFuliData();
    //https://www.wanandroid.com/article/list/0/json

    String wanandroid="https://www.wanandroid.com/";
    @GET("article/list/{page}/json")//wanandroid
    Observable<ArtWanAndroidData>getwanandroid(@Path("page") int page);

}
