package ru.bitreslab.p0942_serviceserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.TimeUnit;

/**
 * Created by Bit on 01.12.2016.
 */

public class MyService extends Service {
    final String LT = "myLogs";

    public void onCreate(){
        super.onCreate();
        Log.d(LT, "onCreate");
    }

    public void onDestroy(){
        Log.d(LT, "onDestroy");
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        Log.d(LT, "onStartCommand, name = " + intent.getStringExtra("name"));
        readFlags(flags);
        MyRun mr = new MyRun(startId);
        new Thread(mr).start();
        return START_REDELIVER_INTENT;
    }

       @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    void readFlags(int flags) {
        switch (flags) {
            case 0:
                Log.d(LT, "0__");
                break;
            case START_FLAG_RETRY:
                Log.d(LT, "START_FLAG_RETRY");
                break;
            case START_FLAG_REDELIVERY:
                Log.d(LT, "START_FLAG_REDELIVERY");
                break;
            default:
                break;
        }
    }

    class MyRun implements Runnable{
        int startId;

        public MyRun(int startId){
            this.startId = startId;
            Log.d(LT, "MyRun#" + startId + " create");
        }

        @Override
        public void run() {
            Log.d(LT, "MyRun#" + startId + " start");
            try{
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            stop();
        }

        void stop(){
            Log.d(LT, "MyRun#" + startId + " end, stopSelfResult(" + startId + ") " + stopSelfResult(startId));
        }
    }
}
