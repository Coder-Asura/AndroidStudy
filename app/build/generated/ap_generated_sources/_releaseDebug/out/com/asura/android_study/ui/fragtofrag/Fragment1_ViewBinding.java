// Generated code from Butter Knife. Do not modify!
package com.asura.android_study.ui.fragtofrag;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.asura.android_study.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Fragment1_ViewBinding implements Unbinder {
  private Fragment1 target;

  private View view7f08007a;

  @UiThread
  public Fragment1_ViewBinding(final Fragment1 target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.button1, "field 'mButton1' and method 'onClick'");
    target.mButton1 = Utils.castView(view, R.id.button1, "field 'mButton1'", Button.class);
    view7f08007a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick();
      }
    });
    target.mTextView1 = Utils.findRequiredViewAsType(source, R.id.textView1, "field 'mTextView1'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Fragment1 target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mButton1 = null;
    target.mTextView1 = null;

    view7f08007a.setOnClickListener(null);
    view7f08007a = null;
  }
}
