package ru.bitreslab.p0941_serviceclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onClick(View v){
        startService(new Intent("ru.bitreslab.p0942_serviceserver").putExtra("name", "value"));
    }
}
