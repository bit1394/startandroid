package ru.bitreslab.p0511_simpleadapterdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main extends AppCompatActivity {
    private static final int CM_DELETE_ID = 1;
    final String ATTR_NAME_TEXT = "text";
    final String ATTR_NAME_IMAGE = "image";

    ListView lvSimlpe;
    SimpleAdapter sAdapter;
    ArrayList<Map<String, Object>> data;
    Map<String, Object> m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        data  = new ArrayList<Map<String, Object>>();
        for (int i = 1; i < 5; i++){
            m = new HashMap<String, Object>();
            m.put(ATTR_NAME_TEXT, "Text " + i);
            m.put(ATTR_NAME_IMAGE, R.mipmap.ic_launcher);
            data.add(m);
        }

        String[] from = {ATTR_NAME_TEXT, ATTR_NAME_IMAGE};
        int[] to = {R.id.tvText, R.id.ivImg};

        sAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);
        lvSimlpe = (ListView) findViewById(R.id.lvSimple);
        lvSimlpe.setAdapter(sAdapter);
        registerForContextMenu(lvSimlpe);
    }

    public void onButtonClick(View v){
        m = new HashMap<String, Object>();
        m.put(ATTR_NAME_TEXT, "Text " + (data.size() + 1));
        m.put(ATTR_NAME_IMAGE, R.mipmap.ic_launcher);
        data.add(m);

        sAdapter.notifyDataSetChanged();
    }

    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, "Удалить запись");
    }
    public boolean onContextItemSelected (MenuItem item){
        if (item.getItemId() == CM_DELETE_ID){
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            data.remove(acmi.position);
            sAdapter.notifyDataSetChanged();
            return true;
        }
        return super.onContextItemSelected(item);
    }

}
