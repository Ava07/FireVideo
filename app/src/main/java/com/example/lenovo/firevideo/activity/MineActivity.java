package com.example.lenovo.firevideo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lenovo.firevideo.R;

//个人中心界面
public class MineActivity extends AppCompatActivity {
    private RadioButton btn_group,btn_home,btn_add,btn_mail,btn_mine;
    private RadioGroup radioGroup_mine;
    private Button btn_edit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_mine);
        btn_home = (RadioButton) findViewById(R.id.btn_home);
        btn_group = (RadioButton) findViewById(R.id.btn_group);
        btn_mail = (RadioButton) findViewById(R.id.btn_mail);
        btn_mine = (RadioButton) findViewById(R.id.btn_mine);
        radioGroup_mine=(RadioGroup)findViewById(R.id.radioGroup_mine);
        btn_edit=(Button)findViewById(R.id.btn_edit);
        btn_add=(RadioButton)findViewById(R.id.btn_add);
        jump();

    }
    public void jump(){
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MineActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MineActivity.this,GroupActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MineActivity.this,MailActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MineActivity.this,EditUserInfActivity.class);
               // Log.d("编辑个人信息",btn_edit.toString());
                startActivity(intent);
                finish();
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineActivity.this,LiveAdVideoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    /*
    public void jumpToHome(){
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MineActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void jumpToGroup(){
        btn_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MineActivity.this,GroupActivity.class);
                startActivity(intent);
            }
        });
    }
    public void jumpToMail(){

        btn_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MineActivity.this,MailActivity.class);
                startActivity(intent);
            }
        });
    }
*/

}

