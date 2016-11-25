package ru.bitreslab.p0881_asynctaskresult;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
            case R.id.btnGet:
                showResult();
                break;
            default:
                break;
        }
    }

   /* private void showResult(){
        if (mt == null) return;
        int result = -1;
        try{
            Log.d(LT, "try to get result");
            result = mt.get();
            Log.d(LT, "get returns " + result);
            Toast.makeText(this, "get returns " + result, Toast.LENGTH_LONG).show();
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
    }*/

    private void showResult(){
        if (mt == null) return;
        int result = -1;
        try{
            Log.d(LT, "try to get result");
            result = mt.get(1, TimeUnit.SECONDS);
            Log.d(LT, "get returns " + result);
            Toast.makeText(this, "get returns " + result, Toast.LENGTH_LONG).show();
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        } catch (TimeoutException e){
            e.printStackTrace();
        }
    }

    class MyTask extends AsyncTask<Void, Void, Integer>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            tvInfo.setText("Begin");
            Log.d(LT, "Begin");
        }

        @Override
        protected Integer doInBackground(Void... params) {
            try{
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            return 100500;
        }

        @Override
        protected void onPostExecute(Integer result){
            super.onPostExecute(result);
            tvInfo.setText("End. Result = " + result);
            Log.d(LT, "End. Result = " + result);
        }
    }
}
