package com.gigabytedevelopersinc.app.calculator;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.gigabytedevelopersinc.app.calculator.util.AutoResizeTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

public class CurrencyFragment extends Fragment {

    private final static String URL_WEB_SERVICE = "https://api.fixer.io/latest?base=";
    ConversionRate conversionRate;
    String strJson = "{\n" +
            "  \"success\":true,\n" +
            "  \"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\n" +
            "  \"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\n" +
            "  \"timestamp\":1477740732,\n" +
            "  \"source\":\"USD\",\n" +
            "  \"quotes\":{\n" +
            "    \"AED\":3.672704,\n" +
            "    \"AFN\":65.949997,\n" +
            "    \"ALL\":124.180403,\n" +
            "    \"AMD\":474.839996,\n" +
            "    \"ANG\":1.770403,\n" +
            "    \"AOA\":165.070999,\n" +
            "    \"ARS\":15.160402,\n" +
            "    \"AUD\":1.315404,\n" +
            "    \"AWG\":1.79,\n" +
            "    \"AZN\":1.637604,\n" +
            "    \"BAM\":1.783204,\n" +
            "    \"BBD\":2,\n" +
            "    \"BDT\":78.330002,\n" +
            "    \"BGN\":1.790704,\n" +
            "    \"BHD\":0.376704,\n" +
            "    \"BIF\":1663.540039,\n" +
            "    \"BMD\":1,\n" +
            "    \"BND\":1.392304,\n" +
            "    \"BOB\":6.860399,\n" +
            "    \"BRL\":3.201304,\n" +
            "    \"BSD\":1,\n" +
            "    \"BTC\":0.001424,\n" +
            "    \"BTN\":66.849998,\n" +
            "    \"BWP\":10.644604,\n" +
            "    \"BYR\":20020,\n" +
            "    \"BZD\":1.980397,\n" +
            "    \"CAD\":1.338041,\n" +
            "    \"CDF\":959.000362,\n" +
            "    \"CHF\":0.98719,\n" +
            "    \"CLF\":0.02466,\n" +
            "    \"CLP\":653.599976,\n" +
            "    \"CNY\":6.775804,\n" +
            "    \"COP\":2985,\n" +
            "    \"CRC\":547.400024,\n" +
            "    \"CUC\":1,\n" +
            "    \"CUP\":1.00036,\n" +
            "    \"CVE\":100.849998,\n" +
            "    \"CZK\":24.70104,\n" +
            "    \"DJF\":176.399994,\n" +
            "    \"DKK\":6.79969,\n" +
            "    \"DOP\":46.029999,\n" +
            "    \"DZD\":109.650002,\n" +
            "    \"EEK\":14.29104,\n" +
            "    \"EGP\":8.858304,\n" +
            "    \"ERN\":15.640392,\n" +
            "    \"ETB\":22.145901,\n" +
            "    \"EUR\":0.909904,\n" +
            "    \"FJD\":2.057804,\n" +
            "    \"FKP\":0.819704,\n" +
            "    \"GBP\":0.820404,\n" +
            "    \"GEL\":2.323704,\n" +
            "    \"GGP\":0.82021,\n" +
            "    \"GHS\":3.97904,\n" +
            "    \"GIP\":0.82039,\n" +
            "    \"GMD\":41.849998,\n" +
            "    \"GNF\":9100.000355,\n" +
            "    \"GTQ\":7.525504,\n" +
            "    \"GYD\":205.949997,\n" +
            "    \"HKD\":7.753404,\n" +
            "    \"HNL\":22.503841,\n" +
            "    \"HRK\":6.826204,\n" +
            "    \"HTG\":63.320388,\n" +
            "    \"HUF\":283.000354,\n" +
            "    \"IDR\":13047,\n" +
            "    \"ILS\":3.846404,\n" +
            "    \"IMP\":0.82021,\n" +
            "    \"INR\":66.795998,\n" +
            "    \"IQD\":1165,\n" +
            "    \"IRR\":31751.000352,\n" +
            "    \"ISK\":113.419998,\n" +
            "    \"JEP\":0.82021,\n" +
            "    \"JMD\":128.800003,\n" +
            "    \"JOD\":0.707704,\n" +
            "    \"JPY\":104.69404,\n" +
            "    \"KES\":101.349998,\n" +
            "    \"KGS\":68.628998,\n" +
            "    \"KHR\":4036.000351,\n" +
            "    \"KMF\":438.399994,\n" +
            "    \"KPW\":900.00035,\n" +
            "    \"KRW\":1146.599976,\n" +
            "    \"KWD\":0.302039,\n" +
            "    \"KYD\":0.820383,\n" +
            "    \"KZT\":334.000349,\n" +
            "    \"LAK\":8146.000349,\n" +
            "    \"LBP\":1506.503779,\n" +
            "    \"LKR\":147.750382,\n" +
            "    \"LRD\":90.000348,\n" +
            "    \"LSL\":14.000348,\n" +
            "    \"LTL\":3.048704,\n" +
            "    \"LVL\":0.62055,\n" +
            "    \"LYD\":1.403404,\n" +
            "    \"MAD\":9.848504,\n" +
            "    \"MDL\":20.000347,\n" +
            "    \"MGA\":3190.000347,\n" +
            "    \"MKD\":56.040001,\n" +
            "    \"MMK\":1286.000346,\n" +
            "    \"MNT\":2263.000346,\n" +
            "    \"MOP\":7.986039,\n" +
            "    \"MRO\":355.000346,\n" +
            "    \"MUR\":35.750379,\n" +
            "    \"MVR\":15.103741,\n" +
            "    \"MWK\":712.51001,\n" +
            "    \"MXN\":18.961599,\n" +
            "    \"MYR\":4.197039,\n" +
            "    \"MZN\":76.820377,\n" +
            "    \"NAD\":13.875039,\n" +
            "    \"NGN\":304.503727,\n" +
            "    \"NIO\":29.075104,\n" +
            "    \"NOK\":8.270377,\n" +
            "    \"NPR\":106.750376,\n" +
            "    \"NZD\":1.395404,\n" +
            "    \"OMR\":0.384604,\n" +
            "    \"PAB\":1,\n" +
            "    \"PEN\":3.364039,\n" +
            "    \"PGK\":3.167104,\n" +
            "    \"PHP\":48.470001,\n" +
            "    \"PKR\":104.739998,\n" +
            "    \"PLN\":3.948804,\n" +
            "    \"PYG\":5727.000341,\n" +
            "    \"QAR\":3.641038,\n" +
            "    \"RON\":4.111904,\n" +
            "    \"RSD\":112.577797,\n" +
            "    \"RUB\":62.955002,\n" +
            "    \"RWF\":805.47998,\n" +
            "    \"SAR\":3.749904,\n" +
            "    \"SBD\":7.853038,\n" +
            "    \"SCR\":13.182038,\n" +
            "    \"SDG\":6.353504,\n" +
            "    \"SEK\":8.995104,\n" +
            "    \"SGD\":1.390904,\n" +
            "    \"SHP\":0.820371,\n" +
            "    \"SLL\":5600.000339,\n" +
            "    \"SOS\":559.000338,\n" +
            "    \"SRD\":6.850371,\n" +
            "    \"STD\":22464,\n" +
            "    \"SVC\":8.722204,\n" +
            "    \"SYP\":517.000338,\n" +
            "    \"SZL\":13.807904,\n" +
            "    \"THB\":35.02037,\n" +
            "    \"TJS\":7.875404,\n" +
            "    \"TMT\":3.41,\n" +
            "    \"TND\":2.253038,\n" +
            "    \"TOP\":2.244204,\n" +
            "    \"TRY\":3.111704,\n" +
            "    \"TTD\":6.679504,\n" +
            "    \"TWD\":31.606001,\n" +
            "    \"TZS\":2179.000336,\n" +
            "    \"UAH\":25.469999,\n" +
            "    \"UGX\":3458.000335,\n" +
            "    \"USD\":1,\n" +
            "    \"UYU\":28.170367,\n" +
            "    \"UZS\":3065.000335,\n" +
            "    \"VEF\":9.975038,\n" +
            "    \"VND\":22320,\n" +
            "    \"VUV\":106.050003,\n" +
            "    \"WST\":2.556404,\n" +
            "    \"XAF\":596.690002,\n" +
            "    \"XAG\":0.056306,\n" +
            "    \"XAU\":0.000756,\n" +
            "    \"XCD\":2.703606,\n" +
            "    \"XDR\":0.729018,\n" +
            "    \"XOF\":600.049988,\n" +
            "    \"XPF\":108.629997,\n" +
            "    \"YER\":249.899994,\n" +
            "    \"ZAR\":13.870364,\n" +
            "    \"ZMK\":5156.103593,\n" +
            "    \"ZMW\":9.780363,\n" +
            "    \"ZWL\":322.355011\n" +
            "  }\n" +
            "}";
    private AutoResizeTextView result_TV, resultant_TV;
    private Spinner fro, too;

