package com.example.michal.paint;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by michal on 02.11.2016.
 */

public class SaveDialog extends DialogFragment {
    private EditText nameFileEditText;
    Bitmap bitmap;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View saveDialog = getActivity().getLayoutInflater().inflate(R.layout.save_fragment,null);
        builder.setView(saveDialog);
        builder.setTitle("Zapisz");

        nameFileEditText = (EditText)saveDialog.findViewById(R.id.nameFileEditText);
        FildDrawing fildDrawing = getFieldDrawinfWithMain().getFildDrawing();
        bitmap = Bitmap.createBitmap(fildDrawing.getBitmapToSave());



        builder.setPositiveButton("Zapisz", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = nameFileEditText.getText().toString() + ".jpg";
                String location = MediaStore.Images.Media.insertImage(getContext().getContentResolver(),bitmap,name,"Obraz paint");

                if(location != null){
                    Toast.makeText(getContext(),"Obraz został zapisany w galeri",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getContext(), "Błąd zapisu :/", Toast.LENGTH_LONG).show();
                }
            }
        });



        return builder.create();
    }


    private final MainActivityFragment getFieldDrawinfWithMain(){
        return (MainActivityFragment)getFragmentManager().findFragmentById(R.id.fildView);
    }


}
