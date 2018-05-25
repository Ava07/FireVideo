package com.example.lenovo.firevideo.activity;

import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.firevideo.R;
import com.example.lenovo.firevideo.adapter.FruitAdapter1;
import com.example.lenovo.firevideo.adapter.FruitAdapter2;
import com.example.lenovo.firevideo.adapter.MineAdapter;
import com.example.lenovo.firevideo.bean.Fruit;
import com.example.lenovo.firevideo.bean.LikeVideoUser;
import com.example.lenovo.firevideo.bean.UserInf;
import com.example.lenovo.firevideo.bean.Video;
import com.example.lenovo.firevideo.bean.VideoUser;
import com.example.lenovo.firevideo.indicators.AVLoadingIndicatorView;
import com.example.lenovo.firevideo.utils.PreferenceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
//
//public class ViewTwo {
//    private MainActivity activity;
//    private List<Fruit> fruitList = new ArrayList<>();
//    private View view;
//    private String VideoFacePath;//视频缩略图路径
//    private Bitmap bitmap;//视频缩略图
//    private ImageView fruit_image;
//    private TextView fruit_name;
//    private String VideoUserId;//上传视频的用户ID
//    private String VideoUsername;//上传视频的用户名
//    private String VideoUrl;//上传视频的路径
//    private String VideoId;//视频ID
//    private String UserCity;//上传视频的用户城市
//    public static final String USER_ID = "user_id";
//    public static final String HEAD_URL = "head_url";
//    public static final String USER_NAME = "user_name";
//    public static final String FOLLOW_NUM = "follow_num";
//    public static final String FOLLOWER_NUM = "follower_num";
//    public static final String ENERGY_VALUE = "energy_value";
//    public static final String USER_CITY = "user_city";
//    private String UserId;
//    private Subscription subscription;
//    private AVLoadingIndicatorView avi;
//    private List<Video> videoArrayList = new ArrayList<>();
//    public ViewTwo(MainActivity activity) throws IOException {
//        this.activity = activity;
//        view = activity.getLayoutInflater().inflate(R.layout.activity_recyclerview2, null, false);
//        init();
//    }
//
////    public void initVideoInf(String VideoId,String VideoFacePath, String VideoUrl){
////            Video video = new Video(VideoId,VideoFacePath,VideoUrl);
////            videoArrayList.add(video);
////    }
//
////    public void initFruits(String VideoId,String Username,String VideoFacePath,String VideoUrl) {
////        Fruit honey1 = new Fruit(VideoId,Username,VideoFacePath,VideoUrl);
////        fruitList.add(honey1);
////
////    }
//
//    public View getView() {
//        return view;
//    }
//
//    public List<Fruit> getFruitList() {
//        return fruitList;
//    }
//
//    public void init()  {
//        String indicator= activity.getIntent().getStringExtra("indicator");
//        avi= (AVLoadingIndicatorView) view.findViewById(R.id.avi);
//        avi.setIndicator(indicator);
//        hideClick(avi);
//        // TextView textView=(TextView) view.findViewById(R.id.txt);//根据获取到的View，实现对控件的获取。
//        fruit_image = (ImageView) view.findViewById(R.id.fruit_image);
//        fruit_name = (TextView) view.findViewById(R.id.fruit_name);
//        UserId = PreferenceUtil.getString(USER_ID, "");//USER_ID就是用户ID
//        initUploadVideo();
//    }
//    public void initFruits(String VideoId,String Username,String VideoFacePath,String VideoUrl) {
//        Fruit honey1 = new Fruit(VideoId,Username,VideoFacePath,VideoUrl);
//        fruitList.add(honey1);
//
//    }
//
//    public void initUploadVideo() {
//        showClick(avi);
//        subscription = rx.Observable.create(new rx.Observable.OnSubscribe<Video>() {
//            @Override
//            public void call(final Subscriber<? super Video> subscriber) {
//                BmobQuery<Video> query1 = new BmobQuery<Video>();
//                query1.addWhereGreaterThanOrEqualTo("Video_Energy_Value",1);
//                Log.i("19374628","");
//                query1.findObjects(new FindListener<Video>() {
//                    @Override
//                    public void done(List<Video> list, BmobException e) {
//                        if (e==null){
//                            for (Video video : list){
//                                subscriber.onNext(video);
//                                Log.i("热门界面得到的视频信息",video.getVideoId());
//                            }
//                        }
//
//                    }
//                });
//            }
//        }).onBackpressureBuffer().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
//                subscribe(new Observer<Video>() {
//                    @Override
//                    public void onCompleted() {
//                        hideClick(avi);
//                        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
//                        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity,2);
//                        recyclerView.setLayoutManager(gridLayoutManager);
//                        FruitAdapter2 adapter = new FruitAdapter2(fruitList);
//                        Log.d("123456", "onCompleted: "+fruitList.size());
//                        recyclerView.setAdapter(adapter);
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//
//                    }
//
//                    @Override
//                    public void onNext(Video video) {
////                        initVideoInf(video.getObjectId(),
////                                video.getVideoFace(), video.getVideoUrl());
//                        initFruits(video.getVideoId(),"",video.getVideoFace(),video.getVideoUrl());
//                        Log.i("热门界面得到的视频信息",video.getVideoId());
//                    }
//                });
//
//    }
//    public void hideClick(View view) {
//        avi.hide();
//        // or avi.smoothToHide();
//    }
//
//    public void showClick(View view) {
//        avi.show();
//        // or avi.smoothToShow();
//    }
//}
//
//
//
//
//
////    //    //查询视频里面所有的缩略图路径
////    public void searchVideoList(){
////        BmobQuery<Video> query = new BmobQuery<Video>();
////        //查询所有VideoFace不为空的的数据
////        query.addWhereExists("VideoFace");
////        //返回50条数据，如果不加上这条语句，默认返回10条数据
////        //query.setLimit(50);
////        //执行查询方法
////        query.findObjects(new FindListener<Video>() {
////            @Override
////            public void done(final List<Video> object, BmobException e) {
////                if(e==null){
////                    //toast("查询成功：共"+object.size()+"条数据。");
////                    for (Video video : object) {
////                        //获得playerName的信息
////                        VideoFacePath = video.getVideoFace();
////                        VideoUrl = video.getVideoUrl();
////                        VideoId = video.getVideoId();
////                        initFruits(VideoId,VideoUsername,VideoFacePath,VideoUrl);
////                        Log.i("查询Video成功",video.getObjectId());
////
////                        BmobQuery<VideoUser> query1 = new BmobQuery<VideoUser>();
////                        query1.addWhereEqualTo("VideoId",video.getObjectId());
////                        query1.findObjects(new FindListener<VideoUser>() {
////                            @Override
////                            public void done(List<VideoUser> list, BmobException e) {
////                                if (e==null){
////                                    for (VideoUser videoUser : list){
////                                        VideoUserId = videoUser.getUserId();
////                                        Log.i("查询VideoUseer成功",videoUser.getObjectId());
////
////                                        BmobQuery<UserInf> userInfBmobQuery = new BmobQuery<UserInf>();
////                                        userInfBmobQuery.addWhereEqualTo("UserId",VideoUserId);
////                                        userInfBmobQuery.findObjects(new FindListener<UserInf>() {
////                                            @Override
////                                            public void done(List<UserInf> list, BmobException e) {
////                                                if (e==null){
////                                                    for (UserInf userInf : list){
////                                                        VideoUsername= userInf.getUsername();
////                                                        UserCity = userInf.getUserCity();
////                                                        Log.i("查询UserInf成功",userInf.getObjectId());
////                                                    }
////                                                }
////                                                else {
////                                                    Log.i("bmob","viewtwo查询UserInf失败："+e.getMessage()+","+e.getErrorCode());
////                                                }
////
////                                            }
////                                        });
////
////                                    }
////                                }
////                                else {
////                                    Log.i("bmob","viewtwo查询VideoUser失败："+e.getMessage()+","+e.getErrorCode());
////                                }
////                            }
////                        });
////
////
//////                        initFruits(VideoId,VideoUsername,VideoFacePath,VideoUrl);
//////                        initFruits(VideoUsername,VideoFacePath,VideoUrl);
////                        // Glide.with(view.getContext()).load(VideoFacePath).into(fruit_image);
////                    }
////                }else{
////                    Log.i("bmob","viewtwo查询Video失败："+e.getMessage()+","+e.getErrorCode());
////                }
////            }
////        });
////    }
////}




