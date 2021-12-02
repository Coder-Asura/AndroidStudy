// Generated code from Butter Knife. Do not modify!
package com.asura.android_study.ui.viewpager;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.asura.android_study.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LazyFragment4_ViewBinding implements Unbinder {
  private LazyFragment4 target;

  @UiThread
  public LazyFragment4_ViewBinding(LazyFragment4 target, View source) {
    this.target = target;

    target.mTv = Utils.findRequiredViewAsType(source, R.id.tv, "field 'mTv'", TextView.class);
    target.tv_name = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tv_name'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LazyFragment4 target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTv = null;
    target.tv_name = null;
  }
}
