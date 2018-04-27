package com.example.lenovo.firevideo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.firevideo.R;
import com.example.lenovo.firevideo.bean.UserInf;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
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
    public String new_pwd;//新密码
    public String phone_num;//手机号码
    public int TIME=1000;
    public int countdown=60;//不能够在方法里面去定义
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        setContentView(R.layout.activity_findpasswd);
        Bmob.initialize(this, "8304511e908e2215a5bc8f02043c04c4");
        add();
        btn_reset=(Button)findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPasswd();  //重置密码
            }
        });
        //initViews();
    }
    public void add(){
        UserInf userInf=new UserInf();
        userInf.setPhoneNum("18428333596");
        userInf.setUsername("Ava");
        userInf.setUserKey("123456");
        userInf.save(new SaveListener() {
            @Override
            public void done(Object o, BmobException e) {

            }

            @Override
            public void done(Object o, Object o2) {

            }
        });
    }
    public void initViews() {
        //更新“发送验证码”按钮的文本内容
        btn_send = (Button) findViewById(R.id.btn_send);
        final Handler handler = new Handler();
        while(countdown>=0) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        handler.postDelayed(this, TIME);
                        //Log.d("print","1-------------");
                        btn_send.setText("重新发送" + countdown + "秒");
                        countdown--;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            handler.postDelayed(runnable, TIME); // 在初始化方法里.延时1s
        }
        btn_send.setText("发送验证码");
    }
    public void resetPasswd(){
        //重置密码
        et_pwd=(EditText)findViewById(R.id.et_pwd);
        et_phone=(EditText)findViewById(R.id.et_phone);
        new_pwd=et_pwd.getText().toString();
        phone_num=et_phone.getText().toString();
        BmobQuery<UserInf> query = new BmobQuery<UserInf>();
        //查询playerName叫“比目”的数据
        query.addWhereEqualTo("PhoneNum", phone_num);
        //返回1条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(1);
        //执行查询方法
        query.findObjects(new FindListener<UserInf>() {
            @Override
            public void done(List<UserInf> object, BmobException e) {
                if(e==null){
                    //toast("查询成功：共"+object.size()+"条数据。");
                    for (UserInf userInf : object) {
                        //获得数据的objectId信息
                        UserInf userInf1=new UserInf();
                        userInf1.setPassword(new_pwd);
                        userInf1.update(userInf.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Log.i("bmob","更新成功");
                                }else{
                                    Log.i("bmob","更新失败："+e.getMessage()+","+e.getErrorCode());
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
