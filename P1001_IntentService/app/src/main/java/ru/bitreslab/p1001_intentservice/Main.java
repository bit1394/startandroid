package ru.bitreslab.p1001_intentservice;

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
        startService(new Intent(this, MyService.class).putExtra("time", 3).putExtra("label", "Call 1"));
        startService(new Intent(this, MyService.class).putExtra("time", 1).putExtra("label", "Call 2"));
        startService(new Intent(this, MyService.class).putExtra("time", 4).putExtra("label", "Call 3"));
    }
}
