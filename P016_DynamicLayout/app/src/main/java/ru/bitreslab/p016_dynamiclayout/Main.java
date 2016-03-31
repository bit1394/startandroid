package ru.bitreslab.p016_dynamiclayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout lLayout = new LinearLayout(this);
        lLayout.setOrientation(LinearLayout.VERTICAL);
        ViewGroup.LayoutParams lLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        setContentView(lLayout, lLayoutParams);

        ViewGroup.LayoutParams lpView = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView tv = new TextView(this);
        tv.setText("Text View");
        tv.setLayoutParams(lpView);
        lLayout.addView(tv);

        Button btn = new Button(this);
        btn.setText("Button");
        lLayout.addView(btn, lpView);

        LinearLayout.LayoutParams leftMargPar = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftMargPar.leftMargin = 50;

        Button btn2 = new Button(this);
        btn2.setText("Button 2");
        lLayout.addView(btn2,leftMargPar);

        LinearLayout.LayoutParams rightGravPar = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightGravPar.gravity = Gravity.RIGHT;

        Button btn3 = new Button(this);
        btn3.setText("Button 3");
        lLayout.addView(btn3, rightGravPar);

    }
}
