package com.gigabytedevelopersinc.app.calculator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import com.gigabytedevelopersinc.app.calculator.view.AboutFragment;

/**
 * @author Emmanuel Nwokoma (Founder and CEO of Gigabyte Developers INC)
 **/
public class About extends Activity {

    //Create New Variable of type InterstitialAd
    private InterstitialAd mInterstitialAd;

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

        // Prepare the interstitial Ad
        mInterstitialAd = new InterstitialAd(getApplicationContext());
        // Insert the Ad Unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ads));
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        // Load requested Ad
        mInterstitialAd.loadAd(adRequest);

        /*//initialize an object on InterstitialAd
        mInterstitialAd = new InterstitialAd(this);

        //SetAdUnitId of InterstitialAds
        mInterstitialAd.setAdUnitId("ca-app-pub-6559138681672642/9998639521");

        //Load new Add by passing AdRequest
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        //setting AdListener to the interstitial Ad
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {

                //When Ad is closed, Start Second Activity
                startActivity(new Intent(About.this, AboutFragment.class));

                //Prepare for loading new interstitial Ad when first Ad is served(shown)
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
        //If loaded show ad
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            //Even if Ad is not loaded show the Second Activity
            startActivity(new Intent(About.this, AboutFragment.class));
        }*/
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void action_rate(View view) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener() {
                public void onAdClosed() {
                    AdRequest adRequest = new AdRequest.Builder()
                            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                            .build();
                    mInterstitialAd.loadAd(adRequest);
                    Intent rate = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.gigabytedevelopersinc.app.calculator"));
                    startActivity(rate);

                }
            });
        } else {
            Intent rate = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.gigabytedevelopersinc.app.calculator"));
            startActivity(rate);
        }
    }
    public void action_share(View v) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener() {
                public void onAdClosed() {
                    AdRequest adRequest = new AdRequest.Builder()
                            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                            .build();
                    mInterstitialAd.loadAd(adRequest);
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("text/plain");
                    String shareSub = "\nLet me recommend this cool calculator app called CalcMate\n\n";
                    shareSub = shareSub + "https://play.google.com/store/apps/details?id=com.gigabytedevelopersinc.app.calculator \n\n";
                    share.putExtra(Intent.EXTRA_SUBJECT, "Check out CalcMate");
                    share.putExtra(Intent.EXTRA_TEXT, shareSub);
                    startActivity(Intent.createChooser(share, "Share CalcMate using"));
                }
            });
        } else {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            String shareSub = "\nLet me recommend this cool calculator app called CalcMate\n\n";
            shareSub = shareSub + "https://play.google.com/store/apps/details?id=com.gigabytedevelopersinc.app.calculator \n\n";
            share.putExtra(Intent.EXTRA_SUBJECT, "Check out CalcMate");
            share.putExtra(Intent.EXTRA_TEXT, shareSub);
            startActivity(Intent.createChooser(share, "Share CalcMate using"));
        }
    }
    public void action_feedback(View v) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener() {
                public void onAdClosed() {
                    AdRequest adRequest = new AdRequest.Builder()
                            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                            .build();
                    mInterstitialAd.loadAd(adRequest);
                    final Intent feedback = new Intent(Intent.ACTION_SEND);
                    feedback.setType("text/html");
                    feedback.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.feedback_email_address)});
                    feedback.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback_email_subject));
                    feedback.putExtra(Intent.EXTRA_TEXT, getString(R.string.feedback_email_message));
                    startActivity(Intent.createChooser(feedback, getString(R.string.feedback_email_title)));
                }
            });
        } else {
            final Intent feedback = new Intent(Intent.ACTION_SEND);
            feedback.setType("text/html");
            feedback.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.feedback_email_address)});
            feedback.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback_email_subject));
            feedback.putExtra(Intent.EXTRA_TEXT, getString(R.string.feedback_email_message));
            startActivity(Intent.createChooser(feedback, getString(R.string.feedback_email_title)));
        }
    }
}