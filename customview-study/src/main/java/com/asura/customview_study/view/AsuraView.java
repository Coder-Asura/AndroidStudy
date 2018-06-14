package com.asura.customview_study.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.asura.customview_study.utils.ALog;
import com.orhanobut.logger.Logger;

/**
 * Created by Liuxd on 2016/9/28 9:47.
 * Canvas基础用法实践
 */

public class AsuraView extends View {
    private int mWidth;
    private int mHeight;

    //三个构造方法尽量不要用级联方式，因为有一些父View有默认的defStyleAttr，例如Listview
//    public LxdView(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public LxdView(Context context) {
//        this(context, null);
//    }
    public AsuraView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AsuraView(Context context) {
        super(context);
    }

    public AsuraView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AsuraView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        ALog.INSTANCE.d("width=" + mWidth + "  widthMode=" + widthMode + "  height=" + mHeight + "  heightMode=" + heightMode);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GRAY);

//        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        paint.setStrokeWidth(5f);
//        canvas.drawPoint(100, 100, paint);
//        canvas.drawLine(120, 100, 260, 360, paint);
//        canvas.drawPoints(new float[]{100, 200, 100, 300}, paint);
//        canvas.drawRect(150, 200, 500, 400, paint);
        RectF rectF = new RectF(0, 0, 720, 720);
        canvas.drawRect(rectF, paint);
        paint.setColor(Color.LTGRAY);
        RectF rectF2 = new RectF(79, 79, 641, 641);
        canvas.drawRect(rectF2, paint);
        paint.setColor(Color.BLACK);
        canvas.drawLine(0, 360, 720, 360, paint);
        paint.setTextSize(40);
        paint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        int distance = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        int baseline = (fontMetrics.bottom - fontMetrics.top) / 2 + distance;
        int y = 720 - 79 + (79 - (fontMetrics.bottom - fontMetrics.top)) / 2 + baseline;
//        int y = (int) (rectF2.centerY() - (fontMetrics.bottom + fontMetrics.top) / 2);
        Log.d("lxd", "distance = " + distance + "  baseline = " + baseline + "  y = " + y);
        canvas.drawText("My 二狗子", rectF2.centerX(), y, paint);
        canvas.drawLine(0, y, 720, y, paint);


//        RectF rectf2 = new RectF(480, 450, 560, 800);
//        canvas.drawRoundRect(rectF, 30, 30, paint);
////        canvas.drawRoundRect(240,150,480,500,30,10,paint);
//        canvas.drawRoundRect(rectf2, 40, 175, paint);

/*        // 矩形
        RectF rectF = new RectF(100, 100, 800, 400);

// 绘制背景矩形
        paint.setColor(Color.GRAY);
        canvas.drawRect(rectF, paint);

// 绘制圆角矩形
        paint.setColor(Color.BLUE);
        canvas.drawRoundRect(rectF, 700, 400, paint);
        //绘制椭圆
        RectF rectF2 = new RectF(100, 450, 800, 750);
        canvas.drawOval(rectF2, paint);
        //绘制正圆（正方形）
        RectF rectF1 = new RectF(100, 800, 500, 1200);
        canvas.drawOval(rectF1, paint);
        paint.setColor(Color.RED);
        //绘制圆
        canvas.drawCircle(500,500,200,paint);*/

        /*//绘制圆弧，而不使用中心点则是圆弧起始点和结束点之间的连线加上圆弧围成的图形
        RectF rectF3 = new RectF(100, 100, 800, 800);
        canvas.drawArc(rectF3, 0, 90, false, paint);
        //使用了中心点之后绘制出来类似于一个扇形，
        RectF rectF4 = new RectF(100, 420, 800, 720);
        canvas.drawArc(rectF4, 0, 90, true, paint);*/

/*//不同的填充风格
        paint.setStrokeWidth(60f);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(170, 200, 100, paint);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(200, 500, 100, paint);
        //宽度的一半来合成
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(200, 800, 100, paint);*/


//        paint.setStrokeWidth(2f);
//        paint.setStyle(Paint.Style.STROKE);
        /*//画布的变换
        //将远点移到中心处
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawCircle(0, 0, 300, paint);
        canvas.drawCircle(0, 0, 100, paint);
        canvas.rotate(45);
        canvas.drawLine(100, 0, 300, 0, paint);
        for (int i = 0; i < 3; i++) {
            canvas.rotate(90);
            canvas.drawLine(100, 0, 300, 0, paint);
        }*/

        //缩放
//        缩放比例(sx,sy)取值范围详解：
//
//        取值范围(n)	说明
//                [-∞, -1)	先根据缩放中心放大n倍，再根据中心轴进行翻转
//                -1	根据缩放中心轴进行翻转
//                (-1, 0)	先根据缩放中心缩小到n，再根据中心轴进行翻转
//        0	不会显示，若sx为0，则宽度为0，不会显示，sy同理
//                (0, 1)	根据缩放中心缩小到n
//        1	没有变化
//                (1, +∞)	根据缩放中心放大n倍
//        canvas.translate(mWidth / 2, mHeight / 2);
//        RectF rectF5 = new RectF(0, -300, 300, 0);
//        canvas.drawRect(rectF5, paint);
//        canvas.translate(100, 0);
//        canvas.scale(-1.5f, -0.5f);
//        canvas.drawRect(rectF5, paint);
//        // 水平错切
//        canvas.skew(1, 0);
//        // 垂直错切
//        canvas.skew(0, 1);
//        paint.setColor(Color.BLUE);
//        canvas.drawRect(rectF5, paint);

        //保存及回滚
//        canvas.save();
//        canvas.getSaveCount();
//        canvas.restore();
//        canvas.restoreToCount(1);
//        canvas.saveLayer()
//        canvas.saveLayerAlpha()

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Logger.d("onSizeChanged", w, h, oldw, oldh);
    }
}
