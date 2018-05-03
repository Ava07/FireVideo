package com.example.lenovo.firevideo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.lenovo.firevideo.R;
import com.example.lenovo.firevideo.bean.UserInf;
import com.example.lenovo.firevideo.utils.PreferenceUtil;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class EditUserInfActivity extends AppCompatActivity {
    private ImageView iv_back; //返回到个人中心设置界面
    private Button btn_save;//保存当前设置的用户城市信息
    private EditText edt_region;//用户所输入的城市
    public EditText edt_phoneNum;//用户手机号
    private String UserRegion;//地区的字符形式
    public String UserPhoneNum;//手机号字符形式
    public static final String USER_ID = "user_id";
    public static String USER_CITY="user_city";
    public static String USER_PHONE="user_phone";
    public String userid;//用户ID
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_edit_userinfo);
        iv_back=(ImageView)findViewById(R.id.iv_back);
        btn_save=(Button)findViewById(R.id.btn_save);
        edt_region=(EditText)findViewById(R.id.edt_region);
        edt_phoneNum=(EditText)findViewById(R.id.edt_phoneNum);
        edt_region.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               // UserRegion=edt_region.getText().toString();
                UserRegion=s.toString();
                Log.i("地区",UserRegion);
            }
        });
        edt_phoneNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //UserPhoneNum=edt_phoneNum.getText().toString();
                UserPhoneNum=s.toString();
                Log.i("手机号",UserPhoneNum);
            }
        });
        jump();
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(EditUserInfActivity.this,MineActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void jump(){
            //UserRegion=edt_region.getText().toString();
            //PreferenceUtil.put(USER_CITY,UserRegion);
            //UserPhoneNum=edt_phoneNum.getText().toString();
            //PreferenceUtil.put(USER_PHONE,UserPhoneNum);
            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userid=PreferenceUtil.getString(USER_ID,userid);
                    UserInf userInf = new UserInf();
                    userInf.setUserCity(UserRegion);
                    userInf.setPhoneNum(UserPhoneNum);
                    userInf.update(userid, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Log.i("bmob","更新用户所处城市成功");
                                //PreferenceUtil.put(USER_CITY,UserRegion);
                                PreferenceUtil.put(USER_CITY,UserRegion);
                                PreferenceUtil.put(USER_PHONE,UserPhoneNum);
                                Intent intent= new Intent(EditUserInfActivity.this,ShowUserInfoActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Log.i("bmob","更新用户城市失败："+e.getMessage()+","+e.getErrorCode());
                            }

                        }
                    });
                }
            });
        }
}

