package com.asura.android_study.slidingchecklayout

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.asura.android_study.R
import com.asura.android_study.databinding.ActivitySlidingCheckLayoutBinding
import com.asura.android_study.utils.LightSectionsUtils

/**
 * Author: Asuraliu
 * Date: 2021/11/2 10:08
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * Asuraliu 2021/11/2 1.0 首次创建
 */
class SlidingCheckLayoutActivity : AppCompatActivity() {
    var binding: ActivitySlidingCheckLayoutBinding? = null

    var count = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivitySlidingCheckLayoutBinding>(this, R.layout.activity_sliding_check_layout)
        binding?.btnDes?.setOnClickListener {
            count--
            LightSectionsUtils.getLightSections(count = count)
        }
        binding?.btnInc?.setOnClickListener {
            count++
            LightSectionsUtils.getLightSections(count = count)
        }
        binding?.etCount?.let {
            with(it) {
                setText(count.toString())
                imeOptions = EditorInfo.IME_ACTION_DONE
                setOnEditorActionListener { v, actionId, event ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        count = text.toString().toInt()
                        LightSectionsUtils.getLightSections(count = count)
                        true
                    } else {
                        false
                    }
                }
            }
        }
    }
}