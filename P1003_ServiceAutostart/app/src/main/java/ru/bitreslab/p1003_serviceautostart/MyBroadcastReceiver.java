package ru.bitreslab.p1003_serviceautostart;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {
    final String LT = "myLogs";

    public MyBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(LT, "onReceive " + intent.getAction());
        context.stopService(new Intent(context, MyService.class));
    }
}
