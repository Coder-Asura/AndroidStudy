package com.asura.customview_study.activity.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.asura.customview_study.utils.ALog;

public class Practice13DrawCanvasView extends View {
    int mWidth;
    int mHeight;

    public Practice13DrawCanvasView(Context context) {
        super(context);
    }

    public Practice13DrawCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice13DrawCanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);

        //画布的变换
        //将原点移到中心处
        canvas.translate(350, 350);
        canvas.drawCircle(0, 0, 300, paint);
        canvas.drawCircle(0, 0, 100, paint);
        //逆时针旋转
        canvas.rotate(45);
        canvas.drawLine(100, 0, 300, 0, paint);

        paint.setColor(Color.YELLOW);
        for (int i = 0; i < 3; i++) {
            canvas.rotate(90);
            canvas.drawLine(100, 0, 300, 0, paint);
        }

        canvas.rotate(45);
        paint.setColor(Color.BLUE);
        RectF rectF1 = new RectF(0, 0, 300, 300);
        canvas.drawRect(rectF1, paint);


        //平移,是以当前原点为基准
        canvas.translate(-350 + paint.getStrokeWidth() * 2, -350 + paint.getStrokeWidth() * 2);
        canvas.drawRect(rectF1, paint);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(20);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(mWidth / 2, mHeight / 2, paint);
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

        canvas.translate(mWidth/2,mHeight/2);

        paint.setColor(Color.RED);
        canvas.scale(-1.5f, -0.5f);
        canvas.drawRect(rectF1, paint);
        // 水平错切
        canvas.skew(1, 0);
        // 垂直错切
        canvas.skew(0, 1);
        paint.setColor(Color.BLUE);
        canvas.drawRect(rectF1, paint);

//        保存及回滚
//        canvas.save();
//        canvas.getSaveCount();
//        canvas.restore();
//        canvas.restoreToCount(1);
//        canvas.saveLayer();
//        canvas.saveLayerAlpha();
    }
}
