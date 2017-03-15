package com.zrx.live.api;

import com.zrx.live.bean.LiveBean;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by xiaoyuan on 17/3/10.
 */

public interface HomeApi {
    @POST(UrlConfig.LIVE_HOME)
    Call<LiveBean> get_live(@Body FormBody body);
}
