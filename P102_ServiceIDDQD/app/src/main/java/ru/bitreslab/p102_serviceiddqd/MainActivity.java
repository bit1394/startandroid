package ru.bitreslab.p102_serviceiddqd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    final String LT = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    void onClickStart(View v){
        startService(new Intent(this, MyS.class));
    }

    void onClickStop(View v){
        stopService(new Intent(this, MyS.class));
    }

}
