package com.example.simon.retrofittest.netutil;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by simon on 2017/10/16.
 */

public abstract class RetrofitUtils {
    private static final String API_SERVER = "http://v.juhe.cn/weather";
    private static Retrofit mRetrofit;
    private static OkHttpClient mOkhttpClient;

    protected static Retrofit getRetrofit(){

        if (mRetrofit == null){
            if (mOkhttpClient == null){
                mOkhttpClient = OkHttp3Util.getOkHttpClient();
            }

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(API_SERVER+"/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(mOkhttpClient)
                    .build();
        }

        return mRetrofit;
    }
}
