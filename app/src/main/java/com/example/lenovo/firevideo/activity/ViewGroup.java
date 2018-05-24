package com.example.lenovo.firevideo.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.firevideo.R;
import com.example.lenovo.firevideo.adapter.FruitAdapter1;
import com.example.lenovo.firevideo.adapter.GroupAdapter;
import com.example.lenovo.firevideo.bean.FollowUser;
import com.example.lenovo.firevideo.bean.Fruit;
import com.example.lenovo.firevideo.bean.UserInf;
import com.example.lenovo.firevideo.utils.PreferenceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ViewGroup {
    private View view;
    private GroupActivity activity;
    private List<UserInf> userInfList = new ArrayList<>();
    public static final String USER_ID = "user_id";
    public static final String HEAD_URL = "head_url";
    public static final String USER_NAME = "user_name";
    public String UserId;
    public String HeadUrl;
    public String Username;
    public ImageView head;
    public TextView tv_username;
    public void ViewGroup(GroupActivity activity) throws IOException {
        this.activity = activity;
        view=activity.getLayoutInflater().inflate(R.layout.activity_recyclergroup, null, false);
        init();
    }
    public View getView() {
        return view;
    }
    public void initUserInf(String Username,String UserHead) {
        UserInf userInf = new UserInf(Username,UserHead);
        userInfList.add(userInf);
    }
    public void init(){
        UserId = PreferenceUtil.getString(USER_ID, "");//USER_ID就是用户ID
        head = (ImageView)view.findViewById(R.id.head);
        tv_username = (TextView)view.findViewById(R.id.tv_username);
        initGroupInf();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        GroupAdapter adapter = new GroupAdapter(userInfList);
        recyclerView.setAdapter(adapter);

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
                                        UserInf userInf1 = new UserInf(Username,HeadUrl);
                                        Log.i("查询关注用户成功",Username);
                                        Log.i("查询关注用户成功",HeadUrl);
                                        userInfList.add(userInf1);
                                    }
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

}
