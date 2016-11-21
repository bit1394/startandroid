package ru.bitreslab.p0771_tabintent;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.TabActivity;
import android.widget.TabHost;

public class Main extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TabHost tabHost = getTabHost();

        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setIndicator("Вкладка 1");
        tabSpec.setContent(new Intent(this, One.class));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setIndicator("Вкладка 2");
        tabSpec.setContent(new Intent(this, Two.class));
        tabHost.addTab(tabSpec);

    }


}
