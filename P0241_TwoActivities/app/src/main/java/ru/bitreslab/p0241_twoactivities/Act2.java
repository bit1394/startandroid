package ru.bitreslab.p0241_twoactivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class Act2 extends AppCompatActivity {

    final String TAG = "lifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act2);
        Log.d(TAG, "Act2 created");

        
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Act2 started");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Act2 restarted");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Act2 paused");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Act2 resumed");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Act2 stoped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Act2 destroyed");
    }
}
