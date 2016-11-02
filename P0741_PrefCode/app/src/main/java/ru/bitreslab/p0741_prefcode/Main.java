package ru.bitreslab.p0741_prefcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public boolean onCreateOptionsMenu (Menu menu){
        MenuItem mi = menu.add(0, 1, 0, "Prefs");
        mi.setIntent(new Intent(this, PrefAct.class));
        return super.onCreateOptionsMenu(menu);
    }
}
