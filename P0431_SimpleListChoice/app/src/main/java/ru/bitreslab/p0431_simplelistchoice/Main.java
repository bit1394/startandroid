package ru.bitreslab.p0431_simplelistchoice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
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
        lvMain.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<CharSequence> ad = ArrayAdapter.createFromResource(this, R.array.names, android.R.layout.simple_list_item_single_choice);
        lvMain.setAdapter(ad);

        Button btnChecked = (Button) findViewById(R.id.btnChecked);
        btnChecked.setOnClickListener(this);

        names = getResources().getStringArray(R.array.names);
    }

    @Override
    public void onClick(View v) {
        Log.d(LT, "Checked: " + names[lvMain.getCheckedItemPosition()]);

    }
}
