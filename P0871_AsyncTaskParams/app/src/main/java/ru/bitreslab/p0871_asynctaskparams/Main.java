package ru.bitreslab.p0871_asynctaskparams;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class Main extends AppCompatActivity {
    final String LT = "myLogs";
    MyTask mt;
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);
    }

    public void onClick(View v){
        mt = new MyTask();
        mt.execute("file1", "file2", "file3", "file4");
        Log.d(LT, "onClick");
    }

    class MyTask extends AsyncTask<String, Integer, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvInfo.setText("Begin");
            Log.d(LT, "onPreExecute");
        }

        @Override
        protected Void doInBackground(String... params) {
            try{
                int cnt = 0;
                for(String url : params){
                    downloadFile(url);
                    publishProgress(++cnt);
                }
                TimeUnit.SECONDS.sleep(1);
                Log.d(LT, "doInBackground");
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }

        protected void onProgressUpdate(Integer... vals){
            super.onProgressUpdate(vals);
            tvInfo.setText("Download " + vals[0] + " files");
            Log.d(LT, "onProgressUpdate");
        }

        protected void onPostExecute(Void res){
            super.onPostExecute(res);
            tvInfo.setText("End");
            Log.d(LT, "onPostExecute");
        }

        private void downloadFile(String url) throws InterruptedException{
            TimeUnit.SECONDS.sleep(2);
            Log.d(LT, "downloadFile");
        }
    }
}
