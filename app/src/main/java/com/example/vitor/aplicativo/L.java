package com.example.vitor.aplicativo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Vitor on 20/04/2015.
 */
public class L {
    public  static void m(String message){
        Log.d("Vitor", "" + message);}
    public static void t (Context context, boolean message){
        Toast.makeText(context,message+"",Toast.LENGTH_SHORT).show();
    }
}
