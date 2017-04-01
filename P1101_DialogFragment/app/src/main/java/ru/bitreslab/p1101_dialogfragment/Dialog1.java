package ru.bitreslab.p1101_dialogfragment;


import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dialog1 extends DialogFragment implements View.OnClickListener{

    final String LT = "myLogs";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getDialog().setTitle("Title, b...!");

        View v = inflater.inflate(R.layout.dialog, null);
        v.findViewById(R.id.btnYes).setOnClickListener(this);
        v.findViewById(R.id.btnNo).setOnClickListener(this);
        v.findViewById(R.id.btnMaybe).setOnClickListener(this);

        return v;
    }

        @Override
        public void onClick(View v) {
            Log.d(LT, "Dialog 1: " + ((Button) v).getText());
            dismiss(); //закрытие диалога - только при использовании своего layout-файла
        }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(LT, "Dialog 1: onDismiss");
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(LT, "Dialog 1: onCancel");
    }
}

