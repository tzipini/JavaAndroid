package com.example.ourex.takengo.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

public class Dialogs {
    public static void openAlertDialog(Context context, String title, String message,String okButton){
        try {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle(title);
            alertDialogBuilder
                    .setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton(okButton,new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } catch (Exception e) {
            Log.w(Constants.Log.APP_LOG,e);
        }
    }

    public static void openAlertDialog(Context context, Integer titleResourceId, Integer messageResourceId,Integer okButtonResourceId){
        try {
            String title = context.getResources().getString(titleResourceId);
            String message = context.getResources().getString(messageResourceId);
            String btnOk = context.getResources().getString(okButtonResourceId);
            openAlertDialog(context,title,message,btnOk);
        } catch (Exception e) {
            Log.w(Constants.Log.APP_LOG,e);
        }
    }
}
