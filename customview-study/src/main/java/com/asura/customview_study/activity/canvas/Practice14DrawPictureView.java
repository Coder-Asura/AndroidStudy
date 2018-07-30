package com.asura.customview_study.activity.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.view.View;

public class Practice14DrawPictureView extends View {
    private Picture mPicture = new Picture();

    public Practice14DrawPictureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        recordPicture();
    }

    public Practice14DrawPictureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        recordPicture();
    }

    public Practice14DrawPictureView(Context context) {
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
        canvas.translate(250, 250);
        // 绘制一个圆
        canvas.drawCircle(0, 0, 100, paint);

        mPicture.endRecording();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
////        1、这种方法在比较低版本的系统上绘制后可能会影响Canvas状态，所以这种方法一般不会使用。
//        mPicture.draw(canvas);
////        2、Canvas的drawPicture不会影响Canvas状态。
//        canvas.drawPicture(mPicture, new RectF(0, 0, mPicture.getWidth(), 200));
        // 3、包装成为Drawable
        PictureDrawable drawable = new PictureDrawable(mPicture);
// 设置绘制区域 -- 注意此处所绘制的实际内容不会缩放
        drawable.setBounds(0, 0, 250, mPicture.getHeight());
// 绘制
        drawable.draw(canvas);
    }
}
