package ru.bitreslab.p0121_logcat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Main extends AppCompatActivity implements OnClickListener{

    private  static final String TAG = "myLogs";

    TextView textView;
    Button button1;
    Button button2;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        Log.d(TAG, "Найдем View-элементы");
        textView = (TextView) findViewById(R.id.textView);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        //1
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int i = 6/0;
                    textView.setText("Нажата кнопка 1");
                } catch (Exception e) {
                    Log.d(TAG, "На ноль делить низзя!!", e);
                }
            }
        });
        //

        //2
        button2.setOnClickListener(this);
        //
    }
    //3
    public void clickButton3(View view) {
        Log.d(TAG, "Нажата кнопка 3");
        textView.setText("Нажата кнопка 3");
    }
    //
    @Override
    public void onClick(View v) {
        Log.d(TAG, "Нажата кнопка 2");
        textView.setText("Нажата кнопка 2");
    }
}
