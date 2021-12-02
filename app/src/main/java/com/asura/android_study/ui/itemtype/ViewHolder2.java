package com.asura.android_study.ui.itemtype;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.asura.android_study.R;


/**
 * Created by Liuxd on 2016/11/20 18:20.
 */

public class ViewHolder2 extends AbstractHolder<DataModel2> {
    public TextView mTextView;
    public Button mButton;

    public ViewHolder2(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.tv_name);
        mButton = (Button) itemView.findViewById(R.id.btn);
    }

    @Override
    void onBind(DataModel2 model) {
        mTextView.setText(model.name);
        mButton.setText(model.age + "岁");
    }
}
