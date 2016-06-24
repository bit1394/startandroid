package ru.bitreslab.p0381_sqltransaction;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Main extends AppCompatActivity {
    final String LT = "myLogs";
    DBHelper dbh;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        dbh = new DBHelper(this);
        myActions();
    }

        void myActions(){
        db = dbh.getWritableDatabase();
        delete (db, "mytable");
        db.beginTransaction();
        insert (db, "mytable", "val1");
        db.setTransactionSuccessful();
        insert (db, "mytable", "val2");
        db.endTransaction();
        insert (db, "mytable", "val3");
        read (db, "mytable");
        dbh.close();
        }

    void insert (SQLiteDatabase db, String table, String value){
            Log.d (LT, "Insert in table " + table + " value " + value);
            ContentValues cv = new ContentValues();
            cv.put ("val", value);
            db.insert(table, null, cv);
        }

    void read (SQLiteDatabase db, String table) {
        Log.d (LT, "Read table "+ table);
        Cursor cursor = db.query(table, null, null, null, null, null, null);
        if (cursor != null){
            Log.d (LT, "Records count = " + cursor.getCount());
            if (cursor.moveToFirst()){
                do {
                    Log.d(LT, cursor.getString(cursor.getColumnIndex("val")));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
    }

    void delete (SQLiteDatabase db, String table){
        Log.d (LT, "Delete all from tale " + table);
        db.delete(table, null, null);
    }

}
