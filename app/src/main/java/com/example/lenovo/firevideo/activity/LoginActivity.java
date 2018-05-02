package com.example.lenovo.firevideo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.firevideo.indicators.AVLoadingIndicatorView;
import com.example.lenovo.firevideo.utils.PreferenceUtil;
import com.example.lenovo.firevideo.R;
import com.example.lenovo.firevideo.bean.UserInf;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
//登陆界面
public class LoginActivity extends AppCompatActivity {
    private Button login_btn_register;
    private Button login_btn_login;
    private EditText login_edit_account;
    TextView login_text_change_pwd;
    private EditText login_edit_pwd;
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_NAME = "user_name";
    private CheckBox Login_Remember;
    private AVLoadingIndicatorView avi;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_login);
        login_btn_login=(Button)findViewById(R.id.login_btn_login);
        login_btn_register=(Button)findViewById(R.id.login_btn_register);
        login_text_change_pwd=(TextView)findViewById(R.id.login_text_change_pwd);
        login_edit_account=(EditText)findViewById(R.id.login_edit_account);
        login_edit_pwd=(EditText)findViewById(R.id.login_edit_pwd);
        Login_Remember =(CheckBox)findViewById(R.id.Login_Remember);
        String indicator=getIntent().getStringExtra("indicator");
        avi= (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.setIndicator(indicator);
        hideClick(avi);
        Bmob.initialize(this, "8304511e908e2215a5bc8f02043c04c4");
        // initViews();
        //这里就是获取有没有登录过，如果登录过了，就在这个缓存的消息
        String userName = PreferenceUtil.getString(USER_NAME, "");
        String userPassWord = PreferenceUtil.getString(USER_PASSWORD, "");
        if(!TextUtils.isEmpty(userName)&&!TextUtils.isEmpty(userPassWord)){
            login_edit_account.setText(userName);
            login_edit_pwd.setText(userPassWord);
        }
        login_btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳闸至注册界面
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        login_text_change_pwd.setOnClickListener(new View.OnClickListener() {
            //跳转至修改忘记密码界面
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,FindPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

        login_btn_login.setOnClickListener(new View.OnClickListener() {
            //跳转至修改忘记密码界面
            @Override
            public void onClick(View v) {
                showClick(avi);
                jump();
            }
        });

    }
    public void jump(){
        String account = login_edit_account.getText().toString();
        String password = login_edit_pwd.getText().toString();
        if(TextUtils.isEmpty(account)||TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this,"账户或密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }else {
             if( password.length()<6){
                 Toast.makeText(LoginActivity.this,"密码长度必须大于6位",Toast.LENGTH_SHORT).show();
                return;
            }
            login(account,password);
        }

    }

    /**
     * 请求登陆
     */
    public  void login(final String username, final String password) {
        UserInf userInfoBean = new UserInf();
        userInfoBean.setUsername(username);
        //userInfoBean.setPassword(password);
        userInfoBean.setUserKey(password);
        /*
        userInfoBean.login(new SaveListener<Object>() {
            @Override
            public void done(Object o, BmobException e) {
                if(o!=null){
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    //跳转到主页   保存账号密码到SharedPreferences
                    if(Login_Remember.isChecked()){
                        PreferenceUtil.put(USER_NAME,username);
                        PreferenceUtil.put(USER_PASSWORD,password);
                    }
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    if(e.getErrorCode()==101){
                        Toast.makeText(LoginActivity.this,"账号密码错误",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });  */
        //2018.5.1.在登陆界面，登陆失败
        cn.bmob.v3.BmobQuery<UserInf> query = new cn.bmob.v3.BmobQuery<UserInf>();
        query.addWhereEqualTo("Username",userInfoBean.getUsername());
        query.findObjects(new FindListener<UserInf>() {
            @Override
            public void done(List<UserInf> list, BmobException e) {
                if(e==null){
                    for (UserInf userInf : list) {
                        //获得playerName的信息
                        if (password.equals(userInf.getUserKey())){
                            hideClick(avi);
                            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                            //跳转到主页   保存账号密码到SharedPreferences
                            if(Login_Remember.isChecked()){
                                PreferenceUtil.put(USER_NAME,username);
                                PreferenceUtil.put(USER_PASSWORD,password);
                            }
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            if(e.getErrorCode()==101){
                                Toast.makeText(LoginActivity.this,"账号密码错误",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }

            }
        });
/*
        userInfoBean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(s!=null){
                    hideClick(avi);
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    //跳转到主页   保存账号密码到SharedPreferences
                    if(Login_Remember.isChecked()){
                        PreferenceUtil.put(USER_NAME,username);
                        PreferenceUtil.put(USER_PASSWORD,password);
                    }
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    if(e.getErrorCode()==101){
                        Toast.makeText(LoginActivity.this,"账号密码错误",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });  */
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
