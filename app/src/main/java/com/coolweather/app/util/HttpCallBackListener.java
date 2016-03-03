package com.coolweather.app.util;

/**
 * Created by lbf on 2016/3/3.
 */
public interface HttpCallBackListener {
    void onFinish(String response);
    void onError(Exception e);
}
