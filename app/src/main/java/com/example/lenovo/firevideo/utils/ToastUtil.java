package com.example.lenovo.firevideo.utils;


import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.example.lenovo.firevideo.app.FireVideoApp;

/**
 * Created by Skyline on 2016/5/24.
 */
public class ToastUtil {
    private static Context context = FireVideoApp.App();
    private static Toast mToast;

    public static void show(int resId) {
        show(context.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(int resId, int duration) {
        show(context.getResources().getText(resId), duration);
    }

    public static void show(CharSequence text) {
        show(text, Toast.LENGTH_SHORT);
    }

    public static void show(CharSequence text, int duration) {
        text = TextUtils.isEmpty(text == null ? "" : text.toString()) ? ""
                : text;
        if (mToast == null) {
            mToast = Toast.makeText(context, text, duration);
            mToast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            mToast.setText(text);
        }
        mToast.show();
    }

    public static void show(int resId, Object... args) {
        show(String.format(context.getResources().getString(resId), args),
                Toast.LENGTH_SHORT);
    }

    public static void show(String format, Object... args) {
        show(String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void show(int resId, int duration, Object... args) {
        show(String.format(context.getResources().getString(resId), args),
                duration);
    }

    public static void show(String format, int duration, Object... args) {
        show(String.format(format, args), duration);
    }

    public void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
