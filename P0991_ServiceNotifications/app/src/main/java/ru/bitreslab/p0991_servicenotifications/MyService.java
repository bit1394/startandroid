package ru.bitreslab.p0991_servicenotifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    NotificationManager nm;

    public void onCreate(){
        super.onCreate();
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        try{
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        sendNotif();
        return super.onStartCommand(intent, flags, startId);
    }

    void sendNotif(){
        Notification.Builder notif = new Notification.Builder(this);

        notif.setSmallIcon(android.R.drawable.ic_dialog_info);
        notif.setTicker("Text in status bar");
        notif.setWhen(System.currentTimeMillis());

        Intent intent = new Intent(this, Main.class);
        intent.putExtra(Main.FILE_NAME, "SomeFile");
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

        notif.setContentTitle("Notification's title");
        notif.setContentText("Notification's text");
        notif.setContentIntent(pIntent);

        notif.setAutoCancel(true);

        Notification notification = notif.getNotification();

        nm.notify(1, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
