package ru.bitreslab.p0271_getintentacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Info extends AppCompatActivity {
    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        tvDate = (TextView) findViewById(R.id.tvDate);
        String format = "", textInfo = "";

        Intent intent = getIntent();
        String action = intent.getAction();

        if (action.equals("bitreslab.time")){
            format = "HH:mm:ss";
            textInfo = "Time: ";
        } else if (action.equals("bitreslab.date")){
            format = "dd.MM.yyyy";
            textInfo = "Date: ";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateTime = sdf.format(new Date(System.currentTimeMillis()));
        tvDate.setText(textInfo + dateTime);

    }
}
