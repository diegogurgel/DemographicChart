package com.diegogurgel.demographic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by diegogurgel on 11/9/15.
 */
public class DemoChart extends View {
    Canvas mCanvas;
    float mDensity;
    private int mWidth;
    private int mHeight;
    Paint mPaintBars;

    public DemoChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDensity = context.getResources().getDisplayMetrics().density;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas = canvas;
        mWidth = canvas.getWidth();
        mHeight = canvas.getHeight();
        init();
        drawLine();

    }
    private void init(){
        mPaintBars = new Paint();
        mPaintBars.setColor(Color.BLUE);
    }
    private void drawLine(){
        RectF rect = new RectF(0,0,100,100);


        mCanvas.drawRect(rect,mPaintBars);
    }
}
