// Generated code from Butter Knife. Do not modify!
package com.asura.android_study.ui.viewpager.multi;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.asura.android_study.R;
import com.google.android.material.tabs.TabLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MultiViewPagerActivity_ViewBinding implements Unbinder {
  private MultiViewPagerActivity target;

  @UiThread
  public MultiViewPagerActivity_ViewBinding(MultiViewPagerActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MultiViewPagerActivity_ViewBinding(MultiViewPagerActivity target, View source) {
    this.target = target;

    target.mViewpager = Utils.findRequiredViewAsType(source, R.id.viewpager, "field 'mViewpager'", ViewPager.class);
    target.mTabLayout = Utils.findRequiredViewAsType(source, R.id.tab_layout, "field 'mTabLayout'", TabLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MultiViewPagerActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mViewpager = null;
    target.mTabLayout = null;
  }
}
