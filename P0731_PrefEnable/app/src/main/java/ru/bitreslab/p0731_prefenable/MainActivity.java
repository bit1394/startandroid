package ru.bitreslab.p0731_prefenable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuItem mi = menu.add(0, 1, 0, "Prefs");
        mi.setIntent(new Intent(this, PrefAct.class));
        return super.onCreateOptionsMenu(menu);
    }
}
