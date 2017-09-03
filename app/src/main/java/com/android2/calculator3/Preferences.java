package com.android2.calculator3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.android2.calculator3.view.PreferencesFragment;

/**
 * @author Emmanuel Nwokoma (Founder and CEO of Gigabyte Developers INC)
 **/
public class Preferences extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getActionBar().setDisplayHomeAsUpEnabled(true);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this, Calculator.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, keyEvent);
    }
}
