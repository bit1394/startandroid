package ru.bitreslab.p0891_asynctaskcancel;

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
        switch (v.getId()){
            case R.id.btnStart:
                mt = new MyTask();
                mt.execute();
                break;
            case R.id.btnCancel:
                cancelTask();
                break;
            default:
                break;
        }
    }

    private void cancelTask(){
        if(mt == null) return;
        Log.d(LT, "cancel result: " + mt.cancel(false));
    }

    class MyTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            tvInfo.setText("Begin");
            Log.d(LT, "Begin");
        }

        @Override
        protected Void doInBackground(Void... params) {
            try{
                for (int i = 0; i < 5; i++){
                    TimeUnit.SECONDS.sleep(1);
                //    if(isCancelled()) return null;
                    Log.d(LT, "isCancelled: " + isCancelled());
                }
            } catch (InterruptedException e){
                Log.d(LT, "InterruptedException");
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            tvInfo.setText("End");
            Log.d(LT, "End");
        }

        @Override
        protected void onCancelled(){
            super.onCancelled();
            tvInfo.setText("Cancel");
            Log.d(LT, "Cancel");
        }
    }
}
