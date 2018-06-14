package com.asura.customview_study.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.asura.customview_study.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liuxd on 2016/10/10 17:17.
 */

public class ScrollerAdapter extends BaseAdapter {
    private List<String> mStrings = new ArrayList<>();
    private Context mContext;

    public ScrollerAdapter(Context context, List<String> strings) {
        this.mContext = context;
        this.mStrings = strings;
    }

    @Override
    public int getCount() {
        return mStrings.size();
    }

    @Override
    public Object getItem(int position) {
        return mStrings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder=new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item, null);
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mTextView.setText(mStrings.get(position));
        if (position==0){
            viewHolder.mTextView.setBackgroundColor(Color.RED);
        }
        if (position==0){
            viewHolder.mTextView.setBackgroundColor(Color.RED);
        }

        return convertView;
    }

    class ViewHolder {
        private TextView mTextView;
    }
}
