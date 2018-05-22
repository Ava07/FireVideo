package com.example.lenovo.firevideo.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.lenovo.firevideo.R;
import com.example.lenovo.firevideo.bean.LikeVideoUser;
import com.example.lenovo.firevideo.bean.Video;
import com.example.lenovo.firevideo.utils.PreferenceUtil;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class VideoPlayerActivity extends AppCompatActivity {
    private VideoView videoView ;
    private String VideoUrl;
    private ImageView like;
    private String VideoId;
    private String UserId;
    private Boolean UpdateFlag = false;//是否更新到LikeVideoFlag表的标志
    public static final String USER_ID = "user_id";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_videoplayer);
        //Bmob.initialize(this, "8304511e908e2215a5bc8f02043c04c4");
        like = (ImageView)findViewById(R.id.like);
        getVideoInf();

        Uri uri = Uri.parse( VideoUrl );
        videoView = (VideoView)this.findViewById(R.id.videoView );

        //设置视频控制器
        videoView.setMediaController(new MediaController(this));

        //播放完成回调
        videoView.setOnCompletionListener( new MyPlayerOnCompletionListener());

        //设置视频路径
        videoView.setVideoURI(uri);

        //开始播放视频
        videoView.start();
        //从获取视频URL到视频显示，大概有1分钟的时延 05-22 11:37:25.003（开始） 05-22 11:38:22.344（获取到视频数据）
        //目前下面的代码已经成功实现点赞，并更新LikeVideoUser表了 2018.5.22早上11：24
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               LikeVideoUser likeVideoUser = new LikeVideoUser();
               UserId =  PreferenceUtil.getString(USER_ID, "");
               likeVideoUser.setVideoId(VideoId);
               likeVideoUser.setUserId(UserId);
               likeVideoUser.save(new SaveListener<String>() {
                   @Override
                   public void done(String s, BmobException e) {
                       if (e==null){
                           UpdateFlag = true;
                           Log.i("更新至LikeVideoUser表成功",UpdateFlag.toString());
                       }
                       else {
                           Log.i("更新至LikeVideoUser表失败",UpdateFlag.toString());
                       }
                   }
               });
            }
        });

    }

    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
           // Toast.makeText( LocalVideoActivity.this, "播放完成了", Toast.LENGTH_SHORT).show();
        }
    }

    public void getVideoInf() {
        Intent intent = getIntent();
        VideoUrl = intent.getStringExtra("VideoUrl");
        VideoId = intent.getStringExtra("VideoId");
    }

}
