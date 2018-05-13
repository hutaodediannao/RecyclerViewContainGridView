package com.hutao.ui.recyclerviewgridview;

import android.content.Context;
import com.hutao.app.passkeyadapter.adapter.PassKeyRecyclerAdapter;
import com.hutao.app.passkeyadapter.viewHolder.PassKeyViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/5/13.
 */

public class MyAdapter extends PassKeyRecyclerAdapter<Car>{

    private Context mContext;

    public MyAdapter(List<Car> mList, Context context) {
        super(mList, context);
        this.mContext = context;
    }

    @Override
    public int getLayout() {
        return R.layout.item_recyclerview;
    }

    @Override
    public void bindHolder(PassKeyViewHolder holder, Car car, final int position) {
        holder.setTextView(R.id.tvCar, car.getCountry());

        MyGridView gv = holder.getView(R.id.gridView);
        MyGridAdapter gridAdapter = new MyGridAdapter(car.getCarType(), mContext);
        gv.setAdapter(gridAdapter);

        gridAdapter.setClickListener(new MyGridAdapter.ClickListener() {
            @Override
            public void clickItem(int childPosition) {
                if (clickGroupListener != null) clickGroupListener.click(position, childPosition);
            }

            @Override
            public void deleteItem(int childPosition) {
                if (clickGroupListener != null) clickGroupListener.delete(position, childPosition);
            }
        });
    }

    public interface ClickGroupListener{
        void click(int groupPosition, int childPosition);

        void delete(int groupPosition, int childPosition);
    }

    private ClickGroupListener clickGroupListener;

    public void setClickGroupListener(ClickGroupListener clickGroupListener) {
        this.clickGroupListener = clickGroupListener;
    }
}
