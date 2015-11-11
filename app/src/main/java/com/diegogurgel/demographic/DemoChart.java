package com.diegogurgel.demographic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diegogurgel on 11/9/15.
 */
public class DemoChart extends View {
    Canvas mCanvas;
    float mD; //Density
    private int mWidth;
    private int mHeight;
    private int mHorizCenter;
    private int mHorizCenterWoman;
    private int mHorizCenterMan;
    private int mLineDistance;
    private int mMidleSpace;
    private int mHeightBars;
    Paint mPaintBarsRight;
    Paint mPaintBarsLeft;
    Paint mPaintLabels;
    List<Integer> mPercentsWomen;
    List<Integer> mValuesWoman;
    List<Integer> mPercentsMan;
    List<Integer> mValuesMan;
    List<String> mLabels;

    public DemoChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        mD = context.getResources().getDisplayMetrics().density;
        mLineDistance = Math.round(10 * mD);
        mMidleSpace = Math.round(20 * mD);
        mHeightBars = Math.round(10 * mD);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas = canvas;
        mWidth = canvas.getWidth();
        mHeight = canvas.getHeight();
        mHorizCenter = mWidth/2;
        mHorizCenterWoman = mHorizCenter+ mMidleSpace;
        mHorizCenterMan = mHorizCenter- mMidleSpace;
        init();

        List<Integer> values = new ArrayList<Integer>();
        List<String> labels = new ArrayList<String>();

        values.add(830);
        values.add(3025);
        values.add(10740);
        values.add(18897);
        values.add(23155);
        values.add(27312);
        values.add(28706);
        values.add(22341);
        values.add(19243);
        setValuesWoman(values);
        setPercentsWoman(mValuesWoman);
        setmValuesMan(values);
        setPercentsMan(mValuesMan);
        drawWoman();
        drawMan();
        labels.add("90+");
        labels.add("80-84");
        labels.add("60-64");
        labels.add("50-54");
        labels.add("40-44");
        labels.add("30-34");
        labels.add("20-24");
        labels.add("10-14");
        labels.add("0-4");

        setLabels(labels);
        drawLabels();

    }
    private void init(){
        mPaintBarsRight = new Paint();
        mPaintBarsLeft = new Paint();
        mPaintLabels = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBarsRight.setColor(Color.BLUE);
        mPaintBarsLeft.setColor(Color.MAGENTA);
        mPaintLabels.setColor(Color.BLACK);
        mPaintLabels.setTextSize(10*mD);
        mPaintLabels.setStyle(Paint.Style.FILL);
        mPaintLabels.setTextAlign(Paint.Align.CENTER);
    }
    private void drawLineRight(int startY,int endY, int endX){
        RectF rect = new RectF(mHorizCenterWoman,startY,endX,endY);
        mCanvas.drawRect(rect, mPaintBarsRight);
    }
    private void drawLineLeft(int startX,int startY,int endY){
        RectF rect = new RectF(startX,startY,mHorizCenterMan,endY);
        mCanvas.drawRect(rect, mPaintBarsLeft);
    }
    public void setPercentsWoman(List<Integer> values){
        mPercentsWomen = new ArrayList<Integer>();
        int max = 0;
        for (Integer value : values) {
            if(value>max){
                max=value;
            }
        }
        for (Integer value : values) {
            mPercentsWomen.add((value*100)/max);
        }
    }
    public void setPercentsMan(List<Integer> values){
        mPercentsMan = new ArrayList<Integer>();
        int max = 0;
        for (Integer value : values) {
            if(value>max){
                max=value;
            }
        }
        for (Integer value : values) {
            mPercentsMan.add((value*100)/max);
        }
    }
    public void drawWoman(){
        int totalSize = mWidth-mHorizCenterWoman;
        for (int i = 0; i <mValuesWoman.size() ; i++) {
            int startY;
            startY = i*(mHeightBars+mLineDistance);
            int endY = startY+mHeightBars;

            int endX = mHorizCenterWoman+((totalSize*mPercentsWomen.get(i))/100);
            drawLineRight(startY,endY,endX);
        }
    }
    public void drawLabels(){
        for (int i = 0; i < mLabels.size() ; i++) {
            int startY;
            startY = (i*(mHeightBars+mLineDistance));
            int endY = startY+mHeightBars;
            drawLabel(mLabels.get(i),endY);
        }
    }
    public void drawLabel(String text,int y){
        mCanvas.drawText(text,mHorizCenter,y,mPaintLabels);
    }
    public void drawMan(){
        int difference = mMidleSpace*2;
        int totalSize = (mWidth-difference)-mHorizCenterMan;
        for (int i = 0; i <mValuesMan.size() ; i++) {
            int startY;
            startY = i*(mHeightBars+mLineDistance);
            int endY = startY+mHeightBars;
            int startX = totalSize - ((mPercentsMan.get(i)*totalSize/100));
            drawLineLeft(startX, startY, endY);
        }
    }
    public void setLabels(List<String> mLabels) {
        this.mLabels = mLabels;
    }
    public void setmValuesMan(List<Integer> mValuesMan) {
        this.mValuesMan = mValuesMan;
    }
    public void setValuesWoman(List<Integer> mValuesWoman) {
        this.mValuesWoman = mValuesWoman;
    }
}
