package ru.bitreslab.p102_serviceiddqd;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MyS extends Service {
    NotificationManager nm;
    final String LT = "myLogs";
    Timer timer;
    TimerTask tTask;
    long interval = 1000;

    public void onCreate(){
        super.onCreate();
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        timer = new Timer();
        shedule();
        return super.onStartCommand(intent, flags, startId);
    }

    void shedule(){
        if(tTask != null) tTask.cancel();
        tTask = new TimerTask() {
            @Override
            public void run() {
                sendNot();
                Log.d(LT, "ping!");
            }
        };
            timer.schedule(tTask, 1000, interval);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    void sendNot(){
        Notification.Builder notif = new Notification.Builder(this);

        notif.setSmallIcon(android.R.drawable.ic_dialog_info);
        notif.setTicker("Status bar");
        notif.setWhen(System.currentTimeMillis());

        notif.setContentTitle("Title");
        notif.setContentText("Bla-Bla-Bla");

        notif.setAutoCancel(true);

        Notification notification = notif.getNotification();

        startForeground(1, notification);
    }

    public void onDestroy(){
        super.onDestroy();
        tTask = null;
        Log.d(LT, "onDestroy");
    }
}
