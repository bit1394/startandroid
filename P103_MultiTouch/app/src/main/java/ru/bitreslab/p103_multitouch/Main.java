package ru.bitreslab.p103_multitouch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class Main extends AppCompatActivity implements View.OnTouchListener{
    StringBuilder sb = new StringBuilder();
    TextView tv;
    int upPI = 0;
    int downPI = 0;
    boolean inTouch = false;
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tv = new TextView(this);
        tv.setTextSize(25);
        tv.setOnTouchListener(this);
        setContentView(tv);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //событие
        int  actionMask = event.getActionMasked();
        //индекс касания
        int pointerIndex = event.getActionIndex();
        //число касаний
        int pointerCount = event.getPointerCount();

        switch (actionMask){
            case MotionEvent.ACTION_DOWN:
                inTouch = true;
            case MotionEvent.ACTION_POINTER_DOWN:
                downPI = pointerIndex;
                break;

            case MotionEvent.ACTION_UP:
                inTouch = false;
                sb.setLength(0);
            case MotionEvent.ACTION_POINTER_UP:
                upPI = pointerIndex;
                break;

            case MotionEvent.ACTION_MOVE:
                sb.setLength(0);
                for(int i = 0; i < 10; i++){
                    sb.append("Index = " + i);
                    if(i < pointerCount) {
                        sb.append(", ID = " + event.getPointerId(i));
                        sb.append(", X = " + event.getX(i));
                        sb.append(", Y = " + event.getY(i));
                    } else {
                        sb.append(", ID = ");
                        sb.append(", X = ");
                        sb.append(", Y = ");
                    }
                    sb.append("\r\n");
                }
                break;
        }
        result = "down: " + downPI + "\n" + "up: " + upPI + "\n";

        if(inTouch){
            result += "pointerCount = " + pointerCount + "\n" + sb.toString();
        }
        tv.setText(result);
        return true;
    }
}
