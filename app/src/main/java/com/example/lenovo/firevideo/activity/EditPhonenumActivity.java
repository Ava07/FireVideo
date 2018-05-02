package com.example.lenovo.firevideo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class EditPhonenumActivity extends AppCompatActivity {
    private ImageView iv_back; //返回到个人中心设置界面
    private Button btn_save;//保存当前设置的用户城市信息
    private EditText edt_phoneNum;//用户所输入的手机号
    private String UserPhoneNum;//手机号的字符形式
    public static final String USER_ID = "user_id";
    public static String USER_PHONE ="user_phone";
    public String userid;//用户ID
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_edit_phonenum);
        jump();
    }
    public void jump(){
        iv_back=(ImageView)findViewById(R.id.iv_back);
        btn_save=(Button)findViewById(R.id.btn_save);
        edt_phoneNum=(EditText)findViewById(R.id.edt_region);
        if(edt_phoneNum.getText().toString().trim().length()!=0){
            UserPhoneNum=edt_phoneNum.getText().toString();
        }
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(EditPhonenumActivity.this,EditUserinfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceUtil.getString(USER_ID,userid);
                UserInf userInf = new UserInf();
                userInf.setUserCity(UserPhoneNum);
                userInf.update(userid, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            Log.i("bmob","更新手机号成功");
                            PreferenceUtil.put(USER_PHONE,UserPhoneNum);
                        }else{
                            Log.i("bmob","更新手机号失败："+e.getMessage()+","+e.getErrorCode());
                        }

                    }
                });
            }
        });
    }
}
