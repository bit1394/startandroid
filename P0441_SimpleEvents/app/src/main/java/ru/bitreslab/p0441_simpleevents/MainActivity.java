package ru.bitreslab.p0441_simpleevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    final String LT = "myLogs";
    ListView lvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMain = (ListView) findViewById(R.id.lvMain);

        ArrayAdapter<CharSequence> ad = ArrayAdapter.createFromResource(this, R.array.names, android.R.layout.simple_list_item_1);
        lvMain.setAdapter(ad);

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(LT, "itemClick: position = " + position + ", id = " + id);
            }
        });

        lvMain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(LT, "itemSelected: position = " + position + ", id = " + id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(LT, "itemSelected: nothing");
            }
        });

        lvMain.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.d(LT, "scrollState = " + scrollState);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d(LT, "scroll: fiirstVisibleItem = " + firstVisibleItem + ", " +
                        "visibleItemCount = " + visibleItemCount + ", " +
                        "totalItemCount = " + totalItemCount);
            }
        });
    }
}
