package com.example.bit.p0801_handler;

import android.app.Activity;
import android.icu.util.TimeUnit;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main extends AppCompatActivity{
    final String LT = "myLogs";

    Handler h;
    TextView tvInfo;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);
        btnStart = (Button) findViewById(R.id.btnStart);

        h = new Handler(){
            public void handleMessage(android.os.Message msg){
                tvInfo.setText("Закачано файлов: " + msg.what);
                if (msg.what == 10) btnStart.setEnabled(true);
            }
        };
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnStart:
                btnStart.setEnabled(false);
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 1; i <= 10; i++){
                            downloadFile();
                            h.sendEmptyMessage(i);
                            Log.d(LT, "i = " + i);
                        }
                    }
                });
                t.start();

                break;
            case R.id.btnTest:
                Log.d(LT, "test");
                break;
            default:
                break;
        }
    }

    void downloadFile(){
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
