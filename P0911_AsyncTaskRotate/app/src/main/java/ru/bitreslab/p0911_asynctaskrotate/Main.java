package ru.bitreslab.p0911_asynctaskrotate;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

        Log.d(LT, "create Main: " + this.hashCode());
        tvInfo = (TextView) findViewById(R.id.tvInfo);

        mt = (MyTask) getLastCustomNonConfigurationInstance();
        if (mt == null){
            mt = new MyTask();
            mt.execute();
        }

        mt.link(this);
        Log.d(LT, "create MyTask: " + mt.hashCode());

    }

    public Object onRetainCustomNonConfigurationInstance(){
        mt.unLink();
        return mt;
    }

    static class MyTask extends AsyncTask<String, Integer, Void>{
        Main activity;

        void link(Main act){
            activity = act;
        }

        void unLink(){
            activity = null;
        }

        @Override
        protected Void doInBackground(String... params) {
            try{
                for (int i = 1; i <= 10; i++){
                    TimeUnit.SECONDS.sleep(1);
                    publishProgress(i);
                    Log.d(activity.LT, "i = " + i + ", MyTask: " + this.hashCode() + ", Main: " + activity.hashCode());
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values){
            super.onProgressUpdate(values);
            activity.tvInfo.setText("i = " + values[0]);
        }
    }
}
