package ru.bitreslab.p1003_serviceautostart;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {
   final String LT = "myLogs";

    public void onCreate(){
        super.onCreate();
        Log.d(LT, "onCreate");
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        try {
            for(int i = 0; i < 5; i++) {
                TimeUnit.SECONDS.sleep(1);
                Log.d(LT, "PING! " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
