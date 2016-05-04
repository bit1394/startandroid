package ru.bitreslab.p0281_intentextras;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        tv = (TextView) findViewById(R.id.tv);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        tv.setText(name);
    }
}
