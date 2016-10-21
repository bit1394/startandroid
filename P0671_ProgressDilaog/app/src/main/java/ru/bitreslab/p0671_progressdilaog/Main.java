package ru.bitreslab.p0671_progressdilaog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main extends AppCompatActivity {
    ProgressDialog pd;
    Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnDefault:
                pd = new ProgressDialog(this);
                pd.setTitle("Default");
                pd.setMessage("No limit");
                pd.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                pd.show();
                break;
            case R.id.btnHoriz:
                pd = new ProgressDialog(this);
                pd.setTitle("Horizontal");
                pd.setMessage("I see end of this");
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd.setMax(2150);
                pd.setIndeterminate(true);
                pd.show();

                h = new Handler(){
                    public void handleMessage(Message msg){
                        pd.setIndeterminate(false);
                        if(pd.getProgress() < pd.getMax()){
                            pd.incrementProgressBy(50);
                            pd.incrementSecondaryProgressBy(61);
                            h.sendEmptyMessageDelayed(0, 100);
                        }
                        else {
                            pd.dismiss();
                        }
                    }
                };
                h.sendEmptyMessageDelayed(0, 2000);
                break;
            default:
                break;
        }

    }
}
