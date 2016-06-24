package ru.bitreslab.p0381_sqltransaction;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bit on 24.06.2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper (Context context){
        super (context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table mytable (" +
                " id integer primary key autoincrement, " +
                "val text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
