package ru.bitreslab.p1061_fragmentactivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static ru.bitreslab.p1061_fragmentactivity.R.id.fragmnt2;
import static ru.bitreslab.p1061_fragmentactivity.R.id.fragmnt2;

public class Main extends FragmentActivity implements Fragment2.onSomeEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Fragment frag2 = new Fragment2();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragmnt2, frag2);
        ft.commit();
    }

//    public void onClick(View v){
//        Fragment frag1 = getSupportFragmentManager().findFragmentById(R.id.fragmnt1);
//        ((TextView) frag1.getView().findViewById(R.id.tView)).setText("Access to Fragment 1 from Activity");
//
//        Fragment frag2 = getSupportFragmentManager().findFragmentById(R.id.fragmnt2);
//        ((TextView) frag2.getView().findViewById(R.id.tView)).setText("Access to Fragment 2 from Activity");
//    }

    @Override
    public void someEvent(String s) {
        Fragment frag1 = getSupportFragmentManager().findFragmentById(R.id.fragmnt1);
        ((TextView) frag1.getView().findViewById(R.id.tView)).setText("Text from Fragment 2: " + s);
    }
}
