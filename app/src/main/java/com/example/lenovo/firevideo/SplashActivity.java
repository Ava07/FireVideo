package com.example.lenovo.firevideo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.lenovo.firevideo.activity.MainActivity;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this,LiveActivity.class));
        finish();
    }
}
