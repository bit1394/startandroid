package ru.bitreslab.p0131_androidcontextmenu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
    //    if (id == R.id.action_settings) {
    //        return true;
     //   }

        switch (id){
            case R.id.action_settings:
                Toast.makeText(Main.this, getString(R.string.action_settings), Toast.LENGTH_LONG).show();
                break;

            case R.id.item1:
                Toast.makeText(Main.this, getString(R.string.item1), Toast.LENGTH_LONG).show();
                break;

            case R.id.item2:
                Toast.makeText(Main.this, getString(R.string.item2), Toast.LENGTH_LONG).show();
                break;

            case R.id.item3:
                Toast.makeText(Main.this, getString(R.string.item3), Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
