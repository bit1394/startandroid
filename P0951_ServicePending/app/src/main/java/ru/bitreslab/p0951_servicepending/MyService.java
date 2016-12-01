package ru.bitreslab.p0951_servicepending;

import android.app.PendingIntent;
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
        PendingIntent pi = intent.getParcelableExtra(Main.PARAM_PINTENT);

        MyRun mr = new MyRun(time, startId, pi);
        es.execute(mr);

        return super.onStartCommand(intent, flags, startId);
    }

    class MyRun implements Runnable{
        int time;
        int startId;
        PendingIntent pi;

        public MyRun (int time, int startId, PendingIntent pi){
            this.time = time;
            this.startId = startId;
            this.pi = pi;
            Log.d(LT, "MyRun#" + startId + " create");
        }

        @Override
        public void run() {
            Log.d(LT, "MyRun#" + startId + " start, time = " + time);
            try{
                pi.send(Main.STATUS_START);
                TimeUnit.SECONDS.sleep(time);
                Intent intent = new Intent().putExtra(Main.PARAM_RESULT, time * 100);
                pi.send(MyService.this, Main.STATUS_FINISH, intent);
            } catch (InterruptedException e){
                e.printStackTrace();
            } catch (PendingIntent.CanceledException e){
                e.printStackTrace();
            }
            stop();
        }

        void stop(){
            Log.d(LT, "MyRun#" + startId + " end, stopSelfResult(" + startId + ")= " + stopSelfResult(startId));
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
