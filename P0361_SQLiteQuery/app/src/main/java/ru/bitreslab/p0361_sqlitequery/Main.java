package ru.bitreslab.p0361_sqlitequery;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Main extends AppCompatActivity implements View.OnClickListener {
    public static final String LT = "myLogs";

    Button btnAll, btnFunc, btnPeople, btnGroup, btnHaving, btnSort;
    EditText etFunc, etPeople, etRegiogPeople;
    RadioGroup rgSort;
    RadioButton rName, rPeople, rRegion;

    String name [] = {"Китай", "США", "Бразилия", "Россия", "Япония", "Германия", "Египет", "Италия", "Франция", "Канада"};
    int people [] = {1400, 311, 195, 142, 128, 82, 80, 66, 60, 35};
    String region [] = {"Азия", "Америка", "Америка", "Европа", "Азия", "Европа", "Африка", "Европа", "Европа", "Америка"};
    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnAll = (Button) findViewById(R.id.btnAll);
        btnFunc = (Button) findViewById(R.id.btnFunc);
        btnPeople = (Button) findViewById(R.id.btnPeople);
        btnGroup = (Button) findViewById(R.id.btnGroup);
        btnHaving = (Button) findViewById(R.id.btnHaving);
        btnSort = (Button) findViewById(R.id.btnSort);

        etFunc = (EditText) findViewById(R.id.etFunc);
        etPeople = (EditText) findViewById(R.id.etPeople);
        etRegiogPeople = (EditText) findViewById(R.id.etRegionPeople);

        rgSort = (RadioGroup) findViewById(R.id.rgSort);

        rName = (RadioButton) findViewById(R.id.rName);
        rPeople = (RadioButton) findViewById(R.id.rPeople);
        rRegion = (RadioButton) findViewById(R.id.rRegion);

        btnAll.setOnClickListener(this);
        btnFunc.setOnClickListener(this);
        btnPeople.setOnClickListener(this);
        btnGroup.setOnClickListener(this);
        btnHaving.setOnClickListener(this);
        btnSort.setOnClickListener(this);

        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query(DBHelper.TABLE_COUNTRY, null, null, null, null, null, null);
        if (cursor.getCount() == 0){
            ContentValues contentValues = new ContentValues();

            for (int i = 0; i < 10; i++){
                contentValues.put(DBHelper.KEY_NAME, name [i]);
                contentValues.put(DBHelper.KEY_PEOPLE, people [i]);
                contentValues.put(DBHelper.KEY_REGION, region [i]);
                db.insert(DBHelper.TABLE_COUNTRY, null, contentValues);
            }
        }
        cursor.close();
        dbHelper.close();
        onClick(btnAll);
    }

    @Override
    public void onClick(View v) {
        db = dbHelper.getWritableDatabase();

        String sFunc = etFunc.getText().toString();
        String sPeople = etPeople.getText().toString();
        String sRegionPeople = etRegiogPeople.getText().toString();

        String [] columns = null;
        String selection = null;
        String [] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;

        Cursor cursor = null;

        switch (v.getId()){
            case R.id.btnAll:
                cursor = db.query(DBHelper.TABLE_COUNTRY, null, null, null, null, null, null);
                break;
            case R.id.btnFunc:
                columns = new String[] {sFunc};
                cursor = db.query(DBHelper.TABLE_COUNTRY, columns, null, null, null, null, null);
                break;
            case R.id.btnPeople:
                selection = "people > ?";
                selectionArgs = new String[] {sPeople};
                cursor = db.query(DBHelper.TABLE_COUNTRY, null, selection, selectionArgs, null, null, null);
                break;
            case R.id.btnGroup:
                columns = new String[] {DBHelper.KEY_REGION, "sum(" + DBHelper.KEY_PEOPLE + ") as " + DBHelper.KEY_PEOPLE};
                groupBy = DBHelper.KEY_REGION;
                cursor = db.query(DBHelper.TABLE_COUNTRY, columns, null, null, groupBy, null, null);
                break;
            case R.id.btnHaving:
                columns = new String[] {DBHelper.KEY_REGION, "sum(" + DBHelper.KEY_PEOPLE + ") as " + DBHelper.KEY_PEOPLE};
                groupBy = DBHelper.KEY_REGION;
                having = "sum(" + DBHelper.KEY_PEOPLE + ") > " + sRegionPeople;
                cursor = db.query(DBHelper.TABLE_COUNTRY, columns, null, null, groupBy, having, null);
                break;
            case R.id.btnSort:
                switch (rgSort.getCheckedRadioButtonId()){
                    case R.id.rName:
                        orderBy = DBHelper.KEY_NAME;
                        break;
                    case R.id.rPeople:
                        orderBy = DBHelper.KEY_PEOPLE;
                        break;
                    case R.id.rRegion:
                        orderBy = DBHelper.KEY_REGION;
                        break;
                }
                cursor = db.query(DBHelper.TABLE_COUNTRY, null, null, null, null, null, orderBy);
                break;
        }
        if (cursor != null){
            if (cursor.moveToFirst()){
                String str;
                do {
                    str = "";
                    for (String cn : cursor.getColumnNames()){
                        str = str.concat(cn + " = " + cursor.getString(cursor.getColumnIndex(cn)) + ";");
                    }
                    Log.d(LT, str);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } else {
            Log.d(LT, "Cursor is null");
        }
        dbHelper.close();
    }
}
