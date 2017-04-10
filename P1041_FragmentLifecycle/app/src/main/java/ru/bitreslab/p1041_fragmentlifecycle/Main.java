package ru.bitreslab.p1041_fragmentlifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Main extends AppCompatActivity {
    final String LT = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d(LT, "Main onCreate");
    }

    protected void onStart(){
        super.onStart();
        Log.d(LT, "Main onStart");
    }

    protected void onResume(){
        super.onResume();
        Log.d(LT, "Main onResume");
    }

    protected void onPause(){
        super.onPause();
        Log.d(LT, "Main onPause");
    }

    protected void onStop(){
        super.onStop();
        Log.d(LT, "Main onStop");
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.d(LT, "Main onDestroy");
    }
}
