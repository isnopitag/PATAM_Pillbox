package com.carmona.pillbox.Classes;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.webkit.WebView;

import com.carmona.pillbox.R;

/**
 * Created by carmona on 24/11/17.
 */

public class About {
    String mensaje="Pillbox ver Early Access \n Developed By Carmona at Bandido Studios \n Powered by Alio Mexico.";
    String titulo= "Pillbox";
    Context context;
    public About(Context c){
        context=c;
    }
    public void alerta(){
    AlertDialog.Builder alert = new AlertDialog.Builder(context);
    alert.setTitle(titulo);
    alert.setMessage(mensaje);
    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {

        }
    });
    /*
    alert.setNegativeButton("Cancel",
            new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {
        }
    });*/

    alert.show();
    }
}
