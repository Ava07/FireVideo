package com.example.lenovo.firevideo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder unbindr;
    protected ImmersionBar mImmersionBar;
    ProgressDialog pd;
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(this.getLayoutId());
        unbindr = ButterKnife.bind(this);
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
        this.initView(savedInstanceState);
    }
    private void ProgressDialog(Context ctx) {
        pd = new ProgressDialog(ctx);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setCancelable(true);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
    }
    public void showProgressDialog() {
        if (pd == null) {
            ProgressDialog(this);
        }
        pd.setMessage("正在加载...");
        pd.show();
    }

    public void hiddenProgressDialog() {
        if (pd != null && pd.isShowing()) {
            pd.cancel();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindr.unbind();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }


    public void startAnimActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    public void finishAnimActivity() {
        finish();
    }

    public void startAnimActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public abstract int getLayoutId();

    public abstract void initView(Bundle savedInstanceState);

}
