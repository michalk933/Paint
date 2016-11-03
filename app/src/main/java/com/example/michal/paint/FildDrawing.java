package com.example.michal.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.MonthDisplayHelper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Created by michal on 30.10.2016.
 */

public class FildDrawing extends View
{
    private static final float TOUCH_TOLERANCE = 10;

    private Bitmap bitmap;// Field image
    private Canvas canvasBit;// drawing in bitmap
    private Paint paintScreen;// show bitmap
    private Paint paintLine;//  drawing in bitmap

    private Map<Integer,Path> pathMap = new HashMap<>();
    private Map<Integer,Point> pointMap = new HashMap<>();

    private int figure = 0;
    private int size;
    private String addTxt;




    public FildDrawing(Context context, AttributeSet attrs) {
        super(context, attrs);

        paintLine = new Paint();
        paintScreen = new Paint();

        ////// next custom by user //////////////
        paintLine.setColor(Color.BLACK);
        paintLine.setStrokeCap(Paint.Cap.ROUND);// end round
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setAntiAlias(true);// no sharp
        paintLine.setStrokeWidth(5);// width line
        ////// next custom by user ///////////////
    }


    ///// make bitmap by good size
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(getWidth(),getHeight(), Bitmap.Config.ARGB_8888);// create field bitmap ( widht screen, height screen, and configuration system color
        canvasBit = new Canvas(bitmap);// create canvas
        bitmap.eraseColor(Color.WHITE);// color backgrand

