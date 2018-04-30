package com.hutao.app.imageprivewproject.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;
import com.hutao.app.imageprivewproject.R;
import com.hutao.app.imageprivewproject.util.ImageLoaderUtil;
import com.hutao.app.imageprivewproject.weight.ViewPagerFixed;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片滑动查看页面
 */
public class ImageDisplayActivity extends AppCompatActivity {

    private ViewPagerFixed  viwePager;
    private List<String> imagePathList = new ArrayList<>();//图片列表集合
    public static final String CURRENT_PAGE_INDEX = "index";
    public static final String IMAGE_LIST = "imageList";
    private int pageIndex = 0;
    private ViewPageAdapter imageWatchAdapter;

    public static void startActivity(int index, ArrayList<String> imagePathList, Context context) {
        Intent intent = new Intent(context, ImageDisplayActivity.class);
        intent.putExtra(CURRENT_PAGE_INDEX, index);
        intent.putStringArrayListExtra(IMAGE_LIST, imagePathList);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);

        parseIntent();
        initView();
        setAdapter();
    }

    private void setAdapter() {
        imageWatchAdapter = new ViewPageAdapter(this, imagePathList);
        viwePager.setAdapter(imageWatchAdapter);
        viwePager.setCurrentItem(pageIndex);
    }

    private void initView() {
        viwePager = findViewById(R.id.vp);
    }

    private void parseIntent() {
        imagePathList = getIntent().getStringArrayListExtra(IMAGE_LIST);
        pageIndex = getIntent().getIntExtra(CURRENT_PAGE_INDEX, -1);
    }

}
