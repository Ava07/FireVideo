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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.lenovo.firevideo.R;
import com.example.lenovo.firevideo.bean.UserInf;
import com.example.lenovo.firevideo.utils.PreferenceUtil;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class ShowUserInfoActivity extends AppCompatActivity {
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
    public TextView tv_userphone;//用户手机号文本
    public TextView tv_usercity;//用户城市文本
    public Button btn_save;//保存按钮
    public String test_userid;//用户测试的用户id
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        setContentView(R.layout.activity_show_userinfo);
        tv_head=(TextView)findViewById(R.id.tv_head);
        //tv_phonenum=(TextView)findViewById(R.id.tv_phonenum);
        //tv_city=(TextView)findViewById(R.id.tv_city);
        iv_back=(ImageView)findViewById(R.id.iv_back);
        btn_save=(Button)findViewById(R.id.btn_save);
        tv_usercity=(TextView)findViewById(R.id.tv_usercity);
        tv_userphone=(TextView)findViewById(R.id.tv_userphone);
        //usercity=tv_usercity.getText().toString();
        //userphone=tv_userphone.getText().toString();
        //jump();
        init();
    }
    public void init(){
        usercity=PreferenceUtil.getString(USER_CITY,usercity);
        userphone=PreferenceUtil.getString(USER_PHONE,userphone);
        tv_usercity.setText(usercity);
        tv_userphone.setText(userphone);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ShowUserInfoActivity.this,MineActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}


