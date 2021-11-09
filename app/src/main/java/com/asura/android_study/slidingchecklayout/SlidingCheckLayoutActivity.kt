package com.asura.android_study.slidingchecklayout

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.rangeTo
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.asura.android_study.R
import com.asura.android_study.databinding.ActivitySlidingCheckLayoutBinding
import com.asura.android_study.model.LightSection
import com.asura.android_study.utils.LightSectionsUtils
import com.asura.android_study.view.SlidingCheckLayout

/**
 * Author: Asuraliu
 * Date: 2021/11/2 10:08
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * Asuraliu 2021/11/2 1.0 首次创建
 */
class SlidingCheckLayoutActivity : AppCompatActivity(), SlidingCheckLayout.OnSlidingPositionListener {
    var binding: ActivitySlidingCheckLayoutBinding? = null

    var count = 29
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivitySlidingCheckLayoutBinding>(this, R.layout.activity_sliding_check_layout)
        binding?.btnDes?.setOnClickListener {
            count--
            refresh()
        }
        binding?.btnInc?.setOnClickListener {
            count++
            refresh()
        }
        binding?.etCount?.let {
            with(it) {
                setText(count.toString())
                imeOptions = EditorInfo.IME_ACTION_DONE
                setOnEditorActionListener { v, actionId, event ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        count = text.toString().toInt()
                        refresh()
                        true
                    } else {
                        false
                    }
                }
            }
        }

        binding?.scl?.setOnSlidingPositionListener(this)
        binding?.scl?.setNeedLongPress(false)
        staggeredGridLayoutManager = StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL)
        mLightSectionAdapter = LightSectionAdapter(createData())
        binding?.rv?.layoutManager = staggeredGridLayoutManager
        binding?.rv?.adapter = mLightSectionAdapter
    }

    private fun refresh() {
        binding?.etCount?.setText(count.toString())
        mLightSectionAdapter?.setmDataList(LightSectionsUtils.getLightSections(count = count))
    }

    private var staggeredGridLayoutManager: StaggeredGridLayoutManager? = null
    private var mLightSectionAdapter: LightSectionAdapter? = null


    private fun createData(): MutableList<LightSection> {
        return LightSectionsUtils.getLightSections(count = count) ?: mutableListOf()
    }

    override fun onSlidingStart(position: Int) {
        Log.d("asuralxd", "onSlidingStart $position")
        val entity = mLightSectionAdapter!!.getEntityByPosition(position)
        if (entity.index != -1) {
            entity.check = !entity.check
            mLightSectionAdapter!!.notifyItemChanged(position)
        }
        //        Log.d("asuralxd", binding?.rv?.getChildAt(0)?.getWidth().toString() + "," + binding?.rv?.getChildAt(0)?.getHeight())
    }

    override fun onSlidingRangePosition(startPosition: Int, endPosition: Int) {
        val startIndex = mLightSectionAdapter!!.getDataList()[startPosition].index
        val endIndex = mLightSectionAdapter!!.getDataList()[endPosition].index
        Log.d("asuralxd", "onSlidingRangePosition $startPosition,$endPosition,$startIndex,$endIndex ")
        if (startIndex != -1 && endIndex != -1) {
            mLightSectionAdapter!!.getDataList().forEach {
                if (it.index != -1) {
                    if (startIndex < endIndex) {
                        it.check = it.index in startIndex..endIndex
                    } else {
                        it.check = it.index in endIndex..startIndex
                    }
                }
            }
            mLightSectionAdapter!!.notifyDataSetChanged()
        }

    }

    override fun onSlidingEnd(endPosition: Int) {
        Log.d("asuralxd", "onSlidingEnd $endPosition")

    }
}