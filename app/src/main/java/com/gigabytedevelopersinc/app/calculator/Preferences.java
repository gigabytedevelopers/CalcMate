package com.gigabytedevelopersinc.app.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import com.gigabytedevelopersinc.app.calculator.view.PreferencesFragment;

/**
 * @author Emmanuel Nwokoma (Founder and CEO of Gigabyte Developers INC)
 **/
public class Preferences extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getActionBar() != null)
        {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null) {
            PreferencesFragment preferences = new PreferencesFragment();
            getFragmentManager().beginTransaction().add(android.R.id.content, preferences).commit();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
