package ru.bitreslab.p1011_contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by Bit on 22.12.2016.
 */

public class MyCP extends ContentProvider {
    final String LT = "myLogs";

    static final String DB_NAME = "mydb";
    static final int DB_VERSION = 1;
    static final String CONTACT_TABLE = "contacts";

    static final String CONTACT_ID = "_id";
    static final String CONTACT_NAME = "name";
    static final String CONTACT_EMAIL = "email";

    static final String DB_CREATE = "create table " + CONTACT_TABLE
            + "("
            + CONTACT_ID + " integer primary key autoincrement, "
            + CONTACT_NAME + " text, "
            + CONTACT_EMAIL + " text"
            + ");";

    //Uri
    static final String AUTHORITY = "bit.ADDR_BOOK"; //authority
    static final String CONTACT_PATH = "contacts";

    //Общий uri
    public static final Uri CONTACT_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + CONTACT_PATH);

    //типы данных
    static final String CONTACT_CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + AUTHORITY + "." + CONTACT_PATH; //набор строк
    static final String CONTACT_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + AUTHORITY + "." + CONTACT_PATH;

    //Uri Matcher
    static final int URI_CONTACTS = 1; //общий Uri
    static final int URI_CONTACTS_ID = 2; //Uri with ID

    //создание UriMatcher
    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, CONTACT_PATH, URI_CONTACTS);
        uriMatcher.addURI(AUTHORITY, CONTACT_PATH + "/#", URI_CONTACTS_ID);
    }

    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        Log.d(LT, "onCreate");
        dbHelper = new DBHelper(getContext());
        return true;
    }

    //чтение
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d(LT, "query, " + uri.toString());
        //проверяем Uri
        switch (uriMatcher.match(uri)){
            case URI_CONTACTS:
                Log.d(LT, "URI_CONTACTS");
                //если сортировка не указана, ставим по имени - так хочется
                if(TextUtils.isEmpty(sortOrder)){
                    sortOrder = CONTACT_NAME + " ASC";
                }
                break;
            case URI_CONTACTS_ID:
                String id = uri.getLastPathSegment();
                Log.d(LT, "URI_CONTACTS_ID" + id);
                //добавим id к условию выборки
                if(TextUtils.isEmpty(selection)){
                    selection = CONTACT_ID + " = " + id;
                } else {
                    selection = selection + " AND " + CONTACT_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(CONTACT_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
        //просим ContentResolver уведомлять этот курсор об изменениях данных в CONTACT_CONTENT_URI
        cursor.setNotificationUri(getContext().getContentResolver(), CONTACT_CONTENT_URI);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        Log.d(LT, "getType, " + uri.toString());
        switch (uriMatcher.match(uri)){
            case URI_CONTACTS:
                return CONTACT_CONTENT_TYPE;
            case URI_CONTACTS_ID:
                return CONTACT_CONTENT_ITEM_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d(LT, "insert, " + uri.toString());
        if (uriMatcher.match(uri) != URI_CONTACTS)
            throw new IllegalArgumentException("Wrong URI: " + uri);
        db = dbHelper.getWritableDatabase();
        long rowID = db.insert(CONTACT_TABLE, null, values);
        Uri resultUri = ContentUris.withAppendedId(CONTACT_CONTENT_URI, rowID);
        //уведомим ContentResolver
        getContext().getContentResolver().notifyChange(resultUri, null);
        return resultUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(LT, "delete, " + uri.toString());
        switch (uriMatcher.match(uri)){
            case URI_CONTACTS:
                Log.d(LT, "URI_CONTACTS");
                break;
            case URI_CONTACTS_ID:
                String id = uri.getLastPathSegment();
                Log.d(LT, "URI_CONTACTS_ID, " + id);
                if(TextUtils.isEmpty(selection)){
                    selection = CONTACT_ID + " = " + id;
                } else {
                    selection = selection + " AND " + CONTACT_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.delete(CONTACT_TABLE, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.d(LT, "update, " + uri.toString());
        switch (uriMatcher.match(uri)){
            case URI_CONTACTS:
                Log.d(LT, "URI_CONTACTS");
                break;
            case URI_CONTACTS_ID:
                String id = uri.getLastPathSegment();
                Log.d(LT, "URI_CONTACTS_ID, " + id);
                if(TextUtils.isEmpty(selection)){
                    selection = CONTACT_ID + " = " + id;
                } else {
                    selection = selection + " AND " + CONTACT_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.update(CONTACT_TABLE, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    private class DBHelper extends SQLiteOpenHelper{
        public DBHelper(Context context){
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
            ContentValues cv = new ContentValues();
            for(int i = 1; i <= 3; i++){
                cv.put(CONTACT_NAME, "name " + i);
                cv.put(CONTACT_EMAIL, "email " + i);
                db.insert(CONTACT_TABLE, null, cv);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
