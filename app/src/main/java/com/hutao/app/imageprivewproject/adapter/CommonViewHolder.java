package com.hutao.app.imageprivewproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hutao.app.imageprivewproject.util.ImageLoaderUtil;
import com.hutao.app.imageprivewproject.weight.ImageGroup;

/**
 * Created by Administrator on 2018/4/29.
 */

public class CommonViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViewSparseArray;
    private View mContentView;
    private Context mContext;

    public static CommonViewHolder commonViewHolderNewInstance(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new CommonViewHolder(itemView, context);
    }

    private CommonViewHolder(View itemView, Context context) {
        super(itemView);
        this.mContext = context;
        this.mContentView = itemView;
        this.mViewSparseArray = new SparseArray<>();
    }

    public <T> T getView(int viewId) {
        T v = (T) mViewSparseArray.get(viewId);
        if (v == null) {
            v = (T) mContentView.findViewById(viewId);
            mViewSparseArray.put(viewId, (View) v);
        }
        return v;
    }

    //设置textView
    public CommonViewHolder setTextView(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    //加载图片
    public CommonViewHolder setImageView(int imageViewId, String imagePath) {
        ImageView iv = getView(imageViewId);
        ImageLoaderUtil.loadImage(iv, imagePath, 50, 50, mContext);
        return this;
    }

    //加载图片容器
    public CommonViewHolder loadImageGroup(int viewId, String sapCode, int childViewPosition) {
        ImageGroup ig = getView(viewId);
        ig.updateImageContent(sapCode, childViewPosition);
        return this;
    }
}
