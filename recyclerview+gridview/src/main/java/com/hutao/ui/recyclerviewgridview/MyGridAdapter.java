package com.hutao.ui.recyclerviewgridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2018/5/13.
 */

public class MyGridAdapter extends BaseAdapter {

    private CarType carType;
    private Context mContext;
    private List<String> mList;

    public MyGridAdapter(CarType carType, Context mContext) {
        this.carType = carType;
        this.mList = this.carType.getCarPhotoList();
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View contertView, ViewGroup viewGroup) {
        ViewHolder vh = null;
        if (null == contertView) {
            contertView = LayoutInflater.from(mContext).inflate(R.layout.item_gridview, null, false);
            vh = new ViewHolder();
            vh.ivIcon = contertView.findViewById(R.id.ivItem);
            vh.ivDelete = contertView.findViewById(R.id.ivDelete);
            contertView.setTag(vh);
        } else {
            vh = (ViewHolder) contertView.getTag();
        }

        Glide.with(mContext)
                .asBitmap()
                .load(mList.get(i))
                .into(vh.ivIcon);

        vh.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //开始删除并刷星数据源
                mList.remove(i);
                notifyDataSetChanged();
                Toast.makeText(mContext, "删除成功" + i, Toast.LENGTH_SHORT).show();
                if (clickListener != null) {
                    clickListener.deleteItem(i);
                }
            }
        });

        vh.ivIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.clickItem(i);
                }
            }
        });

        return contertView;
    }

    class ViewHolder {
        ImageView ivIcon, ivDelete;
    }

    public interface ClickListener {
        void clickItem(int position);

        void deleteItem(int position);
    }

    private ClickListener clickListener;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
