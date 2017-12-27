package com.wwl.can.canvas.canvas1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
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
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2);

        Path ovalPath = new Path();
        RectF rect = new RectF(50,50,200,500);
        ovalPath.addOval(rect, Path.Direction.CCW);

        Region rgn = new Region();
        rgn.setPath(ovalPath, new Region(50,50,200,200));

    }


}
