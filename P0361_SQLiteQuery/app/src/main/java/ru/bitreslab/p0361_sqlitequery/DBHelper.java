package ru.bitreslab.p0361_sqlitequery;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bit on 08.05.2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String KEY_ID = "_id";
    public static final int DB_VERSION = 1;
    public static final String TABLE_COUNTRY = "country";
    public static final String KEY_NAME = "name";
    public static final String KEY_PEOPLE = "people";
    public static final String KEY_REGION = "region";
    public static final String DB_NAME = "myDB";

    public DBHelper (Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_COUNTRY
        + "(" + KEY_ID
        + " integer primary key, "
        + KEY_NAME + " text, "
        + KEY_PEOPLE + " integer, "
        + KEY_REGION + " text"
        + ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
