package com.asura.customview_study.activity.canvas

import android.os.Bundle
import android.support.annotation.LayoutRes
import androidx.core.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asura.customview_study.R
import kotlinx.android.synthetic.main.fragment_canvas.*

/**
 * @author Created by Asura on 2018/6/27 15:56.
 */
class CanvasFragment : Fragment() {
    private var layoutId: Int? = 0

    companion object {
        fun newInstance(@LayoutRes layoutId: Int): CanvasFragment {
            val fragment: CanvasFragment = CanvasFragment()
            val bundle = Bundle()
            bundle.putInt("layoutId", layoutId)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        layoutId = bundle?.getInt("layoutId")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_canvas, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sampleStub.layoutResource = layoutId as Int
        sampleStub.inflate()
    }
}