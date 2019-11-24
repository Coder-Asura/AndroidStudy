package com.asura.customview_study.activity.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {

    public Practice9DrawPathView(Context context) {
        super(context);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPath() 方法画心形
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);
        Path path = new Path();
//        顺时针 (CW clockwise) 和逆时针 (CCW counter-clockwise)
//        path.addCircle(500, 500, 200, Path.Direction.CW);
//        path.moveTo(50, 50);
//        //改变颜色只看最终调用drawPath（）时设置的颜色才生效
////        paint.setColor(Color.RED);
//        //画线，绝对坐标
//        path.lineTo(200, 150);
//        //画线，相对坐标
//        path.rLineTo(20, 100);
        //二次贝塞尔曲线
//        path.quadTo(100,100,800,100);
//        画三次贝塞尔曲线
//        path.cubicTo(100, 100, 300, 200, 500, 100);
//        它的作用是把当前的子图形封闭，即由当前位置向当前子图形的起点绘制一条直线。
//        当需要填充图形时（即 Paint.Style 为 FILL 或  FILL_AND_STROKE），Path 会自动封闭子图形。
//        path.close();
//        path.addCircle(200, 200, 100, Path.Direction.CW);
//        path.addCircle(250, 250, 100, Path.Direction.CCW);
////        WINDING 是「全填充」，而 EVEN_ODD 是「交叉填充」
//        path.setFillType(Path.FillType.EVEN_ODD);
        //画爱心
//        因为 arcTo() 只用来画弧形而不画扇形，所以不再需要 useCenter 参数；
// 而多出来的这个 forceMoveTo 参数的意思是，绘制是要「抬一下笔移动过去」，
// 还是「直接拖着笔过去」，区别在于是否留下移动的痕迹。
        path.addArc(new RectF(100, 100, 300, 300), -225, 225);
        path.arcTo(new RectF(300, 100, 500, 300), -180, 225, false);
        path.lineTo(300, 450);
        canvas.drawPath(path, paint);
    }
}
