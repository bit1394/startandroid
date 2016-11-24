package ru.bitreslab.p0831_handlermessagemanager;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Main extends AppCompatActivity {
    final String LT = "myLogs";
    Handler h;

    Handler.Callback hc = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Log.d(LT, "what = " + msg.what);
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        h = new Handler(hc);
        sendMessages();
    }

    void sendMessages(){
        Log.d(LT, "send messages");
        h.sendEmptyMessageDelayed(1, 1000);
        h.sendEmptyMessageDelayed(2, 2000);
        h.sendEmptyMessageDelayed(3, 3000);
        h.removeMessages(3);
    }
}
