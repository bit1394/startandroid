package ru.bitreslab.p0651_alertdialogcustom;

import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main extends AppCompatActivity {
    final int DIALOG = 1;

    int btn;
    LinearLayout view;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    TextView tvCount;
    ArrayList<TextView> textViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        textViews = new ArrayList<TextView>(10);
    }

    public void onClick(View v){
        btn = v.getId();
        showDialog(DIALOG);
    }

    protected Dialog onCreateDialog(int id){
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Custom dialog");

        view = (LinearLayout)getLayoutInflater().inflate(R.layout.dialog, null);
        adb.setView(view);
        tvCount = (TextView)view.findViewById(R.id.tvCount);
        return adb.create();
    }

    protected void onPrepareDialog(int id, Dialog dialog){
        super.onPrepareDialog(id, dialog);
        if(id == DIALOG){
            TextView tvTime = (TextView)dialog.getWindow().findViewById(R.id.tvTime);
            tvTime.setText(sdf.format(new Date(System.currentTimeMillis())));

            if (btn == R.id.btnAdd){
                TextView tv =  new TextView(this);
                view.addView(tv, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                tv.setText("TV " + (textViews.size() + 1));
                textViews.add(tv);
            } else {
                if (textViews.size() > 0){
                    TextView tv = textViews.get(textViews.size() - 1);
                    view.removeView(tv);
                    textViews.remove(tv);
                }
            }
            tvCount.setText("Количество TextView = " + textViews.size());
        }
    }
}
