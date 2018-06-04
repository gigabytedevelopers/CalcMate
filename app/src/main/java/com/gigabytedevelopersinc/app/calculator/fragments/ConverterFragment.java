package com.gigabytedevelopersinc.app.calculator.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.gigabytedevelopersinc.app.calculator.R;

public class ConverterFragment extends Fragment {
    private int converterSpinnerPosition;

    public ConverterFragment() {
    }

    public static android.support.v4.app.Fragment newInstance() {
        return new ConverterFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        final ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_converter, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Spinner unitSpinner = (Spinner) view.findViewById(R.id.units_spinner);
        String[] unitsArray = {"Area", "Cooking", "Digital Storage", "Energy", "Fuel Consumption",
                "Length / Distance", "Mass / Weight", "Power", "Pressure", "Speed",
                "Time", "Torque", "Volume"};
        final Spinner from = (Spinner) view.findViewById(R.id.from_spinner);
        final Spinner to = (Spinner) view.findViewById(R.id.to_spinner);

        final SharedPreferences preferences = getActivity().getSharedPreferences("pref_name", Context.MODE_PRIVATE);
        final String[] areaHeaders = {getResources().getString(R.string.sq_kilometre),
                getResources().getString(R.string.sq_metre),
                getResources().getString(R.string.sq_centimetre),
                getResources().getString(R.string.hectare),
                getResources().getString(R.string.sq_mile),
                getResources().getString(R.string.sq_yard),
                getResources().getString(R.string.sq_foot),
                getResources().getString(R.string.sq_inch),
                getResources().getString(R.string.acre)};

        final String[] cookingHeaders = {getResources().getString(R.string.teaspoon),
                getResources().getString(R.string.tablespoon),
                getResources().getString(R.string.cup),
                getResources().getString(R.string.fluid_ounce),
                getResources().getString(R.string.fluid_ounce_uk),
                getResources().getString(R.string.pint),
                getResources().getString(R.string.pint_uk),
                getResources().getString(R.string.quart),
                getResources().getString(R.string.quart_uk),
                getResources().getString(R.string.gallon),
                getResources().getString(R.string.gallon_uk),
                getResources().getString(R.string.millilitre),
                getResources().getString(R.string.litre)};

        final String[] digitalHeaders = {getResources().getString(R.string.bit),
                getResources().getString(R.string.Byte),
                getResources().getString(R.string.kilobit),
                getResources().getString(R.string.kilobyte),
                getResources().getString(R.string.megabit),
                getResources().getString(R.string.megabyte),
                getResources().getString(R.string.gigabit),
                getResources().getString(R.string.gigabyte),
                getResources().getString(R.string.terabit),
                getResources().getString(R.string.terabyte)};

        final String[] energyHeaders = {getResources().getString(R.string.joule),
                getResources().getString(R.string.kilojoule),
                getResources().getString(R.string.calorie),
                getResources().getString(R.string.kilocalorie),
                getResources().getString(R.string.btu),
                getResources().getString(R.string.ft_lbF),
                getResources().getString(R.string.in_lbF),
                getResources().getString(R.string.kilowatt_hour),
                getResources().getString(R.string.electron_volt)};

        final String[] fuelHeaders = {getResources().getString(R.string.mpg_us),
                getResources().getString(R.string.mpg_uk),
                getResources().getString(R.string.l_100k),
                getResources().getString(R.string.km_l),
                getResources().getString(R.string.miles_l)};

        final String[] lengthHeaders = {getResources().getString(R.string.kilometre),
                getResources().getString(R.string.mile),
                getResources().getString(R.string.metre),
                getResources().getString(R.string.centimetre),
                getResources().getString(R.string.millimetre),
                getResources().getString(R.string.micrometre),
                getResources().getString(R.string.nanometre),
                getResources().getString(R.string.yard),
                getResources().getString(R.string.feet),
                getResources().getString(R.string.feet_inch),
                getResources().getString(R.string.inch),
                getResources().getString(R.string.nautical_mile),
                getResources().getString(R.string.furlong),
                getResources().getString(R.string.light_year)};

        final String[] massHeaders = {getResources().getString(R.string.kilogram),
                getResources().getString(R.string.pound),
                getResources().getString(R.string.gram),
                getResources().getString(R.string.milligram),
                getResources().getString(R.string.ounce),
                getResources().getString(R.string.grain),
                getResources().getString(R.string.stone),
                getResources().getString(R.string.metric_ton),
                getResources().getString(R.string.short_ton),
                getResources().getString(R.string.long_ton)};

        final String[] powerHeaders = {getResources().getString(R.string.watt),
                getResources().getString(R.string.kilowatt),
                getResources().getString(R.string.megawatt),
                getResources().getString(R.string.hp),
                getResources().getString(R.string.hp_uk),
                getResources().getString(R.string.ft_lbf_s),
                getResources().getString(R.string.calorie_s),
                getResources().getString(R.string.btu_s),
                getResources().getString(R.string.kva),
                getResources().getString(R.string.electron_volt)};

        final String[] pressureHeaders = {getResources().getString(R.string.megapascal),
                getResources().getString(R.string.kilopascal),
                getResources().getString(R.string.pascal),
                getResources().getString(R.string.bar),
                getResources().getString(R.string.psi),
                getResources().getString(R.string.psf),
                getResources().getString(R.string.atmosphere),
                getResources().getString(R.string.technical_atmosphere),
                getResources().getString(R.string.mmhg),
                getResources().getString(R.string.torr)};

        final String[] speedHeaders = {getResources().getString(R.string.km_h),
                getResources().getString(R.string.mph),
                getResources().getString(R.string.m_s),
                getResources().getString(R.string.fps),
                getResources().getString(R.string.knot)};

        final String[] timeHeaders = {getResources().getString(R.string.year),
                getResources().getString(R.string.month),
                getResources().getString(R.string.week),
                getResources().getString(R.string.day),
                getResources().getString(R.string.hour),
                getResources().getString(R.string.minute),
                getResources().getString(R.string.second),
                getResources().getString(R.string.millisecond),
                getResources().getString(R.string.nanosecond)};

        final String[] torqueHeaders = {getResources().getString(R.string.n_m),
                getResources().getString(R.string.ft_lbF),
                getResources().getString(R.string.in_lbF)};

        final String[] volumeHeaders = {getResources().getString(R.string.teaspoon),
                getResources().getString(R.string.tablespoon),
                getResources().getString(R.string.cup),
                getResources().getString(R.string.fluid_ounce),
                getResources().getString(R.string.fluid_ounce_uk),
                getResources().getString(R.string.pint),
                getResources().getString(R.string.pint_uk),
                getResources().getString(R.string.quart),
                getResources().getString(R.string.quart_uk),
                getResources().getString(R.string.gallon),
                getResources().getString(R.string.gallon_uk),
                getResources().getString(R.string.barrel),
                getResources().getString(R.string.barrel_uk),
                getResources().getString(R.string.millilitre),
                getResources().getString(R.string.litre),
                getResources().getString(R.string.cubic_cm),
                getResources().getString(R.string.cubic_m),
                getResources().getString(R.string.cubic_inch),
                getResources().getString(R.string.cubic_foot),
                getResources().getString(R.string.cubic_yard)};

        ArrayAdapter<String> adapterUnits = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, unitsArray);
        adapterUnits.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(adapterUnits);
        unitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unitSpinner.setSelection(position);
                converterSpinnerPosition = unitSpinner.getSelectedItemPosition();
                preferences.edit().putInt("converterSpinnerPosition", converterSpinnerPosition).apply();
                switch (position) {
                    case 0:
                        setUnitsAdapter(areaHeaders, from, to);
                        break;
                    case 1:
                        setUnitsAdapter(cookingHeaders, from, to);
                        break;
                    case 2:
                        setUnitsAdapter(digitalHeaders, from, to);
                        break;
                    case 3:
                        setUnitsAdapter(energyHeaders, from, to);
                        break;
                    case 4:
                        setUnitsAdapter(fuelHeaders, from, to);
                        break;
                    case 5:
                        setUnitsAdapter(lengthHeaders, from, to);
                        break;
                    case 6:
                        setUnitsAdapter(massHeaders, from, to);
                        break;
                    case 7:
                        setUnitsAdapter(powerHeaders, from, to);
                        break;
                    case 8:
                        setUnitsAdapter(pressureHeaders, from, to);
                        break;
                    case 9:
                        setUnitsAdapter(speedHeaders, from, to);
                        break;
                    case 10:
                        setUnitsAdapter(timeHeaders, from, to);
                        break;
                    case 11:
                        setUnitsAdapter(torqueHeaders, from, to);
                        break;
                    case 12:
                        setUnitsAdapter(volumeHeaders, from, to);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        unitSpinner.setSelection(preferences.getInt("converterSpinnerPosition", 0));
    }

    public void setUnitsAdapter(String[] headers, final Spinner from, final Spinner to) {
        ArrayAdapter<String> adapterUnits = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, headers);
        adapterUnits.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        from.setAdapter(adapterUnits);
        to.setAdapter(adapterUnits);
    }
}