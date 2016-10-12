package ru.bitreslab.p0661_alertdialogoperations;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v4.provider.DocumentFile;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Main extends AppCompatActivity {
    final String LT = "myLogs";
    final int DIALOG = 1;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG) {
            Log.d(LT, "Create");
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Title");
            adb.setMessage("Messaga");
            adb.setPositiveButton("OK", null);
            dialog = adb.create();

            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    Log.d(LT, "Show");
                }
            });

            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    Log.d(LT, "Cancel");
                }
            });

            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    Log.d(LT, "Dismiss");
                }
            });
            return dialog;
        }
        return super.onCreateDialog(id);
    }

    void method1(){
        dismissDialog(DIALOG);
    }
    void method2(){
        showDialog(DIALOG);
    }

    public void onClick(View v){
        showDialog(DIALOG);

        Handler h = new Handler();

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                method1();
            }
        }, 2000);

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                method2();
            }
        }, 4000);
    }
}
