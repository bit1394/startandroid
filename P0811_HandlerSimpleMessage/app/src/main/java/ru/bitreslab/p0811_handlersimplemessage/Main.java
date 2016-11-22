package ru.bitreslab.p0811_handlersimplemessage;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class Main extends AppCompatActivity {
    final String LT = "myLogs";

    final int NONE = 0;
    final int CONNECTING = 1;
    final int CONNECTED = 2;

    Handler h;
    TextView tvStatus;
    ProgressBar pbConnect;
    Button btnConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tvStatus = (TextView) findViewById(R.id.tvStatus);
        pbConnect =(ProgressBar) findViewById(R.id.pbConnect);
        btnConnect = (Button) findViewById(R.id.btnConnect);

        h = new Handler(){
            public void handleMessage(android.os.Message msg){
                switch (msg.what){
                    case NONE:
                        btnConnect.setEnabled(true);
                        tvStatus.setText("Not connected");
                        break;
                    case CONNECTING:
                        btnConnect.setEnabled(false);
                        pbConnect.setVisibility(View.VISIBLE);
                        tvStatus.setText("Connecting");
                        break;
                    case CONNECTED:
                        pbConnect.setVisibility(View.GONE);
                        tvStatus.setText("Connected");
                        break;
                }
            }
        };
        h.sendEmptyMessage(NONE);
    }

    public void onClick(View v){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    h.sendEmptyMessage(CONNECTING);
                    TimeUnit.SECONDS.sleep(2);
                    h.sendEmptyMessage(CONNECTED);
                    TimeUnit.SECONDS.sleep(3);
                    h.sendEmptyMessage(NONE);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}
