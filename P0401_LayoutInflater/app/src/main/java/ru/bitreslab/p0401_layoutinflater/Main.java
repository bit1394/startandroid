package ru.bitreslab.p0401_layoutinflater;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Main extends AppCompatActivity {
    final String LT = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        LayoutInflater ltInflater = getLayoutInflater();
        View view = ltInflater.inflate(R.layout.text, null, false);
        ViewGroup.LayoutParams lp = view.getLayoutParams();

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
        linLayout.addView(view);
        Log.d(LT, "Class of view: " + view.getClass().toString());
        Log.d(LT, "LayoutParams of view is null: " + (lp == null));
        Log.d(LT, "Text of view: " + ((TextView) view).getText());

//        RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.relLayout);
//        View view2 = ltInflater.inflate(R.layout.text, relLayout, false);
//        relLayout.addView(view2);
//        Log.d(LT, "Class of view2: " + view2.getClass().toString());
//        Log.d(LT, "Text of view2: " + ((TextView) view2).getText());
//
        RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.relLayout);
        View view3 = ltInflater.inflate(R.layout.text, relLayout, true);





    }
}
