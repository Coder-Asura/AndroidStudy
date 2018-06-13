package com.asura.android_study.activity.itemtype;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Liuxd on 2016/11/20 18:19.
 */

public abstract class AbstractHolder<T> extends RecyclerView.ViewHolder {

    public AbstractHolder(View itemView) {
        super(itemView);
    }

    abstract void onBind(T model);
}
