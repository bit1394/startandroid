package ru.bitreslab.p1041_fragmentlifecycle;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {

    final String LT = "myLogs";

    @Override
    public void onAttach(Activity act){
        super.onAttach(act);
        Log.d(LT, "F2 onAttach");
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d(LT, "F2 onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(LT,"F2 onCreateView");
        return inflater.inflate(R.layout.fragment2, null);
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Log.d(LT, "F2 onActivityCreated");
    }

    public void onStart(){
        super.onStart();
        Log.d(LT, "F2 onStart");
    }

    public void onResume(){
        super.onResume();
        Log.d(LT, "F2 onResume");
    }

    public void onPause(){
        super.onPause();
        Log.d(LT, "F2 onPause");
    }

    public void onStop(){
        super.onStop();
        Log.d(LT, "F2 onStop");
    }

    public void onDestroyView(){
        super.onDestroyView();
        Log.d(LT, "F2 onDestroyView");
    }

    public void onDestroy(){
        super.onDestroy();
        Log.d(LT, "F2 onDestroy");
    }

    public void onDetach(){
        super.onDetach();
        Log.d(LT, "F2 onDetach");
    }

}
