package com.asura.android_study.ui.viewpager.multi

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.asura.android_study.R
import com.asura.android_study.ui.viewpager.*

/**
 * Author: Asuraliu
 * Date: 2021/10/28 20:38
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * Asuraliu 2021/10/28 1.0 首次创建
 */
class ViewpagerLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    var mFragmentList: MutableList<BaseLazyFragment>? = null
    private var mLazyFragment1: LazyFragment1? = null
    private var mLazyFragment2: LazyFragment2? = null
    private var mLazyFragment3: LazyFragment3? = null
    private var mLazyFragment4: LazyFragment4? = null
    private var mAdapter: MyPagerAdapter? = null

    var mViewpager: ViewPager? = null


    init {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_viewpager, this, true)
        mFragmentList = mutableListOf()
        mFragmentList!!.add(LazyFragment1.create("点了1").also { mLazyFragment1 = it })
        mFragmentList!!.add(LazyFragment2.create("点了2").also { mLazyFragment2 = it })
        mFragmentList!!.add(LazyFragment3.create("点了3").also { mLazyFragment3 = it })
        mFragmentList!!.add(LazyFragment4.create("点了4").also { mLazyFragment4 = it })
        mViewpager = view.findViewById<ViewPager>(R.id.viewpager)


        mViewpager!!.setOffscreenPageLimit(4)

    }

    fun initView(fm: FragmentManager, id: Int) {
        mViewpager!!.id = id
        mViewpager!!.setAdapter(MyPagerAdapter(fm, mFragmentList, null).also { mAdapter = it })
    }
}