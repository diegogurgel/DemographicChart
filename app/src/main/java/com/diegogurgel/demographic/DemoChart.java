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
    Paint mPaintBarsRight;
    Paint mPaintBarsLeft;
    List<Integer> mPercentsWomen;
    List<Integer> mValuesWoman;
    List<Integer> mPercentsMan;

    public void setmValuesMan(List<Integer> mValuesMan) {
        this.mValuesMan = mValuesMan;
    }

    List<Integer> mValuesMan;

    public void setValuesWoman(List<Integer> mValuesWoman) {
        this.mValuesWoman = mValuesWoman;
    }



    public DemoChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        mD = context.getResources().getDisplayMetrics().density;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas = canvas;
        mWidth = canvas.getWidth();
        mHeight = canvas.getHeight();
        mHorizCenter = mWidth/2;
        mHorizCenterWoman = mHorizCenter+(int)(30*mD);
        mHorizCenterMan = mHorizCenter-(int)(30*mD);
        init();

        List<Integer> values = new ArrayList<Integer>();

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

    }
    private void init(){
        mPaintBarsRight = new Paint();
        mPaintBarsLeft = new Paint();
        mPaintBarsRight.setColor(Color.BLUE);
        mPaintBarsLeft.setColor(Color.MAGENTA);

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
        int heightBar = (int)(7*mD);
        int distance = (int)(3*mD);

        for (int i = 0; i <mValuesWoman.size() ; i++) {
            int startY;
            startY = (int)(i*(heightBar+distance));
            int endY = startY+heightBar;

            int endX = mHorizCenterWoman+((totalSize*mPercentsWomen.get(i))/100);
            drawLineRight(startY,endY,endX);
        }
    }
    public void drawMan(){
        int difference =(int)((30*2)*mD);
        int totalSize = (mWidth-difference)-mHorizCenterMan;
        int heightBar = (int)(7*mD);
        int distance = (int)(3*mD);

        for (int i = 0; i <mValuesMan.size() ; i++) {
            int startY;
            startY = (int)(i*(heightBar+distance));
            int endY = startY+heightBar;
            int startX = totalSize - ((mPercentsMan.get(i)*totalSize/100));
            drawLineLeft(startX,startY,endY);
        }
    }
}
