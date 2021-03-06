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
import com.example.lenovo.firevideo.adapter.FruitAdapter3;
import com.example.lenovo.firevideo.bean.Fruit;
import com.example.lenovo.firevideo.bean.UserInf;
import com.example.lenovo.firevideo.bean.Video;
import com.example.lenovo.firevideo.bean.VideoUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ViewThree {
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
    public ViewThree(MainActivity activity) throws IOException {
        this.activity = activity;
        view=activity.getLayoutInflater().inflate(R.layout.activity_recyclerview3, null, false);
        init();
    }

    public View getView() {
        return view;
    }

    public List<Fruit> getFruitList() {
        return fruitList;
    }

    public void init() throws IOException {
        // TextView textView=(TextView) view.findViewById(R.id.txt);//根据获取到的View，实现对控件的获取。
        fruit_image = (ImageView)view.findViewById(R.id.fruit_image);
        fruit_name = (TextView)view.findViewById(R.id.fruit_name);
       // Bmob.initialize(view.getContext(), "8304511e908e2215a5bc8f02043c04c4");

        //initFruits();
        searchVideoList();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        FruitAdapter3 adapter = new FruitAdapter3(fruitList);
        recyclerView.setAdapter(adapter);
    }
    public void initFruits(String VideoId,String Username,String VideoFacePath,String VideoUrl) {
        Fruit honey1 = new Fruit(VideoId,Username,VideoFacePath,VideoUrl);
        fruitList.add(honey1);

    }
//    public void initFruits(String Username,String VideoFacePath,String VideoUrl) {
//        Fruit honey1 = new Fruit(Username,VideoFacePath,VideoUrl);
//        fruitList.add(honey1);
//
//    }

    //    //查询视频里面所有的缩略图路径
    public void searchVideoList(){
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
                    //toast("查询成功：共"+object.size()+"条数据。");
                    for (Video video : object) {
                        //获得playerName的信息
                        VideoFacePath = video.getVideoFace();
                        VideoUrl = video.getVideoUrl();
                        VideoId = video.getVideoId();
                        initFruits(VideoId,VideoUsername,VideoFacePath,VideoUrl);
                        Log.i("查询Video成功",video.getObjectId());

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
                                                    Log.i("bmob","viewthree查询UserInf失败："+e.getMessage()+","+e.getErrorCode());
                                                }

                                            }
                                        });

                                    }
                                }
                                else {
                                    Log.i("bmob","viewthree查询VideoUser失败："+e.getMessage()+","+e.getErrorCode());
                                }
                            }
                        });

//                        initFruits(VideoUsername,VideoFacePath,VideoUrl);
                        // Glide.with(view.getContext()).load(VideoFacePath).into(fruit_image);
                    }
                }else{
                    Log.i("bmob","viewthree查询Video失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }
}
