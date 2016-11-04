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
 * Created by michal on 31.10.2016.
 */

public class ColorDialog extends DialogFragment {

    private SeekBar alphaSeekBar, redSeekBar, greenSeekBar, blueSeekBar;
    private View colorView;
    private int color;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View colorDialogView = getActivity().getLayoutInflater().inflate(R.layout.color_fragment,null);
        builder.setView(colorDialogView);

        builder.setTitle("Wybierz kolor");

        alphaSeekBar = (SeekBar)colorDialogView.findViewById(R.id.alphaSeekBar);
        redSeekBar = (SeekBar)colorDialogView.findViewById(R.id.redSeekBar);
        greenSeekBar = (SeekBar)colorDialogView.findViewById(R.id.greenSeekBar);
        blueSeekBar = (SeekBar)colorDialogView.findViewById(R.id.blueSeekBar);
        colorView = colorDialogView.findViewById(R.id.colorView);


        alphaSeekBar.setOnSeekBarChangeListener(changeListener);
        redSeekBar.setOnSeekBarChangeListener(changeListener);
        greenSeekBar.setOnSeekBarChangeListener(changeListener);
        blueSeekBar.setOnSeekBarChangeListener(changeListener);

        final FildDrawing fildDrawingg = getFildDrawing().getFildDrawing();


        color = fildDrawingg.getColorLine();
        alphaSeekBar.setProgress(Color.alpha(color));
        redSeekBar.setProgress(Color.red(color));
        greenSeekBar.setProgress(Color.green(color));
        blueSeekBar.setProgress(Color.blue(color));


         builder.setPositiveButton("Wybierz", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 fildDrawingg.setColorLine(color);
             }
         });
        return builder.create();
    }

    private MainActivityFragment getFildDrawing(){
        return (MainActivityFragment) getFragmentManager().findFragmentById(R.id.fildView);
    }

    private final SeekBar.OnSeekBarChangeListener changeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser) {
                color = Color.argb(alphaSeekBar.getProgress(), redSeekBar.getProgress(), greenSeekBar.getProgress(), blueSeekBar.getProgress());
            }
            colorView.setBackgroundColor(color);
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    };

}
