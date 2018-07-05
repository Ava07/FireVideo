package com.example.lenovo.firevideo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;

import com.example.lenovo.firevideo.R;
import com.example.lenovo.firevideo.adapter.GroupAdapter;
import com.example.lenovo.firevideo.adapter.MailAdapter;
import com.example.lenovo.firevideo.bean.FollowUser;
import com.example.lenovo.firevideo.bean.Like;
import com.example.lenovo.firevideo.bean.LikeVideoUser;
import com.example.lenovo.firevideo.bean.UserInf;
import com.example.lenovo.firevideo.bean.Video;
import com.example.lenovo.firevideo.bean.VideoUser;
import com.example.lenovo.firevideo.utils.PreferenceUtil;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindListener;

//消息界面
public class MailActivity extends AppCompatActivity {
    private RadioButton btn_group,btn_home,btn_add,btn_mail,btn_mine;
    public static final String USER_ID = "user_id";
    public String UserId;
    public String LikeVideoId;//被点赞视频ID
    public String LikeUserHead;//点赞用户头像路径
    public String LikeUsername;//点赞用户名
    public String VideoFace;//视频封面路径
    public Integer LikeNum;//视频被点赞数
    private List<Like> likeArrayList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_mail);
        btn_home = (RadioButton) findViewById(R.id.btn_home);
        btn_group = (RadioButton) findViewById(R.id.btn_group);
        btn_mail = (RadioButton) findViewById(R.id.btn_mail);
        btn_mine = (RadioButton) findViewById(R.id.btn_mine);
        btn_add=(RadioButton)findViewById(R.id.btn_add);
        UserId = PreferenceUtil.getString(USER_ID, "");//USER_ID就是用户ID
        initMailInf();
        jump();
    }

    public void initLikeInf(String LikeUserHead,String LikeUsername,String VideoFace) {
        Like like = new Like(LikeUserHead,LikeUsername,VideoFace);
        likeArrayList.add(like);
    }
    public void initMailInf(){
        BmobQuery<VideoUser> query = new BmobQuery<VideoUser>();
        query.addWhereEqualTo("UserId",UserId);
        query.findObjects(new FindListener<VideoUser>() {
            @Override
            public void done(List<VideoUser> list, BmobException e) {
                if (e==null){
                    for (VideoUser videoUser : list){
                        BmobQuery<LikeVideoUser> query = new BmobQuery<LikeVideoUser>();
                        query.addWhereEqualTo("VideoId",videoUser.getVideoId());
                        LikeVideoId = videoUser.getVideoId();

                        BmobQuery<Video> query2 = new BmobQuery<Video>();
                        query2.addWhereEqualTo("VideoId",LikeVideoId);
                        query2.findObjects(new FindListener<Video>() {
                            @Override
                            public void done(List<Video> list, BmobException e) {
                                if (e==null){
                                    for (Video video : list){
                                        VideoFace = video.getVideoFace();
                                        Log.i("视频封面路径获取到",VideoFace);
                                    }
                                }
                                else {
                                    Log.i("视频封面路径未获取",e.getMessage()+""+e.getErrorCode());

                                }
                            }
                        });

                        BmobQuery<LikeVideoUser> query1 = new BmobQuery<LikeVideoUser>();
                        query1.addWhereEqualTo("VideoId",LikeVideoId);
                        query1.findObjects(new FindListener<LikeVideoUser>() {
                            @Override
                            public void done(List<LikeVideoUser> list, BmobException e) {
                                if (e==null){
                                    for (LikeVideoUser likeVideoUser : list){
                                        BmobQuery<UserInf> query = new BmobQuery<UserInf>();
                                        query.addWhereEqualTo("UserId",likeVideoUser.getUserId());
                                        query.findObjects(new FindListener<UserInf>() {
                                            @Override
                                            public void done(List<UserInf> list, BmobException e) {
                                                if (e==null){
                                                    for (UserInf userInf : list){
                                                        LikeUsername = userInf.getUsername();
                                                        LikeUserHead = userInf.getUserHead();
                                                        initLikeInf(LikeUserHead,LikeUsername,VideoFace);
                                                    }
                                                    RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
                                                    Log.i("测试一下recyclerview","");
                                                    GridLayoutManager gridLayoutManager = new GridLayoutManager(MailActivity.this,1);
                                                    recyclerView.setLayoutManager(gridLayoutManager);
                                                    MailAdapter adapter = new MailAdapter(likeArrayList);
                                                    recyclerView.setAdapter(adapter);
                                                }
                                                else {
                                                    Log.i("点赞用户信息未获取",e.getMessage()+""+e.getErrorCode());

                                                }
                                            }
                                        });
                                    }
                                }
                                else {
                                    Log.i("LikeVideoUser信息获取失败",e.getMessage()+""+e.getErrorCode());

                                }
                            }
                        });

                    }
                }
                else {
                    Log.i("VideoUser信息获取失败",e.getMessage()+""+e.getErrorCode());

                }
            }
        });

    }

    public void jump(){
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MailActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MailActivity.this,GroupActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MailActivity.this,MineActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MailActivity.this,LiveAdVideoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
