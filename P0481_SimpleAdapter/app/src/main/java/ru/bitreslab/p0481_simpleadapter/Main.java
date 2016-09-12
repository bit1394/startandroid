package ru.bitreslab.p0481_simpleadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main extends AppCompatActivity {

    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_CHECKED = "checked";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    ListView lvSimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

       //массивы даннных
        String[] texts = {"text1", "text2", "text3", "text4", "text5"};
        boolean[] checked = {true, false, true, false, false};
        int img = R.mipmap.ic_launcher;

        //готовим данные для Adapter в нужной форме:
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(texts.length);

        Map<String, Object> m;

        for(int i = 0; i < texts.length; i++){
            m = new HashMap<String, Object>();

            m.put(ATTRIBUTE_NAME_TEXT, texts[i]);
            m.put(ATTRIBUTE_NAME_CHECKED, checked[i]);
            m.put(ATTRIBUTE_NAME_IMAGE,img);
            data.add(m);
        }

        //массив имен атрибутов, откуда берутся данные:
        String[] from = {ATTRIBUTE_NAME_IMAGE, ATTRIBUTE_NAME_CHECKED, ATTRIBUTE_NAME_TEXT};

        //массив ID View-элементов, куда будут пихаться данные:
        int[] to = {R.id.ivImg, R.id.cbChecked, R.id.tvText};

        //создаем Adapter:
        SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);

        //определяем список и присвоим ему Adapter:
        lvSimple = (ListView) findViewById(R.id.lvSimple);
        lvSimple.setAdapter(sAdapter);
    }
}
