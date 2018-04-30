package com.hutao.app.imageprivewproject.util;

import android.content.Context;

import java.io.File;

/**
 * Created by Administrator on 2018/4/29.
 */

public class FileUtil {


    public static final String IMAGE_CACHE_DIRS =  "imageCacheDir";//图片保存的文件夹目录名称

    /**
     * 图片文件夹
     * @param context
     * @return
     */
    public static final String getImageCacheDirsName(Context context) {
        return context.getExternalCacheDir() + "/" + IMAGE_CACHE_DIRS;
    }

    /**
     * 根据图片imageId查询图片本地地址
     */
    public static String getImagePath(String imageId, Context context) {
        File file = new File(getImageCacheDirsName(context));
        if (file == null && !file.exists()) return "";
        File[] list = file.listFiles();
        for (File f : list) {
            if (f != null && f.exists() && f.getName().equals(imageId)) {
                return f.getAbsolutePath();
            }
        }
        return "";
    }

}
