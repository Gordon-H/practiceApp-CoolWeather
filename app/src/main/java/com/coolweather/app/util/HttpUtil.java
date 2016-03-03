package com.coolweather.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lbf on 2016/3/3.
 */
public class HttpUtil {
    public static void sendHttpRequest(final String address, final HttpCallBackListener callBackListener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                HttpURLConnection connection = null;
                try {
                    url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response = new StringBuilder();
                    String line = "";
                    while((line = reader.readLine())!=null){
                        response.append(line);
                    }
                    if(callBackListener != null){
                        callBackListener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if(callBackListener != null){
                        callBackListener.onError(e);
                    }
                }finally {
                    if(connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
