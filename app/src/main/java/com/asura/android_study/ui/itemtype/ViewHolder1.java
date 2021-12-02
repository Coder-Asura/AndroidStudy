package com.asura.android_study.ui.itemtype;

import android.view.View;
import android.widget.TextView;

import com.asura.android_study.R;


/**
 * Created by Liuxd on 2016/11/20 18:20.
 */

public class ViewHolder1 extends AbstractHolder<DataModel1> {
    public TextView mTextView;

    public ViewHolder1(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.tv_name);
    }

    @Override
    void onBind(DataModel1 model) {
        mTextView.setText(model.name);
    }
}
