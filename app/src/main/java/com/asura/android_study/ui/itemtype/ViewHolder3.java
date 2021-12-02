package com.asura.android_study.ui.itemtype;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.asura.android_study.R;


/**
 * Created by Liuxd on 2016/11/20 18:20.
 */

public class ViewHolder3 extends AbstractHolder<DataModel3> {
    public TextView mTextView;
    public Button mButton;
    public Button mButton2;

    public ViewHolder3(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.tv_name);
        mButton = (Button) itemView.findViewById(R.id.button);
        mButton2 = (Button) itemView.findViewById(R.id.button2);
    }

    @Override
    void onBind(DataModel3 model) {
        mTextView.setText(model.name);
        mButton.setText(model.age+ "岁");
        mButton2.setBackgroundResource(model.color);
    }
}
