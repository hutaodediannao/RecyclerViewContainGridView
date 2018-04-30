package com.hutao.app.imageprivewproject.util;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * Created by Administrator on 2018/4/29.
 */

public class MyGlideModule implements GlideModule {
    @Override
    public void applyOptions(final Context context, GlideBuilder builder) {

        //设置硬盘缓存
        builder .setDiskCache(new DiskCache.Factory() {
            @Override
            public DiskCache build() {
                // Careful: the external cache directory doesn't enforce permissions
                File cacheLocation = new File(context.getExternalCacheDir(), "huTaoImageCache");
                if (!cacheLocation.exists())
                cacheLocation.mkdirs();
                return DiskLruCacheWrapper.get(cacheLocation, 1024*1024*300);//100MB缓存图片
            }
        });

        //设置内存缓存
        builder.setMemoryCache(new LruResourceCache(1024*1024*80));//内存缓存80MB

//        在默认情况下，Glide使用RGB_565格式加载图片，如果想要使用高质量的图片，可以通过如下方式设置系统的图片格式：
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);

    }
    @Override
    public void registerComponents(Context context, Glide glide) {
        // register ModelLoaders here.
    }
}

