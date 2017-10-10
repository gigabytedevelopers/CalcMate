package com.gigabytedevelopersinc.app.calculator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;

public class UnitsFragment extends Fragment {

    public UnitsFragment() {
    }

    public static android.support.v4.app.Fragment newInstance() {
        return new UnitsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_units, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Spinner unitSpinner = (Spinner) view.findViewById(R.id.units_spinner);
        String[] unitsArray = {"Area", "Cooking", "Digital Storage", "Energy", "Fuel Consumption",
                "Length / Distance", "Mass / Weight", "Power", "Pressure", "Speed",
                "Time", "Torque", "Volume"};

        final TextView[] headers = {(TextView) view.findViewById(R.id.unit_header1),
                (TextView) view.findViewById(R.id.unit_header2),
                (TextView) view.findViewById(R.id.unit_header3),
                (TextView) view.findViewById(R.id.unit_header4),
                (TextView) view.findViewById(R.id.unit_header5),
                (TextView) view.findViewById(R.id.unit_header6),
                (TextView) view.findViewById(R.id.unit_header7),
                (TextView) view.findViewById(R.id.unit_header8),
                (TextView) view.findViewById(R.id.unit_header9),
                (TextView) view.findViewById(R.id.unit_header10),
                (TextView) view.findViewById(R.id.unit_header11),
                (TextView) view.findViewById(R.id.unit_header12),
                (TextView) view.findViewById(R.id.unit_header13),
                (TextView) view.findViewById(R.id.unit_header14),
                (TextView) view.findViewById(R.id.unit_header15),
                (TextView) view.findViewById(R.id.unit_header16),
                (TextView) view.findViewById(R.id.unit_header17),
                (TextView) view.findViewById(R.id.unit_header18),
                (TextView) view.findViewById(R.id.unit_header19),
                (TextView) view.findViewById(R.id.unit_header20)};

        final EditText[] values = {(EditText) view.findViewById(R.id.unit_value1),
                (EditText) view.findViewById(R.id.unit_value2),
                (EditText) view.findViewById(R.id.unit_value3),
                (EditText) view.findViewById(R.id.unit_value4),
                (EditText) view.findViewById(R.id.unit_value5),
                (EditText) view.findViewById(R.id.unit_value6),
                (EditText) view.findViewById(R.id.unit_value7),
                (EditText) view.findViewById(R.id.unit_value8),
                (EditText) view.findViewById(R.id.unit_value9),
                (EditText) view.findViewById(R.id.unit_value10),
                (EditText) view.findViewById(R.id.unit_value11),
                (EditText) view.findViewById(R.id.unit_value12),
                (EditText) view.findViewById(R.id.unit_value13),
                (EditText) view.findViewById(R.id.unit_value14),
                (EditText) view.findViewById(R.id.unit_value15),
                (EditText) view.findViewById(R.id.unit_value16),
                (EditText) view.findViewById(R.id.unit_value17),
                (EditText) view.findViewById(R.id.unit_value18),
                (EditText) view.findViewById(R.id.unit_value19),
                (EditText) view.findViewById(R.id.unit_value20)};

        final String[] areaHeaders = {getResources().getString(R.string.sq_kilometre),
                getResources().getString(R.string.sq_metre),
                getResources().getString(R.string.sq_centimetre),
                getResources().getString(R.string.hectare),
                getResources().getString(R.string.sq_mile),
                getResources().getString(R.string.sq_yard),
                getResources().getString(R.string.sq_foot),
                getResources().getString(R.string.sq_inch),
                getResources().getString(R.string.acre)};

        final double[][] areaValues = {{1000000.0, 0.000001}, {1.0, 1.0}, {0.0001, 10000.0}, {10000.0, 0.0001},
                {2589988.110336, 0.000000386102158542445847}, {0.83612736, 1.19599004630108026},
                {0.09290304, 10.7639104167097223}, {0.00064516, 1550.00310000620001},
                {4046.8564224, 0.000247105381467165342}};

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

        final double[][] cookingValues = {{0.0000049289215938, 202884.136211058},
                {0.0000147867647812, 67628.045403686},
                {0.0002365882365, 4226.7528377304},
                {0.0000295735295625, 33814.0227018429972},
                {0.0000284130625, 35195.07972785404600437},
                {0.000473176473, 2113.37641886518732},
                {0.00056826125, 1759.753986392702300218},
                {0.000946352946, 1056.68820943259366},
                {0.0011365225, 879.8769931963511501092},
                {0.003785411784, 264.172052358148415},
                {0.00454609, 219.9692482990877875273},
                {0.000001, 1000000.0},
                {0.001, 1000.0}};

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

        final double[][] digitalValues = {{0.00000011920928955078, 8388608.0},
                {0.00000095367431640625, 1048576.0},
                {0.0001220703125, 8192.0},
                {0.0009765625, 1024.0},
                {0.125, 8.0},
                {1.0, 1.0},
                {128.0, 0.0078125},
                {1024.0, 0.0009765625},
                {131072.0, 0.00000762939453125},
                {1048576.0, 0.00000095367431640625}};

        final String[] energyHeaders = {getResources().getString(R.string.joule),
                getResources().getString(R.string.kilojoule),
                getResources().getString(R.string.calorie),
                getResources().getString(R.string.kilocalorie),
                getResources().getString(R.string.btu),
                getResources().getString(R.string.ft_lbF),
                getResources().getString(R.string.in_lbF),
                getResources().getString(R.string.kilowatt_hour),
                getResources().getString(R.string.electron_volt)};

        final double[][] energyValues = {{1.0, 1.0},
                {1000.0, 0.001},
                {4.184, 0.2390057361376673040153},
                {4184.0, 0.0002390057361376673040153},
                {1055.05585262, 0.0009478171203133172000128},
                {1.3558179483314004, 0.7375621494575464935503},
                {0.1129848290276167, 8.850745793490557922604},
                {3600000.0, 0.0000002777777777777777777778},
                {0.000000000000000000160217653, 6241509479607718382.9424839}};

        final String[] fuelHeaders = {getResources().getString(R.string.mpg_us),
                getResources().getString(R.string.mpg_uk),
                getResources().getString(R.string.l_100k),
                getResources().getString(R.string.km_l),
                getResources().getString(R.string.miles_l)};

        final double[][] fuelValues = {{1.0, 1.0},
                {0.83267418460479, 1.2009499255398},
                {235.214582, 235.214582},
                {2.352145833, 0.42514370749052},
                {3.7854118, 0.264172052}};

        final String[] lengthHeaders = {getResources().getString(R.string.kilometre),
                getResources().getString(R.string.mile),
                getResources().getString(R.string.metre),
                getResources().getString(R.string.centimetre),
                getResources().getString(R.string.millimetre),
                getResources().getString(R.string.micrometre),
                getResources().getString(R.string.nanometre),
                getResources().getString(R.string.yard),
                getResources().getString(R.string.feet),
                getResources().getString(R.string.inch),
                getResources().getString(R.string.nautical_mile),
                getResources().getString(R.string.furlong),
                getResources().getString(R.string.light_year)};

        final double[][] lengthValues = {{1000.0, 0.001},
                {1609.344, 0.00062137119223733397},
                {1.0, 1.0},
                {0.01, 100.0},
                {0.001, 1000.0},
                {0.000001, 1000000.0},
                {0.000000001, 1000000000.0},
                {0.9144, 1.09361329833770779},
                {0.3048, 3.28083989501312336},
                {0.0254, 39.3700787401574803},
                {1852.0, 0.000539956803455723542},
                {201.168, 0.0049709695379},
                {9460730472580800.0, 0.0000000000000001057000834024615463709}};

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

        final double[][] massValues = {{1.0, 1.0},
                {0.45359237, 2.20462262184877581},
                {0.001, 1000.0},
                {0.000001, 1000000.0},
                {0.028349523125, 35.27396194958041291568},
                {0.00006479891, 15432.35835294143065061},
                {6.35029318, 0.15747304441777},
                {1000.0, 0.001},
                {907.18474, 0.0011023113109243879},
                {1016.0469088, 0.0009842065276110606282276}};

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

        final double[][] powerValues = {{1.0, 1.0},
                {1000.0, 0.001},
                {1000000.0, 0.000001},
                {735.49875, 0.00135962161730390432},
                {745.69987158227022, 0.00134102208959502793},
                {1.3558179483314004, 0.737562149277265364},
                {4.1868, 0.23884589662749594},
                {1055.05585262, 0.0009478171203133172},
                {1000.0, 0.001},
                {0.000000000000000000160217653, 6241509479607718382.9424839}};

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

        final double[][] pressureValues = {{1000000.0, 0.000001},
                {1000.0, 0.001},
                {1.0, 1.0},
                {100000.0, 0.00001},
                {6894.757293168361, 0.000145037737730209222},
                {47.880258980335840277777777778, 0.020885434233150127968},
                {101325.0, 0.0000098692326671601283},
                {98066.5, 0.0000101971621297792824257},
                {133.322387415, 0.007500615758456563339513},
                {133.3223684210526315789, 0.00750061682704169751}};

        final String[] speedHeaders = {getResources().getString(R.string.km_h),
                getResources().getString(R.string.mph),
                getResources().getString(R.string.m_s),
                getResources().getString(R.string.fps),
                getResources().getString(R.string.knot)};

        final double[][] speedValues = {{0.27777777777778, 3.6},
                {0.44704, 2.2369362920544},
                {1.0, 1.0},
                {0.3048, 3.2808398950131},
                {0.51444444444444, 1.9438444924406}};

        final String[] timeHeaders = {getResources().getString(R.string.year),
                getResources().getString(R.string.month),
                getResources().getString(R.string.week),
                getResources().getString(R.string.day),
                getResources().getString(R.string.hour),
                getResources().getString(R.string.minute),
                getResources().getString(R.string.second),
                getResources().getString(R.string.millisecond),
                getResources().getString(R.string.nanosecond)};

        final double[][] timeValues = {{31556900.0, 0.000000031688791},
                {2628000.0, 0.0000003805175},
                {604800.0, 0.00000165343915343915344},
                {86400.0, 0.0000115740740740740741},
                {3600.0, 0.000277777777777777778},
                {60.0, 0.0166666666666666667},
                {1.0, 1.0},
                {0.001, 1000.0},
                {0.000000001, 1000000000.0}};

        final String[] torqueHeaders = {getResources().getString(R.string.n_m),
                getResources().getString(R.string.ft_lbF),
                getResources().getString(R.string.in_lbF)};

        final double[][] torqueValues = {{1.0, 1.0},
                {1.3558179483314004, 0.7375621494575464935503},
                {0.1129848290276167, 8.850745793490557922604}};

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

        final double[][] volumeValues = {{0.0000049289215938, 202884.136211058},
                {0.0000147867647812, 67628.045403686},
                {0.0002365882365, 4226.7528377304},
                {0.0000295735295625, 33814.0227018429972},
                {0.0000284130625, 35195.07972785404600437},
                {0.000473176473, 2113.37641886518732},
                {0.00056826125, 1759.753986392702300218},
                {0.000946352946, 1056.68820943259366},
                {0.0011365225, 879.8769931963511501092},
                {0.003785411784, 264.172052358148415},
                {0.00454609, 219.9692482990877875273},
                {0.119240471196, 8.38641436057614017079},
                {0.16365924, 6.11025689719688298687},
                {0.000001, 1000000.0},
                {0.001, 1000.0},
                {0.000001, 1000000.0},
                {1.0, 1.0},
                {0.000016387064, 61023.744094732284},
                {0.028316846592, 35.3146667214885903},
                {0.7645548692741148, 1.3079506}};

        final TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int i = 0;
                while (s != values[i].getEditableText()) {
                    i++;
                }

                if (values[i].isFocused()) {
                    int length;
                    double[][] unitValues = new double[20][];
                    switch (unitSpinner.getSelectedItemPosition()) {
                        case 0:
                            length = areaHeaders.length;
                            System.arraycopy(areaValues, 0, unitValues, 0, areaValues.length);
                            break;
                        case 1:
                            length = cookingHeaders.length;
                            System.arraycopy(cookingValues, 0, unitValues, 0, cookingValues.length);
                            break;
                        case 2:
                            length = digitalHeaders.length;
                            System.arraycopy(digitalValues, 0, unitValues, 0, digitalValues.length);
                            break;
                        case 3:
                            length = energyHeaders.length;
                            System.arraycopy(energyValues, 0, unitValues, 0, energyValues.length);
                            break;
                        case 4:
                            length = fuelHeaders.length;
                            System.arraycopy(fuelValues, 0, unitValues, 0, fuelValues.length);
                            break;
                        case 5:
                            length = lengthHeaders.length;
                            System.arraycopy(lengthValues, 0, unitValues, 0, lengthValues.length);
                            break;
                        case 6:
                            length = massHeaders.length;
                            System.arraycopy(massValues, 0, unitValues, 0, massValues.length);
                            break;
                        case 7:
                            length = powerHeaders.length;
                            System.arraycopy(powerValues, 0, unitValues, 0, powerValues.length);
                            break;
                        case 8:
                            length = pressureHeaders.length;
                            System.arraycopy(pressureValues, 0, unitValues, 0, pressureValues.length);
                            break;
                        case 9:
                            length = speedHeaders.length;
                            System.arraycopy(speedValues, 0, unitValues, 0, speedValues.length);
                            break;
                        case 10:
                            length = timeHeaders.length;
                            System.arraycopy(timeValues, 0, unitValues, 0, timeValues.length);
                            break;
                        case 11:
                            length = torqueHeaders.length;
                            System.arraycopy(torqueValues, 0, unitValues, 0, torqueValues.length);
                            break;
                        case 12:
                            length = volumeHeaders.length;
                            System.arraycopy(volumeValues, 0, unitValues, 0, volumeValues.length);
                            break;
                        default:
                            length = areaHeaders.length;
                            System.arraycopy(areaValues, 0, unitValues, 0, areaValues.length);
                            break;
                    }

                    String value = values[i].getText().toString();
                    try {
                        int j = 0;
                        while (j != i) {
                            BigDecimal multiplier = new BigDecimal(unitValues[j][1]).multiply(new BigDecimal(unitValues[i][0]));
                            BigDecimal bdResult = new BigDecimal(Double.parseDouble(value)).multiply(multiplier);
                            double result = bdResult.doubleValue();
                            if (!values[j].getText().toString().equals(String.valueOf(result)))
                                values[j].setText(String.valueOf(result));
                            j++;
                        }
                        j++;
                        while (j < length) {
                            BigDecimal multiplier = new BigDecimal(unitValues[j][1]).multiply(new BigDecimal(unitValues[i][0]));
                            BigDecimal bdResult = new BigDecimal(Double.parseDouble(value)).multiply(multiplier);
                            double result = bdResult.doubleValue();
                            if (!values[j].getText().toString().equals(String.valueOf(result)))
                                values[j].setText(String.valueOf(result));
                            j++;
                        }
                    } catch (Exception e) {
                        //
                    }
                }
            }
        };

        ArrayAdapter<String> adapterUnits = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, unitsArray);
        adapterUnits.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(adapterUnits);
        unitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unitSpinner.setSelection(position);
                switch (((int) id)) {
                    case 0:
                        setLayout(areaHeaders, headers, values, textWatcher);
                        break;
                    case 1:
                        setLayout(cookingHeaders, headers, values, textWatcher);
                        break;
                    case 2:
                        setLayout(digitalHeaders, headers, values, textWatcher);
                        break;
                    case 3:
                        setLayout(energyHeaders, headers, values, textWatcher);
                        break;
                    case 4:
                        setLayout(fuelHeaders, headers, values, textWatcher);
                        break;
                    case 5:
                        setLayout(lengthHeaders, headers, values, textWatcher);
                        break;
                    case 6:
                        setLayout(massHeaders, headers, values, textWatcher);
                        break;
                    case 7:
                        setLayout(powerHeaders, headers, values, textWatcher);
                        break;
                    case 8:
                        setLayout(pressureHeaders, headers, values, textWatcher);
                        break;
                    case 9:
                        setLayout(speedHeaders, headers, values, textWatcher);
                        break;
                    case 10:
                        setLayout(timeHeaders, headers, values, textWatcher);
                        break;
                    case 11:
                        setLayout(torqueHeaders, headers, values, textWatcher);
                        break;
                    case 12:
                        setLayout(volumeHeaders, headers, values, textWatcher);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setLayout(String[] s, TextView[] t, EditText[] e, TextWatcher tw) {
        int i = 0;
        while (i < s.length) {
            t[i].setVisibility(View.VISIBLE);
            t[i].setText(s[i]);
            e[i].setVisibility(View.VISIBLE);
            e[i].addTextChangedListener(tw);
            i++;
        }
        while (i < 20) {
            t[i].setVisibility(View.GONE);
            e[i].setVisibility(View.GONE);
            i++;
        }
    }

    /*private void getTemperatureConversions() {
        List<Unit> units = new ArrayList<Unit>();
        units.add(new TemperatureUnit(CELSIUS, R.string.celsius));
        units.add(new TemperatureUnit(FAHRENHEIT, R.string.fahrenheit));
        units.add(new TemperatureUnit(KELVIN, R.string.kelvin));
        units.add(new TemperatureUnit(RANKINE, R.string.rankine));
        units.add(new TemperatureUnit(DELISLE, R.string.delisle));
        units.add(new TemperatureUnit(NEWTON, R.string.newton));
        units.add(new TemperatureUnit(REAUMUR, R.string.reaumur));
        units.add(new TemperatureUnit(ROMER, R.string.romer));
        units.add(new TemperatureUnit(GAS_MARK, R.string.gas_mark));
        addConversion(Conversion.TEMPERATURE, new Conversion(Conversion.TEMPERATURE, R.string.temperature, units));
    }*/
}
