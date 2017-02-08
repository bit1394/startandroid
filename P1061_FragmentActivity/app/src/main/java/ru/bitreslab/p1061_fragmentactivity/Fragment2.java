package ru.bitreslab.p1061_fragmentactivity;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {
    public interface onSomeEventListener{
        public void someEvent(String s);
    }

    onSomeEventListener someEventListener;

    @Override
    public void onAttach(Activity act){
        super.onAttach(act);
        try{
            someEventListener = (onSomeEventListener) act;
        } catch (ClassCastException e){
            throw new ClassCastException(act.toString() + " must implement onSomeEventListener!");
        }
    }

    final String LT = "myLogs";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment2, null);
        Button btn = (Button) v.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                someEventListener.someEvent("Text to Fragment 1");
        }
    });
    return v;
    }

}
