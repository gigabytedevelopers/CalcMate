package com.gigabytedevelopersinc.app.calculator.fragments;

import android.content.Context;
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
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.gigabytedevelopersinc.app.calculator.R;
import com.gigabytedevelopersinc.app.calculator.util.AutoResizeTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

public class CurrencyFragment extends Fragment {
    private AutoResizeTextView result_TV, resultant_TV;
    private Spinner fro, too;
    private Button convertBtn;
    private Double convertDouble;
    private int numFromCurrency;
    private int numToCurrency;
    String fromString;
    String toString;

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
        convertBtn = (Button) root.findViewById(R.id.equals_Btn);

        fro = (Spinner) root.findViewById(R.id.fro_spinner);
        too = (Spinner) root.findViewById(R.id.too_spinner);

        ArrayAdapter<String> froAdapter = new ArrayAdapter<>(getActivity(),
                R.layout.currency_spinner_item, getResources().getStringArray(R.array.currency_exp));
        froAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fro.setAdapter(froAdapter);
        too.setAdapter(froAdapter);
        result_TV.setText("1");

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crashlytics.getInstance().crash(); // Force a crash
                resultant_TV.setText(R.string.currency_process);
                String convertValue = result_TV.getText().toString();
                convertDouble = Double.parseDouble(convertValue);

                numFromCurrency = fro.getSelectedItemPosition();
                numToCurrency = too.getSelectedItemPosition();


                if (numFromCurrency != numToCurrency) {
                    String getFromString = getResources().getStringArray(R.array.currency_exp)[numFromCurrency].substring(0, 3);
                    String getToString = getResources().getStringArray(R.array.currency_exp)[numToCurrency].substring(0, 3);
                    ConversionRate conversionRate = new ConversionRate();
                    conversionRate.execute(getFromString, getToString);
                } else {
                    resultant_TV.setText("1");
                    SharedPreferences settings = getActivity().getSharedPreferences("MyPrefs", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putFloat("rate", 1);
                    editor.apply();
                }
            }
        });


        fro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromString = fro.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        too.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toString = too.getSelectedItem().toString();
                resultant_TV.setText(R.string.currency_process);
                String convertValue = result_TV.getText().toString();
                convertDouble = Double.parseDouble(convertValue);

                numFromCurrency = fro.getSelectedItemPosition();
                numToCurrency = too.getSelectedItemPosition();


                if (numFromCurrency != numToCurrency) {
                    String getFromString = getResources().getStringArray(R.array.currency_exp)[numFromCurrency].substring(0, 3);
                    String getToString = getResources().getStringArray(R.array.currency_exp)[numToCurrency].substring(0, 3);
                    ConversionRate conversionRate = new ConversionRate();
                    conversionRate.execute(getFromString, getToString);
                } else {
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

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private class ConversionRate extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String fromString = strings[0];
            String toString = strings[1];

            String jsonUrl = "https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency="+fromString+"&to_currency="+toString+"&apikey=F3ON8QP7H4Z4K3Q1";

            try {
                URL jsonConnect = new URL(jsonUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) jsonConnect.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"), 8);
                String response = " ";
                String line = "";
                while ((line = reader.readLine()) != null) {
                    response += line;
                }
                reader.close();
                httpURLConnection.disconnect();
                inputStream.close();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException io) {
                Log.w("Caught Exception", io);
            }
            return null;
        }

        @Override
        protected  void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                String exResult = jsonObject.getJSONObject("Realtime Currency Exchange Rate").getString("5. Exchange Rate");
                double conversionDouble = Double.parseDouble(exResult);
                double conversion = conversionDouble * convertDouble;
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(5);

                resultant_TV.setText(df.format(conversion));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (NullPointerException npe) {
                Toast.makeText(getContext(), "Sorry! You must be connected to the internet to convert", Toast.LENGTH_LONG).show();
                Crashlytics.logException(npe);
                resultant_TV.setText(R.string.currency_connection_error);
            } catch (NumberFormatException nfe) {
                Toast.makeText(getContext(), "Sorry! You must enter a number to convert", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {
            Toast toast = Toast.makeText(getActivity(), "Updating Exchange Rates", Toast.LENGTH_SHORT);
            toast.show();
            super.onPreExecute();
        }
    }
    @Override
    public void onPause(){
        super.onPause();
        SharedPreferences preferences = getActivity().getSharedPreferences("pref_name", Context.MODE_PRIVATE);
        preferences.edit().putInt("spinner_index", fro.getSelectedItemPosition()).apply();
        preferences.edit().putInt("spinner_indexes", too.getSelectedItemPosition()).apply();
    }
    @Override
    public void onResume(){
        super.onResume();
        SharedPreferences preferences = getActivity().getSharedPreferences("pref_name", Context.MODE_PRIVATE);
        int spinnerIndx = preferences.getInt("spinner_index", 0);
        int spinnerIndxes = preferences.getInt("spinner_indexes", 0);
        fro.setSelection(spinnerIndx);
        too.setSelection(spinnerIndxes);
    }
}