package com.example.michal.paint;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by michal on 02.11.2016.
 */

public class FiguresDialog extends DialogFragment {

    private RadioGroup figureRadioGrup;
    private RadioButton lineRadioButton, ringRadioButton,recRadioButton,ringOneSizeRadioButton,koloPlusRadioButton, kwadratPlusRadioButton;
    private SeekBar sizeSeekBar;
    private TextView showSize,showFigureTextView;
    private int typeFigure = 0;
    private int Size;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View figuresView = getActivity().getLayoutInflater().inflate(R.layout.figures_fragment,null);
        builder.setView(figuresView);
        builder.setTitle("Rodzaj rysunku");

        figureRadioGrup = (RadioGroup)figuresView.findViewById(R.id.figure_grup);
        lineRadioButton = (RadioButton)figuresView.findViewById(R.id.linieRadioButton);
        ringRadioButton = (RadioButton)figuresView.findViewById(R.id.ringRadioButton);
        recRadioButton = (RadioButton)figuresView.findViewById(R.id.recRadioButton);
        ringOneSizeRadioButton = (RadioButton)figuresView.findViewById(R.id.ringOneSizeRadioButton);
        sizeSeekBar = (SeekBar)figuresView.findViewById(R.id.sizeTxtSeekBar);
        showSize = (TextView)figuresView.findViewById(R.id.txtShowTextView);
        showFigureTextView = (TextView) figuresView.findViewById(R.id.showFigureTextView);

        ////////
        koloPlusRadioButton = (RadioButton)figuresView.findViewById(R.id.recPlusRadioButton);
        kwadratPlusRadioButton = (RadioButton)figuresView.findViewById(R.id.recPlusRadioButton);




        sizeSeekBar.setOnSeekBarChangeListener(changeListenerSeek);
        figureRadioGrup.setOnCheckedChangeListener(checkedlistener);

        final FildDrawing fildDrawing = getFieldDrawinfWithMain().getFildDrawing();


        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), String.valueOf(typeFigure),Toast.LENGTH_LONG).show();
                fildDrawing.setFigure(typeFigure,Size);
            }
        });

        return builder.create();
    }

    private MainActivityFragment getFieldDrawinfWithMain(){
        return (MainActivityFragment)getFragmentManager().findFragmentById(R.id.fildView);
    }


    RadioGroup.OnCheckedChangeListener checkedlistener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            switch (checkedId){
                case R.id.linieRadioButton:
                    typeFigure = 0;
                    break;
                case R.id.ringRadioButton:
                    typeFigure = 1;
                    break;
                case R.id.recRadioButton:
                    typeFigure = 2;
                    break;
                case R.id.ringOneSizeRadioButton:
                    typeFigure = 4;
                    break;
                case R.id.ringPlusRadioButton:
                    typeFigure = 5;
                    break;
                case R.id.recPlusRadioButton:
                    typeFigure = 6;
                    break;
            }
        }
    };

    private SeekBar.OnSeekBarChangeListener changeListenerSeek = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser){
                Size = sizeSeekBar.getProgress();
                showFigureTextView.setTextSize(Size);
                showSize.setText(String.valueOf(Size));
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
