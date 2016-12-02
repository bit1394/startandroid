package ru.bitreslab.p0961_servicebroadcastreceiver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    final String LT = "myLogs";
    ExecutorService es;

    public void onCreate(){
        super.onCreate();
        Log.d(LT, "onCreate");
        es = Executors.newFixedThreadPool(2);
    }

    public void onDestroy(){
        super.onDestroy();
        Log.d(LT, "onDestroy");
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        Log.d(LT, "onStartCommand");

        int time = intent.getIntExtra(Main.PARAM_TIME, 1);
        int task = intent.getIntExtra(Main.PARAM_TASK, 0);

        MyRun mr = new MyRun(startId, time, task);
        es.execute(mr);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class MyRun implements  Runnable{
        int time, startId, task;

        public MyRun(int startId, int time, int task){
            this.time = time;
            this.startId = startId;
            this.task = task;
            Log.d(LT, "MyRun#" + startId + " create");
        }

        @Override
        public void run() {
            Intent intent = new Intent(Main.BROADCAST_ACTION);
            Log.d(LT, "MyRun#" + startId + " start, time = " + time);

            try{
                intent.putExtra(Main.PARAM_TASK, task);
                intent.putExtra(Main.PARAM_STATUS, Main.STATUS_START);
                sendBroadcast(intent);

                TimeUnit.SECONDS.sleep(time);

                intent.putExtra(Main.PARAM_STATUS, Main.STATUS_FINISH);
                intent.putExtra(Main.PARAM_RESULT, time * 100);
                sendBroadcast(intent);
            } catch (InterruptedException e){
                e.printStackTrace();
            }

            stop();
        }

        void stop(){
            Log.d(LT, "MyRun#" + startId + " end, stopSelfResult(" + startId + ") = " + stopSelfResult(startId));
        }
    }
}
