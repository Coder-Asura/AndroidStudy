package com.asura.android_study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Liuxd on 2016/10/8 16:44.
 * Canvas画图实践
 */

public class CanvasView extends View {
    private Picture mPicture = new Picture();

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        recordPicture();
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        recordPicture();
    }

    public CanvasView(Context context) {
        super(context);
        recordPicture();
    }

    private void recordPicture() {
        // 开始录制 (接收返回值Canvas)
        Canvas canvas = mPicture.beginRecording(500, 500);
        // 创建一个画笔
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        // 在Canvas中具体操作
        // 位移
        canvas.translate(250,250);
        // 绘制一个圆
        canvas.drawCircle(0,0,100,paint);

        mPicture.endRecording();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPicture(mPicture,new RectF(0,0,mPicture.getWidth(),200));
    }
}
