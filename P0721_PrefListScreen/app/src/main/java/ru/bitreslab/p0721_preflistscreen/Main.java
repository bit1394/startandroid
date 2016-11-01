package ru.bitreslab.p0721_preflistscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Main extends AppCompatActivity {
    TextView tvInfo;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
    }

    protected void onResume(){
        String listValue = sp.getString("list", "not set");
        tvInfo.setText("Value of list - " + listValue);
        super.onResume();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuItem mi = menu.add(0, 1, 0, "Preferences");
        mi.setIntent(new Intent(this, PrefAct.class));
        return super.onCreateOptionsMenu(menu);
    }
}
