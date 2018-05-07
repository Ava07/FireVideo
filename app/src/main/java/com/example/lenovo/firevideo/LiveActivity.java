package com.example.lenovo.firevideo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.lenovo.firevideo.activity.LiveAdVideoActivity;
import com.example.lenovo.firevideo.guest.LiveListActivity;
import com.example.lenovo.firevideo.hoster.PreStartLiveActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LiveActivity extends com.example.lenovo.firevideo.BaseActivity{
    @BindView(R.id.tv_guest)
    TextView tvGuest;
    @BindView(R.id.tv_hoster)
    TextView tvHoster;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main_live;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @OnClick({R.id.tv_guest, R.id.tv_hoster,R.id.tv_call,R.id.tv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_guest:
                startAnimActivity(LiveListActivity.class);
                break;
            case R.id.tv_hoster:
                startAnimActivity(PreStartLiveActivity.class);
                break;
            case R.id.tv_call:
                Uri uri = Uri.parse("tel:021-65650071");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                break;
            case R.id.tv_back:
                startAnimActivity(LiveAdVideoActivity.class);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            System.exit(0);
            finishAnimActivity();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
