package com.asura.android_study.activity.itemtype;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.asura.android_study.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Liuxd on 2016/11/20 18:22.
 */

public class MyAdapter extends RecyclerView.Adapter<AbstractHolder> {
    public static final int TYPE1 = 1;
    public static final int TYPE2 = 2;
    public static final int TYPE3 = 3;
    private List<DataModel1> list1;
    private List<DataModel2> list2;
    private List<DataModel3> list3;

    private Map<Integer, Integer> positions = new HashMap<>();
    private List<Integer> types = new ArrayList<>();

    MyAdapter() {

    }


    public void addList(List<DataModel1> list1, List<DataModel2> list2, List<DataModel3> list3) {
        this.list1 = list1;
        this.list2 = list2;
        this.list3 = list3;

        addTypeByList(TYPE1, list1);
        addTypeByList(TYPE2, list2);
        addTypeByList(TYPE3, list3);
    }

    private void addTypeByList(int type, List list) {
        positions.put(type, types.size());
        for (int i = 0; i < list.size(); i++) {
            types.add(type);
        }
    }

    @Override
    public AbstractHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE1:
                return new ViewHolder1(LayoutInflater.from(parent.getContext()).inflate(R.layout.itemtype1, parent, false));
            case TYPE2:
                return new ViewHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.itemtype2, parent, false));
            case TYPE3:
                return new ViewHolder3(LayoutInflater.from(parent.getContext()).inflate(R.layout.itemtype3, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(AbstractHolder holder, int position) {
        int viewType = getItemViewType(position);
        int realPosition = position - positions.get(viewType);
        switch (viewType) {
            case TYPE1:
                ((ViewHolder1) holder).onBind(list1.get(realPosition));
                break;
            case TYPE2:
                ((ViewHolder2) holder).onBind(list2.get(realPosition));
                break;
            case TYPE3:
                ((ViewHolder3) holder).onBind(list3.get(realPosition));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return types.size();
    }

    @Override
    public int getItemViewType(int position) {
        return types.get(position);
    }
}
