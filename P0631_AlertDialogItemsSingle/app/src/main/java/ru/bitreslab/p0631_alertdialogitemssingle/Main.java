package ru.bitreslab.p0631_alertdialogitemssingle;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main extends AppCompatActivity {
    final String LT = "myLogs";
    final int DIALOG_ITEMS = 1;
    final int DIALOG_ADAPTER = 2;
    final int DIALOG_CURSOR = 3;

    int cnt = 0;
    DB db;
    Cursor cursor;
    String[] data = {"one", "two", "three", "four"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        db = new DB(this);
        db.open();
        cursor = db.getAllData();
        startManagingCursor(cursor);
    }

    public void onClick(View v){

        switch (v.getId()){
            case R.id.btnItems:
                showDialog(DIALOG_ITEMS);
                break;
            case R.id.btnAdapter:
                showDialog(DIALOG_ADAPTER);
                break;
            case R.id.btnCursor:
                showDialog(DIALOG_CURSOR);
                break;
            default:
                break;
        }
    }

    protected Dialog onCreateDialog(int id){
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        switch (id){
            case DIALOG_ITEMS:
                adb.setTitle(R.string.items);
                adb.setSingleChoiceItems(data, -1, myClickListener);
                break;
            case DIALOG_ADAPTER:
                adb.setTitle(R.string.adapter);
                ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, data);
                adb.setSingleChoiceItems(ad, -1, myClickListener);
                break;
            case DIALOG_CURSOR:
                adb.setTitle(R.string.cursor);
                adb.setSingleChoiceItems(cursor, -1, DB.COLUMN_TXT, myClickListener);
                break;
        }
        adb.setPositiveButton(R.string.btnOK, myClickListener);
        return adb.create();
    }

    DialogInterface.OnClickListener myClickListener = new DialogInterface.OnClickListener(){
        public void onClick(DialogInterface dialog, int which){
            ListView lv = ((AlertDialog)dialog).getListView();
            if(which == dialog.BUTTON_POSITIVE)
                Log.d(LT, "pos = " + lv.getCheckedItemPosition());
            else
                Log.d(LT, "whisch = " + which);
        }
    };

    protected void onDestroy(){
        super.onDestroy();
        db.close();
    }
}
