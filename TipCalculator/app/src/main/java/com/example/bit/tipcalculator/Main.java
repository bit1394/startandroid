package com.example.bit.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class Main extends AppCompatActivity {
    private static final String BILL_TOTAL = "BILL_TOTAL";
    private static final String CUSTOM_PERCENT = "CUSTOM_PERCENT";

    private double currentBillTotal;                                    //счет вводимый пользователем
    private int currentCustomPercent;                                   //% чаевых с SeekBar
    private EditText tip10EditText, tip15EditText, tip20EditText;       //10%, 15%, 20% чаевых
    private EditText total10EditText, total15EditText, total20EditText; //общий счет, включающий соответствующие чаевые
    private EditText billEditText;                                      //ввод счет пользователем
    private TextView customTipTextView;                                 //% пользовательских чаевых (default = 18)
    private EditText tipCustomEditText, totalCustomEditText;            //пользовательские чаевые, общий счет с чаевыми

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scr);

        if (savedInstanceState == null) {
            currentBillTotal = 0.0;
            currentCustomPercent = 18;
        } else {
            currentBillTotal = savedInstanceState.getDouble(BILL_TOTAL);
            currentCustomPercent = savedInstanceState.getInt(CUSTOM_PERCENT);
        }

        tip10EditText = (EditText)findViewById(R.id.tip10ET);
        tip15EditText = (EditText)findViewById(R.id.tip15ET);
        tip20EditText = (EditText)findViewById(R.id.tip20ET);

        total10EditText =(EditText) findViewById(R.id.total10ET);
        total15EditText =(EditText) findViewById(R.id.total15ET);
        total20EditText =(EditText) findViewById(R.id.total20ET);

        billEditText = (EditText) findViewById(R.id.billET);

        customTipTextView = (TextView)findViewById(R.id.tipCustomTV);

        tipCustomEditText = (EditText)findViewById(R.id.tipCustomET);
        totalCustomEditText = (EditText)findViewById(R.id.totalCustomET);

        billEditText.addTextChangedListener(billEditTextWatcher);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar3);
        seekBar.setOnSeekBarChangeListener(customSeekBarListener);
    }

    private void updateStandart(){
        double tenPercentTip = currentBillTotal * .1;
        double tenPercentTotal = currentBillTotal + tenPercentTip;

        tip10EditText.setText(String.format(" %.02f", tenPercentTip));
        total10EditText.setText(String.format(" %.02f", tenPercentTotal));

        double fifteenPercentTip = currentBillTotal * .15;
        double fifteenPercentTotal = currentBillTotal + fifteenPercentTip;

        tip15EditText.setText(String.format(" %.02f", fifteenPercentTip));
        total15EditText.setText(String.format(" %.02f", fifteenPercentTotal));

        double twentyPercentTip = currentBillTotal * .2;
        double twentyPercentTotal = currentBillTotal + twentyPercentTip;

        tip20EditText.setText(String.format(" %.02f", twentyPercentTip));
        total20EditText.setText(String.format(" %.02f", twentyPercentTotal));
    }

    private void updateCustom(){
        customTipTextView.setText(currentCustomPercent + " %");

        double customTipAmount = currentBillTotal * currentCustomPercent *  .01;
        double customTotalAmount = currentBillTotal + customTipAmount;

        tipCustomEditText.setText(String.format(" %.02f", customTipAmount));
        totalCustomEditText.setText(String.format(" %.02f", customTotalAmount));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putDouble(BILL_TOTAL, currentBillTotal);
        outState.putInt(CUSTOM_PERCENT, currentCustomPercent);
    }

    private SeekBar.OnSeekBarChangeListener customSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            currentCustomPercent = seekBar.getProgress();
            updateCustom();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private TextWatcher billEditTextWatcher = new TextWatcher(){
        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            try{
                currentBillTotal = Double.parseDouble(s.toString());
            } catch (NumberFormatException e){
                currentBillTotal = 0.0;
            }

            updateStandart();
            updateCustom();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
    };

}
