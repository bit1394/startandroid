package com.example.bit.p0972_servicebindserver;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    final String LT = "myLogs";
    public MyService() {
    }

    public void onCreate(){
        super.onCreate();
        Log.d(LT, "onCreate");
    }

    public void onRebind(Intent intent){
        super.onRebind(intent);
        Log.d(LT, "oNRebind");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LT, "onBind");
        return new Binder();
      }

    public boolean onUnbind(Intent intent){
        Log.d(LT, "onUnbind");
        return true;
    }

    public void onDestroy(){
        Log.d(LT, "onDestroy");
        super.onDestroy();
    }
}
