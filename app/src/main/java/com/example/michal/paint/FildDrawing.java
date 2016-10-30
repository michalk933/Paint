package com.example.michal.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by michal on 30.10.2016.
 */

public class FildDrawing extends View
{
    private Bitmap bitmap;// Field image
    private Canvas canvasBit;// drawing in bitmap
    private Paint paintScreen;// show bitmap
    private Paint paintLine;//  drawing in bitmap

    private Map<Integer,Path> pathMap = new HashMap<>();
    private Map<Integer,Point> pointMap = new HashMap<>();


    public FildDrawing(Context context, AttributeSet attrs) {
        super(context, attrs);

        paintLine = new Paint();
        paintScreen = new Paint();

        paintLine.setColor(Color.BLACK);// color line
        ////// next custom by user //////////////
        paintLine.setStrokeCap(Paint.Cap.ROUND);// end round
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setAntiAlias(true);// no sharp
        paintLine.setStrokeWidth(5);// width line
        ////// next custom by user //////////////

    }



    ///// make bitmap by good size
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(getWidth(),getHeight(), Bitmap.Config.ARGB_8888);// create field bitmap ( widht screen, height screen, and configuration system color
        canvasBit = new Canvas(bitmap);// create canvas
        bitmap.eraseColor(Color.WHITE);// color backgrand

        ////// next custom color backgrand by user //////////////
    }

    //// onDrawing refresh when user chane bitmap ( drawing line )
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,0,0,paintScreen);

        //// take key every id finger and drawing in CANVAS line by paintLine ///
        for( Integer key : pathMap.keySet() ){
            canvas.drawPath(pathMap.get(key),paintLine);
        }


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
