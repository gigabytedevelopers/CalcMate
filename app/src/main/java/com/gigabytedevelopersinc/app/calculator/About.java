package com.gigabytedevelopersinc.app.calculator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.gigabytedevelopersinc.app.calculator.view.AboutFragment;

/**
 * @author Emmanuel Nwokoma (Founder and CEO of Gigabyte Developers INC)
 **/
public class About extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getActionBar() != null)
        {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null) {
            AboutFragment about = new AboutFragment();
            getFragmentManager().beginTransaction().add(android.R.id.content, about).commit();
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void action_rate(View view) {
        Intent rate = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.gigabytedevelopersinc.app.calculator"));
        startActivity(rate);
    }
    public void action_share(View v) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        String shareSub = "\nLet me recommend this cool calculator app called CalcMate\n\n";
        shareSub = shareSub + "https://play.google.com/store/apps/details?id=com.gigabytedevelopersinc.app.calculator \n\n";
        share.putExtra(Intent.EXTRA_SUBJECT, "Check out CalcMate");
        share.putExtra(Intent.EXTRA_TEXT, shareSub);
        startActivity(Intent.createChooser(share, "Share CalcMate using"));
    }
    public void action_feedback(View v) {
        final Intent feedback = new Intent(Intent.ACTION_SEND);
        feedback.setType("text/html");
        feedback.putExtra(Intent.EXTRA_EMAIL, new String[]{ getString(R.string.feedback_email_address)});
        feedback.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback_email_subject));
        feedback.putExtra(Intent.EXTRA_TEXT, getString(R.string.feedback_email_message));
        startActivity(Intent.createChooser(feedback, getString(R.string.feedback_email_title)));
    }
}
