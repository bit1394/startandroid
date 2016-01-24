package ru.bitreslab.p0122_toast;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends AppCompatActivity implements OnClickListener {

    TextView textView;
    Button button1;
    Button button2;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        textView = (TextView) findViewById(R.id.textView);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        //1
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Обработаем нажатие кнопки 0");
                Toast toast = Toast.makeText(Main.this, "Нажата кнопка 1", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 12, 100);
                LinearLayout toastImage = (LinearLayout) toast.getView();
                ImageView imageView = new ImageView(Main.this);
                imageView.setImageResource(R.drawable.logo);
                toastImage.addView(imageView, 0);

                toast.show();
            }
        });
        //

        //2
        button2.setOnClickListener(this);
        //

    }

    //3
    public void clickButton3(View view) {
        textView.setText("Нажата кнопка 3");
    }

    //
    @Override
    public void onClick(View v) {
        textView.setText("Нажата кнопка 2");
    }
}