package ru.bitreslab.p0691_parcelable;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class Second extends AppCompatActivity {
    final String LT = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        Log.d(LT, "getParcelableExtra");
        MyObj mO = (MyObj)getIntent().getParcelableExtra(MyObj.class.getCanonicalName());
        Log.d(LT, "mO: " + mO.s + ", " + mO.i);
    }

}
