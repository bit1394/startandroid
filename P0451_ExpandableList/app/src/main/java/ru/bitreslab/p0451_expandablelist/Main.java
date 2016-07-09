package ru.bitreslab.p0451_expandablelist;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main extends AppCompatActivity {
    String[] brandNames = new String[] {"HTC", "Samsung", "LG"};
    String[] phonesHTC = new String[] {"Sensation", "Desire", "Wildfire", "Hero"};
    String[] phonesSam = new String[] {"Galaxy S 2", "Galaxy Nexus", "Wave"};
    String[] phonesLG = new String[] {"Optimus", "Optimus Link", "Optimus Black"};

    ArrayList<Map<String, String>> brandList;
    ArrayList<Map<String, String>> phones;
    ArrayList<ArrayList<Map<String, String>>> phonesList;
    Map<String, String> name;
    ExpandableListView elvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        brandList = new ArrayList<Map<String, String>>();
        for (String brand : brandNames){
            name = new HashMap<String, String>();
            name.put("brandName", brand);
            brandList.add(name);
        }
        String brandFrom[] = new String[]{"brandName"};
        int brandTo[] = new int[] {android.R.id.text1};

        phonesList = new ArrayList<ArrayList<Map<String,String>>>();

        phones = new ArrayList<Map<String, String>>();
        for (String phone : phonesHTC){
            name = new HashMap<String, String>();
            name.put("modelName", phone);
            phones.add(name);
        }
        phonesList.add(phones);

        phones = new ArrayList<Map<String, String>>();
        for (String phone : phonesSam){
            name = new HashMap<String, String>();
            name.put("modelName", phone);
            phones.add(name);
        }
        phonesList.add(phones);

        phones = new ArrayList<Map<String, String>>();
        for (String phone : phonesLG){
            name = new HashMap<String, String>();
            name.put("modelName", phone);
            phones.add(name);
        }
        phonesList.add(phones);

        String phonesFrom[] = new String[]{"modelName"};
        int phonesTo[] = new int[]{android.R.id.text1};

        SimpleExpandableListAdapter ad = new SimpleExpandableListAdapter(
                this,
                brandList, android.R.layout.simple_expandable_list_item_1, brandFrom, brandTo,
                phonesList, android.R.layout.simple_list_item_1, phonesFrom, phonesTo);

        elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        elvMain.setAdapter(ad);
    }
}
