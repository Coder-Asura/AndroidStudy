package com.asura.android_study.ui.itemtype;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Liuxd on 2016/11/20 18:19.
 */

public abstract class AbstractHolder<T> extends RecyclerView.ViewHolder {

    public AbstractHolder(View itemView) {
        super(itemView);
    }

    abstract void onBind(T model);
}
