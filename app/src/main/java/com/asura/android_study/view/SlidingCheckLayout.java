package com.asura.android_study.view;

import android.content.Context;
import android.os.Handler;

import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Author: Asuraliu
 * Date: 2021/11/1 10:38
 * Description: 滑动选中布局
 * History:
 * <author> <time> <version> <desc>
 * Asuraliu 2021/11/1 1.0 首次创建
 */
public class SlidingCheckLayout extends FrameLayout {
    private static final String TAG = SlidingCheckLayout.class.getSimpleName();
    private int mTouchSlop;

    private RecyclerView mTargetRv;

    private float mInitDownY;

    private float mInitDownX;

    private float mLastY;

    private float mLastX;

    private int mLastPosition = RecyclerView.NO_POSITION;
    private int mFirstDownPosition = RecyclerView.NO_POSITION;

    private static final int sLongPressTime = 500;

    private boolean mSlidingEnable = true;
    private boolean mStartingCheck = false;
    private int mIncrease = 0;
    private boolean needLongPress = true;
    private CheckForLongPress mPendingCheckForLongPress;
    private Handler mHandler;

    private OnSlidingPositionListener mOnSlidingPositionListener;


    public SlidingCheckLayout(@NonNull Context context) {
        this(context, null);
    }

    public SlidingCheckLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingCheckLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final ViewConfiguration vc = ViewConfiguration.get(context);
        mTouchSlop = vc.getScaledTouchSlop();
        mHandler = new Handler();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ensureTarget();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (!isSlidingEnable() || !isEnabled()) {
            return super.dispatchTouchEvent(event);
        }
        if (!isCanIntercept()) {
            return super.dispatchTouchEvent(event);
        }
        final int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
//                Log.i(TAG, "dispatchTouchEvent ACTION_DOWN mStartingCheck:" + mStartingCheck);
                mInitDownY = mLastY = event.getY();
                mInitDownX = mLastX = event.getX();
                if (needLongPress) {
                    checkForLongClick(0, mInitDownX, mInitDownY);
                } else {
                    if ((mFirstDownPosition = mLastPosition = checkDownPosition(mInitDownX, mInitDownY)) != RecyclerView.NO_POSITION) {
                        if (mOnSlidingPositionListener != null) {
                            mOnSlidingPositionListener.onSlidingPosition(mLastPosition);
                        }
                        requestDisallowInterceptTouchEvent(true);
                        mStartingCheck = true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
//                Log.i(TAG, "dispatchTouchEvent ACTION_CANCEL||ACTION_UP mStartingCheck:" + mStartingCheck);
                removeLongPressCallback();
                mLastPosition = RecyclerView.NO_POSITION;
                mIncrease = 0;
                if (mStartingCheck) {
                    mStartingCheck = false;
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
//                Log.i(TAG, "dispatchTouchEvent ACTION_MOVE mStartingCheck:" + mStartingCheck);
                float y = event.getY();
                float x = event.getX();
                final float yInitDiff = y - mInitDownY;
                final float xInitDiff = x - mInitDownX;
                mLastY = y;
                mLastX = x;
                if (!mStartingCheck && (Math.abs(yInitDiff) > mTouchSlop || Math.abs(xInitDiff) > mTouchSlop)) {
                    removeLongPressCallback();
                }
                if (mStartingCheck) {
                    checkSlidingPosition(x, y);
                    return true;
                }
                break;
            default:
                break;
        }
        boolean result = super.dispatchTouchEvent(event);
        //        Log.i(TAG, "dispatchTouchEvent super.dispatchTouchEvent result:" + result);
        return result;
    }

    private void checkSlidingPosition(float x, float y) {
        View childViewUnder = mTargetRv.findChildViewUnder(x, y);
        if (mOnSlidingPositionListener == null || childViewUnder == null) {
            return;
        }

        int currentPosition = mTargetRv.getChildAdapterPosition(childViewUnder);
        Log.w(TAG, "checkSlidingPosition currentPosition:" + currentPosition + ",mLastPosition:" + mLastPosition);

        if (currentPosition == mLastPosition || currentPosition == RecyclerView.NO_POSITION) {
            return;
        }

        if (mLastPosition != RecyclerView.NO_POSITION) {
            mOnSlidingPositionListener.onSlidingRangePosition(mFirstDownPosition, currentPosition);
        }

        mLastPosition = currentPosition;
    }

    private int checkDownPosition(float x, float y) {
        View childViewUnder = mTargetRv.findChildViewUnder(x, y);
        if (mOnSlidingPositionListener == null || childViewUnder == null)
            return RecyclerView.NO_POSITION;

        int currentPosition = mTargetRv.getChildAdapterPosition(childViewUnder);
        if (currentPosition == RecyclerView.NO_POSITION) return RecyclerView.NO_POSITION;

        return currentPosition;
    }

    public void setSlidingEnable(boolean slidingEnable) {
        mSlidingEnable = slidingEnable;
    }

    public void setNeedLongPress(boolean needLongPress) {
        this.needLongPress = needLongPress;
    }

    public void setOnSlidingPositionListener(OnSlidingPositionListener onSlidingPositionListener) {
        mOnSlidingPositionListener = onSlidingPositionListener;
    }

    public boolean isSlidingEnable() {
        return mSlidingEnable;
    }

    private void ensureTarget() {
        if (mTargetRv != null) {
            return;
        }
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof RecyclerView) {
                mTargetRv = (RecyclerView) childAt;
                return;
            }
        }
        throw new IllegalStateException("Children must have a RecyclerView");
    }

    private boolean isCanIntercept() {
        return mTargetRv != null && mTargetRv.getAdapter() != null && mTargetRv.getAdapter().getItemCount() != 0;
    }

    private void checkForLongClick(int delayOffset, float x, float y) {
        if (mPendingCheckForLongPress == null) {
            mPendingCheckForLongPress = new CheckForLongPress();
        }
        mPendingCheckForLongPress.setAnchor(x, y);
        mPendingCheckForLongPress.rememberPressedState();
        mHandler.postDelayed(mPendingCheckForLongPress,
                sLongPressTime - delayOffset);
    }

    /**
     * Remove the longpress detection timer.
     */
    private void removeLongPressCallback() {
        if (mPendingCheckForLongPress != null) {
            mHandler.removeCallbacks(mPendingCheckForLongPress);
        }
    }

    private final class CheckForLongPress implements Runnable {
        private float mX;
        private float mY;
        private boolean mOriginalPressedState;

        @Override
        public void run() {
            if ((mOriginalPressedState == isPressed()) && (mFirstDownPosition = mLastPosition = checkDownPosition(mX, mY)) != RecyclerView.NO_POSITION) {
                if (mOnSlidingPositionListener != null) {
                    mOnSlidingPositionListener.onSlidingPosition(mLastPosition);
                }
                requestDisallowInterceptTouchEvent(true);
                mStartingCheck = true;
            }
        }

        public void setAnchor(float x, float y) {
            mX = x;
            mY = y;
        }

        public void rememberPressedState() {
            mOriginalPressedState = isPressed();
        }
    }

    public interface OnSlidingPositionListener {
        void onSlidingPosition(int position);

        void onSlidingRangePosition(int startPosition, int endPosition);
    }
}
