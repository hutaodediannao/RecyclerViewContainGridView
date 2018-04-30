package com.hutao.app.imageprivewproject.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hutao.app.imageprivewproject.R;

import java.io.File;

/**
 * Created by Administrator on 2018/4/29.
 */

public class ImageLoaderUtil {


    /**
     * 加载本地和网络图片
     * @param iv
     * @param imagePath
     * @param width
     * @param height
     * @param context
     */
    public static void loadImage(ImageView iv, String imagePath, int width, int height, Context context) {
        if (StringUtil.isEntryOrNull(imagePath))return;
        if (imagePath.contains("http://") || imagePath.contains("https://")) {
            Glide.with(context)
                    .load(imagePath)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .skipMemoryCache(false)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .override(DisplayUtil.dip2px(context, width), DisplayUtil.dip2px(context, height))
                    .into(iv);
        } else {
            File file = new File(imagePath);
            if (file.exists()) {
                Glide.with(context)
                        .load(file)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .skipMemoryCache(true)
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .override(DisplayUtil.dip2px(context, width), DisplayUtil.dip2px(context, height))
                        .into(iv);
            }
        }
    }

    public static void loadImage2(ImageView iv, String imagePath,Context context) {
//       loadImage(iv, imagePath, 80, 80, context);

        File file = new File(imagePath);
        if (file.exists()) {
            Glide.with(context)
                    .load(file)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .skipMemoryCache(false)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(iv);
        }
    }

}
