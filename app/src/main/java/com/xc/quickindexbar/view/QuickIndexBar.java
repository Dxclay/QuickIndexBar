package com.xc.quickindexbar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static android.view.MotionEvent.*;

/**
 * Created by xxdeng on 2017/10/20.
 */

public class QuickIndexBar extends View {

    private Paint mPaint;
    private int mColor=0xFF7C7C7A;
    private float cellHeight;
    private float cellWidth;
    private  int lastLetter=-1;
    private OnTouchLetterListener onTouchLetterListener;
    private String [] indexArr=new String[]{"A","B","C","D","E","F","G","H","I","J","K","L",
                                     "M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    public QuickIndexBar(Context context) {
        super(context);
        init();
    }

    public QuickIndexBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuickIndexBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mColor);
        mPaint.setTextSize(18);
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        cellWidth=getMeasuredWidth();
        cellHeight=getMeasuredHeight()*1f/26;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < indexArr.length; i++) {
            float x=cellWidth/2;
            float y=cellHeight/2+getTextHeight(indexArr[i])/2+i*cellHeight;
            //这个判断可用可不用，因为变化速度太快了，了解就好
            mPaint.setColor(lastLetter==i?Color.WHITE:mColor);
            canvas.drawText(indexArr[i],x,y,mPaint);
        }
    }


    /**
     * 触摸事件
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
         switch (event.getAction()) {
             case MotionEvent.ACTION_DOWN:
             case MotionEvent.ACTION_MOVE:
                 float y=event.getY();
                 int index= (int) (y/cellHeight);
                 //安全性的检查，这样安全性能比较好一点，不然可能会发生ANR
                if (index>=0&&index<indexArr.length){
                    if (lastLetter!=index){
//                     Log.d("index",indexArr[index]);
                        onTouchLetterListener.TouchLetter(indexArr[index]);
                    }
                }
                 lastLetter=index;
                 break;
             case MotionEvent.ACTION_UP:
                 lastLetter=-1;
                 break;
                 }
        return true;
    }

    private int getTextHeight(String text) {
        Rect bounds=new Rect();
        mPaint.getTextBounds(text,0, text.length(),bounds);
        return bounds.height();
    }

    //******************************下面是实现点击事件的接口，暴露方法**************************************************
    public void setOnTouchLetterListener(OnTouchLetterListener onTouchLetterListener){
        this.onTouchLetterListener=onTouchLetterListener;
    }
    public interface OnTouchLetterListener{
        void TouchLetter(String letter);
    }
}
