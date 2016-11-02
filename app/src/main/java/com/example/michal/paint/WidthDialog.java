package com.example.michal.paint;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

/**
 * Created by michal on 02.11.2016.
 */

public class WidthDialog extends DialogFragment {

    private SeekBar widthSeekbar;
    private ImageView widthImageView;
    private int widht;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View widthView = getActivity().getLayoutInflater().inflate(R.layout.width_line_fragment,null);
        builder.setView(widthView);
        builder.setTitle("Zmiana grubosci");

        widthSeekbar = (SeekBar)widthView.findViewById(R.id.widthSeekBar);
        widthImageView = (ImageView)widthView.findViewById(R.id.widthImageView);

        widthSeekbar.setOnSeekBarChangeListener(changeListener);

        final FildDrawing fildDrawing = getFieldDrawinfWithMain().getFildDrawing();

        builder.setPositiveButton("Wybierz grubosc", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fildDrawing.setWidhtLine(widthSeekbar.getProgress());
            }
        });


        return builder.create();
    }


    private MainActivityFragment getFieldDrawinfWithMain(){
        return (MainActivityFragment)getFragmentManager().findFragmentById(R.id.fildView);
    }


    SeekBar.OnSeekBarChangeListener changeListener = new SeekBar.OnSeekBarChangeListener() {
        final Bitmap bitmap = Bitmap.createBitmap(400,100, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser){
                widht = widthSeekbar.getProgress();
                Paint paint = new Paint();
                paint.setColor(getFieldDrawinfWithMain().getFildDrawing().getColorLine());
                paint.setStrokeCap(Paint.Cap.ROUND);
                paint.setStrokeWidth(widht);
                bitmap.eraseColor(getResources().getColor(android.R.color.transparent,getContext().getTheme()));
                canvas.drawLine(30,50,370,50,paint);
                widthImageView.setImageBitmap(bitmap);

            }

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };



}
