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

public class EditUserCityActivity extends AppCompatActivity {
    private ImageView iv_back; //返回到个人中心设置界面
    private Button btn_save;//保存当前设置的用户城市信息
    private EditText edt_region;//用户所输入的城市
    private String UserRegion;//地区的字符形式
    public static final String USER_ID = "user_id";
    public static String USER_CITY="user_city";
    public String userid;//用户ID
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_edit_usercity);
        jump();
    }
    public void jump(){
        iv_back=(ImageView)findViewById(R.id.iv_back);
        btn_save=(Button)findViewById(R.id.btn_save);
        edt_region=(EditText)findViewById(R.id.edt_region);
        if(edt_region.getText().toString().trim().length()!=0){
            UserRegion=edt_region.getText().toString();
        }
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(EditUserCityActivity.this,EditUserinfoActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceUtil.getString(USER_ID,userid);
                UserInf userInf = new UserInf();
                userInf.setUserCity(UserRegion);
                userInf.update(userid, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            Log.i("bmob","更新用户所处城市成功");
                            PreferenceUtil.put(USER_CITY,UserRegion);
                        }else{
                            Log.i("bmob","更新用户城市失败："+e.getMessage()+","+e.getErrorCode());
                        }

                    }
                });
            }
        });
    }
}