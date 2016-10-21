package ru.bitreslab.p0681_parcel;

import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    final String LT = "myLogs";
    Parcel p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        writeParcel();
        readParcel();
    }

    void writeParcel(){
        p = Parcel.obtain();

        byte b =1;
        int i = 2;
        long l = 3;
        float f = 4;
        double d = 5;
        String s = "stroka";
        logWriteInfo ("before writing");
        p.writeByte(b);
        logWriteInfo("byte");
        p.writeInt(i);
        logWriteInfo("int");
        p.writeLong(l);
        logWriteInfo("long");
        p.writeFloat(f);
        logWriteInfo("float");
        p.writeDouble(d);
        logWriteInfo("double");
        p.writeString(s);
        logWriteInfo("string");
    }

    void logWriteInfo(String str){
        Log.d(LT, str + ": " +"dataSize = " + p.dataSize());
    }

    void readParcel(){
        logReadInfo("before reading");
        p.setDataPosition(0);
        logReadInfo("byte = " + p.readByte());
        logReadInfo("int = " + p.readInt());
        logReadInfo("long = " + p.readLong());
        logReadInfo("float = " + p.readFloat());
        logReadInfo("double = " + p.readDouble());
        logReadInfo("string = " + p.readString());
    }

    void logReadInfo(String str){
        Log.d(LT, str + ": " + "dataPosition = " + p.dataPosition());
    }

}
