package ru.bitreslab.p0741_prefcode;

import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PrefAct extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceScreen rootScr = getPreferenceManager().createPreferenceScreen(this);
        setPreferenceScreen(rootScr);

        CheckBoxPreference chb1 = new CheckBoxPreference(this);
        chb1.setKey("chb1");
        chb1.setTitle("CBox1");
        chb1.setSummaryOn("Enabled");
        chb1.setSummaryOff("Disabled");
        rootScr.addPreference(chb1);

        ListPreference list = new ListPreference(this);
        list.setKey("list");
        list.setTitle("List");
        list.setEntries(R.array.entries);
        list.setEntryValues(R.array.entry_values);
        rootScr.addPreference(list);

        CheckBoxPreference chb2 = new CheckBoxPreference(this);
        chb2.setKey("chb2");
        chb2.setTitle("CBox2");
        rootScr.addPreference(chb2);

        PreferenceScreen scr = getPreferenceManager().createPreferenceScreen(this);
        scr.setKey("scr");
        scr.setTitle("Screen");
        scr.setSummary("Other screen");

        final CheckBoxPreference chb3 = new CheckBoxPreference(this);
        chb3.setKey("chb3");
        chb3.setTitle("CBox3");
        scr.addPreference(chb3);

        PreferenceCategory categ1 = new PreferenceCategory(this);
        categ1.setKey("categ1");
        categ1.setTitle("Category 1");
        scr.addPreference(categ1);

        CheckBoxPreference chb4 = new CheckBoxPreference(this);
        chb4.setKey("chb4");
        chb4.setTitle("CBox4");
        categ1.addPreference(chb4);

        final PreferenceCategory categ2 = new PreferenceCategory(this);
        categ2.setKey("categ2");
        categ2.setTitle("Category 2");
        scr.addPreference(categ2);

        CheckBoxPreference chb5 = new CheckBoxPreference(this);
        chb5.setKey("chb5");
        chb5.setTitle("CBox5");
        categ2.addPreference(chb5);

        CheckBoxPreference chb6 = new CheckBoxPreference(this);
        chb6.setKey("chb6");
        chb6.setTitle("CBox6");
        categ2.addPreference(chb6);

        rootScr.addPreference(scr);
        list.setDependency("chb1");
        scr.setDependency("chb2");

        categ2.setEnabled(chb3.isChecked());
        chb3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                categ2.setEnabled(chb3.isChecked());
                return false;
            }
        });
    }
}
