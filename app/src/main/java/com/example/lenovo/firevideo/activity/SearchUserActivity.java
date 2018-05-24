package com.example.lenovo.firevideo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.firevideo.R;
import com.example.lenovo.firevideo.bean.FollowUser;
import com.example.lenovo.firevideo.bean.UserInf;
import com.example.lenovo.firevideo.bean.VideoUser;
import com.example.lenovo.firevideo.indicators.AVLoadingIndicatorView;
import com.example.lenovo.firevideo.utils.PreferenceUtil;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class SearchUserActivity extends AppCompatActivity {
    public String KeyWord;
    public ImageView head;
    public TextView tv_username;
    public String headUrl;//用户头像路径
    public String userName;//用户名
    public Button btn_group;//关注按钮
    public String FollowedUserId;//被关注用户ID
    public String FollowUserId;//用户ID
    public static final String USER_ID = "user_id";
    public ImageView iv_back;
    private AVLoadingIndicatorView avi;
    private Integer FollowNum;//关注用户数

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_searchuser);
        head = (ImageView)findViewById(R.id.head);
        tv_username = (TextView)findViewById(R.id.tv_username);
        btn_group = (Button)findViewById(R.id.btn_group);
        iv_back = (ImageView)findViewById(R.id.iv_back);
        String indicator=getIntent().getStringExtra("indicator");
        avi= (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.setIndicator(indicator);
        hideClick(avi);
        if (headUrl != null){
            Glide.with(head.getContext()).load(headUrl).into(head);
        }
        else {
            head.setImageResource(R.mipmap.imgdefault);
        }
        searchUser();
        jump();
    }

    public void jump(){
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchUserActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        head.setImageResource(R.mipmap.imgdefault);
        if (headUrl != null){
            Glide.with(head.getContext()).load(headUrl).into(head);
        }
        else {
            head.setImageResource(R.mipmap.imgdefault);
        }

    }
    public void searchUser(){
        Intent intent = getIntent();
        KeyWord = intent.getStringExtra("KeyWord");
        BmobQuery<UserInf> query1 = new BmobQuery<UserInf>();
        query1.addWhereEqualTo("Username",KeyWord);
        query1.findObjects(new FindListener<UserInf>() {
            @Override
            public void done(List<UserInf> list, BmobException e) {
                if (e==null) {
                    for (UserInf userInf : list) {
                        headUrl = userInf.getUserHead();
                        userName = userInf.getUsername();
                        FollowedUserId = userInf.getObjectId();
                        tv_username.setText(userName);
                        Glide.with(head.getContext()).load(headUrl).into(head);
                        btn_group.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                showClick(avi);
                                FollowUser followUser = new FollowUser();
                                FollowUserId = PreferenceUtil.getString(USER_ID,"");
                                followUser.setFollowId(FollowUserId);
                                followUser.setFollowedId(FollowedUserId);
                                followUser.save(new SaveListener<String>() {
                                    @Override
                                    public void done(String s, BmobException e) {
                                        if (e==null){
                                            hideClick(avi);
                                            btn_group.setText("已关注");
                                            Toast.makeText(SearchUserActivity.this, "关注成功", Toast.LENGTH_SHORT).show();
                                            UserInf userInf1 = new UserInf();
                                            FollowNum = userInf1.getFollow_Num();
                                            FollowNum = FollowNum ++;
                                            userInf1.setFollow_Num(FollowNum);
                                            userInf1.update(FollowUserId, new UpdateListener() {
                                                @Override
                                                public void done(BmobException e) {
                                                    if (e==null){
                                                        Log.i("更新用户关注数据成功",FollowNum.toString());
                                                    }
                                                    else {
                                                        Log.i("更新用户关注数据失败",e.getMessage());
                                                    }
                                                }
                                            });
                                            Log.i("保存用户关注信息到FollowUser表成功","");
                                        }
                                        else {
                                            Toast.makeText(SearchUserActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                                            Log.i("保存用户关注信息到FollowUser表失败",e.getMessage());
                                        }
                                    }
                                });

                            }
                        });
                    }
                }
                else {
                    Log.i("获取用户头像和用户名失败",e.getMessage());
                }
            }
        });
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


