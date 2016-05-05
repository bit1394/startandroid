package ru.bitreslab.p0351_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main extends AppCompatActivity implements View.OnClickListener{
    EditText etName, etEmail, etId;
    Button btnAdd, btnRead, btnClear, btnUpdate, btnDelete;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etId = (EditText) findViewById(R.id.etId);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        btnAdd.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);


        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String id = etId.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        switch (v.getId()){

            case R.id.btnAdd:
                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_EMAIL, email);
                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                break;

            case R.id.btnRead:
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);
                if (cursor.moveToFirst()){
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                    int emailIndex = cursor.getColumnIndex(DBHelper.KEY_EMAIL);

                    do {
                        Log.d("myLogs", "ID = " + cursor.getInt(idIndex)
                                + ", name = " + cursor.getString(nameIndex)
                                + ", email = " + cursor.getString(emailIndex));
                    } while (cursor.moveToNext());
                } else {
                    Log.d("myLogs", "DB is empty :(");
                }
                cursor.close();
                break;

            case R.id.btnClear:
                database.delete(DBHelper.TABLE_CONTACTS, null, null);
                break;

            case R.id.btnUpdate:
                if (id.equalsIgnoreCase("")) {
                    break;
                }
                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_EMAIL, email);

                int updCount = database.update (DBHelper.TABLE_CONTACTS, contentValues, DBHelper.KEY_ID + " = ?", new String[] {id});
                break;
            case R.id.btnDelete:
                if (id.equalsIgnoreCase("")){
                    break;
                }
                int delCount = database.delete(DBHelper.TABLE_CONTACTS, DBHelper.KEY_ID + " = " + id, null);
                break;
        }

        dbHelper.close();

    }

    public class DBHelper extends SQLiteOpenHelper {

        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "contactDB";
        public static final String TABLE_CONTACTS = "contacts";
        public static final String KEY_ID = "_id";
        public static final String KEY_NAME = "name";
        public static final String KEY_EMAIL = "email";

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(
                    "create table "
                            + TABLE_CONTACTS + "("
                            + KEY_ID + " integer primary key, "
                            + KEY_NAME + " text, "
                            + KEY_EMAIL + " text" + ")");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + TABLE_CONTACTS);
            onCreate(db);

        }
    }
}
