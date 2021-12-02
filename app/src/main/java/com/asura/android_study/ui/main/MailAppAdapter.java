package com.asura.android_study.ui.main;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.asura.android_study.R;

import java.util.List;

/**
 * Created by Asura on 2017/2/14 10:55.
 */

public class MailAppAdapter extends BaseAdapter {
    private List<ResolveInfo> mResolveInfos;
    private Context mContext;

    public MailAppAdapter(Context context, List<ResolveInfo> resolveInfos) {
        this.mContext = context;
        this.mResolveInfos = resolveInfos;
    }

    @Override
    public int getCount() {
        return mResolveInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return mResolveInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_mail_app, null);
            viewHolder = new ViewHolder();
            viewHolder.logo = (ImageView) convertView.findViewById(R.id.img_logo);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.logo.setImageDrawable(mResolveInfos.get(position).activityInfo.loadIcon(mContext.getPackageManager()));
        viewHolder.name.setText(mResolveInfos.get(position).activityInfo.loadLabel(mContext.getPackageManager()));
        return convertView;
    }

    private static class ViewHolder {
        private ImageView logo;
        private TextView name;
    }
}
