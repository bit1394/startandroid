package ru.bitreslab.p0311_simpleintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity implements View.OnClickListener{

    Button btnWeb, btnMap, btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnWeb = (Button) findViewById(R.id.btnWeb);
        btnMap = (Button) findViewById(R.id.btnMap);
        btnCall = (Button) findViewById(R.id.btnCall);

        btnWeb.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnCall.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()){
            case R.id.btnWeb:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com"));
                startActivity(intent);
                break;
            case R.id.btnMap:
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo: 60.45609946, -40.26607513"));
                startActivity(intent);
                break;
            case R.id.btnCall:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: 123456"));
                startActivity(intent);
                break;
        }

    }
}
