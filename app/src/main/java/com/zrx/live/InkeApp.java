package com.zrx.live;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.zrx.live.network.HttpClient;
import com.zrx.live.utils.ContextUtil;

import java.io.File;

/**
 * Created by xiaoyuan on 17/2/28.
 */

public class InkeApp extends Application {

    private static Context mApplicationContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext= this;
        initImageLoaderCache();
        ContextUtil.init(this);
        HttpClient.initOkHttpClient();

    }



    /**
     * 初始化imgloader
     */
    public void initImageLoaderCache() {
        File cacheDir = StorageUtils.getOwnCacheDirectory(
                getApplicationContext(), "ImageLoader/Cache");

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .memoryCacheExtraOptions(720, 1280)
                // default = device screen dimensions
                .diskCacheExtraOptions(720, 1280, null)
                .memoryCache(new WeakMemoryCache())
//                .memoryCacheSize(2 * 1024 * 1024)
//                .memoryCacheSizePercentage(13)
                // default
                .diskCache(new UnlimitedDiskCache(cacheDir))
                // default
//                .diskCacheSize(50 * 1024 * 1024)
//                .diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                // default
                .imageDownloader(
                        new BaseImageDownloader(getApplicationContext())) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .build();
        ImageLoader.getInstance().init(config);
    }

    // 获取ApplicationContext
    public static Context getContext() {
        return mApplicationContext;
    }

}
