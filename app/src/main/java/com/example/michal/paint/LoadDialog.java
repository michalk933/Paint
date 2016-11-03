package com.example.michal.paint;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ImageView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by michal on 03.11.2016.
 */

public class LoadDialog extends DialogFragment {

    private ImageView i;
    Uri selectedImage;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View loadView = getActivity().getLayoutInflater().inflate(R.layout.load_fragment,null);
        builder.setView(loadView);
        builder.setTitle("Wczytaj zdjecie");

        i = (ImageView)loadView.findViewById(R.id.load_image_menu);

        final FildDrawing fildDrawing = getFieldDrawinfWithMain().getFildDrawing();
        /////////////////// //////////////// ////////////// ///////////////

        //Intent photo = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       // startActivityForResult(photo,0);




        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto , 1);


        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fildDrawing.imgUri(selectedImage);
            }
        });

        return builder.create();
    }

    private MainActivityFragment getFieldDrawinfWithMain() {
        return (MainActivityFragment)getFragmentManager().findFragmentById(R.id.fildView);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:
                if(requestCode == RESULT_OK ) {
                    Uri selectedImage = data.getData();
                    i.setImageURI(selectedImage);
                    break;
                }
            case 1:
                if(resultCode == RESULT_OK){
                    selectedImage = data.getData();
                    //i.setImageURI(selectedImage);
                }
                break;
        }
    }
}
