package com.gigabytedevelopersinc.app.calculator;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
//import android.os.Build;
import android.os.Bundle;
//import android.os.Handler;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
//import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
//import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
//import android.widget.TextView;
import android.widget.Toast;

//import com.afollestad.appthemeengine.ATE;
//import com.afollestad.appthemeengine.Config;
//import com.mikepenz.aboutlibraries.Libs;
//import com.mikepenz.aboutlibraries.LibsBuilder;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
//import com.gigabytedevelopersinc.app.calculator.CurrencyFragment;
import com.gigabytedevelopersinc.app.calculator.util.AutoResizeTextView;
import com.gigabytedevelopersinc.app.calculator.util.PagesBuilder;
//import com.gigabytedevelopersinc.app.calculator.util.ZoomOutPageTransformer;
import com.xlythe.math.Base;
import com.xlythe.math.Constants;
import com.xlythe.math.Solver;

import org.javia.arity.SyntaxException;

//import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Locale;

import javax.sql.DataSource;

//import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final char SELECTION_HANDLE = '\u2620';
    final double[][] areaValues = {{1000000.0, 0.000001}, {1.0, 1.0}, {0.0001, 10000.0}, {10000.0, 0.0001},
            {2589988.110336, 0.000000386102158542445847}, {0.83612736, 1.19599004630108026},
            {0.09290304, 10.7639104167097223}, {0.00064516, 1550.00310000620001},
            {4046.8564224, 0.000247105381467165342}};
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
    final double[][] energyValues = {{1.0, 1.0},
            {1000.0, 0.001},
            {4.184, 0.2390057361376673040153},
            {4184.0, 0.0002390057361376673040153},
            {1055.05585262, 0.0009478171203133172000128},
            {1.3558179483314004, 0.7375621494575464935503},
            {0.1129848290276167, 8.850745793490557922604},
            {3600000.0, 0.0000002777777777777777777778},
            {0.000000000000000000160217653, 6241509479607718382.9424839}};
    final double[][] fuelValues = {{1.0, 1.0},
            {0.83267418460479, 1.2009499255398},
            {235.214582, 235.214582},
            {2.352145833, 0.42514370749052},
            {3.7854118, 0.264172052}};
    final double[][] lengthValues = {{1000.0, 0.001},
            {1609.344, 0.00062137119223733397},
            {1.0, 1.0},
            {0.01, 100.0},
            {0.001, 1000.0},
            {0.000001, 1000000.0},
            {0.000000001, 1000000000.0},
            {0.9144, 1.09361329833770779},
            {0.3048, 3.28083989501312336},
            {0, 0},
            {0.0254, 39.3700787401574803},
            {1852.0, 0.000539956803455723542},
            {201.168, 0.0049709695379},
            {9460730472580800.0, 0.0000000000000001057000834024615463709}};
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
    final double[][] speedValues = {{0.27777777777778, 3.6},
            {0.44704, 2.2369362920544},
            {1.0, 1.0},
            {0.3048, 3.2808398950131},
            {0.51444444444444, 1.9438444924406}};
    final double[][] timeValues = {{31556900.0, 0.000000031688791},
            {2628000.0, 0.0000003805175},
            {604800.0, 0.00000165343915343915344},
            {86400.0, 0.0000115740740740740741},
            {3600.0, 0.000277777777777777778},
            {60.0, 0.0166666666666666667},
            {1.0, 1.0},
            {0.001, 1000.0},
            {0.000000001, 1000000000.0}};
    final double[][] torqueValues = {{1.0, 1.0},
            {1.3558179483314004, 0.7375621494575464935503},
            {0.1129848290276167, 8.850745793490557922604}};
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
    private final String REGEX_NUMBER = Constants.REGEX_NUMBER.substring(0, Constants.REGEX_NUMBER.length() - 1) + SELECTION_HANDLE + "]";
    private final String REGEX_NOT_NUMBER = Constants.REGEX_NOT_NUMBER.substring(0, Constants.REGEX_NOT_NUMBER.length() - 1) + SELECTION_HANDLE + "]";
    public boolean flag;
    int page_no;
    Button hyBtn, myBtn, favBtn;
    ListView historyList, memoryList, favList;
    LinearLayout historyLayout, fav_List_LL, memoryLayout, simplePad;
    EditText expression_TV;
    AutoResizeTextView result_TV, resultant_TV;
    String expression, result;
    Spinner unitSpinner, from, to, fro, too;
    long ans = 0, a = 0, b = 0;
    int operator1 = 0, operator2 = 0, count = 0;
    boolean done = false;
    String x = "", y = "";
    int primary_color, sec_color, primary_text_color, sec_text_color;

    int REQUEST_INVITE = 0;
    private TabLayout mTabs;
    private PagesBuilder mPages;
    private ViewPager mPager;
    //private DbAdapter dbAdapter;
    private DataSource dataSource;
    //private DataBaseHelper dataBaseHelper;
    private SharedPreferences mPrefs;
    private boolean format_bool, vibrate;
    private String num_deci;
    private String group_separator;
    private String decimal_separator;
    private DecimalFormat formatter = new DecimalFormat();
    private DecimalFormatSymbols symbols;
    private Base base = Base.DECIMAL;

    private void addTab(Drawable icon) {
        assert mTabs != null;
        TabLayout.Tab tab = mTabs.newTab().setIcon(icon);
        mTabs.addTab(tab);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_currency);

        mPages = new PagesBuilder(1);
        mPages.add(new PagesBuilder.Page(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_attach_money).color(sec_text_color), new CurrencyFragment()));
        assert mTabs != null;
        mTabs.setTabMode(TabLayout.MODE_FIXED);
        mTabs.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mPager));
        /*mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabs) {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                String name;
                switch (position) {
                    case 0:
                        name = "STANDARD";
                        break;
                    case 1:
                        name = "PROGRAMMER";
                        break;
                    case 2:
                        name = "CURRENCY";
                        break;
                    case 3:
                        name = "CONVERTER";
                        break;
                    case 4:
                        name = "UNIT";
                        break;
                    case 5:
                        name = "DATE";
                        break;
                    case 6:
                        name = "EXTRAS";
                        break;
                    default:
                        name = "Calculator Begins";
                }
            }*/
    }

    @TargetApi(23)
    public boolean getDrawPermit() {
        if (!Settings.canDrawOverlays(MainActivity.this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 1234);
            return false;
        } else
            return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void BtnClick(final View v) {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrate)
            vibrator.vibrate(30);

        page_no = mPager.getCurrentItem();
        switch (page_no) {
            /*case 0:
                result_TV = (AutoResizeTextView) findViewById(R.id.sci_result_TV);
                expression_TV = (EditText) findViewById(R.id.sci_expression_TV);
                break;
            case 1:
                result_TV = (AutoResizeTextView) findViewById(R.id.pro_result_TV);
                expression_TV = (EditText) findViewById(R.id.pro_expression_TV);
                break;*/
            case 2:
                result_TV = (AutoResizeTextView) findViewById(R.id.curr_result_TV);
                expression_TV = (EditText) findViewById(R.id.curr_expression_TV);
                resultant_TV = (AutoResizeTextView) findViewById(R.id.curr_resultant_TV);
                break;
            /*case 3:
                result_TV = (AutoResizeTextView) findViewById(R.id.conv_result_TV);
                expression_TV = (EditText) findViewById(R.id.conv_expression_TV);
                resultant_TV = (AutoResizeTextView) findViewById(R.id.conv_resultant_TV);
                break;*/

        }
        try {
            result_TV.setInputType(InputType.TYPE_NULL);
            result_TV.setRawInputType(InputType.TYPE_CLASS_TEXT);
            result_TV.setTextIsSelectable(true);
            result = result_TV.getText().toString();

            try {
                resultant_TV.setInputType(InputType.TYPE_NULL);
                resultant_TV.setRawInputType(InputType.TYPE_CLASS_TEXT);
                resultant_TV.setTextIsSelectable(true);
            } catch (Exception idk) {
                //
            }

            expression_TV.setInputType(InputType.TYPE_NULL);
            expression_TV.setRawInputType(InputType.TYPE_CLASS_TEXT);
            expression_TV.setTextIsSelectable(true);
            expression = expression_TV.getText().toString();
            if (!flag) {
                expression_TV.setText("");
                result_TV.setMaxTextSize(34);
                result_TV.setTextSize(34);
                result_TV.setText("" + 0);
                expression = "";
                result = "" + 0;
                flag = true;
            }
        } catch (Exception eee) {
            //
        }

        //Converter
        /*from = (Spinner) findViewById(R.id.from_spinner);
        to = (Spinner) findViewById(R.id.to_spinner);
        try {
            from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    evaluate(expression_TV.getText().toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    evaluate(expression_TV.getText().toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } catch (Exception e) {
        }*/

        //Currency
        fro = (Spinner) findViewById(R.id.fro_spinner);
        too = (Spinner) findViewById(R.id.too_spinner);
        /*LinearLayout sciFunLayout = (LinearLayout) findViewById(R.id.sci_fun_layout);
        final ListView constantsList = (ListView) findViewById(R.id.constants_list);*/

        switch (page_no) {
            /*case 0:
                historyLayout = (LinearLayout) findViewById(R.id.sci_history_layout);
                memoryLayout = (LinearLayout) findViewById(R.id.sci_memory_layout);
                simplePad = (LinearLayout) findViewById(R.id.sci_simple_pad);
                historyList = (ListView) findViewById(R.id.sci_history_list);
                historyList.setEmptyView(findViewById(R.id.sci_empty_history));
                memoryList = (ListView) findViewById(R.id.sci_memory_list);
                memoryList.setEmptyView(findViewById(R.id.sci_empty));
                fav_List_LL = (LinearLayout) findViewById(R.id.conv_fav_layout);
                favList = (ListView) findViewById(R.id.conv_fav_list);
                favList.setEmptyView(findViewById(R.id.conv_empty_fav));
                hyBtn = (Button) findViewById(R.id.sci_history_Btn);
                myBtn = (Button) findViewById(R.id.sci_M_Btn);
                favBtn = (Button) findViewById(R.id.conv_fav_Btn);
                break;
            case 1:
                historyLayout = (LinearLayout) findViewById(R.id.pro_history_layout);
                memoryLayout = (LinearLayout) findViewById(R.id.pro_memory_layout);
                simplePad = (LinearLayout) findViewById(R.id.pro_simple_pad);
                historyList = (ListView) findViewById(R.id.pro_history_list);
                historyList.setEmptyView(findViewById(R.id.pro_empty_history));
                memoryList = (ListView) findViewById(R.id.pro_memory_list);
                memoryList.setEmptyView(findViewById(R.id.pro_empty));
                fav_List_LL = (LinearLayout) findViewById(R.id.conv_fav_layout);
                favList = (ListView) findViewById(R.id.conv_fav_list);
                favList.setEmptyView(findViewById(R.id.conv_empty_fav));
                favBtn = (Button) findViewById(R.id.conv_fav_Btn);
                break;*/
            case 2:
                historyLayout = (LinearLayout) findViewById(R.id.curr_history_layout);
                memoryLayout = (LinearLayout) findViewById(R.id.curr_memory_layout);
                simplePad = (LinearLayout) findViewById(R.id.curr_simple_pad);
                historyList = (ListView) findViewById(R.id.curr_history_list);
                historyList.setEmptyView(findViewById(R.id.curr_empty_history));
                memoryList = (ListView) findViewById(R.id.curr_memory_list);
                memoryList.setEmptyView(findViewById(R.id.curr_empty));
                fav_List_LL = (LinearLayout) findViewById(R.id.curr_fav_layout);
                favList = (ListView) findViewById(R.id.curr_fav_list);
                favList.setEmptyView(findViewById(R.id.curr_empty_fav));
                hyBtn = (Button) findViewById(R.id.curr_history_Btn);
                myBtn = (Button) findViewById(R.id.curr_M_Btn);
                favBtn = (Button) findViewById(R.id.curr_fav_Btn);
                break;
            /*case 3:
                historyLayout = (LinearLayout) findViewById(R.id.conv_history_layout);
                memoryLayout = (LinearLayout) findViewById(R.id.conv_memory_layout);
                simplePad = (LinearLayout) findViewById(R.id.conv_simple_pad);
                historyList = (ListView) findViewById(R.id.conv_history_list);
                historyList.setEmptyView(findViewById(R.id.conv_empty_history));
                memoryList = (ListView) findViewById(R.id.conv_memory_list);
                memoryList.setEmptyView(findViewById(R.id.conv_empty));
                fav_List_LL = (LinearLayout) findViewById(R.id.conv_fav_layout);
                favList = (ListView) findViewById(R.id.conv_fav_list);
                favList.setEmptyView(findViewById(R.id.conv_empty_fav));
                hyBtn = (Button) findViewById(R.id.conv_history_Btn);
                myBtn = (Button) findViewById(R.id.conv_M_Btn);
                favBtn = (Button) findViewById(R.id.conv_fav_Btn);
                break;
            default:
                historyLayout = (LinearLayout) findViewById(R.id.conv_history_layout);
                memoryLayout = (LinearLayout) findViewById(R.id.conv_memory_layout);
                simplePad = (LinearLayout) findViewById(R.id.conv_simple_pad);
                historyList = (ListView) findViewById(R.id.conv_history_list);
                historyList.setEmptyView(findViewById(R.id.conv_empty_history));
                fav_List_LL = (LinearLayout) findViewById(R.id.conv_fav_layout);
                memoryList = (ListView) findViewById(R.id.conv_memory_list);
                memoryList.setEmptyView(findViewById(R.id.conv_empty));
                favList = (ListView) findViewById(R.id.conv_fav_list);
                favList.setEmptyView(findViewById(R.id.conv_empty_fav));
                hyBtn = (Button) findViewById(R.id.conv_history_Btn);
                myBtn = (Button) findViewById(R.id.conv_M_Btn);
                favBtn = (Button) findViewById(R.id.conv_fav_Btn);
                break;*/
        }

        /*Button funcBtn = (Button) findViewById(R.id.functions_Btn);
        Button constBtn = (Button) findViewById(R.id.constants_Btn);

        Button decBtn = (Button) findViewById(R.id.dec_Btn);
        Button hexBtn = (Button) findViewById(R.id.hex_Btn);
        Button octBtn = (Button) findViewById(R.id.oct_Btn);
        Button binBtn = (Button) findViewById(R.id.bin_Btn);

        TextView decTV = (TextView) findViewById(R.id.dec_tv);
        TextView hexTV = (TextView) findViewById(R.id.hex_TV);
        TextView octTV = (TextView) findViewById(R.id.oct_TV);
        TextView binTV = (TextView) findViewById(R.id.bin_TV);*/

        int id = v.getId();
        Button btn = (Button) findViewById(id);
        final String btnText = btn.getText().toString();
        switch (id) {
            case R.id.curr_add_to_fav:
                //case R.id.conv_add_to_fav:
                //createFav();
                break;
            //case R.id.sci_history_Btn:
            //case R.id.conv_history_Btn:
            case R.id.curr_history_Btn:
                /*dbAdapter = new DbAdapter(this);
                dbAdapter.open();
                fillData();*/

                if (historyLayout.getVisibility() == View.GONE) {
                    historyLayout.setVisibility(View.VISIBLE);
                    hyBtn.setTextColor(primary_text_color);
                    hyBtn.setBackgroundColor(primary_color);
                    myBtn.setTextColor(sec_text_color);
                    myBtn.setBackgroundColor(sec_color);
                    memoryLayout.setVisibility(View.GONE);
                    try {
                        fav_List_LL.setVisibility(View.GONE);
                        favBtn.setTextColor(sec_text_color);
                        favBtn.setBackgroundColor(sec_color);
                    } catch (Exception e) {
                        //
                    }
                    /*try {
                        constantsList.setVisibility(View.GONE);
                        constBtn.setTextColor(sec_text_color);
                        constBtn.setBackgroundColor(sec_color);
                        sciFunLayout.setVisibility(View.GONE);
                        funcBtn.setTextColor(sec_text_color);
                        funcBtn.setBackgroundColor(sec_color);
                    } catch (Exception e) {
                        //
                    }*/
                    simplePad.setVisibility(View.GONE);
                } else {
                    hyBtn.setTextColor(sec_text_color);
                    hyBtn.setBackgroundColor(sec_color);
                    historyLayout.setVisibility(View.GONE);
                    simplePad.setVisibility(View.VISIBLE);
                }
                break;

            /*case R.id.floating:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (getDrawPermit()) {
                        Intent startServiceIntent = new Intent(MainActivity.this, FloatingCalculator.class);
                        startService(startServiceIntent);
                    } else
                        Toast.makeText(getApplicationContext(),
                                "Allow permission to draw over other apps to use Floating Calculator.",
                                Toast.LENGTH_LONG).show();
                } else {
                    Intent startServiceIntent = new Intent(MainActivity.this, FloatingCalculator.class);
                    startService(startServiceIntent);
                }
                break;
            case R.id.matgraph:
                startActivity(new Intent(getApplicationContext(), Calculator.class).putExtra("MAIN", true));
                break;
            case R.id.settings:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                break;
            case R.id.report:
                Intent report = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/adithya321/Calculator-Begins/issues"));
                startActivity(report);
                break;
            case R.id.about:
                new LibsBuilder()
                        .withLicenseShown(true)
                        .withVersionShown(true)
                        .withAboutVersionShown(true)
                        .withActivityTitle(getString(R.string.app_name))
                        .withAboutIconShown(true)
                        .withListener(libsListener)
                        .start(MainActivity.this);
                break;*/

            case R.id.AC_Btn:
                //createHistory();
                reset();
                break;
            case R.id.C_Btn:
                int start = Math.max(expression_TV.getSelectionStart(), 0);
                int end = Math.max(expression_TV.getSelectionEnd(), 0);
                if (start == end && start != 0)
                    expression_TV.getText().replace(Math.min(start, end) - 1, Math.max(start, end), "",
                            0, 0);
                else
                    expression_TV.getText().replace(Math.min(start, end), Math.max(start, end), "",
                            0, 0);
                evaluate(expression_TV.getText().toString());
                break;

            //Memory
            /*case R.id.MC_Btn:
                dbAdapter = new DbAdapter(this);
                dbAdapter.open();
                dbAdapter.deleteAllMemories();
                fillData();
                break;
            case R.id.MR_Btn:
                dbAdapter = new DbAdapter(this);
                dbAdapter.open();
                fillData();
                String mem = dbAdapter.memoryRetrieve();
                if (mem.equals("xyz")) {
                    Toast toast = Toast.makeText(MainActivity.this, "No Memory Yet!", Toast.LENGTH_SHORT);
                    toast.show();
                } else
                    insert(dbAdapter.memoryRetrieve());
                break;
            case R.id.MS_Btn:
                createMemory();
                break;*/
            //case R.id.sci_M_Btn:
            case R.id.curr_M_Btn:
                //case R.id.conv_M_Btn:
                /*dbAdapter = new DbAdapter(this);
                dbAdapter.open();
                fillData();*/
                if (memoryLayout.getVisibility() == View.GONE) {
                    memoryLayout.setVisibility(View.VISIBLE);
                    myBtn.setTextColor(primary_text_color);
                    myBtn.setBackgroundColor(primary_color);
                    try {
                        fav_List_LL.setVisibility(View.GONE);
                        favBtn.setTextColor(sec_text_color);
                        favBtn.setBackgroundColor(sec_color);
                    } catch (Exception e) {
                        //
                    }
                    /*try {
                        constantsList.setVisibility(View.GONE);
                        constBtn.setTextColor(sec_text_color);
                        constBtn.setBackgroundColor(sec_color);
                        sciFunLayout.setVisibility(View.GONE);
                        funcBtn.setTextColor(sec_text_color);
                        funcBtn.setBackgroundColor(sec_color);
                    } catch (Exception e) {
                        //
                    }*/
                    simplePad.setVisibility(View.GONE);
                    hyBtn.setTextColor(sec_text_color);
                    hyBtn.setBackgroundColor(sec_color);
                    historyLayout.setVisibility(View.GONE);
                } else {
                    myBtn.setTextColor(sec_text_color);
                    myBtn.setBackgroundColor(sec_color);
                    memoryLayout.setVisibility(View.GONE);
                    simplePad.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.curr_fav_Btn:
                //case R.id.conv_fav_Btn:
                /*dbAdapter = new DbAdapter(this);
                dbAdapter.open();
                fillData();*/
                if (fav_List_LL.getVisibility() == View.GONE) {
                    fav_List_LL.setVisibility(View.VISIBLE);
                    try {
                        memoryLayout.setVisibility(View.GONE);
                        myBtn.setTextColor(sec_text_color);
                        myBtn.setBackgroundColor(sec_color);
                    } catch (Exception e) {
                        //
                    }
                    favBtn.setTextColor(primary_text_color);
                    favBtn.setBackgroundColor(primary_color);
                    simplePad.setVisibility(View.GONE);
                    hyBtn.setTextColor(sec_text_color);
                    hyBtn.setBackgroundColor(sec_color);
                    historyLayout.setVisibility(View.GONE);
                } else {
                    fav_List_LL.setVisibility(View.GONE);
                    favBtn.setTextColor(sec_text_color);
                    favBtn.setBackgroundColor(sec_color);
                    simplePad.setVisibility(View.VISIBLE);
                }
                break;

            /*case R.id.constants_Btn:
                dataBaseHelper = new DataBaseHelper(this);
                try {
                    dataBaseHelper.createDataBase();
                } catch (IOException ioe) {
                    throw new Error("Unable to create database");
                }
                dataBaseHelper.openDataBase();
                dataSource = new DataSource(this);
                dataSource.open();
                if (constantsList.getVisibility() == View.GONE) {
                    constantsList.setVisibility(View.VISIBLE);
                    constBtn.setTextColor(primary_text_color);
                    constBtn.setBackgroundColor(primary_color);
                    memoryLayout.setVisibility(View.GONE);
                    myBtn.setTextColor(sec_text_color);
                    myBtn.setBackgroundColor(sec_color);
                    hyBtn.setTextColor(sec_text_color);
                    hyBtn.setBackgroundColor(sec_color);
                    historyLayout.setVisibility(View.GONE);
                    try {
                        sciFunLayout.setVisibility(View.GONE);
                        funcBtn.setTextColor(sec_text_color);
                        funcBtn.setBackgroundColor(sec_color);
                    } catch (Exception e) {
                        //
                    }
                    simplePad.setVisibility(View.GONE);
                } else {
                    constantsList.setVisibility(View.GONE);
                    constBtn.setTextColor(sec_text_color);
                    constBtn.setBackgroundColor(sec_color);
                    simplePad.setVisibility(View.VISIBLE);
                }
                try {
                    Cursor c = dataSource.getConstants();
                    startManagingCursor(c);

                    String[] fro = new String[]{DataBaseHelper.COLUMN_NAME, DataBaseHelper.COLUMN_SYMBOL, DataBaseHelper.COLUMN_VALUE, DataBaseHelper.COLUMN_DIMENSION};
                    int[] too = new int[]{R.id.text11, R.id.text12, R.id.text21, R.id.text22};

                    SimpleCursorAdapter constants =
                            new SimpleCursorAdapter(this, R.layout.constants_row, c, fro, too);
                    constantsList.setAdapter(constants);
                    constantsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Cursor obj = (Cursor) constantsList.getItemAtPosition(position);
                            insert(obj.getString(3));
                        }
                    });
                } catch (Exception e) {
                    //
                }
                break;

            case R.id.functions_Btn:
                if (sciFunLayout.getVisibility() == View.GONE) {
                    funcBtn.setTextColor(primary_text_color);
                    funcBtn.setBackgroundColor(primary_color);
                    sciFunLayout.setVisibility(View.VISIBLE);
                    memoryLayout.setVisibility(View.GONE);
                    myBtn.setTextColor(sec_text_color);
                    myBtn.setBackgroundColor(sec_color);
                    hyBtn.setTextColor(sec_text_color);
                    hyBtn.setBackgroundColor(sec_color);
                    historyLayout.setVisibility(View.GONE);
                    simplePad.setVisibility(View.GONE);
                    constantsList.setVisibility(View.GONE);
                    constBtn.setTextColor(sec_text_color);
                    constBtn.setBackgroundColor(sec_color);
                } else {
                    funcBtn.setTextColor(sec_text_color);
                    funcBtn.setBackgroundColor(sec_color);
                    sciFunLayout.setVisibility(View.GONE);
                    simplePad.setVisibility(View.VISIBLE);
                }
                break;*/

            //case R.id.conv_swap_Btn:
            case R.id.curr_swap_Btn:
                if (page_no == 3) {
                    int temp = from.getSelectedItemPosition();
                    from.setSelection(to.getSelectedItemPosition());
                    to.setSelection(temp);
                } else {
                    SharedPreferences settings = getSharedPreferences("MyPrefs", 0);
                    Float rate = settings.getFloat("rate", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putFloat("rate", 1 / rate);
                    editor.apply();
                    rate = 1 / rate;
                    result_TV.setText("1");
                    resultant_TV.setText(rate.toString());
                    int temp = fro.getSelectedItemPosition();
                    fro.setSelection(too.getSelectedItemPosition());
                    too.setSelection(temp);
                }
                break;

            //Operators
            case R.id.leftBracket_Btn:
            case R.id.rightBracket_Btn:
            case R.id.add_Btn:
            case R.id.sub_Btn:
                //case R.id.factorial_Btn:
                // case R.id.imagine_Btn:
                //Numbers
            case R.id.dot_Btn:
            case R.id.Btn_0:
            case R.id.Btn_1:
            case R.id.Btn_2:
            case R.id.Btn_3:
            case R.id.Btn_4:
            case R.id.Btn_5:
            case R.id.Btn_6:
            case R.id.Btn_7:
            case R.id.Btn_8:
            case R.id.Btn_9:
                insert(btnText);
                break;
            case R.id.div_Btn:
                insert("/");
                break;
            case R.id.mul_Btn:
                insert("*");
                break;

            /*//Hex
            case R.id.A_Btn:
            case R.id.B_Btn:
            case R.id.C_hex_Btn:
            case R.id.D_Btn:
            case R.id.E_Btn:
            case R.id.F_Btn:
                insert(btnText);
                break;
            //Functions
            case R.id.abs_Btn:
            case R.id.ceil_Btn:
            case R.id.cos_Btn:
            case R.id.cosh_Btn:
            case R.id.exp_Btn:
            case R.id.floor_Btn:
            case R.id.log_Btn:
            case R.id.log10_Btn:
            case R.id.log2_Btn:
            case R.id.sin_Btn:
            case R.id.sinh_Btn:
            case R.id.tan_Btn:
            case R.id.tanh_Btn:
                insert(btnText + "(");
                break;

            case R.id.deg_Btn:
                insert("*" + getResources().getString(R.string.pi) + "/180");
                break;

            case R.id.acos_Btn:
                insert("acos(");
                break;
            case R.id.asin_Btn:
                insert("asin(");
                break;
            case R.id.atan_Btn:
                insert("atan(");
                break;
            case R.id.acosd_Btn:
                insert("acosd(");
                break;
            case R.id.acosh_Btn:
                insert("acosh(");
                break;
            case R.id.asind_Btn:
                insert("asind(");
                break;
            case R.id.sind_Btn:
                insert("sind(");
                break;
            case R.id.asinh_Btn:
                insert("asinh(");
                break;
            case R.id.cosd_Btn:
                insert("cosd(");
                break;
            case R.id.tand_Btn:
                insert("tand(");
                break;
            case R.id.atand_Btn:
                insert("atand(");
                break;
            case R.id.atanh_Btn:
                insert("atanh(");
                break;

            case R.id.root_Btn:
                insert("sqrt(");
                break;

            case R.id.cbrt_Btn:
                insert("cbrt(");
                break;

            case R.id.reciprocal_Btn:
                expression_TV.getText().replace(0, 0, "1/(", 0, 3);
                break;*/

            case R.id.percent_Btn:
                insert("%");
                break;

            /*case R.id.e_Btn:
                insert("2.7182818285");
                break;

            case R.id.pi_Btn:
                insert(btnText);
                break;*/

            case R.id.power_Btn:
                insert(btnText);
                break;

            case R.id.equals_Btn:
                done = true;
                evaluate(expression_TV.getText().toString());
                //createHistory();
                try {
                    expression_TV.setText("");
                    insert(result_TV.getText().toString());
                    result_TV.setText("0");
                } catch (RuntimeException ee) {
                    //
                }
                break;

            /*case R.id.dec_Btn:
                try {
                    decBtn.setBackgroundColor(sec_color);
                    decBtn.setTextColor(sec_text_color);
                    hexBtn.setBackgroundColor(primary_color);
                    hexBtn.setTextColor(primary_text_color);
                    octBtn.setBackgroundColor(primary_color);
                    octBtn.setTextColor(primary_text_color);
                    binBtn.setBackgroundColor(primary_color);
                    binBtn.setTextColor(primary_text_color);
                    decTV.setBackgroundColor(sec_color);
                    decTV.setTextColor(sec_text_color);
                    hexTV.setBackgroundColor(primary_color);
                    hexTV.setTextColor(primary_text_color);
                    octTV.setBackgroundColor(primary_color);
                    octTV.setTextColor(primary_text_color);
                    binTV.setBackgroundColor(primary_color);
                    binTV.setTextColor(primary_text_color);
                    expression_TV.setText("");
                    insert(changeBase(expression, base, Base.DECIMAL));
                    base = Base.DECIMAL;
                } catch (SyntaxException S) {
                    Toast.makeText(getApplicationContext(), S.toString(), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.hex_Btn:
                try {
                    decBtn.setBackgroundColor(primary_color);
                    decBtn.setTextColor(primary_text_color);
                    hexBtn.setBackgroundColor(sec_color);
                    hexBtn.setTextColor(sec_text_color);
                    octBtn.setBackgroundColor(primary_color);
                    octBtn.setTextColor(primary_text_color);
                    binBtn.setBackgroundColor(primary_color);
                    binBtn.setTextColor(primary_text_color);
                    decTV.setBackgroundColor(primary_color);
                    decTV.setTextColor(primary_text_color);
                    hexTV.setBackgroundColor(sec_color);
                    hexTV.setTextColor(sec_text_color);
                    octTV.setBackgroundColor(primary_color);
                    octTV.setTextColor(primary_text_color);
                    binTV.setBackgroundColor(primary_color);
                    binTV.setTextColor(primary_text_color);
                    expression_TV.setText("");
                    insert(changeBase(expression, base, Base.HEXADECIMAL));
                    base = Base.HEXADECIMAL;
                } catch (SyntaxException S) {
                    Toast.makeText(getApplicationContext(), S.toString(), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.oct_Btn:
                try {
                    decBtn.setBackgroundColor(primary_color);
                    decBtn.setTextColor(primary_text_color);
                    hexBtn.setBackgroundColor(primary_color);
                    hexBtn.setTextColor(primary_text_color);
                    octBtn.setBackgroundColor(sec_color);
                    octBtn.setTextColor(sec_text_color);
                    binBtn.setBackgroundColor(primary_color);
                    binBtn.setTextColor(primary_text_color);
                    decTV.setBackgroundColor(primary_color);
                    decTV.setTextColor(primary_text_color);
                    hexTV.setBackgroundColor(primary_color);
                    hexTV.setTextColor(primary_text_color);
                    octTV.setBackgroundColor(sec_color);
                    octTV.setTextColor(sec_text_color);
                    binTV.setBackgroundColor(primary_color);
                    binTV.setTextColor(primary_text_color);
                    expression_TV.setText("");
                    insert(changeBase(expression, base, Base.OCTAL));
                    base = Base.OCTAL;
                } catch (SyntaxException S) {
                    Toast.makeText(getApplicationContext(), S.toString(), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bin_Btn:
                try {
                    decBtn.setBackgroundColor(primary_color);
                    decBtn.setTextColor(primary_text_color);
                    hexBtn.setBackgroundColor(primary_color);
                    hexBtn.setTextColor(primary_text_color);
                    octBtn.setBackgroundColor(primary_color);
                    octBtn.setTextColor(primary_text_color);
                    binBtn.setBackgroundColor(sec_color);
                    binBtn.setTextColor(sec_text_color);
                    decTV.setBackgroundColor(primary_color);
                    decTV.setTextColor(primary_text_color);
                    hexTV.setBackgroundColor(primary_color);
                    hexTV.setTextColor(primary_text_color);
                    octTV.setBackgroundColor(primary_color);
                    octTV.setTextColor(primary_text_color);
                    binTV.setBackgroundColor(sec_color);
                    binTV.setTextColor(sec_text_color);
                    expression_TV.setText("");
                    insert(changeBase(expression, base, Base.BINARY));
                    base = Base.BINARY;
                } catch (SyntaxException S) {
                    Toast.makeText(getApplicationContext(), S.toString(), Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }
            try {
                if (v.getId() != R.id.AC_Btn) {
                    result_TV.resizeText();
                    try {
                        resultant_TV.resizeText();
                    } catch (Exception e) {
                        //
                    }
                }

            } catch (Exception eeee) {
                //
            }*/
        }
    }

    public void insert(String string) {
        int start = Math.max(expression_TV.getSelectionStart(), 0);
        int end = Math.max(expression_TV.getSelectionEnd(), 0);
        expression_TV.getText().replace(Math.min(start, end), Math.max(start, end), string,
                0, string.length());
        evaluate(expression_TV.getText().toString());
    }

    void evaluate(String exp) {
        Solver solver = new Solver();
        if (page_no == 3) {
            switch (unitSpinner.getSelectedItem().toString()) {
                case "Area":
                    convert(exp, areaValues);
                    break;
                case "Cooking":
                    convert(exp, cookingValues);
                    break;
                case "Digital Storage":
                    convert(exp, digitalValues);
                    break;
                case "Energy":
                    convert(exp, energyValues);
                    break;
                case "Fuel Consumption":
                    convert(exp, fuelValues);
                    break;
                case "Length / Distance":
                    convert(exp, lengthValues);
                    break;
                case "Mass / Weight":
                    convert(exp, massValues);
                    break;
                case "Power":
                    convert(exp, powerValues);
                    break;
                case "Pressure":
                    convert(exp, pressureValues);
                    break;
                case "Speed":
                    convert(exp, speedValues);
                    break;
                case "Time":
                    convert(exp, timeValues);
                    break;
                case "Torque":
                    convert(exp, torqueValues);
                    break;
                case "Volume":
                    convert(exp, volumeValues);
                    break;
            }
        } else {
            try {
                int test = too.getVisibility();
                SharedPreferences settings = getSharedPreferences("MyPrefs", 0);
                Float rate = settings.getFloat("rate", 0);
                if (format_bool) {
                    try {
                        result_TV.setText(formatter.format(Double.valueOf(solver.solve(exp).replace(Constants.DECIMAL_POINT, '.'))));
                        resultant_TV.setText(formatter.format(Double.valueOf(solver.solve(String.valueOf(solver.solve("(" + exp + ")*" + rate.toString()).replace(Constants.DECIMAL_POINT, '.'))))));
                    } catch (Exception i) {
                        result_TV.setText(String.valueOf(solver.solve(exp)));
                        resultant_TV.setText(String.valueOf(solver.solve(String.valueOf(solver.solve(exp)) + "*" + rate.toString())));
                    }
                } else {
                    result_TV.setText(String.valueOf(solver.solve(exp)));
                    resultant_TV.setText(String.valueOf(solver.solve(String.valueOf(solver.solve(exp)) + "*" + rate.toString())));
                }
            } catch (Exception ee) {
                try {
                    if (format_bool) {
                        try {
                            result_TV.setText(formatter.format(Double.valueOf(solver.solve(exp).replace(Constants.DECIMAL_POINT, '.'))));
                        } catch (Exception i) {
                            result_TV.setText(String.valueOf(solver.solve(exp)));
                        }
                    } else
                        result_TV.setText(String.valueOf(solver.solve(exp)));
                    done = false;
                } catch (SyntaxException S) {
                    if (done) {
                        Toast.makeText(MainActivity.this, S.toString(), Toast.LENGTH_SHORT).show();
                    }
                    done = false;
                }
            }

            /*try {
                TextView decTV = (TextView) findViewById(R.id.dec_tv);
                TextView hexTV = (TextView) findViewById(R.id.hex_TV);
                TextView octTV = (TextView) findViewById(R.id.oct_TV);
                TextView binTV = (TextView) findViewById(R.id.bin_TV);

                DecimalFormat df = new DecimalFormat();
                df.setGroupingUsed(false);
                df.setMaximumFractionDigits(15);
                try {
                    solver.setBase(base);
                    String ans = solver.solve(exp);
                    decTV.setText(changeBase(ans, base, Base.DECIMAL));
                    hexTV.setText(newBase(decTV.getText().toString(), 10, 16));
                    octTV.setText(newBase(decTV.getText().toString(), 10, 8));
                    binTV.setText(newBase(decTV.getText().toString(), 10, 2));
                } catch (SyntaxException R) {
                    if (done) {
                        Toast.makeText(MainActivity.this, R.toString(), Toast.LENGTH_SHORT).show();
                    }
                    done = false;
                } catch (Exception e) {
                    //Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            } catch (RuntimeException R) {
                //
            }*/
        }
    }

    String changeBase(final String originalText, final Base oldBase, final Base newBase) throws SyntaxException {
        if (oldBase.equals(newBase) || originalText.isEmpty() || originalText.matches(REGEX_NOT_NUMBER)) {
            return originalText;
        }

        String[] operations = originalText.split(REGEX_NUMBER);
        String[] numbers = originalText.split(REGEX_NOT_NUMBER);
        String[] translatedNumbers = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            if (!numbers[i].isEmpty()) {
                switch (oldBase) {
                    case BINARY:
                        switch (newBase) {
                            case BINARY:
                                break;
                            case DECIMAL:
                                try {
                                    translatedNumbers[i] = newBase(numbers[i], 2, 10);
                                } catch (NumberFormatException e) {
                                    throw new SyntaxException();
                                }
                                break;
                            case OCTAL:
                                try {
                                    translatedNumbers[i] = newBase(numbers[i], 2, 8);
                                } catch (NumberFormatException e) {
                                    throw new SyntaxException();
                                }
                                break;
                            case HEXADECIMAL:
                                try {
                                    translatedNumbers[i] = newBase(numbers[i], 2, 16);
                                } catch (NumberFormatException e) {
                                    throw new SyntaxException();
                                }
                                break;
                        }
                        break;
                    case DECIMAL:
                        switch (newBase) {
                            case BINARY:
                                try {
                                    translatedNumbers[i] = newBase(numbers[i], 10, 2);
                                } catch (NumberFormatException e) {
                                    throw new SyntaxException();
                                }
                                break;
                            case DECIMAL:
                                break;
                            case HEXADECIMAL:
                                try {
                                    translatedNumbers[i] = newBase(numbers[i], 10, 16);
                                } catch (NumberFormatException e) {
                                    throw new SyntaxException();
                                }
                                break;
                            case OCTAL:
                                try {
                                    translatedNumbers[i] = newBase(numbers[i], 10, 8);
                                } catch (NumberFormatException e) {
                                    throw new SyntaxException();
                                }
                                break;
                        }
                        break;
                    case OCTAL:
                        switch (newBase) {
                            case BINARY:
                                try {
                                    translatedNumbers[i] = newBase(numbers[i], 8, 2);
                                } catch (NumberFormatException e) {
                                    throw new SyntaxException();
                                }
                                break;
                            case DECIMAL:
                                try {
                                    translatedNumbers[i] = newBase(numbers[i], 8, 10);
                                } catch (NumberFormatException e) {
                                    Log.e("TAG", numbers[i] + " is not a number", e);
                                    throw new SyntaxException();
                                }
                                break;
                            case HEXADECIMAL:
                                try {
                                    translatedNumbers[i] = newBase(numbers[i], 8, 16);
                                } catch (NumberFormatException e) {
                                    throw new SyntaxException();
                                }
                                break;
                            case OCTAL:
                                break;
                        }
                        break;
                    case HEXADECIMAL:
                        switch (newBase) {
                            case BINARY:
                                try {
                                    translatedNumbers[i] = newBase(numbers[i], 16, 2);
                                } catch (NumberFormatException e) {
                                    throw new SyntaxException();
                                }
                                break;
                            case DECIMAL:
                                try {
                                    translatedNumbers[i] = newBase(numbers[i], 16, 10);
                                } catch (NumberFormatException e) {
                                    Log.e("TAG", numbers[i] + " is not a number", e);
                                    throw new SyntaxException();
                                }
                                break;
                            case HEXADECIMAL:
                                break;
                            case OCTAL:
                                try {
                                    translatedNumbers[i] = newBase(numbers[i], 16, 8);
                                } catch (NumberFormatException e) {
                                    throw new SyntaxException();
                                }
                                break;
                        }
                        break;
                }
            }
        }
        String text = "";
        Object[] o = removeWhitespace(operations);
        Object[] n = removeWhitespace(translatedNumbers);
        if (originalText.substring(0, 1).matches(REGEX_NUMBER)) {
            for (int i = 0; i < o.length && i < n.length; i++) {
                text += n[i];
                text += o[i];
            }
        } else {
            for (int i = 0; i < o.length && i < n.length; i++) {
                text += o[i];
                text += n[i];
            }
        }
        if (o.length > n.length) {
            text += o[o.length - 1];
        } else if (n.length > o.length) {
            text += n[n.length - 1];
        }
        return text;
    }

    private Object[] removeWhitespace(String[] strings) {
        ArrayList<String> formatted = new ArrayList<>(strings.length);
        for (String s : strings) {
            if (s != null && !s.isEmpty()) formatted.add(s);
        }
        return formatted.toArray();
    }

    private String newBase(String originalNumber, int originalBase, int base) throws SyntaxException {
        String[] split = originalNumber.split("\\.");
        if (split.length == 0) {
            split = new String[1];
            split[0] = "0";
        }
        if (split[0].isEmpty()) {
            split[0] = "0";
        }
        if (originalBase != 10) {
            split[0] = Long.toString(Long.parseLong(split[0], originalBase));
        }

        String wholeNumber = "";
        switch (base) {
            case 2:
                wholeNumber = Long.toBinaryString(Long.parseLong(split[0]));
                break;
            case 8:
                wholeNumber = Long.toOctalString(Long.parseLong(split[0]));
                break;
            case 10:
                wholeNumber = split[0];
                break;
            case 16:
                wholeNumber = Long.toHexString(Long.parseLong(split[0]));
                break;
        }
        if (split.length == 1) return wholeNumber.toUpperCase(Locale.US);

        // Catch overflow (it's a decimal, it can be (slightly) rounded
        if (split[1].length() > 13) {
            split[1] = split[1].substring(0, 13);
        }

        Solver solver = new Solver();
        double decimal = 0;
        if (originalBase != 10) {
            String decimalFraction = Long.toString(Long.parseLong(split[1], originalBase)) + "/" + originalBase + "^" + split[1].length();
            decimal = solver.eval(decimalFraction);
        } else {
            decimal = Double.parseDouble("0." + split[1]);
        }
        if (decimal == 0) return wholeNumber.toUpperCase(Locale.US);

        String decimalNumber = "";
        for (int i = 0; decimal != 0 && i <= 8; i++) {
            decimal *= base;
            int id = (int) Math.floor(decimal);
            decimal -= id;
            decimalNumber += Integer.toHexString(id);
        }
        return (wholeNumber + "." + decimalNumber).toUpperCase(Locale.US);
    }

    void convert(String exp, double[][] unitValues) {
        Solver solver = new Solver();
        try {
            BigDecimal multiplier, bdResult;
            if (unitSpinner.getSelectedItem().toString().equals("Length / Distance") && ((to.getSelectedItem().toString().equals("Feet.Inch")) || (from.getSelectedItem().toString().equals("Feet.Inch")))) {
                String[] splitter = solver.solve(exp).split("\\.");
                double feet = ((double) Integer.valueOf(solver.solve(exp)));
                double inch = (feet * 12) + (splitter[1].length() == 1 ? (Double.valueOf(solver.solve(exp)) % 1) * 10 : (Double.valueOf(solver.solve(exp)) % 1) * 100);
                if (to.getSelectedItem().toString().equals("Feet.Inch")) {
                    to.setSelection(to.getSelectedItemPosition() - 1);
                    multiplier = new BigDecimal(unitValues[to.getSelectedItemPosition()][1]).multiply(new BigDecimal(unitValues[from.getSelectedItemPosition()][0]));
                    bdResult = new BigDecimal(Double.valueOf(solver.solve(exp))).multiply(multiplier);
                } else {
                    multiplier = new BigDecimal(unitValues[to.getSelectedItemPosition()][1]).multiply(new BigDecimal(unitValues[from.getSelectedItemPosition() + 1][0]));
                    bdResult = new BigDecimal(inch).multiply(multiplier);
                }
            } else {
                multiplier = new BigDecimal(unitValues[to.getSelectedItemPosition()][1]).multiply(new BigDecimal(unitValues[from.getSelectedItemPosition()][0]));
                try {
                    bdResult = new BigDecimal(Double.valueOf(solver.solve(exp))).multiply(multiplier);
                } catch (Exception e) {
                    bdResult = new BigDecimal(0);
                }
            }
            if (format_bool) {
                try {
                    result_TV.setText(formatter.format(Double.valueOf(solver.solve(exp).replace(Constants.DECIMAL_POINT, '.'))));
                    resultant_TV.setText(formatter.format(bdResult.doubleValue()));
                } catch (Exception i) {
                    result_TV.setText(String.valueOf(solver.solve(exp)));
                    resultant_TV.setText(String.valueOf(bdResult.doubleValue()));
                }
            } else {
                result_TV.setText(String.valueOf(solver.solve(exp)));
                resultant_TV.setText(String.valueOf(bdResult.doubleValue()));
            }
        } catch (SyntaxException R) {
            if (done) {
                Toast toast = Toast.makeText(MainActivity.this, R.toString(), Toast.LENGTH_SHORT);
                toast.show();
            }
            done = false;
        }
    }

    void reset() {
        expression_TV.setText("");
        result_TV.setMaxTextSize(34);
        result_TV.setTextSize(34);
        result_TV.setText("" + 0);
        expression = "";
        result = "" + 0;
        try {
            resultant_TV.setMaxTextSize(34);
            resultant_TV.setTextSize(34);
            resultant_TV.setText("" + 0);
        } catch (Exception e) {
            //
        }
        x = "";
        y = "";
        ans = 0;
        a = 0;
        b = 0;
        operator1 = 0;
        operator2 = 0;
        count = 0;
    }

    /*void createMemory() {
        dbAdapter = new DbAdapter(this);
        dbAdapter.open();
        dbAdapter.createMemory(expression, result);
        fillData();
    }

    void createFav() {
        dbAdapter = new DbAdapter(this);
        dbAdapter.open();
        if (page_no == 2) {
            fro = (Spinner) findViewById(R.id.fro_spinner);
            too = (Spinner) findViewById(R.id.too_spinner);
            int numFromCurrency = fro.getSelectedItemPosition();
            int numToCurrency = too.getSelectedItemPosition();
            dbAdapter.createFav(getResources().getStringArray(R.array.currency_exp)[numFromCurrency], getResources().getStringArray(R.array.currency_exp)[numToCurrency]);
        } else {
            from = (Spinner) findViewById(R.id.from_spinner);
            to = (Spinner) findViewById(R.id.to_spinner);
            unitSpinner = (Spinner) findViewById(R.id.units_spinner);
            String From = from.getSelectedItem().toString();
            String To = to.getSelectedItem().toString();
            String Unit = unitSpinner.getSelectedItem().toString();
            dbAdapter.createFav(From, To, Unit);
        }
        fillData();
        Toast.makeText(getApplicationContext(), "Added to Favourites", Toast.LENGTH_LONG).show();
    }

    void createHistory() {
        try {
            dbAdapter = new DbAdapter(this);
            dbAdapter.open();
            if (!expression_TV.getText().toString().equals("0") && !result_TV.getText().toString().equals("0"))
                dbAdapter.createHistory(expression_TV.getText().toString(), result_TV.getText().toString());
            fillData();
        } catch (Exception ee) {
            //
        }
    }

    void fillData() {
        Cursor c = dbAdapter.fetchAllMemories();
        startManagingCursor(c);

        String[] from = new String[]{DbAdapter.KEY_EXP, DbAdapter.KEY_RESULT};
        int[] to = new int[]{R.id.text1, R.id.text2};

        SimpleCursorAdapter notes =
                new SimpleCursorAdapter(this, R.layout.history_row, c, from, to);
        memoryList.setAdapter(notes);
        registerForContextMenu(memoryList);

        memoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.showContextMenu();
            }
        });

        c = dbAdapter.fetchAllHistories();
        startManagingCursor(c);

        from = new String[]{DbAdapter.KEY_EXP, DbAdapter.KEY_RESULT};
        to = new int[]{R.id.text1, R.id.text2};

        notes = new SimpleCursorAdapter(this, R.layout.history_row, c, from, to);
        historyList.setAdapter(notes);
        registerForContextMenu(historyList);

        historyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.showContextMenu();
            }
        });

        try {
            if (page_no == 2)
                c = dbAdapter.fetchAllFavs(1);
            else
                c = dbAdapter.fetchAllFavs(2);
            startManagingCursor(c);

            from = new String[]{DbAdapter.KEY_EXP, DbAdapter.KEY_RESULT};
            to = new int[]{R.id.text1, R.id.text2};

            notes = new SimpleCursorAdapter(this, R.layout.history_row, c, from, to);
            favList.setAdapter(notes);
            registerForContextMenu(favList);

            favList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    view.showContextMenu();
                }
            });
        } catch (Exception exe) {
            //
        }
    }*/

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == memoryList.getId()) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            Cursor obj = (Cursor) memoryList.getItemAtPosition(info.position);
            menu.setHeaderTitle("Selection Options");
            menu.add(0, Menu.FIRST + 2, 0, "Insert " + obj.getString(1));
            menu.add(0, Menu.FIRST + 3, 1, "Insert " + obj.getString(2));
            menu.add(1, Menu.FIRST + 4, 2, R.string.menu_delete);
            menu.add(1, Menu.FIRST + 5, 3, "Delete All");
        } else if (v.getId() == historyList.getId()) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            Cursor obj = (Cursor) historyList.getItemAtPosition(info.position);
            menu.setHeaderTitle("Selection Options");
            menu.add(0, Menu.FIRST + 12, 0, "Insert " + obj.getString(1));
            menu.add(0, Menu.FIRST + 13, 1, "Insert " + obj.getString(2));
            menu.add(1, Menu.FIRST + 14, 2, R.string.menu_delete);
            menu.add(1, Menu.FIRST + 15, 3, "Delete All");
        } else {
            menu.setHeaderTitle("Selection Options");
            menu.add(0, Menu.FIRST + 21, 0, "Insert");
            menu.add(1, Menu.FIRST + 22, 0, R.string.menu_delete);
            menu.add(1, Menu.FIRST + 23, 1, "Delete All");
        }
    }

    /*@Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Cursor obj = (Cursor) historyList.getItemAtPosition(info.position);
        Cursor cursor = (Cursor) memoryList.getItemAtPosition(info.position);

        Solver solver = new Solver();

        switch (item.getItemId()) {
            case Menu.FIRST + 2:
                insert(cursor.getString(1));
                return true;
            case Menu.FIRST + 3:
                try {
                    insert(String.valueOf(solver.solve(cursor.getString(1))));
                } catch (SyntaxException e) {
                    insert(cursor.getString(2));
                }
                return true;
            case Menu.FIRST + 4:
                dbAdapter.deleteMemory(info.id);
                fillData();
                return true;
            case Menu.FIRST + 5:
                dbAdapter.deleteAllMemories();
                fillData();
                return true;
            case Menu.FIRST + 12:
                insert(obj.getString(1));
                historyLayout.setVisibility(View.GONE);
                simplePad.setVisibility(View.VISIBLE);
                hyBtn.setTextColor(sec_text_color);
                hyBtn.setBackgroundColor(sec_color);
                return true;
            case Menu.FIRST + 13:
                try {
                    insert(String.valueOf(solver.solve(obj.getString(1))));
                } catch (SyntaxException e) {
                    insert(cursor.getString(2));
                }
                simplePad.setVisibility(View.VISIBLE);
                historyLayout.setVisibility(View.GONE);
                hyBtn.setTextColor(sec_text_color);
                hyBtn.setBackgroundColor(sec_color);
                return true;
            case Menu.FIRST + 14:
                dbAdapter.deleteHistory(info.id);
                fillData();
                return true;
            case Menu.FIRST + 15:
                dbAdapter.deleteAllHistories();
                fillData();
                return true;
            case Menu.FIRST + 21:
                final Cursor cur = (Cursor) favList.getItemAtPosition(info.position);
                fav_List_LL.setVisibility(View.GONE);
                favBtn.setTextColor(sec_text_color);
                favBtn.setBackgroundColor(sec_color);
                simplePad.setVisibility(View.VISIBLE);
                if (page_no == 2) {
                    fro = (Spinner) findViewById(R.id.fro_spinner);
                    too = (Spinner) findViewById(R.id.too_spinner);
                    fro.setSelection(Arrays.asList(getResources().getStringArray(R.array.currency_exp)).indexOf(cur.getString(1)));
                    too.setSelection(Arrays.asList(getResources().getStringArray(R.array.currency_exp)).indexOf(cur.getString(2)));
                } else {
                    unitSpinner = (Spinner) findViewById(R.id.units_spinner);
                    final int i = getIndex(unitSpinner, cur.getString(3));
                    unitSpinner.setSelection(i);
                    from = (Spinner) findViewById(R.id.from_spinner);
                    to = (Spinner) findViewById(R.id.to_spinner);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            from.setSelection(getIndex(from, cur.getString(1)));
                            to.setSelection(getIndex(to, cur.getString(2)));
                        }
                    }, 1000);
                }
                return true;
            case Menu.FIRST + 22:
                if (page_no == 2)
                    dbAdapter.deleteFav(info.id, 1);
                else
                    dbAdapter.deleteFav(info.id, 2);
                fillData();
                return true;
            case Menu.FIRST + 23:
                if (page_no == 2)
                    dbAdapter.deleteAllFavs(1);
                else
                    dbAdapter.deleteAllFavs(2);
                fillData();
                return true;
        }
        return super.onContextItemSelected(item);
    }*/

    private int getIndex(Spinner spinner, String myString) {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equals(myString)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /*@Override
    protected void onPause() {
        super.onPause();
        createHistory();
    }*/

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        format_bool = mPrefs.getBoolean("format_key", false);
        if (format_bool) {
            num_deci = mPrefs.getString("number_decimals", "Any");
            group_separator = mPrefs.getString("group_separator", "None");
            decimal_separator = mPrefs.getString("decimal_separator", ".");
            if (!num_deci.equals("Any"))
                formatter.setMaximumFractionDigits(Integer.parseInt(num_deci));
            else
                formatter.setMaximumFractionDigits(100);
            symbols = formatter.getDecimalFormatSymbols();
            symbols.setDecimalSeparator(decimal_separator.charAt(0));
            boolean isSeparatorUsed = !group_separator.equals("None");
            formatter.setGroupingUsed(isSeparatorUsed);
            if (isSeparatorUsed) {
                symbols.setGroupingSeparator(group_separator.charAt(0));
            }
            formatter.setDecimalFormatSymbols(symbols);
        }
        vibrate = mPrefs.getBoolean("vibrate", true);
    }

    @Override
    protected void onDestroy() {
        mPrefs.unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }
}
