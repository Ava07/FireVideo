package com.example.lenovo.firevideo.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lenovo.firevideo.R;
import com.example.lenovo.firevideo.bean.UserInf;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;

//快速登陆界面
public class LoginFastActivity extends BaseActivity {
    private String userinfomsg;
    private Button button_send = null;
    private EditText edittext = null;
    private EditText et_phone;
    private EditText et_code;
    private MyCountTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginfast);
        Bmob.initialize(this, "7bb6dee8e0a32ba6eda090f0e7387d77");
        button_send = (Button) findViewById(R.id.getyanzhengma1);
        edittext = (EditText) findViewById(R.id.yanzhengma);
        et_phone=(EditText)findViewById(R.id.mobile_login);
        et_code=(EditText)findViewById(R.id.yanzhengma);
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestSMSCode();
            }
        });
        et_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneKeyLogin();
            }
        });

    }
    class MyCountTimer extends CountDownTimer {

        public MyCountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onTick(long millisUntilFinished) {
            button_send.setText((millisUntilFinished / 1000) +"秒后重发");
        }
        @Override
        public void onFinish() {
            button_send.setText("重新发送验证码");
        }
    }

    private void requestSMSCode() {
        String number = et_phone.getText().toString();
        if (!TextUtils.isEmpty(number)) {
                timer = new MyCountTimer(60000, 1000);
                timer.start();
                timer.onTick(60000);
                BmobSMS.requestSMSCode("number","", new QueryListener<Integer>() {

                    @Override
                    public void done(Integer smsId, BmobException ex) {
                        // TODO Auto-generated method stub
                        if (ex == null) {// 验证码发送成功
                            toast("验证码发送成功");// 用于查询本次短信发送详情
                        }else{
                            timer.cancel();
                        }
                    }
                });
            } else {
                toast("请输入手机号码");
            }
        }

    private void oneKeyLogin() {
        final String phone = et_phone.getText().toString();
        final String code = et_code.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            showToast("手机号码不能为空");
            return;
        }

        if (TextUtils.isEmpty(code)) {
            showToast("验证码不能为空");
            return;
        }
        final ProgressDialog progress = new ProgressDialog(LoginFastActivity.this);
        progress.setMessage("正在验证短信验证码...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();
        // V3.3.9提供的一键注册或登录方式，可传手机号码和验证码
        BmobUser.signOrLoginByMobilePhone("11位手机号码", "验证码", new LogInListener<UserInf>() {

            @Override
            public void done(UserInf user, BmobException e) {
                if(user!=null){
                    Log.i("smile","用户登陆成功");
                }
            }
        });

    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if(timer!=null){
            timer.cancel();
        }
    }




}
