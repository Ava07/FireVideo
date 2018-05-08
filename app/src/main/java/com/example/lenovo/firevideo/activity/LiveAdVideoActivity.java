package com.example.lenovo.firevideo.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.lenovo.firevideo.R;
import com.example.lenovo.firevideo.SplashActivity;
import com.example.lenovo.firevideo.bean.Video;
import com.example.lenovo.firevideo.permission.PermissionListener;
import com.example.lenovo.firevideo.permission.PermissionsUtil;
import com.yanzhenjie.nohttp.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UploadFileListener;

//上传视频或者开启直播界面
public class LiveAdVideoActivity extends AppCompatActivity {
    public Button btn_live;
    public Button btn_video;
    public Button btn_back;
    public Bitmap bitmap;
    public Video video;
    public BmobFile bmobFile;
    public BmobFile bitmap_bmobFile;
    public FileOutputStream out;
    public String Video_FileName;//视频的文件名

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_liveadvideo);
        btn_live=(Button)findViewById(R.id.btn_live);
        btn_video=(Button)findViewById(R.id.btn_video);
        btn_back=(Button)findViewById(R.id.btn_back);
        jump();
    }
    protected void requestPermission() {
        if (PermissionsUtil.hasPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            //已经给了权限
        } else {
            PermissionsUtil.requestPermission(getApplication(), new PermissionListener() {
                @Override
                public void permissionGranted(@NonNull String[] permissions) {
                    //给了权限
                }
                @Override
                public void permissionDenied(@NonNull String[] permissions) {
                    Logger.d("requestPermission: 用户没有给了权限");
                }
            }, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (PermissionsUtil.hasPermission(this, Manifest.permission.READ_PHONE_STATE)) {
            //已经给了权限
        } else {
            PermissionsUtil.requestPermission(getApplication(), new PermissionListener() {
                @Override
                public void permissionGranted(@NonNull String[] permissions) {
                    //给了权限
                }
                @Override
                public void permissionDenied(@NonNull String[] permissions) {
                    Logger.d("requestPermission: 用户没有给了权限");
                }
            }, Manifest.permission.READ_PHONE_STATE);
        }
    }
    public void jump(){
        btn_live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LiveAdVideoActivity.this,SplashActivity.class);
                startActivity(intent);
                finish();

            }
        });
        btn_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("video/*");//设置类型，我这里是视频类型，任意后缀的可以这样写。
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent,1);


              /*
                Intent intent = new Intent(LiveAdVideoActivity.this,VideoRecorderActivity.class);
                startActivity(intent);
                finish();  */

            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LiveAdVideoActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {//是否选择，没选择就不会继续
            Uri uri = data.getData();//得到uri，后面就是将uri转化成file的过程。
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor actualimagecursor = managedQuery(uri, proj, null, null, null);
            int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualimagecursor.moveToFirst();
            String img_path = actualimagecursor.getString(actual_image_column_index);
            File video_file = new File(img_path);
            bmobFile = new BmobFile(video_file);
            bitmap=getVideoThumbnail(img_path);
            //下面完成的是，讲缩略图保存到本地
            Video_FileName=video_file.getName();  //获取文件名
            Log.i("bmobFile_Name",Video_FileName);
            //File file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis()+".jpg");
            //下面，终于实现了视频和视频缩略图之间的联系，即：缩略图的名称（除去后缀）就是视频文件的名称
            File file = new File(Environment.getExternalStorageDirectory(), Video_FileName+".jpg");
            try {
                out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //上面部分已经实现了讲缩略图保存至本地目录下
            //下面部分实现了图片和视频从本地上传到服务器，但如何让缩略图和视频之间产生关联呢？

            Toast.makeText(LiveAdVideoActivity.this,"保存已经至"+Environment.getExternalStorageDirectory()+"下", Toast.LENGTH_SHORT).show();
            bitmap_bmobFile= new BmobFile(file);
            bitmap_bmobFile.uploadblock(new UploadFileListener() {
                @Override
                public void done(BmobException e) {
                    if(e==null){
                        Toast.makeText(LiveAdVideoActivity.this, "上传图片文件成功:" +bitmap_bmobFile.getFileUrl(),Toast.LENGTH_SHORT).show();
                        Log.i("上传图片成功",bitmap_bmobFile.getFileUrl());
                    }else{
                        Log.i("上传图片文件失败",e.getMessage());
                    }
                }
            });
            bmobFile.uploadblock(new UploadFileListener() {
                @Override
                public void done(BmobException e) {
                    if(e==null){
                        //bmobFile.getFileUrl()--返回的上传文件的完整地址
                        Toast.makeText(LiveAdVideoActivity.this, "上传视频文件成功:" +bmobFile.getFileUrl(),Toast.LENGTH_SHORT).show();
                        Log.i("上传视频成功",bmobFile.getFileUrl());
                    }else{
                        Log.i("上传视频文件失败",e.getMessage());
                    }

                }
                @Override
                public void onProgress(Integer value) {
                    // 返回的上传进度（百分比）
                }
            });
        }
        //Toast.makeText(LiveAdVideoActivity.this, "上传文件成功:" +bmobFile.getFileUrl(),Toast.LENGTH_SHORT).show();

    }


    public Bitmap getVideoThumbnail(String filePath) {
        Bitmap b=null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            b=retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();

        } finally {
            try {
                retriever.release();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return b;
    }


}