    public CurrencyFragment() {
    }

    public static android.support.v4.app.Fragment newInstance() {
        return new CurrencyFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_currency, container, false);
        result_TV = (AutoResizeTextView) root.findViewById(R.id.curr_result_TV);
        resultant_TV = (AutoResizeTextView) root.findViewById(R.id.curr_resultant_TV);
        conversionRate = new ConversionRate();

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fro = (Spinner) view.findViewById(R.id.fro_spinner);
        too = (Spinner) view.findViewById(R.id.too_spinner);

        ArrayAdapter<String> froAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.currency_spinner_item, getResources().getStringArray(R.array.currency_exp));
        froAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fro.setAdapter(froAdapter);
        too.setAdapter(froAdapter);
        fro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int numFromCurrency = fro.getSelectedItemPosition();
                int numToCurrency = too.getSelectedItemPosition();
                if (numFromCurrency != numToCurrency)
                    getRate();
                else {
                    resultant_TV.setText("1");
                    SharedPreferences settings = getActivity().getSharedPreferences("MyPrefs", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putFloat("rate", 1);
                    editor.apply();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        too.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int numFromCurrency = fro.getSelectedItemPosition();
                int numToCurrency = too.getSelectedItemPosition();
                if (numFromCurrency != numToCurrency)
                    getRate();
                else {
                    resultant_TV.setText("1");
                    SharedPreferences settings = getActivity().getSharedPreferences("MyPrefs", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putFloat("rate", 1);
                    editor.apply();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private String connection(String str) {
        String result = null;
        try {
            URL url = new URL(str);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader in =new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"), 8);
            Scanner s = new Scanner(in).useDelimiter("\\A");
            result = s.hasNext() ? s.next() : "";
            JSONObject jsonObject = new JSONObject(new JSONObject(result).getString("rates"));
            Iterator<String> keys = jsonObject.keys();
            String str_Name = keys.next();
            result = new JSONObject(jsonObject.toString()).getString(str_Name);
            Log.e("Result", result);
        } catch (Exception ex) {
            Log.e("Result", ex.toString());
        }
        return result;
    }

    private void getRate() {
        conversionRate = new ConversionRate();
        conversionRate.execute(getUrl());
    }

    private String checkConversionResult(String str) {
        return (str.equals("-1") || str.equals("") || str == null) ? getString(R.string.no_data) : str;
    }

    private String getUrl() {
        StringBuilder url = new StringBuilder();

        url.append(URL_WEB_SERVICE);

        int numFromCurrency = fro.getSelectedItemPosition();
        int numToCurrency = too.getSelectedItemPosition();

        url.append(getResources().getStringArray(R.array.currency_exp)[numFromCurrency].substring(0, 3));
        url.append("&symbols=").append(getResources().getStringArray(R.array.currency_exp)[numFromCurrency].substring(0, 3));
        url.append(",").append(getResources().getStringArray(R.array.currency_exp)[numToCurrency].substring(0, 3));
        Log.e("URL", url.toString());

        return url.toString();
    }

    private class ConversionRate extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            return connection(params[0]);
        }

        @Override
        protected  void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String str) {
            try {
                result_TV.setText("1");
                resultant_TV.setText(String.valueOf(checkConversionResult(str)));
                SharedPreferences settings = getActivity().getSharedPreferences("MyPrefs", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putFloat("rate", Float.parseFloat(checkConversionResult(str)));
                editor.apply();
            } catch (Exception ex) {
                int numFromCurrency = fro.getSelectedItemPosition();
                int numToCurrency = too.getSelectedItemPosition();
                try {
                    JSONObject jsonRootObject = new JSONObject(URL_WEB_SERVICE);
                    JSONObject jsonObject = jsonRootObject.optJSONObject("rates");

                    float From = Float.parseFloat(jsonObject.optString(getResources().getStringArray(R.array.currency_exp)[numFromCurrency].substring(0, 3)));
                    float To = Float.parseFloat(jsonObject.optString(getResources().getStringArray(R.array.currency_exp)[numToCurrency].substring(0, 3)));
                    float rate = To / From;
                    result_TV.setText("1");
                    resultant_TV.setText(String.valueOf(rate));
                    SharedPreferences settings = getActivity().getSharedPreferences("MyPrefs", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putFloat("rate", rate);
                    editor.apply();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected void onPreExecute() {
            Toast toast = Toast.makeText(getActivity(), "Updating Exchange Rates", Toast.LENGTH_SHORT);
            toast.show();
            super.onPreExecute();
        }
    }
}