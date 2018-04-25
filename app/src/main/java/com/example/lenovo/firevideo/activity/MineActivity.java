package com.example.lenovo.firevideo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;

import com.example.lenovo.firevideo.R;

public class MineActivity extends AppCompatActivity {
    private RadioButton btn_group,btn_home,btn_add,btn_mail,btn_mine;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_mine);
    }
    @Override
    public void onStart() {
        super.onStart();
        btn_home = (RadioButton) findViewById(R.id.btn_home);
        btn_group = (RadioButton) findViewById(R.id.btn_group);
        btn_mail = (RadioButton) findViewById(R.id.btn_mail);
        btn_mine = (RadioButton) findViewById(R.id.btn_mine);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MineActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btn_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MineActivity.this,GroupActivity.class);
                startActivity(intent);
            }
        });
        btn_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MineActivity.this,MailActivity.class);
                startActivity(intent);
            }
        });
    }
}
