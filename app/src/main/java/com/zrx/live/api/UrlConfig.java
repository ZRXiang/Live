package com.zrx.live.api;


/**
 * 此类是api管理类
 */
public class UrlConfig {

    public static String TestHostUrl;
    public static String TestShareUrl;

    // 0测试环境
    public static final int Test = 0;
    // 1线上环境
    public static final int Online = 1;

    static {
        switch (Online) {
            case Test:
                TestHostUrl = "http://121.42.26.175:14444/";
                TestShareUrl = "http://121.42.26.175:14444/";
                break;
            case Online:
                TestHostUrl = "http://121.42.26.175:14444/";
                TestShareUrl = "http://121.42.26.175:14444/";
                break;
        }
    }

    public final static String UTIL_FILE_UPLOAD = "util/file/upload.json";
    //首页接口
    public final static String LIVE_HOME = "live/find.json";




}
