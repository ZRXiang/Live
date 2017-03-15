package com.zrx.live.network;

import android.text.TextUtils;

import com.zrx.live.InkeApp;
import com.zrx.live.utils.ContextUtil;
import com.zrx.live.utils.CookieUtil;
import com.zrx.live.utils.LogUtils;
import com.zrx.live.utils.UserAgentUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;


public class HttpClient {

    public static OkHttpClient mOkHttpClient;

    //短缓存有效期为60分钟
    public static final int CACHE_SHORT = 60 * 60;
    //长缓存有效期为7天
    public static final int CACHE_LONG = 60 * 60 * 24 * 7;

    //查询缓存的Cache-Control设置
    public static final String CACHE_CONTROL_AGE = "Cache-Control: public, max-age=";

    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    public static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_LONG;

    //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0时则不会使用缓存而请求服务器
    public static final String CACHE_CONTROL_NETWORK = "0";

    public static void initOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (HttpClient.class) {
                OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
                if (httpClientBuilder.interceptors() != null) {
                    httpClientBuilder.interceptors().clear();
                }

                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                // 指定缓存路径,缓存大小100Mb
                Cache cache = new Cache(new File(InkeApp.getContext().getCacheDir(), "YuYouBang"),
                        1024 * 1024 * 100);
                mOkHttpClient = httpClientBuilder.cache(cache)
                        .addInterceptor(cacheControlInterceptor)
                        .addNetworkInterceptor(cacheControlInterceptor)
                        .addInterceptor(loggingInterceptor)
                        .retryOnConnectionFailure(true)
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .readTimeout(15, TimeUnit.SECONDS)
                        .build();
            }
        }
    }

    private static Interceptor cacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String destUrlPath = request.url().toString();
            LogUtils.e("destUrl ---> " + destUrlPath);
            String destQuery = request.url().query();
            if (!TextUtils.isEmpty(destQuery)) {
                LogUtils.e("destQuery ---> " + destQuery);
            }
            if (!NetUtil.isNetworkConnected()) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetUtil.isNetworkConnected()) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder().header("Cache-Control", cacheControl)
                        .addHeader("Cookie", CookieUtil.createCookie())
                        .addHeader("User-Agent", UserAgentUtils.getUserAgent(ContextUtil.getInstance()))
                        .removeHeader("Pragma").build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_SHORT)
                        .addHeader("Cookie", CookieUtil.createCookie())
                        .addHeader("User-Agent", UserAgentUtils.getUserAgent(ContextUtil.getInstance()))
                        .removeHeader("Pragma").build();
            }
        }
    };
}
