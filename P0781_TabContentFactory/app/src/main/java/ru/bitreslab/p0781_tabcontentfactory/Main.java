package ru.bitreslab.p0781_tabcontentfactory;

import android.app.TabActivity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class Main extends TabActivity {
    final String TABS_TAG_1 = "Tag 1";
    final String TABS_TAG_2 = "Tag 2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TabHost tabHost = getTabHost();

        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec(TABS_TAG_1);
        tabSpec.setContent(TabFactory);
        tabSpec.setIndicator("Вкладка 1");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec(TABS_TAG_2);
        tabSpec.setContent(TabFactory);
        tabSpec.setIndicator("Вкладка 2");
        tabHost.addTab(tabSpec);
    }

    TabHost.TabContentFactory TabFactory = new TabHost.TabContentFactory() {
        @Override
        public View createTabContent(String tag) {
            if(tag == TABS_TAG_1){
                return getLayoutInflater().inflate(R.layout.tab, null);
            } else if(tag == TABS_TAG_2){
                TextView tv = new TextView(Main.this);
                tv.setText("Это создано вручную");
                return tv;
            }
            return null;
        }
    };
}
