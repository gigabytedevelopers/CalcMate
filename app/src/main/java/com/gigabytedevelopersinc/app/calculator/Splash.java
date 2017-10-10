package com.gigabytedevelopersinc.app.calculator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/**
 * @author Emmanuel Nwokoma (Founder and CEO of Gigabyte Developers INC)
 **/

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        Intent intent = new Intent(this, Calculator.class);
        startActivity(intent);
        if (getActionBar() != null)
        {
            getActionBar().hide();
        }
        finish();
        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }
}
