package com.example.simon.retrofittest.netutil;

import com.example.simon.retrofittest.bean.WeatherInfo;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by simon on 2017/10/16.
 */

public class Networks extends RetrofitUtils {

    //设缓存有效期为1天
    protected static final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，使用缓存
    protected static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置。不使用缓存
    protected static final String CACHE_CONTROL_NETWORK = "max-age=0";

    private static final NetService service = getRetrofit().create(NetService.class);

    private interface NetService{

        @FormUrlEncoded
        @POST("postName")
        Observable<WeatherInfo> postName(@Field("name") String name,@Field("age") int age);

        @GET("queryName")
        Observable<WeatherInfo> queryName();

        @GET("index")
        Observable<WeatherInfo> weather(@Query("format") int format,@Query("cityname") String cityName,@Query("key") String key);


    }

    public static void setName(String name,int age,Observer observer){

        setSubscribe(service.postName(name,age),observer);
    }

    public static void getName(Observer observer){
        setSubscribe(service.queryName(),observer);
    }

    public static void getWeather(String name,Observer<WeatherInfo> observer){
        setSubscribe(service.weather(2,name,"9073789e2c777bf25554d085a016e1e6"),observer);
    }


    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer){
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
