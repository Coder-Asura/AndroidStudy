package com.asura.customview_study.activity.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {

    public Practice8DrawArcView(Context context) {
        super(context);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
//        RectF rectF = new RectF(350, 350, 750, 600);//椭圆
        RectF rectF = new RectF(350, 350, 750, 750);//圆
        canvas.drawArc(rectF, -10, -100, true, paint);
        paint.setColor(Color.YELLOW);
        canvas.drawArc(rectF, 20, 120, false, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.BLUE);
        canvas.drawArc(rectF, -120, -60, false, paint);
    }
}
