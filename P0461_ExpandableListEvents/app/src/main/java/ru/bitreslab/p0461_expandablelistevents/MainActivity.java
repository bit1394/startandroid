package ru.bitreslab.p0461_expandablelistevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final String LT = "myLogs";
    ExpandableListView elvMain;
    AdapterHelper ah;
    SimpleExpandableListAdapter ad;
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        tvInfo = (TextView) findViewById(R.id.tvInfo);

        ah = new AdapterHelper(this);
        ad = ah.getAd();

        elvMain.setAdapter(ad);

        //element events
        elvMain.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.d(LT, "Child: Click on Group = " + groupPosition + ", Click on child = " + childPosition + ", id = " + id);
                tvInfo.setText(ah.getGroupChildText(groupPosition, childPosition));
                return false;
            }
        });

        //group events
        elvMain.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Log.d(LT, "Group: Click on position = " + groupPosition + ", id = " + id);
                //block of listen for position 1 (for illustration what is FALSE/TRUE):
                //if (groupPosition == 1)
                //    return true;
                return false;
            }
        });

        //collapse group
        elvMain.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Log.d(LT, "Group Collapse: position = " + groupPosition);
                tvInfo.setText("Collapse of " + ah.getGroupText(groupPosition));
            }
        });

        //expand group
        elvMain.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Log.d(LT, "Group Expand: position = " + groupPosition);
                tvInfo.setText("Expand of " + ah.getGroupText(groupPosition));
            }
        });

        //expand of Group 2:
        elvMain.expandGroup(2);

    }
}
