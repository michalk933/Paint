package com.example.michal.paint;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.SeekBar;

/**
 * Created by michal on 02.11.2016.
 */

public class ColorBackDialog extends DialogFragment {

    private SeekBar alphaSeekBar, redSeekBar, greenSeekBar, blueSeekBar;
    private View colorView;
    private int color;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View colorBackView = getActivity().getLayoutInflater().inflate(R.layout.color_back_ground,null);
        builder.setView(colorBackView);
        builder.setTitle("backgroun color");

        alphaSeekBar = (SeekBar)colorBackView.findViewById(R.id.alphaBackSeekBar);
        redSeekBar = (SeekBar)colorBackView.findViewById(R.id.redBackSeekBar);
        greenSeekBar = (SeekBar)colorBackView.findViewById(R.id.greenBackSeekBar);
        blueSeekBar = (SeekBar)colorBackView.findViewById(R.id.blueBackSeekBar);
        colorView = colorBackView.findViewById(R.id.colorBackView);


        alphaSeekBar.setOnSeekBarChangeListener(changeListener);
        redSeekBar.setOnSeekBarChangeListener(changeListener);
        greenSeekBar.setOnSeekBarChangeListener(changeListener);
        blueSeekBar.setOnSeekBarChangeListener(changeListener);

        final FildDrawing fildDrawing = getFieldDrawingWithMain().getFildDrawing();

        color = Color.WHITE;
        alphaSeekBar.setProgress(Color.alpha(color));
        redSeekBar.setProgress(Color.red(color));
        greenSeekBar.setProgress(Color.green(color));
        blueSeekBar.setProgress(Color.blue(color));





        builder.setPositiveButton("Wybierz kolor", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fildDrawing.setBackColor(color);
            }
        });




        return builder.create();
    }

    private MainActivityFragment getFieldDrawingWithMain(){
        return (MainActivityFragment) getFragmentManager().findFragmentById(R.id.fildView);
    }




    private final SeekBar.OnSeekBarChangeListener changeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser){
                color = Color.argb(alphaSeekBar.getProgress(),redSeekBar.getProgress(),greenSeekBar.getProgress(),blueSeekBar.getProgress());
            }
            colorView.setBackgroundColor(color);

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };



}

