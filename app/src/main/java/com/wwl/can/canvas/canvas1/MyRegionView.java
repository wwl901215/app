package com.wwl.can.canvas.canvas1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.view.View;

/**
 * Created by wangwenliang on 2017/12/27.
 */

public class MyRegionView extends View{
    private Context context;
    public MyRegionView(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);

        Path ovalPath = new Path();
        RectF rect = new RectF(50,50,200,500);
        ovalPath.addOval(rect, Path.Direction.CCW);

        Region rgn = new Region();
        rgn.setPath(ovalPath, new Region(50,50,200,500));

        drawRegion(canvas,rgn,paint);



        Rect r1 = new Rect(300,100,600,200);
        Rect r2 = new Rect(400,0,500,300);
        canvas.drawRect(r1,paint);
        canvas.drawRect(r2,paint);
        Region region1 = new Region(r1);
        Region region2 = new Region(r2);
        region1.op(region2, Region.Op.INTERSECT);

        Paint paint1 = new Paint();
        paint1.setColor(Color.GREEN);
        paint1.setStyle(Paint.Style.FILL);
        drawRegion(canvas,region1,paint1);

//        DIFFERENCE(0), //最终区域为region1 与 region2不同的区域
//        INTERSECT(1), // 最终区域为region1 与 region2相交的区域
//        UNION(2),      //最终区域为region1 与 region2组合一起的区域
//        XOR(3),        //最终区域为region1 与 region2相交之外的区域
//        REVERSE_DIFFERENCE(4), //最终区域为region2 与 region1不同的区域
//        REPLACE(5); //最终区域为为region2的区域
    }

    private void drawRegion(Canvas canvas, Region region, Paint paint) {
        RegionIterator ite = new RegionIterator(region);
        Rect r = new Rect();
        while (ite.next(r)) {
            canvas.drawRect(r, paint);
        }
    }


}
