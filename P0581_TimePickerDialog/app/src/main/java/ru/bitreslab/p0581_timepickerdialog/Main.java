package ru.bitreslab.p0581_timepickerdialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

public class Main extends AppCompatActivity {
    int DIALOG_TIME = 1;
    int myHour = 14;
    int myMinute = 42;

    TextView tvTime;
    Button btnTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tvTime = (TextView) findViewById(R.id.tvTime);
        btnTime = (Button)findViewById(R.id.btnTime);
    }

    public void onClick(View view){
        showDialog(DIALOG_TIME);
    }

    protected Dialog onCreateDialog(int id){
        if(id == DIALOG_TIME){
            TimePickerDialog tpd = new TimePickerDialog(this, myCallBack, myHour, myMinute, true);
            return tpd;
        }
        return super.onCreateDialog(id);
    }

    TimePickerDialog.OnTimeSetListener myCallBack = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myHour = hourOfDay;
            myMinute = minute;
            tvTime.setText("Time is " + myHour + "hours " + myMinute + "minutes");
        }
    };
}
