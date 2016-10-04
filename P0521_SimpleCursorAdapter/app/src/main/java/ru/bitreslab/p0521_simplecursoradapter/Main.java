package ru.bitreslab.p0521_simplecursoradapter;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Main extends AppCompatActivity {
    private static final int CM_DELETE_ID = 1;
    ListView lvSimple;
    DB db;
    SimpleCursorAdapter scAdapter;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        db = new DB(this);
        db.open();

        cursor = db.getAllData();
        startManagingCursor(cursor);

        String[] from = new String[] {DB.COLUMN_IMG, DB.COLUMN_TXT};
        int[] to = new int[]{R.id.ivImg, R.id.tvText};

        scAdapter = new SimpleCursorAdapter(this, R.layout.item, cursor, from, to);
        lvSimple = (ListView) findViewById(R.id.lvSimple);
        lvSimple.setAdapter(scAdapter);

        registerForContextMenu(lvSimple);
    }

    public void onButtonClick(View v){
        db.addRec("Text " + (cursor.getCount() + 1), R.mipmap.ic_launcher);
        cursor.requery();
    }

    public  void  onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, R.string.delete_record);
    }

    public boolean onContextItemSelected(MenuItem item){
        if (item.getItemId() == CM_DELETE_ID){
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
            db.delRec(acmi.id);
            cursor.requery();
            return true;
        }
        return super.onContextItemSelected(item);
    }
    protected void onDestroy(){
        super.onDestroy();
        db.close();
    }
}