        ////// next custom color backgrand by user //////////////
    }

    //// onDrawing refresh when user chane bitmap ( drawing line )
    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        canvas.drawBitmap(bitmap,0,0,paintScreen);

        //// take key every id finger and drawing in CANVAS line by paintLine ///
        for( Integer key : pathMap.keySet() ){
            canvas.drawPath(pathMap.get(key),paintLine);
        }


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        int idFinger = event.getActionIndex();

        if( action == MotionEvent.ACTION_POINTER_DOWN || action == MotionEvent.ACTION_DOWN){// ferst touch
            touchFirst(event.getX(idFinger), event.getY(idFinger),event.getPointerId(idFinger));
        }else if( action == MotionEvent.ACTION_POINTER_UP || action == MotionEvent.ACTION_UP ){// next touch
            ended(event.getPointerId(idFinger));
        }else {// end touch
            touchMoved(event);
        }
        invalidate();// refresh
        return true;
    }


    //////
    ///// Create Path and Point
    ///// look, is it id in patchmap
    //// if this is ferst touch id, create new path
    private void touchFirst(float x, float y, int idLine){
        Path path;
        Point point;

        if(pathMap.containsKey(idLine)){
            path = pathMap.get(idLine);// get existing line ( this line is visible in screen )
            path.reset(); // restart line ( because new point can't conect with old line
            point = pointMap.get(idLine); // get end point line, which existing

        }else {
            // declaration path and poin, and initialization
            path = new Path();
            pathMap.put(idLine,path);
            point = new Point();
            pointMap.put(idLine,point);
        }

        path.moveTo(x,y);// first co-ordinates tihs finger
        point.x = (int) x;
        point.y = (int) y;
    }


    private void touchMoved(MotionEvent event){
        for( int i = 0; i < event.getPointerCount(); i++){////// step by step all idLine
            int idPointer = event.getPointerId(i); // get id event ( finger )
            int indexPointer = event.findPointerIndex(idPointer);// get index

            if(pathMap.containsKey(idPointer)){// if existing path for finger ( have to path because created in touchFirst()
                float newX = event.getX(indexPointer);// get new point
                float newY = event.getY(indexPointer);

                Path path = pathMap.get(idPointer); // get path and point current finger
                Point point = pointMap.get(idPointer);

                float deltaX = Math.abs(newX - point.x); // count difference carrent point and past point ( point.x declareting int touchFirst and for end this method )
                float deltaY = Math.abs(newY - point.y);

                if( deltaX >= TOUCH_TOLERANCE || deltaY >= TOUCH_TOLERANCE){ // if volue delta bigger than 10 000 drawing line
                    /*
                    switch (figure) {
                        case 0:
                            path.quadTo(point.x, point.y, (newX + point.x) / 2, (newY + point.y) / 2);
                            return ;
                        case 1:
                            Random r = new Random();
                            int i1 = r.nextInt(100 - 15) + 15;
                            path.addCircle(point.x, point.y,i1, Path.Direction.CW);
                            return;
                        case 2:
                            path.addRect(point.x, point.y,point.x+size, point.y+size, Path.Direction.CW);
                            return;
                        case 4:
                            path.addCircle(point.x, point.y,size, Path.Direction.CW);
                            return;

                    }*/

                    if(figure == 0) {
                        path.quadTo(point.x, point.y, (newX + point.x) / 2, (newY + point.y) / 2);
                    }else if(figure == 1){
                        Random r = new Random();
                        int i1 = r.nextInt(100 - 15) + 15;
                        path.addCircle(point.x, point.y,i1, Path.Direction.CW);
                    }else if(figure == 2 ){
                        path.addRect(point.x, point.y,point.x+size, point.y+size, Path.Direction.CW);
                    }else if(figure == 4 ){
                        path.addCircle(point.x, point.y,size, Path.Direction.CW);
                    }else if(figure == 5 ){
                        switch (figure) {
                            case 0:
                                path.quadTo(point.x, point.y, (newX + point.x) / 2, (newY + point.y) / 2);
                                return ;
                            case 1:
                                Random r = new Random();
                                int i1 = r.nextInt(100 - 15) + 15;
                                path.addCircle(point.x, point.y,i1, Path.Direction.CW);
                                return;
                            case 2:
                                path.addRect(point.x, point.y,point.x+size, point.y+size, Path.Direction.CW);
                                return;
                            case 4:
                                path.addCircle(point.x, point.y,size, Path.Direction.CW);
                                return;
                        }
                    }else if(figure == 6 ) {
                        switch (figure) {
                            case 0:
                                path.quadTo(point.x, point.y, (newX + point.x) / 2, (newY + point.y) / 2);
                                return;
                            case 1:
                                Random r = new Random();
                                int i1 = r.nextInt(100 - 15) + 15;
                                path.addCircle(point.x, point.y, i1, Path.Direction.CW);
                                return;
                            case 2:
                                path.addRect(point.x, point.y, point.x + size, point.y + size, Path.Direction.CW);
                                return;
                            case 4:
                                path.addCircle(point.x, point.y, size, Path.Direction.CW);
                                return;
                        }
                    }


                    point.x = (int) newX;
                    point.y = (int) newY;
                }else if(figure == 3){
                    paintLine.setTextSize(size);
                    canvasBit.drawTextOnPath(addTxt,path,point.x,point.y,paintLine);
                    figure = 0;

                }
            }
        }
    }

    private void ended(int idLine){
        Path path = pathMap.get(idLine);
        canvasBit.drawPath(path,paintLine);
        path.reset();
    }


    public void setColorLine(int color){
        paintLine.setColor(color);
    }
    public int getColorLine(){
        return paintLine.getColor();
    }

    public void setBackColor(int color){ canvasBit.drawColor(color);}

    public void setWidhtLine(int width){
        paintLine.setStrokeWidth(width);
    }


    public FildDrawing cleanField(){
        pathMap.clear();
        pointMap.clear();
        bitmap.eraseColor(Color.WHITE);
        paintLine.setColor(Color.BLACK);
        invalidate();
        return null;
    }

    public Bitmap getBitmapToSave(){
        return bitmap;
    }

    public void setFigure(int typeFigure, int sizeFigure){
        size = sizeFigure;
        figure = typeFigure;
    }

    public void setText(String txt,int sizeTxt){
        addTxt = txt;
        figure = 3;
        size = sizeTxt;

    }


















}
