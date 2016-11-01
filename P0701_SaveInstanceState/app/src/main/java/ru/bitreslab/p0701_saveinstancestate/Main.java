package ru.bitreslab.p0701_saveinstancestate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Main extends AppCompatActivity {
    final String LT ="myLogs";
    int cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d(LT, "onCreate");
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.d(LT, "onDestroy");
    }

    protected void onRestart(){
        super.onRestart();
        Log.d(LT, "onRestart");
    }

    protected void onSaveInstanceState(Bundle outSave){
        super.onSaveInstanceState(outSave);
        outSave.putInt("count", cnt);
        Log.d(LT, "onSaveInstanceState");
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        cnt = savedInstanceState.getInt("count");
        Log.d(LT, "onRestoreInstanceState");
    }

    protected void onPause(){
        super.onPause();
        Log.d(LT, "onPause");
    }

    protected void onResume(){
        super.onResume();
        Log.d(LT, "onResume");
    }

    protected void onStart(){
        super.onStart();
        Log.d(LT, "onStart");
    }

    protected void onStop(){
        super.onStop();
        Log.d(LT, "onStop");
    }

    public void onClick(View v){
        Toast.makeText(this, "Count = " + ++cnt, Toast.LENGTH_SHORT).show();
    }
}
