package ru.bitreslab.p0371_sqliteinnerjoin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Main extends AppCompatActivity {
    final String LT = "myLogs";
    int[] position_id = {1, 2, 3, 4};
    String [] position_name = {"Директор", "Программист", "Бухгалетр", "Охранник"};
    int[] position_salary = {15000, 13000, 10000, 8000};
    String [] people_name = {"Иван", "Марья", "Пётр", "Антон", "Даша", "Борис", "Костя", "Игорь"};
    int people_posid [] = {2, 3, 2, 2, 3, 1, 2, 4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        DBHelper dbh = new DBHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();

        Cursor cursor;

        Log.d(LT, "Position: ");
        cursor = db.query("position", null, null, null, null, null, null);
        logCursor(cursor);

        Log.d(LT, "People: ");
        cursor = db.query("people", null, null, null, null, null, null);
        logCursor(cursor);

        Log.d(LT, "INNER JOIN with rawQuery:");
        String sqlQuery = "select PL.name as Name, " +
                "PS.name as Position, salary as Salary" +
                " from people as PL " +
                "inner join position as PS " +
                "on PL.posid = PS.id " +
                "where salary > ?";
        cursor = db.rawQuery(sqlQuery, new String [] {"12000"});
        logCursor(cursor);

        Log.d(LT, "INNER JOIN with query:");
        String table = "people as PL inner join position as PS " +
                "on PL.posid = ps.id";
        String columns[] = {"PL.name as Nmae", "PS.name as Position ", "salary as Salary"};
        String selection  = "salary < ?";
        String selectionArgs[] = {"12000"};
        cursor = db.query(table, columns, selection, selectionArgs, null, null, null);
        logCursor(cursor);
        dbh.close();
    }

    void logCursor(Cursor cursor){
        if (cursor != null){
            if (cursor.moveToFirst()){
                String str;
                do {
                    str = "";
                    for (String cn:cursor.getColumnNames()){
                        str = str.concat(cn + " = " + cursor.getString(cursor.getColumnIndex(cn)) + ";");
                    }
                    Log.d(LT, str);
                } while (cursor.moveToNext());
            } else {
                Log.d(LT, "Cursor is null");
            }
        }
    }

    class DBHelper extends SQLiteOpenHelper {
        public DBHelper (Context context){
            super(context, "myDB", null, 1);
        }

        public void onCreate(SQLiteDatabase db){
            ContentValues cv = new ContentValues();
            db.execSQL("create table position (" +
                    " id integer primary key, " +
                    " name text, " +
                    " salary integer);");
            for (int i = 0; i < position_id.length; i++){
                cv.clear();
                cv.put("id", position_id [i]);
                cv.put("name", position_name [i]);
                cv.put("salary", position_salary [i]);
                db.insert("position", null, cv);
            }

            db.execSQL("create table people (" +
                    " id integer primary key autoincrement, " +
                    " name text, " +
                    " posid integer);");
            for (int i = 0; i < people_name.length; i++){
                cv.clear();
                cv.put("name", people_name [i]);
                cv.put("posid", people_posid [i]);
                db.insert("people", null, cv);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
