package com.asura.greendao_study.widgt;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.asura.greendao_study.utils.UIUtils;


/**
 * Created by Liuxd on 2016/9/8 11:12.
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpace;

    /**
     * 设置间距
     *
     * @param context 上下文
     * @param space   间距（dp）
     */
    public SpaceItemDecoration(Context context, float space) {
        this.mSpace = UIUtils.dip2px(context, space);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = mSpace;
    }
}
