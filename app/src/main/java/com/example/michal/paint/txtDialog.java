package com.example.michal.paint;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by michal on 03.11.2016.
 */



public class txtDialog extends DialogFragment {

    private EditText txt;
    private SeekBar txtSizeSeekBar;
    private TextView showSizeTextView;
    int size = 20;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View txtView = getActivity().getLayoutInflater().inflate(R.layout.txt_fragment,null);
        builder.setView(txtView);
        builder.setTitle("Podaj tekst");


        txt = (EditText)txtView.findViewById(R.id.txtEditText);
        txtSizeSeekBar = (SeekBar)txtView.findViewById(R.id.sizeTxtSeekBar);
        showSizeTextView = (TextView)txtView.findViewById(R.id.txtShowTextView);
        txtSizeSeekBar.setProgress(20);

        txtSizeSeekBar.setOnSeekBarChangeListener(changeListener);


        final FildDrawing fildDrawing = getFieldDrawinfWithMain().getFildDrawing();

        builder.setPositiveButton("Dodaj +", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String txtUser = txt.getText().toString();
                fildDrawing.setText(txtUser,size);
            }
        });


       return builder.create();
    }

    private MainActivityFragment getFieldDrawinfWithMain(){
        return (MainActivityFragment)getFragmentManager().findFragmentById(R.id.fildView);
    }


    private SeekBar.OnSeekBarChangeListener changeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser){
                size = txtSizeSeekBar.getProgress();
            }
            txt.setTextSize(size/2);
            showSizeTextView.setText(String.valueOf(size));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

}


