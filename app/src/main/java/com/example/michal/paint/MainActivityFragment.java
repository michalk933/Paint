package com.example.michal.paint;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Collection;
import java.util.zip.Inflater;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private FildDrawing fildDrawing;
    private final static int SAVE_IMAGE_PERMISSION_REQUEST_CODE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        setHasOptionsMenu(true);// set visible menu

        fildDrawing = (FildDrawing) view.findViewById(R.id.fildDrawingFragment);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_paint,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.color_menu:
                ColorDialog colorDialog = new ColorDialog();
                colorDialog.show(getFragmentManager(), "color");
                return true;
            case R.id.color_back_menu:
                ColorBackDialog colorBackDialog = new ColorBackDialog();
                colorBackDialog.show(getFragmentManager(),"back color");
                return true;
            case R.id.width_menu:
                WidthDialog widthDialog = new WidthDialog();
                widthDialog.show(getFragmentManager(),"Wybierz grubosc");
                return true;
            case R.id.save_menu:
                saveImage();
                return true;
            case R.id.figures_menu:
                FiguresDialog figuresDialog = new FiguresDialog();
                figuresDialog.show(getFragmentManager(),"Rodzaj rysunku");
                return true;
            case R.id.txt_menu:
                txtDialog txtDialog = new txtDialog();
                txtDialog.show(getFragmentManager(),"Podaj tekst");
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    // this method get current object FildDrawing, because we would like work in one object, and no open new object
    public FildDrawing getFildDrawing(){
        return fildDrawing;
    }

    private void saveImage(){
        if(getContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            if(shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Aplikacja może zapisać obraz dpiero po uzyskaniu zezwolenia");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},SAVE_IMAGE_PERMISSION_REQUEST_CODE);
                    }
                });
                builder.create();
            }else {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},SAVE_IMAGE_PERMISSION_REQUEST_CODE);
            }
        }else{
            SaveDialog saveDialog = new SaveDialog();
            saveDialog.show(getFragmentManager(),"Zapisz");
        }

    }

}
