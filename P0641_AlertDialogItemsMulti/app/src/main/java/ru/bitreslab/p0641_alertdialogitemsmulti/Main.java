package ru.bitreslab.p0641_alertdialogitemsmulti;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ListView;

public class Main extends AppCompatActivity {
    final String LT = "myLogs";
    final int DIALOG_ITEMS = 1;
    final int DIALOG_CURSOR = 3;

    int cnt = 0;
    DB db;
    Cursor cursor;
    String[] data = {"one", "two", "three", "four"};
    boolean[] chkd = {false, true, true, false};

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
            case R.id.btnCursor:
                showDialog(DIALOG_CURSOR);
                break;
            default:
                break;
        }
    }

    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        switch (id) {
            case DIALOG_ITEMS:
                adb.setTitle(R.string.items);
                adb.setMultiChoiceItems(data, chkd, myItemsMultiCLickListener);
                break;
            case DIALOG_CURSOR:
                adb.setTitle(R.string.cursor);
                adb.setMultiChoiceItems(cursor, DB.COLUMN_CHK, DB.COLUMN_TXT, myCursorMultiClickListener);
                break;
        }
        adb.setPositiveButton(R.string.bntOk, myBtnClickLIstener);
        return adb.create();
    }

    DialogInterface.OnMultiChoiceClickListener myItemsMultiCLickListener = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            Log.d(LT, "which = " + which + ", isChecked = " + isChecked);
        }
    };

    DialogInterface.OnMultiChoiceClickListener myCursorMultiClickListener = new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
            ListView lv = ((AlertDialog)dialog).getListView();
            Log.d(LT, "which = " + which + ", isChecked = " + isChecked);
            db.changeRec(which, isChecked);
            cursor.requery();
        }
    };

    DialogInterface.OnClickListener myBtnClickLIstener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            SparseBooleanArray sbArray = ((AlertDialog)dialog).getListView().getCheckedItemPositions();
            for(int i = 0; i < sbArray.size(); i++){
                int key = sbArray.keyAt(i);
                if(sbArray.get(key))
                    Log.d(LT, "isChecked = " + key); //какие поля менялись
            }
        }
    };

    protected void onDestroy(){
        super.onDestroy();
        db.close();
    }
}
