package com.asura.android_study.view

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import kotlin.jvm.JvmOverloads
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import android.view.MotionEvent
import android.view.ViewConfiguration
import java.lang.IllegalStateException

/**
 * Author: Asuraliu
 * Date: 2021/11/1 10:38
 * Description: 滑动选中布局
 * History:
 * <author> <time> <version> <desc>
 * Asuraliu 2021/11/1 1.0 首次创建
</desc></version></time></author> */
class SlidingCheckLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val mTouchSlop: Int
    private var mTargetRv: RecyclerView? = null
    private var mInitDownY = 0f
    private var mInitDownX = 0f
    private var mLastY = 0f
    private var mLastX = 0f
    private var mLastPosition = RecyclerView.NO_POSITION
    private var mFirstDownPosition = RecyclerView.NO_POSITION
    private var mStartingCheck = false
    private var mIncrease = 0
    private var mPendingCheckForLongPress: CheckForLongPress? = null
    private val mHandler: Handler
    private var mOnSlidingPositionListener: OnSlidingPositionListener? = null

    /**
     * 是否可滑动
     */
    var isSlidingEnable = true

    /**
     * 是否需要长按触发滑动
     */
    var needLongPress: Boolean = false

    companion object {
        private val TAG = SlidingCheckLayout::class.java.simpleName
        private const val LONG_PRESS_TIME = 500
    }

    init {
        val vc = ViewConfiguration.get(context)
        mTouchSlop = vc.scaledTouchSlop
        mHandler = Handler(Looper.getMainLooper())
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        ensureTarget()
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (!isSlidingEnable || !isEnabled) {
            return super.dispatchTouchEvent(event)
        }
        if (!isCanIntercept) {
            return super.dispatchTouchEvent(event)
        }
        val action = event.actionMasked
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                mLastY = event.y
                mInitDownY = mLastY
                mLastX = event.x
                mInitDownX = mLastX

                if (needLongPress) {
                    checkForLongClick(0, mInitDownX, mInitDownY)
                } else {
                    if (checkDownPosition(mInitDownX, mInitDownY).also {
                            mLastPosition = it
                            mFirstDownPosition = it
                        } != RecyclerView.NO_POSITION) {
                        if (mOnSlidingPositionListener != null) {
                            mOnSlidingPositionListener!!.onSlidingStart(mLastPosition)
                        }
                        requestDisallowInterceptTouchEvent(true)
                        mStartingCheck = true
                    }
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                //                Log.i(TAG, "dispatchTouchEvent ACTION_CANCEL||ACTION_UP mStartingCheck:" + mStartingCheck);
                if (mOnSlidingPositionListener != null) {
                    mOnSlidingPositionListener!!.onSlidingEnd(mLastPosition)
                }
                removeLongPressCallback()
                mLastPosition = RecyclerView.NO_POSITION
                mIncrease = 0
                if (mStartingCheck) {
                    mStartingCheck = false
                    return true
                }
            }
            MotionEvent.ACTION_MOVE -> {
                //                Log.i(TAG, "dispatchTouchEvent ACTION_MOVE mStartingCheck:" + mStartingCheck);
                val y = event.y
                val x = event.x
                val yInitDiff = y - mInitDownY
                val xInitDiff = x - mInitDownX
                mLastY = y
                mLastX = x
                if (!mStartingCheck && (Math.abs(yInitDiff) > mTouchSlop || Math.abs(xInitDiff) > mTouchSlop)) {
                    removeLongPressCallback()
                }
                if (mStartingCheck) {
                    checkSlidingPosition(x, y)
                    return true
                }
            }
            else -> {
            }
        }
        val result = super.dispatchTouchEvent(event)
        //        Log.i(TAG, "dispatchTouchEvent super.dispatchTouchEvent result:" + result);
        return result
    }

    private fun checkSlidingPosition(x: Float, y: Float) {
        val childViewUnder = mTargetRv!!.findChildViewUnder(x, y)
        if (mOnSlidingPositionListener == null || childViewUnder == null) {
            return
        }
        val currentPosition = mTargetRv!!.getChildAdapterPosition(childViewUnder)
        //        Log.w(TAG, "checkSlidingPosition currentPosition:$currentPosition,mLastPosition:$mLastPosition")
        if (currentPosition == mLastPosition || currentPosition == RecyclerView.NO_POSITION) {
            return
        }
        if (mLastPosition != RecyclerView.NO_POSITION) {
            mOnSlidingPositionListener!!.onSlidingRangePosition(mFirstDownPosition, currentPosition)
        }
        mLastPosition = currentPosition
    }

    private fun checkDownPosition(x: Float, y: Float): Int {
        val childViewUnder = mTargetRv!!.findChildViewUnder(x, y)
        if (mOnSlidingPositionListener == null || childViewUnder == null) return RecyclerView.NO_POSITION
        val currentPosition = mTargetRv!!.getChildAdapterPosition(childViewUnder)
        return if (currentPosition == RecyclerView.NO_POSITION) RecyclerView.NO_POSITION else currentPosition
    }

    fun setOnSlidingPositionListener(onSlidingPositionListener: OnSlidingPositionListener?) {
        mOnSlidingPositionListener = onSlidingPositionListener
    }

    private fun ensureTarget() {
        if (mTargetRv != null) {
            return
        }
        for (i in 0 until childCount) {
            val childAt = getChildAt(i)
            if (childAt is RecyclerView) {
                mTargetRv = childAt
                return
            }
        }
        throw IllegalStateException("Children must have a RecyclerView")
    }

    private val isCanIntercept: Boolean
        get() = mTargetRv != null && mTargetRv!!.adapter != null && mTargetRv!!.adapter!!.itemCount != 0

    private fun checkForLongClick(delayOffset: Int, x: Float, y: Float) {
        if (mPendingCheckForLongPress == null) {
            mPendingCheckForLongPress = CheckForLongPress()
        }
        mPendingCheckForLongPress!!.setAnchor(x, y)
        mPendingCheckForLongPress!!.rememberPressedState()
        mHandler.postDelayed(
            mPendingCheckForLongPress!!, (
                    LONG_PRESS_TIME - delayOffset).toLong()
        )
    }

    private fun removeLongPressCallback() {
        if (mPendingCheckForLongPress != null) {
            mHandler.removeCallbacks(mPendingCheckForLongPress!!)
        }
    }

    private inner class CheckForLongPress : Runnable {
        private var mX = 0f
        private var mY = 0f
        private var mOriginalPressedState = false
        override fun run() {
            if (mOriginalPressedState == isPressed && checkDownPosition(mX, mY).also { mLastPosition = it }
                    .also { mFirstDownPosition = it } != RecyclerView.NO_POSITION) {
                if (mOnSlidingPositionListener != null) {
                    mOnSlidingPositionListener!!.onSlidingStart(mLastPosition)
                }
                requestDisallowInterceptTouchEvent(true)
                mStartingCheck = true
            }
        }

        fun setAnchor(x: Float, y: Float) {
            mX = x
            mY = y
        }

        fun rememberPressedState() {
            mOriginalPressedState = isPressed
        }
    }

    interface OnSlidingPositionListener {
        /**
         * 开始滑动
         * @param position 手指触摸位置
         */
        fun onSlidingStart(position: Int)

        /**
         * 区间滑动
         * @param startPosition 起始位置
         * @param endPosition 结束位置
         */
        fun onSlidingRangePosition(startPosition: Int, endPosition: Int)

        /**
         * 滑动结束
         * @param endPosition 手指抬起位置
         */
        fun onSlidingEnd(endPosition: Int)
    }
}