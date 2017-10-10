package com.gigabytedevelopersinc.app.calculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Created by Emmanuel Nwokoma (Founder and CEO at Gigabyte Developers) on 10/3/2017
 **/
public class FragmentTwo extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        return inflater.inflate(R.layout.main,container,false);
    }
}
