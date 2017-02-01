package ru.bitreslab.p1041_fragmentlifecycle;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {

    final String LT = "myLogs";

    @Override
    public void onAttach(Activity act){
        super.onAttach(act);
        Log.d(LT, "F1 onAttach");
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d(LT, "F1 onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(LT,"F1 onCreateView");
        return inflater.inflate(R.layout.fragment1, null);
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Log.d(LT, "F1 onActivityCreated");
    }

    public void onStart(){
        super.onStart();
        Log.d(LT, "F1 onStart");
    }

    public void onResume(){
        super.onResume();
        Log.d(LT, "F1 onResume");
    }

    public void onPause(){
        super.onPause();
        Log.d(LT, "F1 onPause");
    }

    public void onStop(){
        super.onStop();
        Log.d(LT, "F1 onStop");
    }

    public void onDestroyView(){
        super.onDestroyView();
        Log.d(LT, "F1 onDestroyView");
    }

    public void onDestroy(){
        super.onDestroy();
        Log.d(LT, "F1 onDestroy");
    }

    public void onDetach(){
        super.onDetach();
        Log.d(LT, "F1 onDetach");
    }
}
