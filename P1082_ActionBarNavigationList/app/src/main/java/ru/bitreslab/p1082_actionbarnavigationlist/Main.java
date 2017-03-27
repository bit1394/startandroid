package ru.bitreslab.p1082_actionbarnavigationlist;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Main extends AppCompatActivity implements ActionBar.OnNavigationListener{

    String[] data = new String[] {"one", "two", "three"};
    final String LT = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //android.app.ActionBar bar = getActionBar();

        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(android.app.ActionBar.NAVIGATION_MODE_LIST);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bar.setListNavigationCallbacks(adapter, this);
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        Log.d(LT, "Selected: position = " + itemPosition + ", id = " + itemId + ", " + data[itemPosition]);
        return false;
    }
}
