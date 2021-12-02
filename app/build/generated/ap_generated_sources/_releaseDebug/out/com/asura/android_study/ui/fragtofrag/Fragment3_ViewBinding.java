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

public class Fragment3_ViewBinding implements Unbinder {
  private Fragment3 target;

  private View view7f08007b;

  @UiThread
  public Fragment3_ViewBinding(final Fragment3 target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.button2, "field 'mButton2' and method 'onClick'");
    target.mButton2 = Utils.castView(view, R.id.button2, "field 'mButton2'", Button.class);
    view7f08007b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick();
      }
    });
    target.mTextView2 = Utils.findRequiredViewAsType(source, R.id.textView2, "field 'mTextView2'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Fragment3 target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mButton2 = null;
    target.mTextView2 = null;

    view7f08007b.setOnClickListener(null);
    view7f08007b = null;
  }
}
