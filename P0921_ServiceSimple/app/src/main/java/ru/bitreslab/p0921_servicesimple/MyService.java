package ru.bitreslab.p0921_servicesimple;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.TimeUnit;

/**
 * Created by Bit on 29.11.2016.
 */

public class MyService extends Service {
    final String LT = "myLogs";

    public void onCreate(){
        super.onCreate();
        Log.d(LT, "onCreate");
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        Log.d(LT, "onStartCommand");
        someTask();
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy(){
        super.onDestroy();
        Log.d(LT, "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LT, "onBind");
        return null;
    }

    void someTask(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 5; i++){
                    Log.d(LT, "i = " + i);
                    try{
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                stopSelf();
            }
        }).start();

    }
}
