package com.liuxd.baseapplibrary.base;

import androidx.appcompat.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Liuxd on 2016/11/8 14:55.
 * <p>
 * RecyclerView 通用的适配器
 * </p>
 * 通过convert（）抽象方法将holder及数据公布出去，以及基本的点击、长按事件监听处理
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private List<T> mDatas;
    private int mLayoutId;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    /**
     * @param datas    数据源
     * @param layoutId 布局id
     */
    public BaseAdapter(List<T> datas, int layoutId) {
        this.mDatas = datas;
        this.mLayoutId = layoutId;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        return new BaseViewHolder(parent.getContext(), itemView);
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, int position) {
        convert(holder, mDatas.get(position));
        //设置点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mOnItemClickListener.onItemClickListener(v, holder.getAdapterPosition());
                }

            });
        }
        //设置长按事件
        if (mOnItemLongClickListener == null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mOnItemLongClickListener.onItemLongClickListener(v, holder.getAdapterPosition());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public void setDatas(List<T> datas, boolean loadMore) {
        if (loadMore) {
            this.mDatas.addAll(datas);
        } else {
            this.mDatas = datas;
        }
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(View v, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClickListener(View v, int position);
    }

    public abstract void convert(BaseViewHolder holder, T data);
}
