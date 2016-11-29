package ru.bitreslab.p0921_servicesimple;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main extends AppCompatActivity {
    final String LT = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onClickStart(View v){
        startService(new Intent(this, MyService.class));
    }

    public void onClickStop(View v){
        stopService(new Intent(this, MyService.class));
    }
}
