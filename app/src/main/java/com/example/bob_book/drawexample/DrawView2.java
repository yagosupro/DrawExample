package com.example.bob_book.drawexample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Roman Parkhomenko on 3/29/2018.
 * Sibers company
 * yagosupro@gmail.com
 */
public class DrawView2 extends View{
    Paint p;
    public float pitch = 0.0f;

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    void refresh(){
        invalidate();
    }

    void newPaint(){
        p = new Paint();
        p.setColor(Color.WHITE);
        p.setStrokeWidth(14);
    }

    public DrawView2(Context context) {
        super(context);
        newPaint();
    }

    public DrawView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        newPaint();
    }

    public DrawView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        newPaint();
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);

        canvas.rotate(-pitch*360/(2*3.14159f),canvas.getWidth()/2,canvas.getHeight()/2);
        canvas.drawLine(0, getHeight()/2, getWidth(), getHeight()/2, p);


    }

}
