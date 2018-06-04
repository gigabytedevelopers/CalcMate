package com.gigabytedevelopersinc.app.calculator.view;

import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.gigabytedevelopersinc.app.calculator.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class PreferencesFragment extends PreferenceFragment {

    //Create New Variable of type InterstitialAd
    private InterstitialAd mInterstitialAd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.layout.preferences);
        Preference about = findPreference("ABOUT");

        // Prepare the interstitial Ad
        mInterstitialAd = new InterstitialAd(getActivity());
        // Insert the Ad Unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ads));
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        // Load requested Ad
        mInterstitialAd.loadAd(adRequest);

        if(about != null) {
            String versionName = "";
            try {
                versionName = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
            }
            catch(NameNotFoundException e) {
                e.printStackTrace();
            }
            about.setTitle(about.getTitle() + " " + versionName);
            about.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                        mInterstitialAd.setAdListener(new AdListener() {
                            public void onAdClosed() {
                                AdRequest adRequest = new AdRequest.Builder()
                                        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                                        .build();
                                mInterstitialAd.loadAd(adRequest);
                                Intent github = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/gigabytedevelopers/CalcMate"));
                                startActivity(github);

                            }
                        });
                    } else {
                        Intent github = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/gigabytedevelopers/CalcMate"));
                        startActivity(github);
                    }
                    return false;
                }
            });
        }
    }
}