//为主界面的每一个item，生成相应的类，并每个页面分开，做相应的点击处理
public class ViewTwo {
    private MainActivity activity;
    private List<Fruit> fruitList = new ArrayList<>();
    private View view;
    private String VideoFacePath;//视频缩略图路径
    private Bitmap bitmap;//视频缩略图
    private ImageView fruit_image;
    private TextView fruit_name;
    private String VideoUserId;//上传视频的用户ID
    private String VideoUsername;//上传视频的用户名
    private String VideoUrl;//上传视频的路径
    private String VideoId;//视频ID
    private Subscription subscription;
    private AVLoadingIndicatorView avi;
    private Integer MaxEnergy = 1;
    public ViewTwo(MainActivity activity) throws IOException {
        this.activity = activity;
        view=activity.getLayoutInflater().inflate(R.layout.activity_recyclerview1, null, false);
        init(view);
    }

    public View getView() {
        return view;
    }

    public List<Fruit> getFruitList() {
        return fruitList;
    }

    public void init(View view) throws IOException {
        String indicator= activity.getIntent().getStringExtra("indicator");
        avi= (AVLoadingIndicatorView) view.findViewById(R.id.avi);
        avi.setIndicator(indicator);
        hideClick(avi);
        // TextView textView=(TextView) view.findViewById(R.id.txt);//根据获取到的View，实现对控件的获取。
        fruit_image = (ImageView)view.findViewById(R.id.fruit_image);
        fruit_name = (TextView)view.findViewById(R.id.fruit_name);
        searchVideoList();

    }
    public void initFruits(String VideoId,String Username,String VideoFacePath,String VideoUrl) {
        Fruit honey1 = new Fruit(VideoId,Username,VideoFacePath,VideoUrl);
        fruitList.add(honey1);

    }

