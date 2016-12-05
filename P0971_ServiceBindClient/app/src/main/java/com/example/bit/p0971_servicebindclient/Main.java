package com.example.bit.p0971_servicebindclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Main extends AppCompatActivity {
    final String LT = "myLogs";
    boolean bound = false;
    ServiceConnection sConn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        intent = new Intent("p0972ServiceServer");
        sConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d(LT, "onServiceConnected");
                bound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d(LT, "onServiceDisconnected");
                bound = false;
            }
        };
    }

    public void onClickStart(View v){
        startService(intent);
    }

    public void  onClickStop(View v){
        stopService(intent);
    }

    public void onClickBind(View v){
        bindService(intent, sConn, 0);
    }

    public void onClickUnBind(View v){
        if(!bound) return;
        unbindService(sConn);
        bound = false;
    }

    protected void onDestroy(){
        super.onDestroy();
        onClickUnBind(null);
    }
}
