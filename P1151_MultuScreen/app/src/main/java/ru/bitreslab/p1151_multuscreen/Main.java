package ru.bitreslab.p1151_multuscreen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main  extends FragmentActivity implements TitleFragment.onItemClickListener{
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if(savedInstanceState != null)
            position = savedInstanceState.getInt("position");
        showDetails(position);
    }

    void showDetails(int pos){
        DetailsFragment details = (DetailsFragment)getSupportFragmentManager().findFragmentById(R.id.cont);

        if (details == null || details.getPosition() != pos){
            details = DetailsFragment.newInstance(pos);
            getSupportFragmentManager().beginTransaction().replace(R.id.cont, details).commit();
        }
    }

    @Override
    public void itemClick(int position) {
        this.position = position;
        showDetails(position);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", position);
    }
}
