package com.asura.customview_study.activity.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Practice12DrawTextView extends View {

    public Practice12DrawTextView(Context context) {
        super(context);
    }

    public Practice12DrawTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12DrawTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GRAY);

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(1f);
        canvas.drawPoint(100, 100, paint);
        canvas.drawLine(120, 100, 260, 360, paint);
        canvas.drawPoints(new float[]{100, 200, 100, 300}, paint);
        canvas.drawRect(150, 200, 500, 400, paint);
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
    }
}
