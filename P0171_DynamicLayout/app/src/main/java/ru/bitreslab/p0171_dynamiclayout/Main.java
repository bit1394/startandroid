package ru.bitreslab.p0171_dynamiclayout;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Main extends AppCompatActivity implements View.OnClickListener{

    LinearLayout llMain;
    RadioGroup rgGravity;
    RadioButton rbLeft, rbCenter, rbRight;
    EditText etName;
    Button btnCreate, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        llMain = (LinearLayout) findViewById(R.id.llMain);
        rgGravity = (RadioGroup) findViewById(R.id.rgGravity);
        rbLeft = (RadioButton) findViewById(R.id.rbLeft);
        rbCenter = (RadioButton) findViewById(R.id.rbCenter);
        rbRight = (RadioButton) findViewById(R.id.rbRight);
        etName = (EditText) findViewById(R.id.etName);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnClear = (Button) findViewById(R.id.btnClear);

        btnCreate.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCreate:
                LinearLayout.LayoutParams lPar = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                int btnGravity = Gravity.LEFT; // by default

                switch (rgGravity.getCheckedRadioButtonId()){
                    case R.id.rbLeft:
                        btnGravity = Gravity.LEFT;
                        break;
                    case R.id.rbCenter:
                        btnGravity = Gravity.CENTER_HORIZONTAL;
                        break;
                    case R.id.rbRight:
                        btnGravity = Gravity.RIGHT;
                        break;
                }
                lPar.gravity = btnGravity;
                Button btnNew = new Button(this);
                btnNew.setText(etName.getText().toString());
                llMain.addView(btnNew, lPar);
                break;
            case R.id.btnClear:
                llMain.removeAllViews();
                Toast.makeText(Main.this, "Deleted", Toast.LENGTH_SHORT).show();
                break;

        }

    }
}
