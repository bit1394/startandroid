package ru.bitreslab.p0611_alertdialogprepare;

import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends AppCompatActivity {
    final String LT = "myLogs";
    final int DIALOG = 1;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        showDialog(DIALOG);
    }

    protected Dialog onCreateDialog(int id){
        if(id == DIALOG){
            Log.d(LT, "onCreateDialog");
            AlertDialog.Builder abd = new AlertDialog.Builder(this);
            abd.setTitle("Current Time");
            abd.setMessage(sdf.format(new Date(System.currentTimeMillis())));
            return abd.create();
        }
        return super.onCreateDialog(id);
    }
    protected void onPrepareDialog(int id, Dialog dialog){
        super.onPrepareDialog(id, dialog);
        if (id == DIALOG) {
            Log.d(LT, "onPrepareDialog");
            ((AlertDialog) dialog).setMessage(sdf.format(new Date(System.currentTimeMillis())));
        }
    }
}
