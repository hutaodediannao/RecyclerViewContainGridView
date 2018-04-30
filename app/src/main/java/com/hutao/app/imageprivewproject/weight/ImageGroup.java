package com.hutao.app.imageprivewproject.weight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.hutao.app.imageprivewproject.R;
import com.hutao.app.imageprivewproject.activity.ImageDisplayActivity;
import com.hutao.app.imageprivewproject.dao.daoHelper.ImageModelHelper;
import com.hutao.app.imageprivewproject.model.ImageModel;
import com.hutao.app.imageprivewproject.util.FileUtil;
import com.hutao.app.imageprivewproject.util.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/29.
 */

public class ImageGroup extends FrameLayout implements View.OnClickListener{

    private int mChildViewPosition;
    private String mSapCode;//所属于的sapCede定位
    private List<ImageModel> mImagePathList = new ArrayList<>();//图片文件路径集合

    private View mRootView;
    private GridLayout gridLayout;//图片容器
    private ImageView iv0, iv1, iv2, ivDel0, ivDel1, ivDel2;
    private View itemView0, itemView1, itemView2, ivAdd;

    public ImageGroup(@NonNull Context context) {
        this(context, null);
    }

    public ImageGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initConfig();
        setListener();
    }

    public List<ImageModel> getmImagePathList() {
        return mImagePathList;
    }

    private void setListener() {
        ivDel0.setOnClickListener(this);
        ivDel1.setOnClickListener(this);
        ivDel2.setOnClickListener(this);
        ivAdd.setOnClickListener(this);

        iv0.setOnClickListener(this);
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
    }

    private void initConfig() {
        mRootView = LayoutInflater.from(getContext()).inflate(R.layout.image_group_lay, null);
        gridLayout = mRootView.findViewById(R.id.imageGroup);
        itemView0 = gridLayout.findViewById(R.id.item0);
        itemView1 = gridLayout.findViewById(R.id.item1);
        itemView2 = gridLayout.findViewById(R.id.item2);
        iv0 = itemView0.findViewById(R.id.iv);
        iv1 = itemView1.findViewById(R.id.iv);
        iv2 = itemView2.findViewById(R.id.iv);
        ivDel0 = itemView0.findViewById(R.id.ivDelete);
        ivDel1 = itemView1.findViewById(R.id.ivDelete);
        ivDel2 = itemView2.findViewById(R.id.ivDelete);
        ivAdd = mRootView.findViewById(R.id.ivAdd);
        this.addView(mRootView);
    }

    public void updateImageContent(String sapCode, int childViewPosition) {
        this.mSapCode = sapCode;
        this.mChildViewPosition = childViewPosition;
        this.mImagePathList = ImageModelHelper.queryImageModelListBySapCode(mSapCode);//根据sapCode查询得到图片集合
        switch (mImagePathList.size()) {
            case 0:
                itemView0.setVisibility(GONE);
                itemView1.setVisibility(GONE);
                itemView2.setVisibility(GONE);
                ivAdd.setVisibility(VISIBLE);
                break;
            case 1:
                itemView0.setVisibility(VISIBLE);
                itemView1.setVisibility(GONE);
                itemView2.setVisibility(GONE);
                ivAdd.setVisibility(VISIBLE);
                ImageLoaderUtil.loadImage2(iv0, FileUtil.getImagePath(mImagePathList.get(0).getImageId(), getContext()), getContext());
                break;
            case 2:
                itemView0.setVisibility(VISIBLE);
                itemView1.setVisibility(VISIBLE);
                itemView2.setVisibility(GONE);
                ivAdd.setVisibility(VISIBLE);
                ImageLoaderUtil.loadImage2(iv0,  FileUtil.getImagePath(mImagePathList.get(0).getImageId(), getContext()), getContext());
                ImageLoaderUtil.loadImage2(iv1,  FileUtil.getImagePath(mImagePathList.get(1).getImageId(), getContext()), getContext());
                break;
            case 3:
                itemView0.setVisibility(VISIBLE);
                itemView1.setVisibility(VISIBLE);
                itemView2.setVisibility(VISIBLE);
                ivAdd.setVisibility(GONE);
                ImageLoaderUtil.loadImage2(iv0,  FileUtil.getImagePath(mImagePathList.get(0).getImageId(), getContext()), getContext());
                ImageLoaderUtil.loadImage2(iv1,  FileUtil.getImagePath(mImagePathList.get(1).getImageId(), getContext()),getContext());
                ImageLoaderUtil.loadImage2(iv2,  FileUtil.getImagePath(mImagePathList.get(2).getImageId(), getContext()),getContext());
                break;
        }
    }

    public void updateImageContent2(List<ImageModel> imageModels) {
        this.mImagePathList = imageModels;
        switch (mImagePathList.size()) {
            case 0:
                itemView0.setVisibility(GONE);
                itemView1.setVisibility(GONE);
                itemView2.setVisibility(GONE);
                ivAdd.setVisibility(VISIBLE);
                break;
            case 1:
                itemView0.setVisibility(VISIBLE);
                itemView1.setVisibility(GONE);
                itemView2.setVisibility(GONE);
                ivAdd.setVisibility(VISIBLE);
                ImageLoaderUtil.loadImage2(iv0, FileUtil.getImagePath(mImagePathList.get(0).getImageId(), getContext()), getContext());
                break;
            case 2:
                itemView0.setVisibility(VISIBLE);
                itemView1.setVisibility(VISIBLE);
                itemView2.setVisibility(GONE);
                ivAdd.setVisibility(VISIBLE);
                ImageLoaderUtil.loadImage2(iv0,  FileUtil.getImagePath(mImagePathList.get(0).getImageId(), getContext()), getContext());
                ImageLoaderUtil.loadImage2(iv1,  FileUtil.getImagePath(mImagePathList.get(1).getImageId(), getContext()), getContext());
                break;
            case 3:
                itemView0.setVisibility(VISIBLE);
                itemView1.setVisibility(VISIBLE);
                itemView2.setVisibility(VISIBLE);
                ivAdd.setVisibility(GONE);
                ImageLoaderUtil.loadImage2(iv0,  FileUtil.getImagePath(mImagePathList.get(0).getImageId(), getContext()), getContext());
                ImageLoaderUtil.loadImage2(iv1,  FileUtil.getImagePath(mImagePathList.get(1).getImageId(), getContext()),getContext());
                ImageLoaderUtil.loadImage2(iv2,  FileUtil.getImagePath(mImagePathList.get(2).getImageId(), getContext()),getContext());
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (v == ivDel0) {
            if (mCallbackInterListener != null) mCallbackInterListener.delete(mChildViewPosition,0, mSapCode, mImagePathList.get(0));
        } else if (v == ivDel1) {
            if (mCallbackInterListener != null) mCallbackInterListener.delete(mChildViewPosition, 1,mSapCode, mImagePathList.get(1));
        } else if (v == ivDel2) {
            if (mCallbackInterListener != null) mCallbackInterListener.delete(mChildViewPosition, 2,mSapCode, mImagePathList.get(2));
        } else if (v == ivAdd) {
            if (mCallbackInterListener != null) mCallbackInterListener.add(mChildViewPosition, mSapCode);
        } else if (v == iv0) {
            ImageDisplayActivity.startActivity(0, getIamgePahtList(), getContext());
        } else if (v == iv1) {
            ImageDisplayActivity.startActivity(1, getIamgePahtList(), getContext());
        } else if (v == iv2) {
            ImageDisplayActivity.startActivity(2, getIamgePahtList(), getContext());
        }
    }

    private ArrayList<String> getIamgePahtList() {
        ArrayList<String> imgList = new ArrayList<>();
        for (ImageModel imageModel: mImagePathList) {
            imgList.add(imageModel.getImageId());
        }
        return imgList;
    }

    public interface CallbackInterListener{
        void delete(int itemPosition, int childGroupPosition, String sapCode, ImageModel imageModel);
        void add(int childPosition, String sapCode);
    }

    private CallbackInterListener mCallbackInterListener;

    public void setCallbackInterListener(CallbackInterListener callbackInterListener) {
        this.mCallbackInterListener = callbackInterListener;
    }
}
