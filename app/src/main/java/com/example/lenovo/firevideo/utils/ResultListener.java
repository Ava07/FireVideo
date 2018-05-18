package com.example.lenovo.firevideo.utils;

import com.yanzhenjie.nohttp.rest.Response;

public interface ResultListener<T>{

    public void onSucceed(int what, Response<T> response);

    public void onFailed(int what, Response<T> response);
}
