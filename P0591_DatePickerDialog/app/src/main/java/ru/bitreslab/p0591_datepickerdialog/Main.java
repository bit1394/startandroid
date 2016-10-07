package ru.bitreslab.p0591_datepickerdialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class Main extends AppCompatActivity {
    int DIALOG_DATE = 1;
    int myYear = 2016;
    int myMonth = 10;
    int myDay = 07;

    TextView tvDate;
    Button btnDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tvDate = (TextView) findViewById(R.id.tvDate);
        btnDate = (Button) findViewById(R.id.btnDate);
    }

    public void onClick(View vie){
        showDialog(DIALOG_DATE);
    }

    protected Dialog onCreateDialog (int id){
        if(id == DIALOG_DATE){
            DatePickerDialog dpd = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
            return dpd;
        }
        return super.onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear;
            myDay = dayOfMonth;

            tvDate.setText("Today is " + myDay + "/" + (myMonth + 1) + "/" + myYear);
        }
    };
}
