package com.example.bob_book.drawexample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by Roman Parkhomenko on 3/29/2018.
 * Sibers company
 * yagosupro@gmail.com
 */

public class DrawView extends SurfaceView implements SurfaceHolder.Callback {

    private DrawView.DrawThread drawThread;
    Paint p;
    public float pitch=0.0f;

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    void newPaint(){
        p = new Paint();

        p.setColor(Color.WHITE);
        p.setStrokeWidth(14);
        getHolder().addCallback(this);
    }

    public DrawView(Context context) {
        super(context);
        newPaint();

    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        newPaint();
    }

    public DrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        newPaint();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawView.DrawThread(getHolder());
        drawThread.setRunning(true);
        drawThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        drawThread.setRunning(false);
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    class DrawThread extends Thread {


        private boolean running = false;
        private SurfaceHolder surfaceHolder;

        public DrawThread(SurfaceHolder surfaceHolder) {
            this.surfaceHolder = surfaceHolder;
        }

        public void setRunning(boolean running) {
            this.running = running;
        }

        @Override
        public void run() {
            Canvas canvas;
            while (running) {
                canvas = null;
                try {
                    canvas = surfaceHolder.lockCanvas(null);
                    if (canvas == null)
                        continue;
                    canvas.drawColor(Color.GREEN);



//                            System.out.println(Math.toDegrees(azimut));
                    canvas.rotate(-pitch
                            *360/(2*3.14159f),canvas.getWidth()/2,canvas.getHeight()/2);
                    canvas.drawLine(0, getHeight()/2, getWidth(), getHeight()/2, p);


                } finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }

}