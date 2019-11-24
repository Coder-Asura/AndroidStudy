package com.asura.android_study.activity.itemtype;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asura.android_study.R;
import com.asura.android_study.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liuxd on 2016/11/20 18:28.
 */

public class ItemTypeActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;

    @Override
    public int setLayoutId() {
        return R.layout.activity_itemtype;
    }

    @Override
    public void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (mRecyclerView.getAdapter().getItemViewType(position) == MyAdapter.TYPE1) {
                    return 1;
                } else if (mRecyclerView.getAdapter().getItemViewType(position) == MyAdapter.TYPE2) {
                    return 2;
                } else {
                    return gridLayoutManager.getSpanCount();
                }
            }
        });
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 20;
                GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
                int spanSize = layoutParams.getSpanSize();
                int spanIndex = layoutParams.getSpanIndex();
                if (spanSize == 1) {
                    if (spanIndex == 0) {
                        outRect.right = 10;
                    } else if (spanIndex == 3) {
                        outRect.left = 10;
                    } else {
                        outRect.left = 10;
                        outRect.right = 10;
                    }
                } else if (spanSize == 2) {
                    if (spanIndex == 0) {
                        outRect.right = 10;
                    } else {
                        outRect.left = 10;
                    }
                } else {

                }
            }
        });
        mRecyclerView.setAdapter(mAdapter = new MyAdapter());
        initData();
    }

    private void initData() {
        int[] colors = new int[]{android.R.color.holo_red_dark, android.R.color.holo_green_dark, android.R.color.holo_blue_bright,};
        List<DataModel1> list1 = new ArrayList<>();
        List<DataModel2> list2 = new ArrayList<>();
        List<DataModel3> list3 = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i < 10) {
                DataModel1 model1 = new DataModel1();
                model1.name = "name" + i;
                list1.add(model1);
            } else if (i < 30) {
                DataModel2 model2 = new DataModel2();
                model2.name = "name name" + i;
                model2.age = i;
                list2.add(model2);
            } else {
                DataModel3 modle3 = new DataModel3();
                modle3.name = "name name name" + i;
                modle3.age = i;
                modle3.color = colors[i % 3];
                list3.add(modle3);
            }
        }
        mAdapter.addList(list1, list2, list3);
        mAdapter.notifyDataSetChanged();
    }
}
