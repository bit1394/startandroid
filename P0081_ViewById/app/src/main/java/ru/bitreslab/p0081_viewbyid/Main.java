package ru.bitreslab.p0081_viewbyid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TextView myText = (TextView) findViewById(R.id.myText);
        myText.setText("Android it`s easy!");

        Button myBtn = (Button) findViewById(R.id.myBtn);
        myBtn.setText("My first BUTTON");
        myBtn.setEnabled(false);

        CheckBox myChb = (CheckBox) findViewById(R.id.myChb);
        myChb.setText("CheckBox");
        myChb.setChecked(false);

    }
}
