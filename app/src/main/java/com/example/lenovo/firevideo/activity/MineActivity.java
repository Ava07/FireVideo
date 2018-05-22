package com.example.lenovo.firevideo.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.firevideo.R;
import com.example.lenovo.firevideo.bean.UserInf;
import com.example.lenovo.firevideo.permission.PermissionListener;
import com.example.lenovo.firevideo.permission.PermissionsUtil;
import com.example.lenovo.firevideo.utils.PreferenceUtil;
import com.yanzhenjie.nohttp.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

//个人中心界面
public class MineActivity extends AppCompatActivity {
    private RadioButton btn_group,btn_home,btn_add,btn_mail,btn_mine;
    private RadioGroup radioGroup_mine;
    private Button btn_edit;
    private ImageView head;
    private String UserId;
    private BmobFile headbmobFile;
    private String headUrl;//头像的网络路径
    public static final String USER_ID = "user_id";
    public static final String HEAD_URL = "head_url";
    public static final String USER_NAME = "user_name";
    public String Username;//用户名
    public TextView user_name;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_mine);
        btn_home = (RadioButton) findViewById(R.id.btn_home);
        btn_group = (RadioButton) findViewById(R.id.btn_group);
        btn_mail = (RadioButton) findViewById(R.id.btn_mail);
        btn_mine = (RadioButton) findViewById(R.id.btn_mine);
        radioGroup_mine=(RadioGroup)findViewById(R.id.radioGroup_mine);
        btn_edit=(Button)findViewById(R.id.btn_edit);
        btn_add=(RadioButton)findViewById(R.id.btn_add);
        head = (ImageView)findViewById(R.id.head);
        user_name = (TextView)findViewById(R.id.user_name);
        Username = PreferenceUtil.getString(USER_NAME,"");
        headUrl = PreferenceUtil.getString(HEAD_URL,"");
        init();
        jump();

    }

    private void init() {
        Log.i("用户名",Username);
        if (Username != null){
            user_name.setText(Username);
        }
        if (headUrl != null){
            Glide.with(head.getContext()).load(headUrl).into(head);//让用户头像显示
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if (Username != null){
            user_name.setText(Username);
        }
        if (headUrl != null){
            Glide.with(head.getContext()).load(headUrl).into(head);//让用户头像显示
        }
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
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MineActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MineActivity.this,GroupActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MineActivity.this,MailActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MineActivity.this,EditUserInfActivity.class);
               // Log.d("编辑个人信息",btn_edit.toString());
                startActivity(intent);
                finish();
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineActivity.this,LiveAdVideoActivity.class);
                startActivity(intent);
                finish();
            }
        });
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermission();
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");//设置类型，我这里是视频类型，任意后缀的可以这样写。
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent,1);

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
            File head_file = new File(img_path);
            headbmobFile = new BmobFile(head_file);
            //下面部分实现了图片和视频从本地上传到服务器，但如何让缩略图和视频之间产生关联呢？
            headbmobFile.uploadblock(new UploadFileListener() {
                @Override
                public void done(BmobException e) {
                    if(e==null){
                        //bmobFile.getFileUrl()--返回的上传文件的完整地址
                        Toast.makeText(MineActivity.this, "上传用户头像文件成功:" +headbmobFile.getFileUrl(),Toast.LENGTH_SHORT).show();
                        Log.i("上传用户头像文件成功",headbmobFile.getFileUrl());
                        headUrl=headbmobFile.getFileUrl();
                        updateUserHead();
                    }else{
                        Log.i("上传用户头像文件失败",e.getMessage());
                    }

                }
                @Override
                public void onProgress(Integer value) {
                    // 返回的上传进度（百分比）
                }
            });
        }
    }
    public void   updateUserHead(){
        UserId = PreferenceUtil.getString(USER_ID, "");//USER_ID就是用户ID
        final UserInf userInf = new UserInf();
        userInf.setUserHead(headUrl);
        userInf.update(UserId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Log.i("bmob","更新用户头像至UserInf表成功");
                    PreferenceUtil.put(HEAD_URL, headUrl);//USER_ID就是用户ID
                    Glide.with(head.getContext()).load(headUrl).into(head);//让用户头像显示
                    //PreferenceUtil.put(USER_CITY,UserRegion);
                }else{
                    Log.i("bmob","更新用户头像至UserInf表失败："+e.getMessage()+","+e.getErrorCode());
                }

            }
        });
    }

}

