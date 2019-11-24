package com.asura.customview_study.activity.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Practice10HistogramView extends View {
    private final static String NAME = "直方图";

    private List<Data> datas;
    private Paint paint;
    private float startX;
    private float space;
    private float width;
    private float max;

    public Practice10HistogramView(Context context) {
        super(context);
        init();
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        datas = new ArrayList<>();
        Data data = new Data("Froyo", 10.0f, Color.GREEN);
        datas.add(data);
        data = new Data("ICS", 18.0f, Color.GREEN);
        datas.add(data);
        data = new Data("JB", 22.0f, Color.GREEN);
        datas.add(data);
        data = new Data("KK", 27.0f, Color.GREEN);
        datas.add(data);
        data = new Data("L", 40.0f, Color.GREEN);
        datas.add(data);
        data = new Data("M", 60.0f, Color.GREEN);
        datas.add(data);
        data = new Data("N", 33.5f, Color.GREEN);
        datas.add(data);
        max = Float.MIN_VALUE;
        for (Data d : datas) {
            max = Math.max(max, d.getNumber());
        }
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        paint.setColor(Color.WHITE);
        paint.setTextSize(48);
        canvas.drawText(NAME, (canvas.getWidth() - paint.measureText(NAME)) / 2, canvas.getHeight() * 0.9f, paint);

        canvas.translate(canvas.getWidth() * 0.1f, canvas.getHeight() * 0.7f); // 将画图原点移动直方图的原点位置

        width = (canvas.getWidth() * 0.8f - 100) / datas.size() * 0.8f;
        space = (canvas.getWidth() * 0.8f - 100) / datas.size() * 0.2f;

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(0, 0, canvas.getWidth() * 0.8f, 0, paint);   // 画x轴
        canvas.drawLine(0, 0, 0, -canvas.getHeight() * 0.6f, paint); // 画y轴

        startX = 0f;

        paint.setTextSize(36);
        paint.setStyle(Paint.Style.FILL);
        for (Data data : datas) {
            paint.setColor(data.getColor());
            canvas.drawRect(startX + space, -(data.getNumber() / max * canvas.getHeight() * 0.6f), startX + space + width, 0, paint);
            paint.setColor(Color.WHITE);
            canvas.drawText(data.getName(), startX + space + (width - paint.measureText(data.getName())) / 2, 40, paint);
            startX += width + space;
        }
    }
}
