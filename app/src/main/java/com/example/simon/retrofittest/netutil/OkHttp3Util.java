package com.example.simon.retrofittest.netutil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by simon on 2017/10/16.
 */

public class OkHttp3Util {
    private static volatile OkHttpClient mInstance;

    public static OkHttpClient getOkHttpClient(){
        if (mInstance == null){
            mInstance = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30,TimeUnit.SECONDS)
                    .writeTimeout(30,TimeUnit.SECONDS)
                    .build();
        }
        return mInstance;
    }
}
