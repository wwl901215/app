package com.wwl.can.canvas.canvas1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by wangwenliang on 2017/12/26.
 */

public class MyView extends View {
    Context context;
    public MyView(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRGB(255,255,255);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);//设置填充样式   Style.FILL/Style.FILL_AND_STROKE/Style.STROKE
        paint.setStrokeWidth(5);
        paint.setShadowLayer(10,15,15,Color.GREEN);//对几何图形设置阴影没效果
        canvas.drawCircle(190,200,150,paint);

        Paint linePaint = new Paint();
        linePaint.setColor(Color.RED);
        linePaint.setStyle(Paint.Style.FILL);
        linePaint.setStrokeWidth(5);
        canvas.drawLine(200,400,300,500,linePaint);

        float[] pts = {300,500,400,200,10,10,100,100};
        canvas.drawLines(pts,linePaint);

        Paint point = new Paint();
        point.setColor(Color.GREEN);
        point.setStyle(Paint.Style.FILL);
        point.setStrokeWidth(15);
//        int offset:集合中跳过的数值个数，注意不是点的个数！一个点是两个数值；
//        count:参与绘制的数值的个数，指pts[]里人数值个数，而不是点的个数，因为一个点是两个数值
        canvas.drawPoint(100,200,point);
        float[] pos = {10,10,100,100,200,200,300,300,400,400,500,500,600,600};
        canvas.drawPoints(pos,2,4,point);

//        5、矩形工具类RectF与Rect
//        这两个都是矩形辅助类，区别不大，用哪个都行，根据四个点构建一个矩形结构；在画图时，利用这个矩形结构可以画出对应的矩形或者与其它图形Region相交、相加等等；
//        RectF：
//        构造函数有下面四个，但最常用的还是第二个，根据四个点构造出一个矩形；
//        RectF()
//        RectF(float left, float top, float right, float bottom)
//        RectF(RectF r)
//        RectF(Rect r)
//        Rect
//        构造函数如下，最常用的也是根据四个点来构造矩形
//        Rect()
//        Rect(int left, int top, int right, int bottom)
//        Rect(Rect r)
//        6、矩形
//        void drawRect (float left, float top, float right, float bottom, Paint paint)
//        void drawRect (RectF rect, Paint paint)
//        void drawRect (Rect r, Paint paint)
//        参数：
//        第一个的写法是直接传入矩形的四个点，画出矩形
//        第二、三个构造函数是根据传入RectF或者Rect矩形变量来指定所画的矩形的

//        7、圆角矩形
//        void drawRoundRect (RectF rect, float rx, float ry, Paint paint)
//        参数：
//        RectF rect:要画的矩形
//        float rx:生成圆角的椭圆的X轴半径
//        float ry:生成圆角的椭圆的Y轴半径

//        8、圆形
//        void drawCircle (float cx, float cy, float radius, Paint paint)

//        9、椭圆
//        椭圆是根据矩形生成的，以矩形的长为椭圆的X轴，矩形的宽为椭圆的Y轴，建立的椭圆图形
//        void drawOval (RectF oval, Paint paint)

//        10、弧
//        弧是椭圆的一部分，而椭圆是根据矩形来生成的，所以弧当然也是根据矩形来生成的；
//        void drawArc (RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint)
//        参数：
//        RectF oval:生成椭圆的矩形
//        float startAngle：弧开始的角度，以X轴正方向为0度
//        float sweepAngle：弧持续的角度
//        boolean useCenter:是否有弧的两边，True，还两边，False，只有一条弧

    }
}
