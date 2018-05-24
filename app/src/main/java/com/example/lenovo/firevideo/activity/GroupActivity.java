package com.example.lenovo.firevideo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lenovo.firevideo.R;
import com.example.lenovo.firevideo.adapter.GroupAdapter;
import com.example.lenovo.firevideo.adapter.MyPagerAdapter;
import com.example.lenovo.firevideo.bean.FollowUser;
import com.example.lenovo.firevideo.bean.UserInf;
import com.example.lenovo.firevideo.bean.VideoUser;
import com.example.lenovo.firevideo.utils.PreferenceUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

//关注界面
public class GroupActivity extends AppCompatActivity {
    private RadioButton btn_group,btn_home,btn_add,btn_mail,btn_mine;
    public static final String USER_ID = "user_id";
    public static final String HEAD_URL = "head_url";
    public static final String USER_NAME = "user_name";
    public String UserId;
    public String HeadUrl;
    public String Username;
    private List<UserInf> mData = null;
    private Context mContext;
    private GroupAdapter mAdapter = null;
    private ImageView imageView;
    private ViewPager vpager_four;
    private ImageView img_cursor;
    private ArrayList<View> listViews;
    private List<UserInf> userInfList = new ArrayList<>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        setContentView(R.layout.activity_group);
        mContext = GroupActivity.this;
//        mData = new LinkedList<UserInf>();
        btn_home = (RadioButton) findViewById(R.id.btn_home);
        btn_group = (RadioButton) findViewById(R.id.btn_group);
        btn_mail = (RadioButton) findViewById(R.id.btn_mail);
        btn_mine = (RadioButton) findViewById(R.id.btn_mine);
        btn_add=(RadioButton)findViewById(R.id.btn_add);
        UserId = PreferenceUtil.getString(USER_ID, "");//USER_ID就是用户ID
        Log.i("关注界面的用户ID",UserId);
        initGroupInf();
        jump();
    }

    public void initUserInf(String Username,String UserHead) {
        UserInf userInf = new UserInf(Username,UserHead);
        userInfList.add(userInf);
    }
    public void initGroupInf(){
        BmobQuery<FollowUser> query = new BmobQuery<FollowUser>();
        query.addWhereEqualTo("FollowedId",UserId);
        query.findObjects(new FindListener<FollowUser>() {
            @Override
            public void done(List<FollowUser> list, BmobException e) {
                if (e==null){
                    for (FollowUser followUser : list){
                        Log.i("关注界面的关注用户ID",followUser.getFollowId());

                        BmobQuery<UserInf> query1 = new BmobQuery<UserInf>();
                        query1.addWhereEqualTo("objectId",followUser.getFollowId());
                        query1.findObjects(new FindListener<UserInf>() {
                            @Override
                            public void done(List<UserInf> list, BmobException e) {
                                if (e==null){
                                    for (UserInf userInf : list){
                                        Username = userInf.getUsername();
                                        HeadUrl = userInf.getUserHead();
//                                        UserInf userInf1 = new UserInf(Username,HeadUrl);
                                        initUserInf(Username,HeadUrl);
                                        Log.i("查询关注用户成功",Username);
                                        Log.i("查询关注用户成功",HeadUrl);
//                                        userInfList.add(userInf1);
                                    }
                                    RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
                                    Log.i("测试一下recyclerview","");
                                    GridLayoutManager gridLayoutManager = new GridLayoutManager(GroupActivity.this,1);
                                    recyclerView.setLayoutManager(gridLayoutManager);
                                    GroupAdapter adapter = new GroupAdapter(userInfList);
                                    recyclerView.setAdapter(adapter);
                                }
                                else{
                                    Log.i("查询关注用户失败",e.getMessage()+""+e.getErrorCode());
                                }
                            }
                        });
                    }
                }
                else {
                    Log.i("查询FollowUser表失败",e.getMessage()+""+e.getErrorCode());
                }
            }
        });

    }
    public void jump(){
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupActivity.this,MailActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(GroupActivity.this,MineActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupActivity.this,LiveAdVideoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
