package com.zrx.live.network;


import com.zrx.live.api.UrlConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static Retrofit mTestRetrofit;
    public static Retrofit getTestRetrofit() {
        if (mTestRetrofit == null) {
            synchronized (RetrofitManager.class) {
                mTestRetrofit = new Retrofit.Builder().baseUrl(UrlConfig.TestHostUrl)
                        .client(HttpClient.mOkHttpClient)//加入okhttp
//                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())//加入Gson工厂解析器
                        .build();
            }
        }
        return mTestRetrofit;
    }

}
