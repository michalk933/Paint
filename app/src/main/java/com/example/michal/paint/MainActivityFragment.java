package com.example.michal.paint;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;
import java.util.zip.Inflater;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private FildDrawing fildDrawing;

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

        }

        return super.onOptionsItemSelected(item);
    }

    // this method get current object FildDrawing, because we would like work in one object, and no open new object
    public FildDrawing getFildDrawing(){
        return fildDrawing;
    }

}
