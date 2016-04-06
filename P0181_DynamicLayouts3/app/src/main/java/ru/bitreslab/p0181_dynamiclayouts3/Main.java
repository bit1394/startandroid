package ru.bitreslab.p0181_dynamiclayouts3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class Main extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{
    SeekBar sbWeight;
    Button btn1, btn2;
    LinearLayout.LayoutParams lPar1, lPar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        sbWeight = (SeekBar) findViewById(R.id.sbWeight);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        lPar1 = (LinearLayout.LayoutParams) btn1.getLayoutParams();
        lPar2 = (LinearLayout.LayoutParams) btn2.getLayoutParams();

        sbWeight.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int leftValue = progress;
        int rightValue = seekBar.getMax() - progress;

        lPar1.weight = leftValue;
        lPar2.weight = rightValue;

        btn1.setText(String.valueOf(leftValue));
        btn2.setText(String.valueOf(rightValue));

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
