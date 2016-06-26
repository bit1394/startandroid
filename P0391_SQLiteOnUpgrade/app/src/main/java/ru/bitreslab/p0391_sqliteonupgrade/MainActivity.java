package ru.bitreslab.p0391_sqliteonupgrade;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    public static final String LT = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbh = new DBHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        Log.d(LT, DBHelper.DB_NAME + " db v." + DBHelper.DB_VERSION + ":");
        writeStaff(db);
        dbh.close();
    }

    private void writeStaff(SQLiteDatabase db){
        Cursor cursor = db.rawQuery("select * from " + DBHelper.TABLE_NAME, null);
        logCursor (cursor, "Table " + DBHelper.TABLE_NAME);
        cursor.close();

        cursor = db.rawQuery("select * from " + DBHelper.NEW_TABLE, null);
        logCursor(cursor, "Table " + DBHelper.NEW_TABLE);
        cursor.close();

        String sqlQuery = "select " + DBHelper.TABLE_NAME + ".name as Name, " +
                DBHelper.NEW_TABLE + ".name as Position, " +
                "salary as Salary from " + DBHelper.TABLE_NAME +
                " inner join " + DBHelper.NEW_TABLE +
                " on " + DBHelper.TABLE_NAME + ".posid = " +
                DBHelper.NEW_TABLE + ".id";
        cursor = db.rawQuery(sqlQuery, null);
        logCursor(cursor, "inner join");
        cursor.close();
    }

    void logCursor(Cursor cursor, String title){
        if (cursor != null){
            if (cursor.moveToFirst()){
                Log.d(LT, title + "." + cursor.getCount() + " rows:");
                StringBuilder sb = new StringBuilder();
                do {
                    sb.setLength(0);
                    for (String cn: cursor.getColumnNames()){
                        sb.append(cn + " = " + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(LT, sb.toString());
                } while (cursor.moveToNext());
            }
        } else
            Log.d(LT, title + ". Cursor is null");
    }
}
