package ru.bitreslab.p1121_dynamicactionbar;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;

public class Main extends ActionBarActivity {
    final int MENU_ID = 1;

    CheckBox chbAddDel, chbVisible;

    Fragment frag;
    Fragment frag1;
    Fragment frag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        chbAddDel = (CheckBox) findViewById(R.id.chbAddDel);
        chbVisible = (CheckBox) findViewById(R.id.chbVisible);

        frag =  new Fragment1();
        frag1 = new Fragment1();
        frag2 = new Fragment2();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.setGroupVisible(R.id.groupVsbl, chbVisible.isChecked());

        if (chbAddDel.isChecked()){
            menu.add(0, MENU_ID, 0, R.string.menu_item1).setIcon(android.R.drawable.ic_delete).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        } else {
            menu.removeItem(MENU_ID);
        }
        return true;
    }

   public void onClick(View v){
       switch (v.getId()){
           case R.id.chbAddDel:
           case R.id.chbVisible:
               invalidateOptionsMenu();
               break;
           case R.id.btnFrag:
              frag = (frag == frag1) ? frag2 : frag1;
                  getFragmentManager().beginTransaction().replace(R.id.cont, frag).commit();

               break;
           default:
               break;
       }
   }
}