    //    //查询视频里面所有的缩略图路径
    public void searchVideoList(){
        showClick(avi);
        subscription = rx.Observable.create(new rx.Observable.OnSubscribe<Video>() {
            @Override
            public void call(final Subscriber<? super Video> subscriber) {
                BmobQuery<Video> query = new BmobQuery<Video>();
                //查询所有VideoFace不为空的的数据
                query.addWhereExists("VideoFace");
                //返回50条数据，如果不加上这条语句，默认返回10条数据
                //query.setLimit(50);
                //执行查询方法
                query.findObjects(new FindListener<Video>() {
                    @Override
                    public void done(final List<Video> object, BmobException e) {
                        if(e==null){
                            for (Video video : object) {
                                subscriber.onNext(video);
                                BmobQuery<VideoUser> query1 = new BmobQuery<VideoUser>();
                                query1.addWhereEqualTo("VideoId",video.getObjectId());
                                query1.findObjects(new FindListener<VideoUser>() {
                                    @Override
                                    public void done(List<VideoUser> list, BmobException e) {
                                        if (e==null){
                                            for (VideoUser videoUser : list){
                                                VideoUserId = videoUser.getUserId();
                                                Log.i("查询VideoUseer成功",videoUser.getObjectId());

                                                BmobQuery<UserInf> userInfBmobQuery = new BmobQuery<UserInf>();
                                                userInfBmobQuery.addWhereEqualTo("UserId",VideoUserId);
                                                //userInfBmobQuery.setLimit(1);
                                                userInfBmobQuery.findObjects(new FindListener<UserInf>() {
                                                    @Override
                                                    public void done(List<UserInf> list, BmobException e) {
                                                        if (e==null){
                                                            for (UserInf userInf : list){
                                                                VideoUsername= userInf.getUsername();
                                                                Log.i("查询UserInf成功",userInf.getObjectId());
                                                            }
                                                        }
                                                        else {
                                                            Log.i("bmob","查询UserInf失败："+e.getMessage()+","+e.getErrorCode());
                                                        }

                                                    }
                                                });

                                            }
                                        }
                                        else {
                                            Log.i("bmob","查询VideoUser失败："+e.getMessage()+","+e.getErrorCode());
                                        }
                                    }
                                });
                            }
                            subscriber.onCompleted();
                        }else{
                            Log.i("bmob","查询Video失败："+e.getMessage()+","+e.getErrorCode());
                        }
                    }
                });
            }
        }).onBackpressureBuffer().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<Video>() {
                    @Override
                    public void onCompleted() {
                        hideClick(avi);
                        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity,2);
                        recyclerView.setLayoutManager(gridLayoutManager);
                        FruitAdapter1 adapter = new FruitAdapter1(fruitList);
                        Log.d("123", "onCompleted: "+fruitList.size());
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Video video) {
                        initFruits(video.getVideoId(),"",video.getVideoFace(),video.getVideoUrl());
                    }
                });

    }

    //点击缩略图之后的跳转
    public void playVideo(){
    }
    public void hideClick(View view) {
        avi.hide();
        // or avi.smoothToHide();
    }

    public void showClick(View view) {
        avi.show();
        // or avi.smoothToShow();
    }

}
