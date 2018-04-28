package com.example.lenovo.firevideo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.lenovo.firevideo.R;

public class EditUserinfoActivity extends AppCompatActivity {
    private TextView tv_head;
    private TextView tv_phonenum;
    private TextView tv_sex;
    private RadioButton radio_boy,radio_girl;
    private TextView tv_city;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_edit_userinfo);
        jump();
    }
    public void jump(){
        tv_head=(TextView)findViewById(R.id.tv_head);
        tv_phonenum=(TextView)findViewById(R.id.tv_phonenum);
        tv_sex=(TextView)findViewById(R.id.tv_sex);
        tv_city=(TextView)findViewById(R.id.tv_city);
        tv_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(EditUserinfoActivity.this,EditUserCityActivity.class);
                startActivity(intent);
            }
        });
        tv_phonenum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(EditUserinfoActivity.this,EditPhonenumActivity.class);
                startActivity(intent);
            }
        });
    }
}
