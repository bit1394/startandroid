package ru.bitreslab.p0391_sqliteonupgrade;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Bit on 26.06.2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Staff";
    public static final String TABLE_NAME = "people";
    public static final String NEW_TABLE = "position";
    public static final int DB_VERSION = 2;


    public DBHelper (Context context){
        super (context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(MainActivity.LT, "onCreate DB " + DB_NAME + "...");
        String [] people_name = {"Иван", "Марья", "Пётр", "Антон", "Даша", "Борис", "Костя", "Игорь"};
        String [] people_positions = {"Программист", "Бухгалтер", "Программист", "Программист", "Бухгалтер", "Директор", "Программист", "Охранник"};

        ContentValues cv = new ContentValues();
        db.execSQL("create table " + TABLE_NAME + "(" +
                "id integer primary key autoincrement, " +
                "name text, position text);");

        for (int i = 0; i < people_name.length; i++){
            cv.clear();
            cv.put("name", people_name [i]);
            cv.put("position", people_positions [i]);
            db.insert(TABLE_NAME, null, cv);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(MainActivity.LT, "onUpgrade database from " + oldVersion + " to " + newVersion + " version in progress..." );
        if (oldVersion == 1 && newVersion == 2){
            ContentValues cv = new ContentValues();
            int[] position_id = {1, 2, 3, 4};
            String[] position_name = {"Директор", "Программист", "Бухгалтер", "Охранник"};
            int[] position_salary = {15000, 13000, 10000, 8000};

            db.beginTransaction();
            try {
                //Создание таблицы position
                db.execSQL("create table " + NEW_TABLE + "(" +
                        "id integer primary key, " +
                        "name text, salary integer);");
                for (int i = 0; i < position_id.length; i++){
                    cv.clear();
                    cv.put("id", position_id[i]);
                    cv.put("name", position_name[i]);
                    cv.put("salary", position_salary[i]);
                    db.insert(NEW_TABLE, null, cv);
                }
                //добавление к таблице people столбца posid
                db.execSQL("alter table " + TABLE_NAME + " add column posid integer;");
                for (int i = 0; i < position_id.length; i++){
                    cv.clear();
                    cv.put("posid", position_id[i]);
                    db.update(TABLE_NAME, cv, NEW_TABLE + " = ?", new String[] {position_name[i]});
                }
                //создание временной таблицы people_tmp для выгрузки в нее анных их people
                db.execSQL("create temporary table people_tmp (" +
                        "id integer, name text, position text, posid integer);");
                //выгрузка данных из people в people_tmp
                db.execSQL("insert into people_tmp select id, name, position, posid from people;");
                //удаление уже ненужной people
                db.execSQL("drop table " + TABLE_NAME);
                //создание новой people с нужной структурой
                db.execSQL("create table " + TABLE_NAME + " (" +
                        "id integer primary key autoincrement, " +
                        "name text, posid integer);");
                //выгрузка из временной people_tmp в новую People
                db.execSQL("insert into " + TABLE_NAME + " select id, name, posid from people_tmp;");
                //удаление временной people_tmp
                db.execSQL("drop table people_tmp;");
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        }

    }
}
