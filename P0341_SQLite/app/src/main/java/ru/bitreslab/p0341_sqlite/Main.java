package ru.bitreslab.p0341_sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main extends AppCompatActivity implements View.OnClickListener{

    EditText etName, etEmail;
    Button btnAdd, btnRead, btnClear;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnClear = (Button) findViewById(R.id.btnClear);

        btnAdd.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnClear.setOnClickListener(this);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();

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
        }
        dbHelper.close();
    }
}
