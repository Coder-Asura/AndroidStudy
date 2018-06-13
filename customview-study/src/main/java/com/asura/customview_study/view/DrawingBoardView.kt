package com.asura.customview_study.view

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.View.OnTouchListener


/**
 * 画板视图
 *
 * @author Created by Asura on 2018/6/12 16:35.
 */
class DrawingBoardView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback, OnTouchListener {
    private var mPaint: Paint? = null
    private var mPath: Path? = null
    //上一次触摸事件的终点的x，y
    private var mLastX: Float = 0.toFloat()
    private var mLastY: Float = 0.toFloat()

    init {
        init()
    }

    private fun init() {
        holder.addCallback(this)
        mPaint = Paint()
        mPath = Path()
        mPaint!!.setColor(Color.BLACK)
        mPaint!!.setStrokeWidth(10F)
        mPaint!!.setAntiAlias(true)
        mPaint!!.setStyle(Paint.Style.STROKE)
        setOnTouchListener(this)
    }


    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        draw()
    }

    private fun draw() {
        val canvas = holder.lockCanvas()
        canvas.drawColor(Color.WHITE)
        canvas.drawPath(mPath, mPaint)
        holder.unlockCanvasAndPost(canvas)
    }

    /**
     * 清除视图
     */
    fun clearView() {
        mPath!!.reset()
        draw()
    }

    override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int) {

    }

    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {

    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        /*switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(motionEvent.getX(), motionEvent.getY());
                draw();
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(motionEvent.getX(), motionEvent.getY());
                draw();
                break;
        }
        return true;*/

        val x = motionEvent.x
        val y = motionEvent.y

        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                mPath!!.moveTo(x, y)
                mLastX = x
                mLastY = y
                draw()
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                //结束点
                val endX = (mLastX + x) / 2
                val endY = (mLastY + y) / 2
                //贝塞尔曲线让线条更顺滑
                //上一次的终点作为起点,上一个点作为控制点,中间点作为终点
                mPath!!.quadTo(mLastX, mLastY, endX, endY)
                //记录上一次的操作点
                mLastX = x
                mLastY = y
                draw()
            }
            MotionEvent.ACTION_UP -> {
            }
        }
        return super.onTouchEvent(motionEvent)
    }
}