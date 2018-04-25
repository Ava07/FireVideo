package com.example.lenovo.firevideo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.firevideo.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {
    public EditText setpwd_edit_name;
    public EditText setpwd_edit_pwd;
    public Button register_btn_cancel;
    public Button register_btn_sure;
    public EditText resetpwd_edit_pwd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_register);
        Bmob.initialize(this, "8304511e908e2215a5bc8f02043c04c4");
        initView();
        //init();

    }

    private void initView() {
        setpwd_edit_name=(EditText)findViewById(R.id.resetpwd_edit_name);
        setpwd_edit_pwd=(EditText)findViewById(R.id.resetpwd_edit_pwd_old);
        resetpwd_edit_pwd=(EditText)findViewById(R.id.resetpwd_edit_pwd_new);
        register_btn_cancel=(Button)findViewById(R.id.register_btn_cancel);
        register_btn_sure=(Button)findViewById(R.id.register_btn_sure);
        //这里不能简单的点击就去主页，必须登录成功才去主页，在服务器返回成就去主页，在点击事件里面执行注册的请求 setinfo
          register_btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUserinfo();//从界面获取信息并设置用户信息，保存在后台
            }
        });
        register_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setUserinfo(){
        String set_pwd=setpwd_edit_pwd.getText().toString();
        String set_userName=setpwd_edit_name.getText().toString();
        String reset_pwd=resetpwd_edit_pwd.getText().toString();
        if(TextUtils.isEmpty(set_pwd)||TextUtils.isEmpty(reset_pwd)){
            Toast.makeText(RegisterActivity.this,"密码输入不能为空",Toast.LENGTH_SHORT).show();
        }
        if (set_pwd.equals(reset_pwd)){
            BmobUser bmobUser = new BmobUser();
            bmobUser.setUsername(set_userName);
            bmobUser.setPassword(reset_pwd);
            bmobUser.signUp(new SaveListener<Object>() {
                @Override
                public void done(Object o, BmobException e) {
                    if(o!=null){
                        //在这里就显示已经注册成功了，看你是跳到登录页面还是直接到主页
                        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                          Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                          startActivity(intent);
                          finish();
                    }else {
                        if(e.getErrorCode()==202){
                            Toast.makeText(RegisterActivity.this,"当前账户已经注册了",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

        }else {
            Toast.makeText(RegisterActivity.this,"两次密码输入不一样",Toast.LENGTH_SHORT).show();
        }

    }

}
