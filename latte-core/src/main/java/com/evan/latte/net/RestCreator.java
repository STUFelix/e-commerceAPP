package com.evan.latte.net;

import com.evan.latte.app.ConfigType;
import com.evan.latte.app.Latte;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author Evan_zch
 * Program: FastEC
 * Time  2018-09-16 11:01
 */
public class RestCreator {


    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }


    public static WeakHashMap<String, Object> getParams() {
        return paramsHolder.PARAMS;
    }

    private static final class paramsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }


    // 采用静态内部类的方式来实现单例模式
    private static final class RetrofitHolder {
        // 获取初始化设置的url
        private static final String API_HOST = (String) Latte.getConfigurations().get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder().baseUrl(API_HOST)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .build();
    }

    private static final class OkHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient().newBuilder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }


    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }
}
