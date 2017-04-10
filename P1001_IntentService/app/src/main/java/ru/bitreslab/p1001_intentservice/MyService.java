package ru.bitreslab.p1001_intentservice;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MyService extends IntentService {
    final String LT = "myLogs";

    public MyService() {
        super("myName");
    }

    public void onCreate(){
        super.onCreate();
        Log.d(LT, "onCreate");
    }

    protected void onHandleIntent(Intent intent){
        int tm = intent.getIntExtra("time", 0);
        String label = intent.getStringExtra("label");
        Log.d(LT, "onHandleIntent start " + label);
        try{
            TimeUnit.SECONDS.sleep(tm);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        Log.d(LT, "onHandleIntent end " + label);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onDestroy(){
        super.onDestroy();
        Log.d(LT, "onDestroy");
    }
}
