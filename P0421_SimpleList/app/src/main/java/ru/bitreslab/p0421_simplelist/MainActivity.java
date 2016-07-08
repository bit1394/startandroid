package ru.bitreslab.p0421_simplelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String[] names = {"Иван", "Марья", "Пётр", "Антон", "Даша", "Борис", "Костя", "Игорт", "Анна", "Денис", "Андрей"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvMain = (ListView) findViewById(R.id.lvMain);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item, names);
        lvMain.setAdapter(adapter);
    }
}
