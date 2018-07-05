package com.example.lenovo.firevideo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.firevideo.R;
import com.example.lenovo.firevideo.bean.UserInf;
import com.example.lenovo.firevideo.utils.NameUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

//找回密码界面
public class FindPasswordActivity extends AppCompatActivity {
    public Button btn_send;//发送验证码按钮
    public Button btn_reset;//重置密码按钮
    public EditText et_pwd;//新密码
    public EditText et_phone;//用户手机号
    public EditText et_verify_code;//手机验证码
    public String new_pwd;//新密码
    public String phone_num;//手机号码
    public String verify_code;
    public ImageView iv_back;
    public int TIME=1000;
    public int countdown=60;//不能够在方法里面去定义
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        setContentView(R.layout.activity_findpasswd);
        Bmob.initialize(this, "8304511e908e2215a5bc8f02043c04c4");
        verifyCode();
        jump();
    }
    public void jump(){
        iv_back = (ImageView)findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindPasswordActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void verifyCode(){
        et_pwd=(EditText)findViewById(R.id.et_pwd);
        et_phone=(EditText)findViewById(R.id.et_phone);
        et_verify_code = (EditText)findViewById(R.id.et_verify_code);
        new_pwd=et_pwd.getText().toString();
        verify_code = et_verify_code.getText().toString();
        phone_num=et_phone.getText().toString();
        btn_reset = (Button)findViewById(R.id.btn_reset);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobSMS.requestSMSCode("18428333596", NameUtils.getNickName(), new QueryListener<Integer>() {
                    @Override
                    public void done(Integer integer, BmobException e) {
                        if (e == null){
                            Log.i("smile","短信id:"+ integer);
                            et_verify_code.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {

                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    verify_code = s.toString();
                                    Log.i("smile",verify_code);
                                    BmobSMS.verifySmsCode("18428333596", verify_code, new UpdateListener() {
                                        @Override
                                        public void done(BmobException e) {
                                            if (e == null){
                                                et_pwd.addTextChangedListener(new TextWatcher() {
                                                    @Override
                                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                    }

                                                    @Override
                                                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                                                    }

                                                    @Override
                                                    public void afterTextChanged(Editable s) {
                                                        //UserPhoneNum=edt_phoneNum.getText().toString();
                                                        new_pwd=s.toString();
                                                        Log.i("手机新密码",new_pwd);
                                                        btn_reset.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                resetPasswd(new_pwd);
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        }
                                    });
                                }
                            });


                        }
                        else{
                            Log.i("发送验证码失败",e.getMessage()+""+e.getErrorCode());
                        }

                    }
                });

            }
        });

    }

    public void resetPasswd(String pwd){
        //重置密码
        //et_pwd=(EditText)findViewById(R.id.et_pwd);
        //et_phone=(EditText)findViewById(R.id.et_phone);
        new_pwd=pwd;
        //phone_num=et_phone.getText().toString();
        BmobQuery<UserInf> query = new BmobQuery<UserInf>();
        //查询playerName叫“比目”的数据
        query.addWhereEqualTo("PhoneNum", "18428333596");
        //返回1条数据，如果不加上这条语句，默认返回10条数据
       // query.setLimit(1);
        //执行查询方法
        query.findObjects(new FindListener<UserInf>() {
            @Override
            public void done(List<UserInf> object, BmobException e) {
                if(e==null){
                    //toast("查询成功：共"+object.size()+"条数据。");
                    for (UserInf userInf : object) {
                        //获得数据的objectId信息
                        UserInf userInf1=new UserInf();
                        userInf1.setUserKey(new_pwd);
                        userInf1.update(userInf.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Log.i("bmob","更新密码成功");
                                }else{
                                    Log.i("bmob","更新密码失败："+e.getMessage()+","+e.getErrorCode());
                                }

                            }
                        });
                    }
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });

    }

}
