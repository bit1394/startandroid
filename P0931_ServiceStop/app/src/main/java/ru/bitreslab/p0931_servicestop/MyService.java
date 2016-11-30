package ru.bitreslab.p0931_servicestop;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Bit on 30.11.2016.
 */

public class MyService extends Service {

    final String LT = "myLogs";

    ExecutorService es;
    Object someRes;

    public void onCreate(){
        super.onCreate();
        Log.d(LT, "MyService onCreate");
        es = Executors.newFixedThreadPool(3);
        someRes = new Object();
    }

    public void onDestroy(){
        super.onDestroy();
        Log.d(LT, "MyService onDestroy");
        someRes = null;
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        Log.d(LT, "MyService onStartCommand");
        int time = intent.getIntExtra("time", 1);
        MyRun mr = new MyRun(time, startId);
        es.execute(mr);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class MyRun implements Runnable{
        int time;
        int startId;

        public MyRun (int time, int startId){
            this.time = time;
            this.startId = startId;
            Log.d(LT, "MyRun#" + startId + " create");
        }

        @Override
        public void run() {
            Log.d(LT, "MyRun#" + startId + " start, time = " + time);
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException e){
                e.printStackTrace();
            }

            try{
                Log.d(LT, "MyRun#" + startId + "someRes = " + someRes.getClass());
            } catch (NullPointerException e){
                Log.d(LT, "MyRun#" + startId + " error, null pointer");
            }
            stop();
        }

        void stop(){
            Log.d(LT, "MyRun#" + startId + " end, stopSelf(" + startId + ")" + stopSelfResult(startId));
        }
    }
}
