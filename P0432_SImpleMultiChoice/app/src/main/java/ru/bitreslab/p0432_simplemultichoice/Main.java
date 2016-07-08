package ru.bitreslab.p0432_simplemultichoice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Main extends AppCompatActivity implements View.OnClickListener{
    final String LT = "myLogs";
    ListView lvMain;
    String[] names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        lvMain = (ListView) findViewById(R.id.lvMain);
        lvMain.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter<CharSequence> ad = ArrayAdapter.createFromResource(this, R.array.names, android.R.layout.simple_list_item_multiple_choice);
        lvMain.setAdapter(ad);

        Button btnChecked = (Button) findViewById(R.id.btnChecked);
        btnChecked.setOnClickListener(this);

        names = getResources().getStringArray(R.array.names);
    }

    @Override
    public void onClick(View v) {
        Log.d(LT, "Checked: ");
        SparseBooleanArray sbArray = lvMain.getCheckedItemPositions();

        for (int i = 0; i < sbArray.size(); i++){
            int key = sbArray.keyAt(i);
            if (sbArray.get(key))
                Log.d(LT, names[key]);
        }

    }
}