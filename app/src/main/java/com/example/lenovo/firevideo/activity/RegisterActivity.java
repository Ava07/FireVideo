package com.example.lenovo.firevideo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.firevideo.R;
import com.example.lenovo.firevideo.bean.UserInf;
import com.example.lenovo.firevideo.indicators.AVLoadingIndicatorView;
import com.example.lenovo.firevideo.utils.PreferenceUtil;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

//注册界面
public class RegisterActivity extends AppCompatActivity {
    public EditText setpwd_edit_name;
    public EditText setpwd_edit_pwd;
    public Button register_btn_cancel;
    public Button register_btn_sure;
    public EditText resetpwd_edit_pwd;
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public int SameNameNum = 0;
    private AVLoadingIndicatorView avi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_register);
        Bmob.initialize(this, "8304511e908e2215a5bc8f02043c04c4");
        initView();
        //init();
        String indicator=getIntent().getStringExtra("indicator");
        avi= (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.setIndicator(indicator);
        hideClick(avi);
    }

    private void initView() {
        setpwd_edit_name = (EditText) findViewById(R.id.resetpwd_edit_name);
        setpwd_edit_pwd = (EditText) findViewById(R.id.resetpwd_edit_pwd_old);
        resetpwd_edit_pwd = (EditText) findViewById(R.id.resetpwd_edit_pwd_new);
        register_btn_cancel = (Button) findViewById(R.id.register_btn_cancel);
        register_btn_sure = (Button) findViewById(R.id.register_btn_sure);
        //这里不能简单的点击就去主页，必须登录成功才去主页，在服务器返回成就去主页，在点击事件里面执行注册的请求 setinfo
        register_btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showClick(avi);
                setUserinfo();//从界面获取信息并设置用户信息，保存在后台
            }
        });
        register_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void setUserinfo() {
        String set_pwd = setpwd_edit_pwd.getText().toString();
        final String set_userName = setpwd_edit_name.getText().toString();
        String reset_pwd = resetpwd_edit_pwd.getText().toString();
        if (TextUtils.isEmpty(set_pwd) || TextUtils.isEmpty(reset_pwd)) {
            Toast.makeText(RegisterActivity.this, "密码输入不能为空", Toast.LENGTH_SHORT).show();
        }
        if (set_pwd.equals(reset_pwd)) {
            final UserInf userInf = new UserInf();
            userInf.setUsername(set_userName);
            userInf.setUserKey(reset_pwd);
            //需要有一个查找是否有用户名重名的情况，没有重名的情况下，才可以将用户信息存储到服务器
            userInf.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                       // Log.d("用户ID", s);
                        hideClick(avi);
                        userInf.setUserId(s);
                        userInf.update(s, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e==null){
                                    Log.i("更新用户信息成功",userInf.getUserId());
                                }
                                else {
                                    Log.i("更新用户信息失败",userInf.getUserId());

                                }
                            }
                        });
//                        PreferenceUtil.put(USER_ID, s);//USER_ID就是用户ID
//                        PreferenceUtil.put(USER_NAME,set_userName);
                        Log.d("用户ID", s);
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
                    } else {
                        Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(RegisterActivity.this, "两次输入密码不一样", Toast.LENGTH_SHORT).show();
        }
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




