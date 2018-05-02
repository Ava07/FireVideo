package com.example.lenovo.firevideo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.firevideo.R;
import com.example.lenovo.firevideo.bean.UserInf;
import com.example.lenovo.firevideo.utils.PreferenceUtil;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class EditUserinfoActivity extends AppCompatActivity {
    private TextView tv_head;
    private TextView tv_phonenum;
    private TextView tv_sex;
    private RadioButton radio_boy,radio_girl;
    private TextView tv_city;
    private ImageView iv_back;
    public static String usercity;
    public static String userphone;
    public static String userid;//用户ID
    public static final String USER_ID = "user_id";
    public static String USER_CITY="user_city";
    public static String USER_PHONE ="user_phone";
    public EditText edt_userphone;//用户手机号文本
    public EditText edt_usercity;//用户城市文本
    public Button btn_save;//保存按钮
    public String test_userid;//用户测试的用户id
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        setContentView(R.layout.activity_edit_userinfo);
        tv_head=(TextView)findViewById(R.id.tv_head);
        //tv_phonenum=(TextView)findViewById(R.id.tv_phonenum);
        tv_sex=(TextView)findViewById(R.id.tv_sex);
        //tv_city=(TextView)findViewById(R.id.tv_city);
        iv_back=(ImageView)findViewById(R.id.iv_back);
        btn_save=(Button)findViewById(R.id.btn_save);
        edt_usercity=(EditText)findViewById(R.id.edt_usercity);
        edt_userphone=(EditText)findViewById(R.id.edt_userphone);
        usercity=edt_usercity.getText().toString();
        userphone=edt_userphone.getText().toString();
        jump();
    }

    public void jump(){
        usercity=edt_usercity.getText().toString();
        userphone=edt_userphone.getText().toString();
        Log.i("用户城市",usercity);
        PreferenceUtil.put(USER_CITY,usercity);
        PreferenceUtil.put(USER_PHONE,userphone);
        userid=PreferenceUtil.getString(USER_ID,"");
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInf userInf = new UserInf();
                userInf.setUserId(userid);
                userInf.setUserCity(usercity);
                userInf.setPhoneNum(userphone);
                edt_usercity.setText(usercity);
                edt_userphone.setText(userphone);
                userInf.update(userid, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            Log.i("bmob","更新用户所处城市成功");

                        }else{
                            Log.i("bmob","更新用户城市失败："+e.getMessage()+","+e.getErrorCode());
                        }

                    }
                });
            }
        });



        /*
        tv_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(EditUserinfoActivity.this,EditUserCityActivity.class);
                startActivity(intent);
                finish();
            }
        });
        tv_phonenum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(EditUserinfoActivity.this,EditPhonenumActivity.class);
                startActivity(intent);
                finish();
            }
        });
        */
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(EditUserinfoActivity.this,MineActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
