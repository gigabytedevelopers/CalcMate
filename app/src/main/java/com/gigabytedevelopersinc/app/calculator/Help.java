package com.gigabytedevelopersinc.app.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import com.gigabytedevelopersinc.app.calculator.view.HelpFragment;

/**
 * @author Emmanuel Nwokoma (Founder and CEO of Gigabyte Developers INC)
 **/
public class Help extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getActionBar() != null)
        {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null) {
            HelpFragment help = new HelpFragment();
            getFragmentManager().beginTransaction().add(android.R.id.content, help).commit();
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
