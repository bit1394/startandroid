package ru.bitreslab.p0241_twoactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity implements View.OnClickListener{
    Button btn;

    final String TAG = "lifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d(TAG, "Main created");

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Main started");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Main restarted");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Main paused");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Main resumed");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Main stoped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Main destroyed");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Act2.class);
        startActivity(intent);
        
    }
}
