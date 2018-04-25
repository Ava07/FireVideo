package com.example.lenovo.firevideo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.lenovo.firevideo.R;
//个人中心界面
public class PeopleActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_mine);
    }
}
