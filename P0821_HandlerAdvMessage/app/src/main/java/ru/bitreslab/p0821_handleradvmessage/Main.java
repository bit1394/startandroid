package ru.bitreslab.p0821_handleradvmessage;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main extends AppCompatActivity {
    final int NONE = 0;
    final int CONNECTING = 1;
    final int CONNECTED = 2;
    final int DOWNLOAD_START = 3;
    final int DOWNLOAD_FILE = 4;
    final int DOWNLOAD_END = 5;
    final int DOWNLOAD_NONE = 6;

    Handler h;
    TextView tvStatus;
    Button btnConnect;
    ProgressBar pbDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tvStatus = (TextView) findViewById(R.id.tvStatus);
        btnConnect = (Button) findViewById(R.id.btnConnect);
        pbDownload = (ProgressBar)findViewById(R.id.pbDownload);

        h = new Handler(){
            public void handleMessage(android.os.Message msg){
                switch (msg.what){
                    case NONE:
                        btnConnect.setEnabled(true);
                        tvStatus.setText("Not connected");
                        pbDownload.setVisibility(View.GONE);
                        break;
                    case CONNECTING:
                        btnConnect.setEnabled(false);
                        tvStatus.setText("Connecting...");
                        break;
                    case CONNECTED:
                        tvStatus.setText("Connected");
                        break;
                    case DOWNLOAD_START:
                        tvStatus.setText("Start download " + msg.arg1 + " files");
                        pbDownload.setMax(msg.arg1);
                        pbDownload.setProgress(0);
                        pbDownload.setVisibility(View.VISIBLE);
                        break;
                    case DOWNLOAD_FILE:
                        tvStatus.setText("Downloading. Left " + msg.arg2 + " files");
                        pbDownload.setProgress(msg.arg1);
                        saveFile((byte[]) msg.obj);
                        break;
                    case DOWNLOAD_NONE:
                        tvStatus.setText("No files for download");
                        break;
               }
            }
        };
        h.sendEmptyMessage(NONE);
    }

    public void onClick(View v){
        Thread t = new Thread(new Runnable() {
            Message msg;
            byte[] file;
            Random rand = new Random();
            @Override
            public void run() {
                try{
                    h.sendEmptyMessage(CONNECTING);
                    TimeUnit.SECONDS.sleep(1);

                    h.sendEmptyMessage(CONNECTED);

                    TimeUnit.SECONDS.sleep(1);
                    int filesCount = rand.nextInt(5);
                    if (filesCount == 0){
                        h.sendEmptyMessage(DOWNLOAD_NONE);
                        TimeUnit.SECONDS.sleep(2);
                        h.sendEmptyMessage(NONE);
                        return;
                    }

                    msg = h.obtainMessage(DOWNLOAD_START, filesCount, 0);
                    h.sendMessage(msg);

                    for (int i = 1; i <= filesCount; i++){
                        file = downloadFile();
                        msg = h.obtainMessage(DOWNLOAD_FILE, i, filesCount - i, file);
                        h.sendMessage(msg);
                    }

                    h.sendEmptyMessage(DOWNLOAD_END);
                    TimeUnit.SECONDS.sleep(1);
                    h.sendEmptyMessage(NONE);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    byte[] downloadFile() throws InterruptedException{
        TimeUnit.SECONDS.sleep(2);
        return new byte[1024];
    }

    void saveFile(byte[] file){

    }
}
