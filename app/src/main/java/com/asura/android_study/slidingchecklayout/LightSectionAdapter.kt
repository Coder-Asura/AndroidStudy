package com.asura.android_study.slidingchecklayout;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.asura.android_study.R;
import com.asura.android_study.model.LightSection;
import com.asura.android_study.model.SectionShape;
import com.asura.android_study.slidingchecklayout.viewholder.AbstractHolder;
import com.asura.android_study.slidingchecklayout.viewholder.LightSectionStartViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by homgwu on 2018/2/2.
 */

public class LightSectionAdapter extends RecyclerView.Adapter<AbstractHolder<LightSection>> {
    private List<LightSection> mDataList = new ArrayList<>();

    public LightSectionAdapter(List<LightSection> dataList) {
        mDataList = dataList;
    }

    public LightSection getEntityByPosition(int position) {
        return mDataList.get(position);
    }


    public void setmDataList(List<LightSection> mDataList) {
        this.mDataList = mDataList;
        notifyDataSetChanged();
    }

    @Override
    public AbstractHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == SectionShape.SHAPE_TURNING_LEFT.ordinal()
                || viewType == SectionShape.SHAPE_TURNING_RIGHT.ordinal()) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_rv2, parent, false);
            return new LightSectionStartViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_rv, parent, false);
            return new LightSectionStartViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final AbstractHolder holder, final int position) {
        LightSection lightSection = mDataList.get(holder.getAdapterPosition());
        holder.onBind(lightSection);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightSection.setCheck(!lightSection.getCheck());
                notifyItemChanged(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return mDataList.get(position).getShape().ordinal();
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

}
