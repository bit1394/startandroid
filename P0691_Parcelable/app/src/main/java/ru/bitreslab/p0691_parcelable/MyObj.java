package ru.bitreslab.p0691_parcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by Bit on 22.10.2016.
 */

public class MyObj implements Parcelable{
    final static String LT = "myLogs";
    public String s;
    public int i;

    public MyObj (String _s, int _i){
        Log.d(LT, "MyObj (String _s, int _i");
        s = _s;
        i = _i;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.d(LT, "writeToParcel");
        dest.writeString(s);
        dest.writeInt(i);
    }

    public static final Parcelable.Creator<MyObj> CREATOR = new Parcelable.Creator<MyObj>(){
        public MyObj createFromParcel(Parcel in){
            Log.d(LT, "createFromParcel");
            return new MyObj(in);
        }

        @Override
        public MyObj[] newArray(int size) {
            return new MyObj[size];
        }
    };

    private MyObj (Parcel p){
        Log.d(LT, "MyObj (Parcel p)");
        s = p.readString();
        i = p.readInt();
    }
}
