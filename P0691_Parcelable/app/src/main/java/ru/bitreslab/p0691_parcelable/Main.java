package ru.bitreslab.p0691_parcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Main extends AppCompatActivity {
    final String LT = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onClick(View v){
        MyObj mO = new MyObj("text", 1);
        Intent intent = new Intent(this, Second.class);
        intent.putExtra(MyObj.class.getCanonicalName(), mO);
        Log.d(LT, "startActivity");
        startActivity(intent);
    }
}
