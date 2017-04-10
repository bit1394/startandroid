package ru.bitreslab.p1101_dialogfragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dialog2 extends DialogFragment implements DialogInterface.OnClickListener {

    final String LT = "myLogs";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity()).
                setTitle("Title, a...!").
                setPositiveButton(R.string.yes, (DialogInterface.OnClickListener) this).
                setNegativeButton(R.string.no, (DialogInterface.OnClickListener) this)
                .setNeutralButton(R.string.maybe, (DialogInterface.OnClickListener) this).
                setMessage(R.string.message_text);


        return adb.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        int i = 0;
        switch (which){
            case Dialog.BUTTON_POSITIVE:
                i = R.string.yes;
                break;
            case Dialog.BUTTON_NEGATIVE:
                i = R.string.no;
                break;
            case Dialog.BUTTON_NEUTRAL:
                i = R.string.maybe;
                break;
        }

        if(i > 0)
            Log.d(LT, "Dialog 2 " + getResources().getString(i));
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(LT, "Dialog 2: onDismiss");
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(LT, "Dialog 2: onCancel");
    }
}
