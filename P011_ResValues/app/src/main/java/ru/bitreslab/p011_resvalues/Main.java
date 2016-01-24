package ru.bitreslab.p011_resvalues;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main extends AppCompatActivity {

    Button btn;
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                imageView = (ImageView) findViewById(R.id.imageView);
                textView = (TextView) findViewById(R.id.textView);

                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_camera_black_48dp));
                textView.setText(R.string.newText);
                btn.setText(R.string.btnText);
            }

        });
    }
}
