package com.asura.customview_study.activity.canvas

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import com.asura.customview_study.R
import com.asura.customview_study.base.BaseActivity
import kotlinx.android.synthetic.main.activity_canvas_view.*

/**
 * CanvasView 页面
 *
 * @author Created by Asura on 2018/6/13 13:35.
 */
class CanvasViewActivity : BaseActivity() {

    lateinit var mutableList: MutableList<CanvasModel>

    override fun setLayoutId(): Int = R.layout.activity_canvas_view

    override fun initView() {
        mutableList = mutableListOf()

        mutableList.add(CanvasModel(R.string.title_draw_picture, R.layout.practice_picture));
        mutableList.add(CanvasModel(R.string.title_draw_color, R.layout.practice_color));
        mutableList.add(CanvasModel(R.string.title_draw_canvas, R.layout.practice_canvas));
        mutableList.add(CanvasModel(R.string.title_draw_circle, R.layout.practice_circle));
        mutableList.add(CanvasModel(R.string.title_draw_rect, R.layout.practice_rect));
        mutableList.add(CanvasModel(R.string.title_draw_point, R.layout.practice_point));
        mutableList.add(CanvasModel(R.string.title_draw_oval, R.layout.practice_oval));
        mutableList.add(CanvasModel(R.string.title_draw_line, R.layout.practice_line));
        mutableList.add(CanvasModel(R.string.title_draw_round_rect, R.layout.practice_round_rect));
        mutableList.add(CanvasModel(R.string.title_draw_path, R.layout.practice_path));
        mutableList.add(CanvasModel(R.string.title_draw_histogram, R.layout.practice_histogram));
        mutableList.add(CanvasModel(R.string.title_draw_pie_chart, R.layout.practice_pie_chart));
        mutableList.add(CanvasModel(R.string.title_draw_text, R.layout.practice_text));

        viewpager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                val fragment = CanvasFragment.newInstance(mutableList.get(position).layoutId)
                return fragment
            }

            override fun getCount(): Int {
                return mutableList.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return resources.getString(mutableList.get(position).titleRes)
            }

            override fun getItemId(position: Int): Long {
                return position.toLong()
            }
        }

        tabLayout.setupWithViewPager(viewpager)
    }
}