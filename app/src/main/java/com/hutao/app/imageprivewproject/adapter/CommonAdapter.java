package com.hutao.app.imageprivewproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2018/4/29.
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {

    public List<T> mList;
    public Context mContext;

    public CommonAdapter(List<T> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    public void updateData(List<T> updateList) {
        this.mList = updateList;
        notifyDataSetChanged();
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonViewHolder.commonViewHolderNewInstance(mContext, parent, getLayoutId());
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        bindHolder(holder, position, mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public abstract int getLayoutId();

    public abstract void bindHolder(CommonViewHolder holder, int position, T t);
}
