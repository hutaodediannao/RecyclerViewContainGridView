package com.hutao.app.imageprivewproject.adapter.childAdapter;

import android.content.Context;

import com.hutao.app.imageprivewproject.R;
import com.hutao.app.imageprivewproject.adapter.CommonAdapter;
import com.hutao.app.imageprivewproject.adapter.CommonViewHolder;
import com.hutao.app.imageprivewproject.model.Model;
import com.hutao.app.imageprivewproject.weight.ImageGroup;

import java.util.List;

/**
 * Created by Administrator on 2018/4/29.
 */

public class ModelAdapter extends CommonAdapter<Model> {

    private ImageGroup.CallbackInterListener mCallbackInterListener;

    public ModelAdapter(List<Model> mList, Context mContext,ImageGroup.CallbackInterListener callbackInterListener) {
        super(mList, mContext);
        this.mCallbackInterListener = callbackInterListener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_lay;
    }

    @Override
    public void bindHolder(CommonViewHolder holder, int position, Model model) {
        holder.setTextView(R.id.tvTitle, model.getTitle())
                .setTextView(R.id.tvContent, model.getContent());
        ImageGroup ig = holder.getView(R.id.imageGroup);
        ig.setCallbackInterListener(mCallbackInterListener);//必须要先设置监听，才设置图片
        holder.loadImageGroup(R.id.imageGroup, model.getSapCode(), position);
    }
}
