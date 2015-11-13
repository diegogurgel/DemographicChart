package com.diegogurgel.demographicchart;

import android.content.Context;
import android.content.res.TypedArray;
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
    private int mMax = 0;
    Paint mPaintBarsRight;
    Paint mPaintBarsLeft;
    Paint mPaintLabels;
    Paint mPaintScale;
    List<Integer> mPercentsWomen;
    List<Integer> mValuesWoman;
    List<Integer> mPercentsMan;
    List<Integer> mValuesMan;
    List<String> mLabels;
    int mColorMan,mColorWoman,mTextColor;


    public DemoChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        mD = context.getResources().getDisplayMetrics().density;

        mMidleSpace = Math.round(20 * mD);
        mHeightBars = Math.round(10 * mD);
        TypedArray a =  context.getTheme().obtainStyledAttributes(attrs,R.styleable.DemoChart,0,0);
        mColorMan = a.getColor(R.styleable.DemoChart_manColor,Color.BLUE);
        mColorWoman = a.getColor(R.styleable.DemoChart_womanColor,Color.MAGENTA);
        mTextColor = a.getColor(R.styleable.DemoChart_textColor,Color.BLACK);

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
        mLineDistance = Math.round(mCanvas.getHeight()/mValuesWoman.size())-mHeightBars;
        init();
        drawWoman();
        drawMan();
        drawLabels();
    }
    private void init(){
        mPaintBarsRight = new Paint();
        mPaintBarsLeft = new Paint();
        mPaintLabels = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintScale = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBarsRight.setColor(mColorWoman);
        mPaintBarsLeft.setColor(mColorMan);
        mPaintLabels.setColor(mTextColor);
        mPaintLabels.setTextSize(10 * mD);
        mPaintLabels.setStyle(Paint.Style.FILL);
        mPaintLabels.setTextAlign(Paint.Align.CENTER);
        mPaintScale.setColor(mTextColor);

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
        for (Integer value : values) {
            if(value>mMax){
                mMax=value;
            }
        }
        for (Integer value : values) {
            mPercentsWomen.add((value*100)/mMax);
        }
    }
    public void setPercentsMan(List<Integer> values){
        mPercentsMan = new ArrayList<Integer>();
        for (Integer value : values) {
            if(value>mMax){
                mMax=value;
            }
        }
        for (Integer value : values) {
            mPercentsMan.add((value*100)/mMax);
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
            int endY = (startY+mHeightBars)-(int)(1*mD);
            drawLabel(mLabels.get(i), endY);
        }
    }
    public void drawLabel(String text,int y){
        mCanvas.drawText(text,mHorizCenter,y,mPaintLabels);
    }
    public void drawScale(){

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
        setPercentsMan(mValuesMan);
    }
    public void setValuesWoman(List<Integer> mValuesWoman) {
        this.mValuesWoman = mValuesWoman;
        setPercentsWoman(mValuesWoman);
    }
}
