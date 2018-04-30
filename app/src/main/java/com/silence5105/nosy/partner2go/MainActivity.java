package com.silence5105.nosy.partner2go;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.silence5105.nosy.partner2go.ActivityFragment.BottomToast;
import com.silence5105.nosy.partner2go.ActivityFragment.FaqsActivity;
import com.silence5105.nosy.partner2go.ActivityFragment.JoinMe;
import com.silence5105.nosy.partner2go.ActivityFragment.Last5minFragment;
import com.silence5105.nosy.partner2go.ActivityFragment.Loginactivity;
import com.silence5105.nosy.partner2go.ActivityFragment.NewBookingActivity;
import com.silence5105.nosy.partner2go.ActivityFragment.OrderHaveDFragement;
import com.silence5105.nosy.partner2go.ActivityFragment.PayDoneActivity;
import com.silence5105.nosy.partner2go.ActivityFragment.SettingActivity;
import com.silence5105.nosy.partner2go.ActivityFragment.VerifiedActivity;
import com.silence5105.nosy.partner2go.ActivityFragment.WaitDriverActivtiy;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback, GoogleMap.OnCameraIdleListener, DirectionFinderListener
        , GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    GoogleMap mmap;
    LinearLayout otwselectlayout;
    LocationManager locationManager;
    RelativeLayout vnumberimg, menunumberimg;
    private final static String CALL = "android.intent.action.CALL";
    ProgressDialog progressDialog;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    List<Marker> startlocation = new ArrayList<>();
    List<Marker> endlocation = new ArrayList<>();
    RelativeLayout bottoncontainer;
    List<Marker> driverlocation = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    List<Address> lstAddress;
    public static final int LOCATION_UPDATE_MIN_DISTANCE = 10;
    public static final int LOCATION_UPDATE_MIN_TIME = 5000;
    private Button checkbtn;
    private AutoCompleteTextView etOrigin;
    private AutoCompleteTextView etDestination;
    double nowlat;
    TextView vnumbertxt;
    DrawerLayout drawer;
    RelativeLayout showview, menuback, callbtn;
    Button avgbtn, okbtn;
    double nowlng;
    RelativeLayout containermenubtn, menubtn, btnFindPath, container1, where2golayout, loadlinearlayoout, officfindpathbtn, onthewaylayout, icarlayout;
    LinearLayout linearLayout, btnlayout, checklayout, viewlayout, icslectlayout;
    AQuery aQuery;
    GoogleApiClient googleApiClient;
    TextView numbertxt, dtxt, rattxt, nametxt, iccartxt, iccnametxt, iccrate;
    ImageView menuimg, redimg, blueimg, sosbtn;
    PlaceAutocompleteAdapter placeAutocompleteAdapter;
    LinearLayout paymentbtn, mybookingbtn, verificationbtn, helpbtn, settingbtn;
    TextView drivetogobtn, menunumbertxt;

    private static final LatLngBounds BOUNDS_GREATER_SYDNEY = new LatLngBounds(
            new LatLng(-34.041458, 150.790100), new LatLng(-33.682247, 151.383362));

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {


            switch (msg.what) {
                case 1:
//                    System.out.println(" ===== 司機街到");
                    if (PrefsHelper.sethaved(getApplication()) != null) {
                        if (PrefsHelper.sethaved(getApplication()).equals("1")) {
//
                            if (originMarkers != null) {
                                for (Marker marker : originMarkers) {
                                    marker.remove();
                                }
                            }

                            if (destinationMarkers != null) {
                                for (Marker marker : destinationMarkers) {
                                    marker.remove();
                                }
                            }

                            if (polylinePaths != null) {
                                for (Polyline polyline : polylinePaths) {
                                    polyline.remove();
                                }
                            }
//                            PrefsHelper.gethaved(getApplication(), "0");
                        }
                        if (PrefsHelper.sethaved(getApplication()).equals("2")) {
//                                new BookingFragment().dismiss();
//                            new BookingFragment().dismiss();
//                            BookingFragment bookingFragment = new BookingFragment();
//                            getFragmentManager().beginTransaction().remove(bookingFragment).commit();
//                            bookingFragment.dismiss();
//                            dismissdialoglayout();
//                            new BookingFragment().layoutdismiss();
                            Toast.makeText(getApplication(), "已上車", Toast.LENGTH_SHORT).show();
                            if (originMarkers != null) {
                                for (Marker marker : originMarkers) {
                                    marker.remove();
                                }
                            }

                            if (destinationMarkers != null) {
                                for (Marker marker : destinationMarkers) {
                                    marker.remove();
                                }
                            }

                            if (polylinePaths != null) {
                                for (Polyline polyline : polylinePaths) {
                                    polyline.remove();
                                }
                            }
                            PrefsHelper.gethaved(getApplication(), "0");
                        }
                        if (PrefsHelper.sethaved(getApplication()).equals("3")) {
//                                new BookingFragment().dismiss();
//                            new BookingFragment().dismiss();
//                            BookingFragment bookingFragment = new BookingFragment();
//                            getFragmentManager().beginTransaction().remove(bookingFragment).commit();
//                            bookingFragment.dismiss();
//                            dismissdialoglayout();
//                            new BookingFragment().layoutdismiss();
                            Toast.makeText(getApplication(), "評分", Toast.LENGTH_SHORT).show();
                            if (originMarkers != null) {
                                for (Marker marker : originMarkers) {
                                    marker.remove();
                                }
                            }

                            if (destinationMarkers != null) {
                                for (Marker marker : destinationMarkers) {
                                    marker.remove();
                                }
                            }

                            if (polylinePaths != null) {
                                for (Polyline polyline : polylinePaths) {
                                    polyline.remove();
                                }
                            }
                            PrefsHelper.gethaved(getApplication(), "0");
                        }
                    }
                    break;
                case 2:
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            loaddriverlocation();
//                        }
//                    });
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
////                 booking = null;
//                            try {
//                                URL booking = new URL("https://my.here2go.asia///api_booking/get_once");
//                                httpURLConnection = (HttpURLConnection) booking.openConnection();
//                                httpURLConnection.setRequestMethod("POST");
//                                httpURLConnection.setDoOutput(true);
//                                httpURLConnection.setDoInput(true);
//                                String data = "order_id=" + PrefsHelper.setorderid(getApplication());
//
//                                OutputStream outputStream = httpURLConnection.getOutputStream();
//                                outputStream.write(data.getBytes());
//                                outputStream.flush();
//                                outputStream.close();
//                                int responseCode = httpURLConnection.getResponseCode();
////                            String sys_code = httpURLConnection.get;
//                                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
//                                StringBuilder sb = new StringBuilder();
//                                String line = null;
//                                while ((line = reader.readLine()) != null) {
//                                    sb.append(line + "\n");
//                                }
//
//                                System.out.println(" ====driverloacation responsecode" + sb + " ");
//                                System.out.println(" ======= orderid ==== " + PrefsHelper.setorderid(getApplication()));
////                            if(PrefsHelper.sethav)
//                                JSONObject jsonObject = new JSONObject(String.valueOf(sb));
//                                PrefsHelper.getdriverlat(getApplication(), jsonObject.getJSONObject("order_info").getJSONObject("partner").getString("lat"));
//                                PrefsHelper.getdriverlng(getApplication(), jsonObject.getJSONObject("order_info").getJSONObject("partner").getString("lng"));
//
////                    System.out.println(" gcm    ====== rate time mame number  " + rate + " " + drivername + " " + drivernumber + " " + gotime+" "+textnumber);
//                                if (jsonObject.getString("sys_code").equals("200")) {
//                                    System.out.println(" LOADDRIVER LOCATION 200 === " + PrefsHelper.setdrivercarclass(getApplication()));
//                                    switch (PrefsHelper.setdrivercarclass(getApplication())) {
//                                        case "Teks1m":
//                                            Double lat = Double.valueOf(PrefsHelper.setdriverlat(getApplication()));
//                                            Double lng = Double.valueOf(PrefsHelper.setdriverlng(getApplication()));
//                                            LatLng latLng = new LatLng(lat, lng);
//                                            driverlocation.add(mmap.addMarker(new MarkerOptions()
//                                                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.car_icon_golden))
//                                                    .title(PrefsHelper.setdrivername(getApplication()))
//                                                    .position(latLng)));
//                                            break;
//                                        case "Budget":
//                                            Double blat = Double.valueOf(PrefsHelper.setdriverlat(getApplication()));
//                                            Double blng = Double.valueOf(PrefsHelper.setdriverlng(getApplication()));
//                                            LatLng blatLng = new LatLng(blat, blng);
//                                            driverlocation.add(mmap.addMarker(new MarkerOptions()
//                                                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.car_icon_red))
//                                                    .title(PrefsHelper.setdrivername(getApplication()))
//                                                    .position(blatLng)));
//                                            System.out.println("add marker ===== ");
//                                            break;
//                                    }
//
//
//                                }
//
//                            } catch (MalformedURLException e) {
//                                e.printStackTrace();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                    }).start();
                    if (driverlocation != null) {
                        for (Marker marker : driverlocation) {
                            marker.remove();
                        }
                    }
                    loaddriverlocation();
                    System.out.println(" mainactivity 222 ==== yes");
                    break;
            }


            super.handleMessage(msg);

        }
    };
    private HttpURLConnection httpURLConnection;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println(" MAIN ACTIVITY RESULET ===== : " + requestCode + " " + resultCode + " " + data);
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle = this.getIntent().getExtras();
        String teststring = bundle.getString("1");
        System.out.println(" mainactivity ====== : " + teststring);
        System.out.println(" MAIN ACTIVITY RESULET ===== : " + requestCode + " " + resultCode + " " + data);
    }

    public void initview() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        aQuery = new AQuery(this);

        menunumberimg = (RelativeLayout) findViewById(R.id.menunumberimg);
        vnumberimg = (RelativeLayout) findViewById(R.id.vnumberimg);
        bottoncontainer = (RelativeLayout) findViewById(R.id.bottoncontainer);
        containermenubtn = (RelativeLayout) findViewById(R.id.containermenubtn);
        containermenubtn.setOnClickListener(this);
        menuimg = (ImageView) findViewById(R.id.menuimg);
        sosbtn = (ImageView) findViewById(R.id.sosbtn);
        iccnametxt = (TextView) findViewById(R.id.iccnametext);
        iccrate = (TextView) findViewById(R.id.iccratetxt);
        iccartxt = (TextView) findViewById(R.id.iccartxt);
        vnumbertxt = (TextView) findViewById(R.id.vnumbertxt);
        paymentbtn = (LinearLayout) findViewById(R.id.paymentbtn);
        paymentbtn.setOnClickListener(this);
        mybookingbtn = (LinearLayout) findViewById(R.id.mybookingbtn);
        mybookingbtn.setOnClickListener(this);
        verificationbtn = (LinearLayout) findViewById(R.id.verificationbtn);
        verificationbtn.setOnClickListener(this);
        menunumbertxt = (TextView) findViewById(R.id.menenumbertxt);
        helpbtn = (LinearLayout) findViewById(R.id.helpbtn);
        helpbtn.setOnClickListener(this);
        settingbtn = (LinearLayout) findViewById(R.id.settingbtn);
        settingbtn.setOnClickListener(this);
        drivetogobtn = (TextView) findViewById(R.id.drivetogotxt);
        drivetogobtn.setOnClickListener(this);
        officfindpathbtn = (RelativeLayout) findViewById(R.id.officbtnFindPath);
        officfindpathbtn.setOnClickListener(this);
        where2golayout = (RelativeLayout) findViewById(R.id.where2golayout);
        where2golayout.setOnClickListener(this);
        onthewaylayout = (RelativeLayout) findViewById(R.id.onthewaylayout);
//        onthewaylayout.setVisibility(View.VISIBLE);
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, 0 /* clientId */, this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        redimg = (ImageView) findViewById(R.id.redimg);
        callbtn = (RelativeLayout) findViewById(R.id.callbtn);
        callbtn.setOnClickListener(this);
        blueimg = (ImageView) findViewById(R.id.blueimg);
        loadlinearlayoout = (RelativeLayout) findViewById(R.id.linearlayoout);
        btnlayout = (LinearLayout) findViewById(R.id.btnlayout);
        avgbtn = (Button) findViewById(R.id.avgbtn);
//        avgbtn.setBackgroundResource(R.layout.testbtn);
        viewlayout = (LinearLayout) findViewById(R.id.viewlayout);
        menubtn = (RelativeLayout) findViewById(R.id.menubtn);
        menubtn.setOnClickListener(this);
        okbtn = (Button) findViewById(R.id.okbtn);
//        okbtn.setBackgroundResource(R.layout.testbtn);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewlayout.setVisibility(View.GONE);
                btnlayout.setVisibility(View.VISIBLE);
            }
        });
        menuback = (RelativeLayout) findViewById(R.id.menuback);
        menuback.setOnClickListener(this);
//        menunametxt = (TextView) findViewById(R.id.menunametxt);
//        menunametxt.setText(PrefsHelper.setusername(getApplication()));
        otwselectlayout = (LinearLayout) findViewById(R.id.otwselectlayout);
        container1 = (RelativeLayout) findViewById(R.id.container1);
        loadlinearlayoout.getBackground().setAlpha(240);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        showview = (RelativeLayout) findViewById(R.id.showview);
        icarlayout = (RelativeLayout) findViewById(R.id.incarlayout);
        icslectlayout = (LinearLayout) findViewById(R.id.iccselectlayout);
        sosbtn = (ImageView) findViewById(R.id.sosbtn);
        sosbtn.setOnClickListener(this);
        checkbtn = (Button) findViewById(R.id.checkbtn);

//        checkbtn.setBackgroundResource(R.layout.testbtn);
        checkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checklayout.setVisibility(View.GONE);
                sendRequest();
                viewlayout.setVisibility(View.VISIBLE);
//                btnFindPath.setVisibility(View.VISIBLE);
//                avgbtn.setVisibility(View.VISIBLE);
//                btnlayout.setVisibility(View.VISIBLE);
            }
        });


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        checklayout = (LinearLayout) findViewById(R.id.checklayout);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        nametxt = (TextView) findViewById(R.id.nametext);
        numbertxt = (TextView) findViewById(R.id.numbertxt);
        rattxt = (TextView) findViewById(R.id.ratetxt);
        dtxt = (TextView) findViewById(R.id.dtxt);
        btnFindPath = (RelativeLayout) findViewById(R.id.btnFindPath);
//        btnFindPath.setBackgroundResource(R.layout.testbtn);
        etOrigin = (AutoCompleteTextView) findViewById(R.id.etOrigin);
//        etOrigin.setBackgroundResource(R.layout.testedittxt);
        etOrigin.setOnItemClickListener(mAutocompleteClickListener);
        etOrigin.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                System.out.println("keyevent ===== : " + event);
                return false;
            }
        });
        etDestination = (AutoCompleteTextView) findViewById(R.id.etDestination);
        etDestination.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }
        });
        etDestination.setOnItemClickListener(mAutocompleteClickListener);
        etDestination.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence != null) {
//                    bookinglayout.setVisibility(View.VISIBLE);
                }
                if (etDestination.getText().toString().matches("")) {
//                    bookinglayout.setVisibility(View.GONE);
                }
                System.out.println(" etdestination ======beforeTextChanged :" + charSequence + " " + i + " " + i1 + " " + i2);
                btnFindPath.setVisibility(View.VISIBLE);
                officfindpathbtn.setVisibility(View.VISIBLE);


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (charSequence == null) {
//                    bookinglayout.setVisibility(View.GONE);
//                }
//                System.out.println(" etdestination ====== :" + );
                System.out.println(" etdestination ======onTextChanged :" + charSequence + " " + i + " " + i1 + " " + i2);
                btnFindPath.setVisibility(View.VISIBLE);
                officfindpathbtn.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
//                if (charSequence == null) {
//                    bookinglayout.setVisibility(View.GONE);
//                }
                System.out.println(" etdestination ======afterTextChanged :" + editable);
                btnFindPath.setVisibility(View.VISIBLE);
                officfindpathbtn.setVisibility(View.VISIBLE);
                if (editable.equals("")) {
                    btnFindPath.setVisibility(View.INVISIBLE);
                    officfindpathbtn.setVisibility(View.INVISIBLE);
                }
            }
        });

        placeAutocompleteAdapter = new PlaceAutocompleteAdapter(this, android.R.layout.simple_list_item_1
                , googleApiClient, BOUNDS_GREATER_SYDNEY, null);
        etDestination.setAdapter(placeAutocompleteAdapter);
        etOrigin.setAdapter(placeAutocompleteAdapter);
        btnFindPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getFragmentManager().beginTransaction().replace(R.id.container, new BookingSelectFragment()).commit();
//                container.setVisibility(View.VISIBLE);
                PrefsHelper.getstartadress(getApplication(), etOrigin.getText().toString());
                PrefsHelper.getendaddress(getApplication(), etDestination.getText().toString());
                sendRequest();
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        IntentService intentService = new IntentService(this);
        intentService.startGCM();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newdrawerlayout);
        initview();
        PrefsHelper.getgoreservatione(getApplication(), "0");
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (PrefsHelper.setorderid(getApplication()) != null) {
            if (PrefsHelper.sethaved(getApplication()).equals("1")) {

                System.out.println("mainactivity 1 ====== : " + PrefsHelper.setdrivername(getApplication()) + " " + PrefsHelper.setrate(getApplication()));
            }

        }
        if (PrefsHelper.setmcashdone(getApplication()) != null) {
            if (PrefsHelper.setmcashdone(getApplication()).equals("0")) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, PayDoneActivity.class);
                startActivity(intent);
                finish();
            }
        }
//        PrefsHelper.getbookingselect(getApplication(), 0);
        PrefsHelper.getbookingselect(getApplication(), "0");
        PrefsHelper.getlistselect(getApplication(), 0);
        PrefsHelper.getofficalbooking(getApplication(), "0");
//        loadorderinfo();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                Message message = new Message();
//                message.what = 1;
//                handler.sendMessage(message);
//                handler.postDelayed(this, 1000);
//            }
//        }).start();
        Loginactivity loginactivity = new Loginactivity();
        loginactivity.finish();

        if (PrefsHelper.sethaved(getApplication()) != null && PrefsHelper.sethaved(getApplication()).equals("7")) {
            Toast.makeText(MainActivity.this, "canceled", Toast.LENGTH_SHORT).show();
            PrefsHelper.gethaved(getApplication(), "0");
        }
        if (PrefsHelper.sethaved(getApplication()) != null && PrefsHelper.sethaved(getApplication()).equals("9")) {

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("We are very sorry！")
                    .setMessage("There was a situation that the driver can not reach your location.\n" +
                            "\n" +
                            "We will research the new driver for you. Thank you for your understanding.")

                    .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })

                    .show();
            PrefsHelper.getdriverotw(getApplication(), "0");
            PrefsHelper.gethaved(getApplication(), "0");
        }
        if (PrefsHelper.sethaved(getApplication()) != null && PrefsHelper.sethaved(getApplication()).equals("6")) {
            Toast.makeText(MainActivity.this, "We found you a driver for you order.", Toast.LENGTH_SHORT).show();
            PrefsHelper.gethaved(getApplication(), "0");
        }
        if (PrefsHelper.sethaved(getApplication()) != null && PrefsHelper.sethaved(getApplication()).equals("5")) {
            Toast.makeText(MainActivity.this, "Wait for order review.", Toast.LENGTH_SHORT).show();
            PrefsHelper.gethaved(getApplication(), "0");
        }
//        if (PrefsHelper.sethaved(getApplication()) != null && PrefsHelper.sethaved(getApplication()).equals("1")) {
////            Toast.makeText(MainActivity.this, "Wait for order review.", Toast.LENGTH_SHORT).show();
//            System.out.println("mainactivity 11 ====== : " + PrefsHelper.setdrivername(getApplication()) + " " + PrefsHelper.setrate(getApplication()));
////            PrefsHelper.gethaved(getApplication(), "0");
//            onthewaylayout.setVisibility(View.VISIBLE);
//
//        }
//        if (PrefsHelper.setdrivername(getApplication())!=nu){
//            nametxt.setText(PrefsHelper.setdrivername(getApplication()));
////            rattxt.setText(PrefsHelper.setrate(getApplication()));
//        }

        AutocompleteFilter filter = new AutocompleteFilter.Builder().setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES).build();


        if (PrefsHelper.setpushkey(getApplication()) == null) {
            pushkey();
        }
        if (PrefsHelper.sethaved(getApplication()) != null) {
            if (PrefsHelper.sethaved(getApplication()).equals("1")) {
                bottoncontainer.setVisibility(View.INVISIBLE);
                onthewaylayout.setVisibility(View.VISIBLE);
                otwselectlayout.setVisibility(View.VISIBLE);
                PrefsHelper.getdriverotw(getApplication(), "1");
                where2golayout.setVisibility(View.GONE);
                nametxt.setText(PrefsHelper.setdrivername(getApplication()));
                numbertxt.setText(PrefsHelper.setdrivercarclass(getApplication()) + "．" + PrefsHelper.settextnumber(getApplication()));
                rattxt.setText(PrefsHelper.setrate(getApplication()));
                dtxt.setText(PrefsHelper.setgotime(getApplication()));


//                loaddriverlocation();
//                updatadriverlocation();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
//                        loaddriverlocation();
                        Message message = new Message();
                        message.what = 2;
                        handler.sendMessage(message);
                        handler.postDelayed(this, 10000);
                    }
                });


            }
        }

        if (PrefsHelper.sethaved(getApplication()) != null) {
            if (PrefsHelper.sethaved(getApplication()).equals("2")) {
//                onthewaylayout.setVisibility(View.VISIBLE);
//                otwselectlayout.setVisibility(View.VISIBLE);
//                where2golayout.setVisibility(View.GONE);
//                nametxt.setText(PrefsHelper.setdrivername(getApplication()));
//                numbertxt.setText(PrefsHelper.setdriverphonenumber(getApplication()));
//                rattxt.setText(PrefsHelper.setrate(getApplication()));
//                dtxt.setText(PrefsHelper.setgotime(getApplication()));
                PrefsHelper.getdriverotw(getApplication(), "0");
                icarlayout.setVisibility(View.VISIBLE);
//                icslectlayout.setVisibility(View.VISIBLE);
                sosbtn.setVisibility(View.VISIBLE);
                where2golayout.setVisibility(View.INVISIBLE);
                iccrate.setText(PrefsHelper.setrate(getApplication()));
                iccnametxt.setText(PrefsHelper.setdrivername(getApplication()));
                iccartxt.setText(PrefsHelper.setdrivercarclass(getApplication()) + "．" + PrefsHelper.settextnumber(getApplication()));
            }
        }
//        bottoncontainer.setVisibility(View.VISIBLE);

        if (PrefsHelper.setlast5m(getApplication()) != null) {
            if (PrefsHelper.setlast5m(getApplication()).equals("1")) {
                getFragmentManager().beginTransaction().replace(R.id.bottoncontainer, new Last5minFragment()).commit();
                bottoncontainer.setVisibility(View.VISIBLE);
                System.out.println("mainactivity last30m ======= true + ");
                PrefsHelper.getlast5m(getApplication(), "0");
            }
        }
        if (PrefsHelper.setlast30m(getApplication()) != null) {
            if (PrefsHelper.setlast30m(getApplication()).equals("1")) {
                getFragmentManager().beginTransaction().replace(R.id.bottoncontainer, new BottomToast()).commit();
                bottoncontainer.setVisibility(View.VISIBLE);
                System.out.println("mainactivity last30m ======= true + ");
                PrefsHelper.getlast30m(getApplication(), "0");
            }
        }
        loadverificationnumberapi();
    }


    public void showendlocation() {
        Double slat = Double.valueOf(PrefsHelper.setendlat(getApplication()));
        Double slng = Double.valueOf(PrefsHelper.setendlng(getApplication()));
        System.out.println("showstartlocation ===== + " + slat + " " + slng);
        final LatLng slatlng = new LatLng(slat, slng);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.customendmarkerlayout, null);
                TextView startadresstxt = (TextView) marker.findViewById(R.id.endadresstxt);
                startadresstxt.setText(PrefsHelper.setendaddress(getApplication()));

//                CustomMarker customMarker = new CustomMarker(MainActivity.this);
//                mmap.setInfoWindowAdapter(customMarker);
//
//  mmap.addMarker(markerOpt).showInfoWindow();
                startlocation.add(mmap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(MainActivity.this, marker)))
                        .title(PrefsHelper.setendaddress(getApplication()))
                        .position(slatlng)));

//                System.out.println("add marker ===== " + blatLng);
            }
        });

    }

    public void showstartlocation() {
        Double slat = Double.valueOf(PrefsHelper.setstartlat(getApplication()));
        Double slng = Double.valueOf(PrefsHelper.setstartlng(getApplication()));
        System.out.println("showstartlocation ===== + " + slat + " " + slng);
        final LatLng slatlng = new LatLng(slat, slng);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custommarkerlayout, null);
                TextView startadresstxt = (TextView) marker.findViewById(R.id.startadresstxt);
                startadresstxt.setText(PrefsHelper.setstartadress(getApplication()));

//                customMarker = mMap.addMarker(new MarkerOptions()
//                        .position(markerLatLng)
//                        .title("Title")
//                        .snippet("Description")
//                        .icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this, marker))));

//                Double blat = Double.valueOf(PrefsHelper.setdriverlat(getApplication()));
//                Double blng = Double.valueOf(PrefsHelper.setdriverlng(getApplication()));
//                LatLng blatLng = new LatLng(blat, blng);

//                CustomMarker customMarker = new CustomMarker(MainActivity.this);
//                mmap.setInfoWindowAdapter(customMarker);
//
//  mmap.addMarker(markerOpt).showInfoWindow();
                startlocation.add(mmap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(MainActivity.this, marker)))
                        .title(PrefsHelper.setstartadress(getApplication()))
                        .position(slatlng)));

//                System.out.println("add marker ===== " + blatLng);
            }
        });

    }

    public static Bitmap createDrawableFromView(Context context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }

    public void updatadriverlocation() {
//        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();
//        System.out.println(" ===== route1 "+route1+ " route ==== "+route);
//        System.out.println(" ===== route ==== " + route);
//        for (Route route1 : route) {
////            mmap.moveCamera(CameraUpdateFactory.newLatLngZoom(route1.startLocation, 16));
//            ((TextView) findViewById(R.id.tvDuration)).setText(route1.duration.text);
//            ((TextView) findViewById(R.id.tvDistance)).setText(route1.distance.text);
//            String km = String.valueOf(route1.distance.value);
//            String startlat = String.valueOf(route1.startLocation.latitude);
//            String startlng = String.valueOf(route1.startLocation.longitude);
//            String endlat = String.valueOf(route1.endLocation.latitude);
//            String endlng = String.valueOf(route1.endLocation.longitude);
//            PrefsHelper.getkm(this, km);
//            PrefsHelper.getstartadress(this, etOrigin.getText().toString());
//            PrefsHelper.getendaddress(this, etDestination.getText().toString());
////            LatLng startlocation =
//            PrefsHelper.getstartlocation(this, startlat + "," + startlng);
//            System.out.println("  ===== lat lng === :" + endlng + "," + endlng);
//            PrefsHelper.getendlocation(this, endlat + "," + endlng);
//            PrefsHelper.gettimes(this, route1.duration.text);
////            PrefsHelper.getsteps(this,route1.st);
//
//
//            originMarkers.add(mmap.addMarker(new MarkerOptions()
//                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.location_green_normal))
//                    .title(route1.startAddress)
//                    .position(route1.startLocation)));
//            destinationMarkers.add(mmap.addMarker(new MarkerOptions()
//                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.location_red_normal))
//                    .title(route1.endAddress)
//                    .position(route1.endLocation)));
//
//            PolylineOptions polylineOptions = new PolylineOptions().
//                    geodesic(true).
//                    color(Color.RED).
//                    width(15);
//
//            for (int i = 0; i < route1.points.size(); i++)
//                polylineOptions.add(route1.points.get(i));
//
//            polylinePaths.add(mmap.addPolyline(polylineOptions));
//        }
        new Thread(new Runnable() {
            @Override
            public void run() {
//                 booking = null;
                try {
                    URL booking = new URL("https://my.here2go.asia///api_booking/get_once");
                    httpURLConnection = (HttpURLConnection) booking.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    String data = "order_id=" + PrefsHelper.setorderid(getApplication());

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    outputStream.write(data.getBytes());
                    outputStream.flush();
                    outputStream.close();
                    int responseCode = httpURLConnection.getResponseCode();
//                            String sys_code = httpURLConnection.get;
                    BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }

                    System.out.println(" ====driverloacation responsecode" + sb + " ");
                    System.out.println(" ======= orderid ==== " + PrefsHelper.setorderid(getApplication()));
//                            if(PrefsHelper.sethav)
                    JSONObject jsonObject = new JSONObject(String.valueOf(sb));
                    PrefsHelper.getdriverlat(getApplication(), jsonObject.getJSONObject("order_info").getJSONObject("partner").getString("lat"));
                    PrefsHelper.getdriverlng(getApplication(), jsonObject.getJSONObject("order_info").getJSONObject("partner").getString("lng"));

//                    System.out.println(" gcm    ====== rate time mame number  " + rate + " " + drivername + " " + drivernumber + " " + gotime+" "+textnumber);
                    if (jsonObject.getString("sys_code").equals("200")) {
                        System.out.println(" LOADDRIVER LOCATION 200 === " + PrefsHelper.setdrivercarclass(getApplication()));
//                        switch (PrefsHelper.setdrivercarclass(getApplication())) {
//                            case "Teks1m":
//                                Double lat = Double.valueOf(PrefsHelper.setdriverlat(getApplication()));
//                                Double lng = Double.valueOf(PrefsHelper.setdriverlng(getApplication()));
//                                LatLng latLng = new LatLng(lat, lng);
//                                driverlocation.add(mmap.addMarker(new MarkerOptions()
//                                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.car_icon_golden))
//                                        .title(PrefsHelper.setdrivername(getApplication()))
//                                        .position(latLng)));
//                                break;
//                            case "Budget":
//                                driverlocation = new ArrayList<>();
//                                Double blat = Double.valueOf(PrefsHelper.setdriverlat(getApplication()));
//                                Double blng = Double.valueOf(PrefsHelper.setdriverlng(getApplication()));
//                                LatLng blatLng = new LatLng(blat, blng);
//                                driverlocation.add(mmap.addMarker(new MarkerOptions()
//                                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.car_icon_red))
//                                        .title(PrefsHelper.setdrivername(getApplication()))
//                                        .position(blatLng)));
//                                System.out.println("add marker ===== "+ blatLng);
//                                break;
//                        }
                        if (PrefsHelper.setdrivercarclass(getApplication()).equals("Budget")) {
                            driverlocation = new ArrayList<>();
                            Double blat = Double.valueOf(PrefsHelper.setdriverlat(getApplication()));
                            Double blng = Double.valueOf(PrefsHelper.setdriverlng(getApplication()));
                            LatLng blatLng = new LatLng(blat, blng);
                            originMarkers.add(mmap.addMarker(new MarkerOptions()
                                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.car_icon_red))
                                    .title("")
                                    .position(blatLng)));
                        }


                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    public void havedriverview() {
        if (PrefsHelper.sethaved(getApplication()) != null) {
            if (PrefsHelper.sethaved(getApplication()).equals("1")) {

                container1.setVisibility(View.VISIBLE);
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
//                fragmentTransaction.remove(new BookSelectTimeFragment1());
//                fragmentTransaction.replace(R.id.container1, new OrderHaveDFragement());
//                fragmentTransaction.commit();
                getFragmentManager().beginTransaction().replace(R.id.container1, new OrderHaveDFragement()).commit();
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            Thread.sleep(3000);
//                            Message msg = new Message();
//                            msg.what=2;
//                            handler.sendMessage(msg);
//                            OrderHaveDFragement fragment = new OrderHaveDFragement();
                            OrderHaveDFragement fragment = new OrderHaveDFragement();
                            getFragmentManager().beginTransaction().remove(fragment).commit();
                            container1.setVisibility(View.GONE);
                            System.out.println("Mainactivity case 2 ====== : this");

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //    private AdapterView.OnItemClickListener autocompleteClickListener = new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            final PlaceAutocompleteAdapter.PlaceAutocomplete item = adapter.getItem(position);
//            final String placeId = String.valueOf(item.placeId);
//            Log.i(TAG, "Autocomplete item selected: " + item.description);
//            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi.getPlaceById(mGoogleApiClient, placeId);
//            placeResult.setResultCallback(mToUpdatePlaceDetailsCallback);
//            Log.i(TAG, "Called getPlaceById to get Place details for " + item.placeId);
//        }
//    };

    //    private AdapterView.OnItemClickListener mAutocompleteClickListener
//            = new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            /*
//             Retrieve the place ID of the selected item from the Adapter.
//             The adapter stores each Place suggestion in a AutocompletePrediction from which we
//             read the place ID and title.
//              */
//            final AutocompletePrediction item = adapter.getItem(position);
//            final String placeId = item.getPlaceId();
//            final CharSequence primaryText = item.getPrimaryText(null);
//
////            Log.i(TAG, "Autocomplete item selected: " + primaryText);
//
//            /*
//             Issue a request to the Places Geo Data API to retrieve a Place object with additional
//             details about the place.
//              */
//            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
//                    .getPlaceById(googleApiClient, placeId);
//            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
//
//            Toast.makeText(getApplicationContext(), "Clicked: " + primaryText,
//                    Toast.LENGTH_SHORT).show();
////            Log.i(TAG, "Called getPlaceById to get Place details for " + placeId);
//        }
//    };
//    //    private T mPlaceDetailsText;
//    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
//            = new ResultCallback<PlaceBuffer>() {
//        @Override
//        public void onResult(PlaceBuffer places) {
//            if (!places.getStatus().isSuccess()) {
//                // Request did not complete successfully
////                Log.e(TAG, "Place query did not complete. Error: " + places.getStatus().toString());
//                places.release();
//                return;
//            }
//            // Get the Place object from the buffer.
//            final Place place = places.get(0);
//
//            // Format details of the place for display and show it in a TextView.
//
////            mPlaceDetailsText.setText(formatPlaceDetails(getResources(), place.getName(),
////                    place.getId(), place.getAddress(), place.getPhoneNumber(),
////                    place.getWebsiteUri()));
//
//            // Display the third party attributions if set.
//            final CharSequence thirdPartyAttribution = places.getAttributions();
//            if (thirdPartyAttribution == null) {
////                mPlaceDetailsAttribution.setVisibility(View.GONE);
//            } else {
////                mPlaceDetailsAttribution.setVisibility(View.VISIBLE);
////                mPlaceDetailsAttribution.setText(Html.fromHtml(thirdPartyAttribution.toString()));
//            }
//
////            Log.i(TAG, "Place details received: " + place.getName());
//
//            places.release();
//        }
//    };
//
//    private static Spanned formatPlaceDetails(Resources res, CharSequence name, String id,
//                                              CharSequence address, CharSequence phoneNumber, Uri websiteUri) {
////        Log.e(TAG, res.getString(R.string.place_details, name, id, address, phoneNumber,
////                websiteUri));
//        return Html.fromHtml(res.getString(R.string.place_autocomplete_search_hint, name, id, address, phoneNumber,
//                websiteUri));
//
//    }
    public void loaddriverlocation() {

        new Thread(new Runnable() {
            @Override
            public void run() {
//                 booking = null;
                driverlocation = new ArrayList<>();
                try {
                    URL booking = new URL("https://my.here2go.asia///api_booking/get_once");
                    httpURLConnection = (HttpURLConnection) booking.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    String data = "order_id=" + PrefsHelper.setorderid(getApplication());

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    outputStream.write(data.getBytes());
                    outputStream.flush();
                    outputStream.close();
                    int responseCode = httpURLConnection.getResponseCode();
//                            String sys_code = httpURLConnection.get;
                    BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }

                    System.out.println(" ====driverloacation responsecode" + sb + " ");
                    System.out.println(" ======= orderid ==== " + PrefsHelper.setorderid(getApplication()));
//                            if(PrefsHelper.sethav)
                    JSONObject jsonObject = new JSONObject(String.valueOf(sb));
                    PrefsHelper.getdriverlat(getApplication(), jsonObject.getJSONObject("order_info").getJSONObject("partner").getString("lat"));
                    PrefsHelper.getdriverlng(getApplication(), jsonObject.getJSONObject("order_info").getJSONObject("partner").getString("lng"));

//                    System.out.println(" gcm    ====== rate time mame number  " + rate + " " + drivername + " " + drivernumber + " " + gotime+" "+textnumber);
                    if (jsonObject.getString("sys_code").equals("200")) {
                        System.out.println(" LOADDRIVER LOCATION 200 === " + PrefsHelper.setdrivercarclass(getApplication()));
                        switch (PrefsHelper.setdrivercarclass(getApplication())) {
                            case "Teks1m":
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Double blat = Double.valueOf(PrefsHelper.setdriverlat(getApplication()));
                                        Double blng = Double.valueOf(PrefsHelper.setdriverlng(getApplication()));
                                        LatLng blatLng = new LatLng(blat, blng);
                                        driverlocation.add(mmap.addMarker(new MarkerOptions()
                                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.car_icon_golden))
                                                .title(PrefsHelper.setdrivername(getApplication()))
                                                .position(blatLng)));
                                        System.out.println("add marker ===== " + blatLng);
                                    }
                                });
                                break;
                            case "Budget":


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Double blat = Double.valueOf(PrefsHelper.setdriverlat(getApplication()));
                                        Double blng = Double.valueOf(PrefsHelper.setdriverlng(getApplication()));
                                        LatLng blatLng = new LatLng(blat, blng);
                                        driverlocation.add(mmap.addMarker(new MarkerOptions()
                                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.car_icon_red))
                                                .title(PrefsHelper.setdrivername(getApplication()))
                                                .position(blatLng)));
                                        System.out.println("add marker ===== " + blatLng);
                                    }
                                });


                                break;
                            case "Executive":
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Double blat = Double.valueOf(PrefsHelper.setdriverlat(getApplication()));
                                        Double blng = Double.valueOf(PrefsHelper.setdriverlng(getApplication()));
                                        LatLng blatLng = new LatLng(blat, blng);
                                        driverlocation.add(mmap.addMarker(new MarkerOptions()
                                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.car_icon_blue))
                                                .title(PrefsHelper.setdrivername(getApplication()))
                                                .position(blatLng)));
                                        System.out.println("add marker ===== " + blatLng);
                                    }
                                });
                                break;
                        }


                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }


        return super.onOptionsItemSelected(item);
    }

//    public void imvblockbg() {
////        blockbg.setVisibility(View.INVISIBLE);
////        System.out.println("mainactivity ===== here : ");
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        switch (item.getItemId()) {
            case R.id.mybookings:
                Intent intent = new Intent();
//                PrefsHelper.getbookingselect(getApplication(), 1);
                intent.setClass(MainActivity.this, NewBookingActivity.class);
                startActivity(intent);
                finish();
//                relativeLayout.setVisibility(View.VISIBLE);
//                getFragmentManager().beginTransaction().replace(R.id.container, new MybookingsFragment()).commit();
                break;
            case R.id.payment:
//                relativeLayout.setVisibility(View.VISIBLE);
//                getFragmentManager().beginTransaction().replace(R.id.container, new MyprofileActivity()).commit();
//                Intent i = new Intent();
//                i.setClass(MainActivity.this, MyprofileActivity.class);
//                startActivity(i);
                break;
//            case R.id.pickupfavourites:
//                relativeLayout.setVisibility(View.VISIBLE);
//                getFragmentManager().beginTransaction().replace(R.id.container, new PickupFravouritesFragment()).commit();
//                break;
            case R.id.verification:
                Intent intent1 = new Intent();
                intent1.setClass(MainActivity.this, VerifiedActivity.class);
                startActivity(intent1);
                break;
            case R.id.remotevotrol:
                Intent intent4 = new Intent();
                intent4.setClass(MainActivity.this, FaqsActivity.class);
                startActivity(intent4);
//                relativeLayout.setVisibility(View.VISIBLE);
//                getFragmentManager().beginTransaction().replace(R.id.container, new RemotecontrolFramgnet()).commit();
                break;
            case R.id.faq:
//                relativeLayout.setVisibility(View.VISIBLE);
//                getFragmentManager().beginTransaction().replace(R.id.container, new FaqsActivity()).commit();
//                Intent intent5  ntent5);
                Intent intent2 = new Intent();
                intent2.setClass(MainActivity.this, SettingActivity.class);
                startActivity(intent2);

                break;
//            case R.id.logout:
//                new AlertDialog.Builder(this)
//                        .setTitle("")
//                        .setMessage("是否登出?")
//                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                            }
//                        })
//                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                PrefsHelper.getautologin(getApplication(), "0");
//                                System.exit(0);
//
//                            }
//                        }).show();
//                break;
//            case R.id.verified:
//                Intent intent1 = new Intent();
//                intent1.setClass(MainActivity.this, VerifiedActivity.class);
//                startActivity(intent1);
//                break;
            case R.id.joinme:
                Intent intent3 = new Intent();
                intent3.setClass(MainActivity.this, JoinMe.class);
                startActivity(intent3);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
//                Logger.d(String.format("%f, %f", location.getLatitude(), location.getLongitude()));
//                drawMarker(location);
//                mLocationManager.removeUpdates(mLocationListener);
            } else {
//                Logger.d("Location is null");
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {
        }

        @Override
        public void onProviderDisabled(String s) {
        }
    };


    public void getCurrentLocation() {
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        Location location = null;
        if (!(isGPSEnabled || isNetworkEnabled)) {
        }
//            Snackbar.make(mMapView, R.string.error_location_provider, Snackbar.LENGTH_INDEFINITE).show();
        else {
            if (isNetworkEnabled) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                        1000, 0, mLocationListener);
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

            if (isGPSEnabled) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        1000, 0, mLocationListener);
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
            locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER,
                    1000, 0, mLocationListener);
            location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        }
        Geocoder gc = new Geocoder(MainActivity.this, Locale.TRADITIONAL_CHINESE);
        lstAddress = null;

        System.out.println("mainactivity getcurrent ===== : " + location.toString() + " " + location);

        if (location.toString() != null) {
            nowlat = location.getLatitude();
            nowlng = location.getLongitude();
            try {
                lstAddress = gc.getFromLocation(nowlat, nowlng, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            if (lstAddress != null) {
                etOrigin.setText(lstAddress.get(0).getAddressLine(0));
            }

        }
//        if (!lstAddress.isEmpty()) {

        System.out.println(" ===== " + nowlng + " ===== lat" + nowlat);
    }

    @Override
    public void onCameraIdle() {

    }

    @Override
    protected void onStart() {
        super.onStart();

        getCurrentLocation();
        loadverificationnumberapi();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        mmap = googleMap;
//        mmap = googleMap;
//        mmap.setOnCameraIdleListener(this);
//        mmap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng latLng) {
//                System.out.println(" ==== onmapclick ==== : " + latLng);
//            }
//        });

//        mmap.setMyLocationEnabled(true);
        mmap.getUiSettings().setMyLocationButtonEnabled(true);
        mmap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                getCurrentLocation();
                if (lstAddress != null) {
//            String returnAddress = lstAddress.get(0).getAddressLine(0);
                    etOrigin.setText(lstAddress.get(0).getAddressLine(0));
                }

                return false;
            }
        });
        if (PrefsHelper.sethaved(getApplication()) != null) {
            if (PrefsHelper.sethaved(getApplication()).equals("1") || PrefsHelper.sethaved(getApplication()).equals("2")) {

            } else {
                //附近車機
                String url = "https://my.here2go.asia///api_booking/get_near?lat=" + nowlat + "&lng=" + nowlng;
                aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                    @Override
                    public void callback(String url, JSONObject object, AjaxStatus status) {

                        System.out.println(" ==== mainactivity car object ==== " + object);
                        try {

                            System.out.println(" ======car  mainactivityobject ==== " + object.getJSONArray("partners").toString());

                            for (int i = 0; i <= object.getJSONArray("partners").length(); i++) {
                                if (object.getJSONArray("partners").getJSONObject(i).getString("class").equals("Executive")) {
                                    LatLng hcmus = new LatLng(object.getJSONArray("partners").getJSONObject(i).getDouble("lat"), object.getJSONArray("partners").getJSONObject(i).getDouble("lng"));
                                    mmap.addMarker(new MarkerOptions()
                                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.car_icon_blue))
                                            .title(object.getJSONArray("partners").getJSONObject(i).getString("name").toString())
                                            .position(hcmus));
                                }
                                if (object.getJSONArray("partners").getJSONObject(i).getString("class").equals("Teks1m")) {
                                    LatLng hcmus = new LatLng(object.getJSONArray("partners").getJSONObject(i).getDouble("lat"), object.getJSONArray("partners").getJSONObject(i).getDouble("lng"));
                                    mmap.addMarker(new MarkerOptions()
                                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.car_icon_golden))
                                            .title(object.getJSONArray("partners").getJSONObject(i).getString("name").toString())
                                            .position(hcmus));
                                }
                                if (object.getJSONArray("partners").getJSONObject(i).getString("class").equals("Budget")) {
                                    LatLng hcmus = new LatLng(object.getJSONArray("partners").getJSONObject(i).getDouble("lat"), object.getJSONArray("partners").getJSONObject(i).getDouble("lng"));
                                    mmap.addMarker(new MarkerOptions()
                                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.car_icon_red))
                                            .title(object.getJSONArray("partners").getJSONObject(i).getString("name").toString())
                                            .position(hcmus));
                                }
                            }
//                        System.out.println(" ===== start object ==== " + object.getJSONArray("partners").getJSONObject(0).toString());
//                        for (int x = 0; x <= object.getJSONArray("partners").length(); x++) {
//                        System.out.println(" ===== start for object ==== "+ object.getJSONArray("partners").getJSONObject(0).toString());
//                        if (object.getJSONObject("partners").getString("class").equals("Budget")) {

//                        }
//                        if (object.getJSONObject("partners").getString("class").equals("Teks1m")) {
//                            LatLng hcmus1 = new LatLng(object.getJSONArray("partners").getJSONObject(i).getDouble("lat"), object.getJSONArray("partners").getJSONObject(i).getDouble("lng"));
//                            mmap.addMarker(new MarkerOptions()
//                                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.car_icon_golden))
//                                    .title(object.getJSONArray("partners").getJSONObject(i).getString("name").toString())
//                                    .position(hcmus1));
//                        }
//                        if (object.getJSONObject("partners").getString("class").equals("Executive")) {
//                            LatLng hcmus2 = new LatLng(object.getJSONArray("partners").getJSONObject(i).getDouble("lat"), object.getJSONArray("partners").getJSONObject(i).getDouble("lng"));
//                            mmap.addMarker(new MarkerOptions()
//                                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.car_icon_blue))
//                                    .title(object.getJSONArray("partners").getJSONObject(i).getString("name").toString())
//                                    .position(hcmus2));
//                        }

//                    }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }

//        mmap.getUiSettings().setAllGesturesEnabled(true);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            try {
//            } catch (NullPointerException e) {
//                Toast.makeText(this, " === " + e, Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
//        }
//
//        mmap.getCameraPosition().toString();
//
//        getCurrentFocus();


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mmap.setMyLocationEnabled(true);
//        mmap.setMapType(LITE);
        LatLng hcmus = new LatLng(nowlat, nowlng);
        mmap.moveCamera(CameraUpdateFactory.newLatLngZoom(hcmus, 17));


        if (PrefsHelper.sethaved(getApplication()) != null) {
            if (PrefsHelper.sethaved(getApplication()).equals("1")) {
                showstartlocation();
            }
        }
        if (PrefsHelper.sethaved(getApplication()) != null) {
            if (PrefsHelper.sethaved(getApplication()).equals("2")) {
                showendlocation();
            }
        }

    }

    public void mapsetonlongclick() {
        mmap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
//                onDirectionFinderSuccess(latLng);

//                Bundle b = latlngIntent.getBundleExtra("latlng");

                Double lat = latLng.latitude;
                Double lng = latLng.longitude;
                System.out.println(" ==== longonclicke ===== :" + lat + "   " + lng);

                Geocoder gc = new Geocoder(MainActivity.this, Locale.TRADITIONAL_CHINESE);
                List<Address> lstAddress = null;
                try {
                    lstAddress = gc.getFromLocation(lat, lng, 1);
//                    onDirectionFinderSuccess(lstAddress);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String returnAddress = lstAddress.get(0).getAddressLine(0);

//                mmap.addMarker(new MarkerOptions().position(latLng));
                String origin = etOrigin.getText().toString();


                String destination = returnAddress;
                System.out.println(" ==== longonclick origin ==== :" + origin);
                System.out.println(" ==== longonclick returnaddress ==== :" + returnAddress);
                if (origin.isEmpty()) {
//                    Toast.makeText(MainActivity.this, "Please enter origin address!", Toast.LENGTH_SHORT).show();
                    return;
                }
//                if (destination.isEmpty()) {
//                    Toast.makeText(this, "Please enter destination address!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                    onDirectionFinderSuccess();
//                new DirectionFinder(MainActivity.this, origin, destination);
                try {
                    new DirectionFinder(MainActivity.this, origin, destination).execute();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
//                    Toast.makeText(MainActivity.this, (CharSequence) e,Toast.LENGTH_SHORT).show();
                }
                etDestination.setText(returnAddress);
            }
        });
    }

    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(this, "Please wait.",
                "", true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline : polylinePaths) {
                polyline.remove();
            }
        }
    }

    private void offcialsRequest() {
//        menuback.setVisibility(View.VISIBLE);
        String origin = etOrigin.getText().toString();
        String destination = etDestination.getText().toString();
        if (origin.isEmpty()) {
            Toast.makeText(this, "Please enter origin address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (destination.isEmpty()) {
            Toast.makeText(this, "Please enter destination address!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            new DirectionFinder(this, origin, destination).execute();
            container1.setVisibility(View.VISIBLE);
//            blockbg.setVisibility(View.VISIBLE);
            showview.setVisibility(View.VISIBLE);
//            etOrigin.setFocusable(false);
//            etDestination.setFocusable(false);
            btnFindPath.setVisibility(View.INVISIBLE);
            redimg.setVisibility(View.GONE);
            blueimg.setVisibility(View.VISIBLE);
            officfindpathbtn.setVisibility(View.INVISIBLE);
            PrefsHelper.getofficalbooking(getApplication(), "1");
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
            fragmentTransaction.remove(new BookSelectTimeFragment1());
            fragmentTransaction.replace(R.id.showview, new BookSelectTimeFragment1());
            fragmentTransaction.commit();

//            getFragmentManager().beginTransaction().replace(R.id.showview, new BookSelectTimeFragment1()).commit();
//            new BookingFragment().show(getFragmentManager(), "");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private void sendRequest() {
        String origin = etOrigin.getText().toString();
        String destination = etDestination.getText().toString();
        if (origin.isEmpty()) {
            Toast.makeText(this, "Please enter origin address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (destination.isEmpty()) {
            Toast.makeText(this, "Please enter destination address!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            new DirectionFinder(this, origin, destination).execute();
            container1.setVisibility(View.VISIBLE);
//            menuback.setVisibility(View.VISIBLE);
//            blockbg.setVisibility(View.VISIBLE);
            showview.setVisibility(View.VISIBLE);
            btnFindPath.setVisibility(View.INVISIBLE);
//            etOrigin.setFocusable(false);
//            etDestination.setFocusable(false);
            officfindpathbtn.setVisibility(View.INVISIBLE);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
            fragmentTransaction.remove(new BookSelectTimeFragment1());
            fragmentTransaction.replace(R.id.showview, new BookSelectTimeFragment1());
            fragmentTransaction.commit();
//            getFragmentManager().beginTransaction().replace(R.id.showview, new BookSelectTimeFragment1()).commit();
//            new BookingFragment().show(getFragmentManager(), "");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDirectionFinderSuccess(List<Route> route) {
        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();
//        System.out.println(" ===== route1 "+route1+ " route ==== "+route);
        System.out.println(" ===== route ==== " + route);
        for (Route route1 : route) {
//            mmap.moveCamera(CameraUpdateFactory.newLatLngZoom(route1.startLocation, 16));
            ((TextView) findViewById(R.id.tvDuration)).setText(route1.duration.text);
            ((TextView) findViewById(R.id.tvDistance)).setText(route1.distance.text);
            String km = String.valueOf(route1.distance.value);
            String startlat = String.valueOf(route1.startLocation.latitude);
            String startlng = String.valueOf(route1.startLocation.longitude);
            String endlat = String.valueOf(route1.endLocation.latitude);
            String endlng = String.valueOf(route1.endLocation.longitude);
            PrefsHelper.getkm(this, km);
            PrefsHelper.getstartadress(this, etOrigin.getText().toString());
            PrefsHelper.getendaddress(this, etDestination.getText().toString());
//            LatLng startlocation =
            PrefsHelper.getstartlocation(this, startlat + "," + startlng);
            System.out.println("  ===== lat lng === :" + endlng + "," + endlng + " startlat " + startlat);
            PrefsHelper.getendlocation(this, endlat + "," + endlng);
            PrefsHelper.gettimes(this, route1.duration.text);
//            PrefsHelper.getsteps(this,route1.st);
            PrefsHelper.getstartlat(this, startlat);
            PrefsHelper.getstartlng(this, startlng);
            PrefsHelper.getendlat(this, endlat);
            PrefsHelper.getendlng(this, endlng);
//            PrefsHelper.getstartlat(this,route1.startAddress.);
            originMarkers.add(mmap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.location_green_normal))
                    .title(route1.startAddress)
                    .position(route1.startLocation)));
            destinationMarkers.add(mmap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.location_red_normal))
                    .title(route1.endAddress)
                    .position(route1.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.RED).
                    width(15);

            for (int i = 0; i < route1.points.size(); i++)
                polylineOptions.add(route1.points.get(i));

            polylinePaths.add(mmap.addPolyline(polylineOptions));
        }
    }

    private static Boolean isExit = false;
    private static Boolean hasTask = false;

    Timer timerExit = new Timer();
    TimerTask task = new TimerTask() {

        @Override
        public void run() {
            isExit = false;
            hasTask = true;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 判斷是否按下Back
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            relativeLayout.setVisibility(View.INVISIBLE);
            // 是否要退出
            if (isExit == false) {
                isExit = true; //記錄下一次要退出
//                Toast.makeText(this, "再按一次返回離開程式", Toast.LENGTH_SHORT).show();
                // 如果超過兩秒則恢復預設值
                if (!hasTask) {
                    timerExit.schedule(task, 1000);
                }
            } else {
//                new AlertDialog.Builder(this)
//                        .setTitle("")
//                        .setMessage("exit app?")
//                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                            }
//                        })
//                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                System.exit(0);
//
//                            }
//                        }).show();
            }
        }
        return false;
    }

    public void loadnearcar() {
        String url = "https://my.here2go.asia///api_booking/get_near?lat=" + nowlat + "&lng=" + nowlng;
//        String url="http://uat.fofo.tw/2go_be/api/get_partner_once?lat=" + nowlat + "&lng=" + nowlng;
        aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {

                System.out.println(" ==== car mainactivity ==== " + object);
                try {
                    System.out.println(" ===== start object ==== " + object.getJSONArray("partners").getJSONObject(0).toString());
                    for (int x = 0; x <= object.getJSONArray("partners").length(); x++) {
//                        System.out.println(" ===== start for object ==== "+ object.getJSONArray("partners").getJSONObject(0).toString());
                        LatLng hcmus = new LatLng(object.getJSONArray("partners").getJSONObject(x).getDouble("lat"), object.getJSONArray("partners").getJSONObject(x).getDouble("lng"));
                        mmap.addMarker(new MarkerOptions()
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.car))
                                .title(object.getJSONArray("partners").getJSONObject(x).getString("name").toString())
                                .position(hcmus));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                try {
//                    for (int i = 0; i <= object.getJSONArray("partners").length(); i++) {
//                        System.out.println(" ====== startobject ==== " + object.getJSONObject("partners").length());
//
//
//                        System.out.println(" ===== hcmus :" + hcmus);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    public void pushkey() {
        String url = "http://uat.fofo.tw/2go_be/api/member_push_key_setting?email=" + PrefsHelper.setmail(getApplication()) + "&os=android&push_key=" + PrefsHelper.setregiid(getApplication());
        //uat.fofo.tw/2go_be/api/member_push_key_setting?email=silence5105@gmail.com&os=android&push_key=AIzaSyC0hgFIW2LT-WAUh_KMmE6lLsn5Z-CkMQc
//        if(PrefsHelper.)
        aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                System.out.println(" ===== pushkeyupdata " + object);
                try {
                    if (object.getString("sys_code").equals("200")) {
                        PrefsHelper.getpushkey(getApplication(), "1");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private AdapterView.OnItemClickListener mAutocompleteClickListener
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            /*
             Retrieve the place ID of the selected item from the Adapter.
             The adapter stores each Place suggestion in a PlaceAutocomplete object from which we
             read the place ID.
              */
            final PlaceAutocompleteAdapter.PlaceAutocomplete item = placeAutocompleteAdapter.getItem(position);
            final String placeId = String.valueOf(item.placeId);
//            Log.i(TAG, "Autocomplete item selected: " + item.description);

            /*
             Issue a request to the Places Geo Data API to retrieve a Place object with additional
              details about the place.
              */
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(googleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
//            Log.i(TAG, "Called getPlaceById to get Place details for " + item.placeId);
        }

        private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
                = new ResultCallback<PlaceBuffer>() {
            @Override
            public void onResult(PlaceBuffer places) {
                if (!places.getStatus().isSuccess()) {
                    // Request did not complete successfully
//                    Log.e(TAG, "Place query did not complete. Error: " + places.getStatus().toString());
                    places.release();
                    return;
                }
                // Get the Place object from the buffer.
                final Place place = places.get(0);

                // Format details of the place for display and show it in a TextView.
//                mPlaceDetailsText.setText(formatPlaceDetails(getResources(), place.getName(),
//                        place.getId(), place.getAddress(), place.getPhoneNumber(),
//                        place.getWebsiteUri()));

                // Display the third party attributions if set.
                final CharSequence thirdPartyAttribution = places.getAttributions();
                if (thirdPartyAttribution == null) {
//                    mPlaceDetailsAttribution.setVisibility(View.GONE);
                } else {
//                    mPlaceDetailsAttribution.setVisibility(View.VISIBLE);
//                    mPlaceDetailsAttribution.setText(Html.fromHtml(thirdPartyAttribution.toString()));
                }

//                Log.i(TAG, "Place details received: " + place.getName());

                places.release();
            }
        };
    };

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this,
                "Could not connect to Google API Client: Error " + connectionResult.getErrorCode(),
                Toast.LENGTH_SHORT).show();
    }

    public void loadverificationnumberapi() {
        String url = "https://my.here2go.asia///api_official/pending_review?member_id=" + PrefsHelper.setphonenumber(getApplication());
        aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                try {
                    if (object.getString("sys_code").equals("200")) {
                        if (object.getString("count").equals("0")) {
                            vnumberimg.setVisibility(View.INVISIBLE);
                            menunumberimg.setVisibility(View.INVISIBLE);
                        } else {
                            vnumbertxt.setText(object.getString("count"));
                            menunumbertxt.setText(object.getString("count"));
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.drivetogotxt:
                Intent intent4 = new Intent();
                intent4.setClass(MainActivity.this, JoinMe.class);
                startActivity(intent4);
                break;
            case R.id.settingbtn:
                Intent intent3 = new Intent();
                intent3.setClass(MainActivity.this, SettingActivity.class);
                startActivity(intent3);
                break;
            case R.id.helpbtn:
                Intent intent2 = new Intent();
                intent2.setClass(MainActivity.this, FaqsActivity.class);
                startActivity(intent2);
                break;
            case R.id.verificationbtn:
                Intent intent1 = new Intent();
                intent1.setClass(MainActivity.this, VerifiedActivity.class);
                startActivity(intent1);
                break;
            case R.id.mybookingbtn:
                Intent intent = new Intent();
                PrefsHelper.getbookingselect(getApplication(), "1");
                intent.setClass(MainActivity.this, NewBookingActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.menuback:
                cleanmap();
                break;
            case R.id.sosbtn:
                SmsManager smsMgr = SmsManager.getDefault();
                smsMgr.sendTextMessage("0920516317", null, "sos", null, null);
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.putExtra("sms_body", "default content");
                sendIntent.setType("vnd.android-dir/mms-sms");
                startActivity(sendIntent);
                Toast.makeText(MainActivity.this, "sended sms.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.callbtn:
                Intent call = new Intent(CALL, Uri.parse("tel:" + PrefsHelper.setdriverphonenumber(getApplication())));
                startActivity(call);
                break;
            case R.id.containermenubtn:
                cleanmap();

                break;
            case R.id.where2golayout:
                where2golayout.setVisibility(View.INVISIBLE);
                mapsetonlongclick();
                menuback.setVisibility(View.VISIBLE);
//                btnFindPath.setVisibility(View.VISIBLE);
//                officfindpathbtn.setVisibility(View.VISIBLE);
                loadlinearlayoout.setVisibility(View.VISIBLE);
//                menubtn.setImageDrawable(getResources().getDrawable(R.mipmap.arrow_left_black));
                menubtn.setVisibility(View.GONE);
                containermenubtn.setVisibility(View.VISIBLE);
                break;
            case R.id.menubtn:
//                menubtn.setImageDrawable(getResources().getDrawable(R.mipmap.menu_black));
//                loadlinearlayoout.setVisibility(View.INVISIBLE);
//                where2golayout.setVisibility(View.VISIBLE);
//                btnFindPath.setVisibility(View.INVISIBLE);
//                officfindpathbtn.setVisibility(View.INVISIBLE);
//                mmap.setOnMapLongClickListener(null);
//                drawer.closeDrawer(GravityCompat.START);
                drawer.openDrawer(GravityCompat.START);
                break;
            case R.id.officbtnFindPath:

                if (PrefsHelper.setofficaltype(getApplication()).equals("false")) {
                    new AlertDialog.Builder(this)
                            .setTitle("")
                            .setMessage("This service is for Official Out of Office Business Travel/Meeting Services. If interested, kindly inform your employer to register or kindly email your request to below email and we will contact your employer for further details")
                            //
                            .setNegativeButton("Confirm", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setPositiveButton("baharidris@2go.com.my", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
//                                    Intent intent = new Intent((Intent.ACTION_VIEW);
//                                    intent = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");

//                                    startActivity(intent);
                                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "baharidris@2go.com.my", null));
                                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                                    getApplication().startActivity(Intent.createChooser(emailIntent, null));
                                }
                            }).show();
                }

                if (PrefsHelper.setofficaltype(getApplication()).equals("true")) {
                    PrefsHelper.getstartadress(getApplication(), etOrigin.getText().toString());
                    PrefsHelper.getendaddress(getApplication(), etDestination.getText().toString());

//                btnlayout.setVisibility(View.GONE);
//                checklayout.setVisibility(View.VISIBLE);

//                    new AlertDialog.Builder(MainActivity.this)
//                            .setTitle("What Purpose?")
//                            .setItems(liststring.toArray(new String[liststring.size()]), new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    String name = liststring.get(which);
////                                    Toast.makeText(getApplicationContext(),   name, Toast.LENGTH_SHORT).show();
//
//                                    new AlertDialog.Builder(MainActivity.this)
//                                            .setTitle(name)
//                                            .setView(v)
//                                            .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialogInterface, int i) {
//
//                                                }
//                                            })
//                                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialog, int which) {
//                                                    EditText editText = (EditText) (v.findViewById(R.id.editText));
////                                                    Toast.makeText(getApplicationContext(), "你的id是" +
////
////                                                            editText.getText().toString(), Toast.LENGTH_SHORT).show();
//                                                }
//                                            })
//
//                                            .show();
//                                }
//                            })
//                            .show();
                    offcialsRequest();
//                    sendRequest();
//                    btnFindPath.setVisibility(View.INVISIBLE);
//                    officfindpathbtn.setVisibility(View.INVISIBLE);
                }
                break;
        }
    }

    public class BookSelectTimeFragment1 extends Fragment implements View.OnClickListener {
        TextView mytxt, dtimetxt, officletxt, oktxt, canceltxt;
        DatePickerDialog datePickerDialog;
        private int mYear, mMonth, mDay, mH, mm;
        DatePickerDialog dpd;
        TimePickerDialog timePicker;
        RelativeLayout cancelbtn, nextbtn;
        Date nowdate, chosdate, ynowdate, ychosdate;

        int AM_PM;
        private RelativeLayout bnext;
        private RelativeLayout bcancelbtn;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.bookselecttimelayout, container, false);
            nextbtn = (RelativeLayout) view.findViewById(R.id.nextbtn);
            mytxt = (TextView) view.findViewById(R.id.mytxt);
//            oktxt = (TextView) view.findViewById(R.id.oktxt);
//            canceltxt = (TextView) view.findViewById(R.id.canceltxt);
            bcancelbtn = (RelativeLayout) view.findViewById(R.id.bcanclebtn);
            bcancelbtn.setOnClickListener(this);
            mytxt.setOnClickListener(this);
            bnext = (RelativeLayout) view.findViewById(R.id.bnextbtn);
            bnext.setOnClickListener(this);
            bnext.setVisibility(View.INVISIBLE);
            dtimetxt = (TextView) view.findViewById(R.id.dtimetxt);
            nextbtn.setOnClickListener(this);
            cancelbtn = (RelativeLayout) view.findViewById(R.id.canclebtn);
            cancelbtn.setOnClickListener(this);
            PrefsHelper.getlistselect(getActivity(), 0);
            officletxt = (TextView) view.findViewById(R.id.officletxt);
            final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            final SimpleDateFormat dDateFormat = new SimpleDateFormat("HH:mm");
//        String date=sdf.format(new java.util.Date());
//            mytxt.setText(sDateFormat.format(new java.util.Date()));
//            dtimetxt.setText(dDateFormat.format(new java.util.Date()));
            mytxt.setText(Html.fromHtml("<u>" + sDateFormat.format(new java.util.Date()) + "</u>"));
            dtimetxt.setText(Html.fromHtml("<u>" + dDateFormat.format(new java.util.Date()) + "</u>"));
//            System.out.println("time ===== : " + testformat.format(new Date()));
            try {
                nowdate = dDateFormat.parse(dDateFormat.format(new Date()));
                ynowdate = sDateFormat.parse(sDateFormat.format(new Date()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dtimetxt.setOnClickListener(this);
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            mYear = gregorianCalendar.get(Calendar.YEAR);
            mMonth = gregorianCalendar.get(Calendar.MONTH);
            mDay = gregorianCalendar.get(Calendar.DAY_OF_MONTH);
            mH = gregorianCalendar.get(Calendar.HOUR_OF_DAY);
            mm = gregorianCalendar.get(Calendar.MINUTE);
            AM_PM = gregorianCalendar.get(Calendar.AM_PM);
            System.out.println("TIME ====== : AM_PM  " + AM_PM);
            dpd = new DatePickerDialog(getActivity(),
                    new DatePickerDialog.OnDateSetListener() {
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            mytxt.setText(year + "-" + (monthOfYear + 1) + "-"
                                    + dayOfMonth);
                            try {
                                ychosdate = sDateFormat.parse(mytxt.getText().toString());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            if (PrefsHelper.setgoreservatione(getActivity()) != null || PrefsHelper.setgoreservatione(getActivity()) == null) {
                                Long ynowt = ynowdate.getTime();
                                Long ychost = ychosdate.getTime();
                                Long yanser = ychost - ynowt;
                                Long ydt = yanser / 86400000;
                                System.out.println("TIME Y ====== + " + yanser + " " + ynowt + " " + ychost);
                                if (ydt >= 1) {
                                    PrefsHelper.getgoreservatione(getActivity(), "1");
                                }
                            }
                            if (PrefsHelper.setofficalbooking(getActivity()) != null) {
                                if (PrefsHelper.setofficalbooking(getActivity()).equals("1")) {
                                    Long ynowt = ynowdate.getTime();
                                    Long ychost = ychosdate.getTime();
                                    Long yanser = ychost - ynowt;
                                    Long ydt = yanser / 86400000;
                                    System.out.println("TIME Y ====== + " + yanser + " " + ynowt + " " + ychost);
                                    if (ydt >= 1) {
//                                        oktxt.setVisibility(View.VISIBLE);
                                        bnext.setVisibility(View.VISIBLE);
                                    }


                                }
                            }

                        }
                    }, mYear, mMonth, mDay);

            timePicker = new TimePickerDialog(getActivity(),
                    new TimePickerDialog.OnTimeSetListener() {
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {


//                            if (hourOfDay < 12) {
//                                AM_PM = false;
//                            }if(hourOfDay>12){
//                                AM_PM = true;
//                            }


                            dtimetxt.setText(hourOfDay + ":" + minute);
                            try {
                                chosdate = dDateFormat.parse(dtimetxt.getText().toString());
                                System.out.println("time ======= : " + nowdate + " " + chosdate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            if (PrefsHelper.setofficalbooking(getActivity()) != null || PrefsHelper.setofficalbooking(getActivity()) == null) {
                                if (PrefsHelper.setofficalbooking(getActivity()).equals("0")) {
                                    Long nowt = nowdate.getTime();
                                    Long chost = chosdate.getTime();
                                    Long ansert = chost - nowt;
                                    Long min = ansert / 60000;
                                    if (min >= 30) {
                                        PrefsHelper.getgoreservatione(getActivity(), "1");
                                    }
                                    if (min < 30) {
                                        PrefsHelper.getgoreservatione(getActivity(), "0");
                                    }
                                }
                            }
                            if (PrefsHelper.setofficalbooking(getActivity()) != null) {
                                if (PrefsHelper.setofficalbooking(getActivity()).equals("1")) {
                                    Long nowt = nowdate.getTime();
                                    Long chost = chosdate.getTime();
                                    Long ansert = chost - nowt;
                                    Long min = ansert / 60000;
                                    if (min >= 45) {
//                                        oktxt.setVisibility(View.VISIBLE);
                                        bnext.setVisibility(View.VISIBLE);
                                    }
                                    if (min < 45) {
                                        Toast.makeText(MainActivity.this, "Need to advance 45 minutes ahead of time", Toast.LENGTH_SHORT).show();
                                    }
                                    System.out.println("Time 2 ======== : " + min + " " + ansert);
                                }
                            }


//                        hh = String.valueOf(hourOfDay);
//                        hh = hourOfDay;
                        }
                    }, mH, mm, true);
//        dpd.show();
            if (PrefsHelper.setofficalbooking(getActivity()).equals("0")) {
//                oktxt.setVisibility(View.INVISIBLE);
                officletxt.setVisibility(View.INVISIBLE);
//                oktxt.setText(R.string.officialbookingok);
                nextbtn.setVisibility(View.VISIBLE);
                bnext.setVisibility(View.INVISIBLE);
//                canceltxt.setText(R.string.officialbookingcancel);
                cancelbtn.setVisibility(View.VISIBLE);
                bcancelbtn.setVisibility(View.INVISIBLE);
            }

            if (PrefsHelper.setofficalbooking(getActivity()).equals("1")) {
//                oktxt.setVisibility(View.INVISIBLE);
                officletxt.setVisibility(View.VISIBLE);
//                oktxt.setText(R.string.officialbookingok);
//                bnext.setVisibility(View.VISIBLE);
                nextbtn.setVisibility(View.INVISIBLE);
                cancelbtn.setVisibility(View.INVISIBLE);
                bcancelbtn.setVisibility(View.VISIBLE);
//                canceltxt.setText(R.string.officialbookingcancel);
            }
            return view;
        }


        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bnextbtn:
                    String hh = dtimetxt.getText().toString().substring(0, 2);
                    PrefsHelper.gethtime(getActivity(), hh);
//                getActivity().
                    String alltime = mytxt.getText().toString() + " " + dtimetxt.getText().toString();
//                getActivity().imvblockbg();
//                    imvblockbg();
                    System.out.println("alltime ====== : " + alltime);
                    menubtn.setVisibility(View.GONE);
                    containermenubtn.setVisibility(View.VISIBLE);
//                    BookSelectTimeFragment1 bookSelectTimeFragment1 = new BookSelectTimeFragment1();
                    PrefsHelper.getalltime(getActivity(), alltime);
                    FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
                    fragmentTransaction.remove(new BookSelectTimeFragment1());
                    fragmentTransaction.replace(R.id.showview, new BookingCarSelectFragment1());
                    fragmentTransaction.commit();
                    break;
                case R.id.bcanclebtn:
                    cleanmap();
                    break;
                case R.id.canclebtn:
                    cleanmap();
                    break;
                case R.id.nextbtn:
                    String hhh = dtimetxt.getText().toString().substring(0, 2);
                    PrefsHelper.gethtime(getActivity(), hhh);
//                getActivity().
                    String alltime1 = mytxt.getText().toString() + " " + dtimetxt.getText().toString();
//                getActivity().imvblockbg();
//                    imvblockbg();
                    System.out.println("alltime ====== : " + alltime1);
                    menubtn.setVisibility(View.GONE);
                    containermenubtn.setVisibility(View.VISIBLE);
//                    BookSelectTimeFragment1 bookSelectTimeFragment1 = new BookSelectTimeFragment1();
                    PrefsHelper.getalltime(getActivity(), alltime1);
                    FragmentTransaction fragmentTransaction1 = getActivity().getFragmentManager().beginTransaction();
                    fragmentTransaction1.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
                    fragmentTransaction1.remove(new BookSelectTimeFragment1());
                    fragmentTransaction1.replace(R.id.showview, new BookingCarSelectFragment1());
                    fragmentTransaction1.commit();
//                    getActivity().getFragmentManager().beginTransaction().replace(R.id.showview, new BookingCarSelectFragment1()).commit();

                    break;
                case R.id.mytxt:
                    dpd.show();
                    break;
                case R.id.dtimetxt:
                    timePicker.show();
                    break;
            }
        }
    }

    public void cleanmap() {
        container1.setVisibility(View.INVISIBLE);
        etDestination.setText("");
        menuback.setVisibility(View.INVISIBLE);
        containermenubtn.setVisibility(View.GONE);
        menubtn.setVisibility(View.VISIBLE);
        showview.setVisibility(View.INVISIBLE);
        PrefsHelper.getofficalbooking(getApplication(), "0");
        where2golayout.setVisibility(View.VISIBLE);
        blueimg.setVisibility(View.GONE);
        redimg.setVisibility(View.VISIBLE);
        menuimg.setImageDrawable(getResources().getDrawable(R.mipmap.menu_black));
        loadlinearlayoout.setVisibility(View.INVISIBLE);
        btnFindPath.setVisibility(View.INVISIBLE);
        officfindpathbtn.setVisibility(View.INVISIBLE);
        mmap.setOnMapLongClickListener(null);
//        onDirectionFinderStart();
        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline : polylinePaths) {
                polyline.remove();
            }
        }
    }

    public int SHOWVIEWINV = 0;

    public class BookingCarSelectFragment1 extends Fragment implements View.OnClickListener {
        TextView faretxt, officialcattxt, budgettxt, teks1mtxt, executivetxt;
        LinearLayout teks1mbtn, budgetbtn, extbtn;
        ImageView t1mimg, budgetimg, extimg, officialcarimg;
        RelativeLayout bookingbtn, officialbookingbtn;
        AQuery aQuery;
        public ProgressDialog dialog;
        HttpURLConnection httpURLConnection;
        RelativeLayout offciallayout, bookingclayout;
        Spinner sp;
        ArrayList<String> cashlist = new ArrayList<>();

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.bookingcarselectlayout, container, false);
            aQuery = new AQuery(getActivity());
            bookingbtn = (RelativeLayout) view.findViewById(R.id.bookingbtn);
            bookingbtn.setOnClickListener(this);
            teks1mbtn = (LinearLayout) view.findViewById(R.id.teks1mcarebtn);
            teks1mbtn.setOnClickListener(this);
            budgetbtn = (LinearLayout) view.findViewById(R.id.buggetbtn);
            budgetbtn.setOnClickListener(this);
            sp = (Spinner) view.findViewById(R.id.sp);
            cashlist.add("Cash");
            cashlist.add("MCash");
            cashlist.add("amBank");
            ArrayAdapter<String> starttimelist = new ArrayAdapter<>(getApplication(),
                    android.R.layout.simple_spinner_dropdown_item,
                    cashlist);
            sp.setAdapter(starttimelist);
            final ImageView cashimg = (ImageView) view.findViewById(R.id.cashimg);
            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    System.out.println("select = : " + parent + " " + view.getId() + " " + position + " " + id);
                    if (position == 1) {
                        cashimg.setImageResource(R.mipmap.mcash);
                        PrefsHelper.getcashtype(getApplication(), "mcash");
                    }
                    if (position == 0) {
                        cashimg.setImageResource(R.mipmap.list_cash);
                        PrefsHelper.getcashtype(getApplication(), "cash");
                    }
                    if (position == 2) {
                        cashimg.setImageResource(R.mipmap.list_credit_card);
                        PrefsHelper.getcashtype(getApplication(), "ambank");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            budgettxt = (TextView) view.findViewById(R.id.budgettxt);
            teks1mtxt = (TextView) view.findViewById(R.id.teks1mtxt);
            executivetxt = (TextView) view.findViewById(R.id.executivetxt);
            extbtn = (LinearLayout) view.findViewById(R.id.executivebtn);
            extbtn.setOnClickListener(this);
            officialbookingbtn = (RelativeLayout) view.findViewById(R.id.officebookingbtn);
            officialbookingbtn.setOnClickListener(this);
            officialcarimg = (ImageView) view.findViewById(R.id.officialcarimg);
            officialcattxt = (TextView) view.findViewById(R.id.officialcartext);
            t1mimg = (ImageView) view.findViewById(R.id.teks1mimg);
            budgetimg = (ImageView) view.findViewById(R.id.budgetimg);
            extimg = (ImageView) view.findViewById(R.id.exectuiveimg);
            faretxt = (TextView) view.findViewById(R.id.faretxt);
            offciallayout = (RelativeLayout) view.findViewById(R.id.officbookinglayout);
            bookingclayout = (RelativeLayout) view.findViewById(R.id.bookinglayout);
            if (PrefsHelper.setofficalbooking(getActivity()) != null && PrefsHelper.setofficalbooking(getActivity()).equals("1")) {
                offciallayout.setVisibility(View.VISIBLE);
//                officialcarimg.setVisibility();
//                if(PrefsHelper.setcarclass(getActivity()).equals("B"))
                switch (PrefsHelper.setcarclass(getActivity())) {
                    case "Budget":
                        officialcarimg.setImageDrawable(getResources().getDrawable(R.mipmap.car_img_red));
                        officialcattxt.setText("BUDGET");
                        break;
                    case "Teks1m":
                        officialcarimg.setImageDrawable(getResources().getDrawable(R.mipmap.car_img_golden));
                        officialcattxt.setText("TEKS1M");
                        break;
                    case "Executive":
                        officialcarimg.setImageDrawable(getResources().getDrawable(R.mipmap.car_img_blue));
                        officialcattxt.setText("EXECUTIVE");
                        break;
                }
            } else {
                bookingclayout.setVisibility(View.VISIBLE);
            }
            return view;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bookingbtn:
                    if (PrefsHelper.setcashtype(getApplication()).equals("cash")) {
                        dialog = ProgressDialog.show(getActivity(), "",
                                "please wait.", true);
                        dialog.show();
                        if (PrefsHelper.setgoreservatione(getActivity()) != null || PrefsHelper.setgoreservatione(getActivity()) == null) {

                            if (PrefsHelper.setgoreservatione(getActivity()).equals("0")) {
//                Intent intent = new Intent();
//                intent.setClass(getActivity(), WaitDriverActivtiy.class);
//                startActivity(intent);
                                System.out.println("bookingcarselectfragment ===== " + PrefsHelper.setphonenumber(getActivity()));
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
//                 booking = null;

                                        try {
                                            URL booking = new URL("https://my.here2go.asia///api_booking/order_create");
                                            httpURLConnection = (HttpURLConnection) booking.openConnection();
                                            httpURLConnection.setRequestMethod("POST");
                                            httpURLConnection.setDoOutput(true);
                                            httpURLConnection.setDoInput(true);
                                            String data = "member_id=" + PrefsHelper.setphonenumber(getActivity())
                                                    + "&class=" + PrefsHelper.setcarclass(getActivity())
                                                    + "&start_address=" + PrefsHelper.setstartadress(getActivity())
                                                    + "&end_address=" + PrefsHelper.setendaddress(getActivity())
                                                    + "&start_location=" + PrefsHelper.setstartlocation(getActivity())
                                                    + "&end_location=" + PrefsHelper.setendlocation(getActivity())
                                                    + "&distance=" + PrefsHelper.setkm(getActivity())
                                                    + "&times=" + PrefsHelper.settimes(getActivity())
                                                    + "&payment=" + PrefsHelper.setcashtype(getActivity())
                                                    + "&cost=" + PrefsHelper.setcost(getActivity())
                                                    + "&expected_time_onboard=" + PrefsHelper.setalltime(getActivity());
//                            String testdata = "email=testpppp@gmail.com&class=Budget&start_address=123&end_address=321&start_location=123&end_location=321&distance=1";
                                            OutputStream outputStream = httpURLConnection.getOutputStream();
                                            outputStream.write(data.getBytes());
                                            outputStream.flush();
                                            outputStream.close();
                                            int responseCode = httpURLConnection.getResponseCode();
//                            String sys_code = httpURLConnection.get;
                                            System.out.println("mainactivity data ===== : " + data);
                                            PrefsHelper.getmenucost(getActivity(), faretxt.getText().toString());
                                            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
                                            StringBuilder sb = new StringBuilder();
                                            String line = null;
                                            while ((line = reader.readLine()) != null) {
                                                sb.append(line + "\n");
                                            }

                                            System.out.println(" ==== responsecode" + sb);
                                            JSONObject jsonObject = new JSONObject(sb.toString());
                                            System.out.println("bookingcarselectfragment ===== :" + jsonObject.getString("sys_code").equals("200"));
                                            if (jsonObject.getString("sys_code").equals("200")) {
//                                showview.setVisibility(View.INVISIBLE);
                                                PrefsHelper.getclientorderid(getActivity(), jsonObject.getString("order_id").toString());
                                                new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        try {
                                                            Thread.sleep(500);

                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        } finally {
                                                            dialog.dismiss();
                                                            Message message = new Message();
                                                            message.what = SHOWVIEWINV;
                                                            viewhandler.sendMessage(message);
//
                                                            Intent intent = new Intent();
                                                            intent.setClass(getActivity(), WaitDriverActivtiy.class);
                                                            startActivity(intent);
                                                            getActivity().finish();
                                                        }
                                                    }
                                                }).start();
                                            } else {
                                                new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        try {
                                                            Thread.sleep(500);

                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        } finally {
                                                            dialog.dismiss();
                                                        }
                                                    }
                                                }).start();
                                                Looper.prepare();
                                                Toast.makeText(getActivity(), "sorry, Near no driver.plz change other car.", Toast.LENGTH_SHORT).show();
//

                                                Looper.loop();//


                                            }
//                                        System.out.println(" ==== data : " + "https://my.here2go.asia///api_booking/order_create?" + data);
                                        } catch (MalformedURLException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }).start();
                            }
                        }
                    }
                    if (PrefsHelper.setcashtype(getApplication()).equals("mcash")) {
                        dialog = ProgressDialog.show(getActivity(), "",
                                "please wait.", true);
                        dialog.show();
                        if (PrefsHelper.setgoreservatione(getActivity()) != null || PrefsHelper.setgoreservatione(getActivity()) == null) {

                            if (PrefsHelper.setgoreservatione(getActivity()).equals("0")) {
//                Intent intent = new Intent();
//                intent.setClass(getActivity(), WaitDriverActivtiy.class);
//                startActivity(intent);
                                System.out.println("bookingcarselectfragment ===== " + PrefsHelper.setphonenumber(getActivity()));
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
//                 booking = null;
                                        try {
                                            URL booking = new URL("https://my.here2go.asia///api_booking/order_create");
                                            httpURLConnection = (HttpURLConnection) booking.openConnection();
                                            httpURLConnection.setRequestMethod("POST");
                                            httpURLConnection.setDoOutput(true);
                                            httpURLConnection.setDoInput(true);
                                            String data = "member_id=" + PrefsHelper.setphonenumber(getActivity())
                                                    + "&class=" + PrefsHelper.setcarclass(getActivity())
                                                    + "&start_address=" + PrefsHelper.setstartadress(getActivity())
                                                    + "&end_address=" + PrefsHelper.setendaddress(getActivity())
                                                    + "&start_location=" + PrefsHelper.setstartlocation(getActivity())
                                                    + "&end_location=" + PrefsHelper.setendlocation(getActivity())
                                                    + "&distance=" + PrefsHelper.setkm(getActivity())
                                                    + "&times=" + PrefsHelper.settimes(getActivity())
                                                    + "&payment=" + PrefsHelper.setcashtype(getActivity())
                                                    + "&cost=" + PrefsHelper.setcost(getActivity())
                                                    + "&expected_time_onboard=" + PrefsHelper.setalltime(getActivity());
//                            String testdata = "email=testpppp@gmail.com&class=Budget&start_address=123&end_address=321&start_location=123&end_location=321&distance=1";
                                            OutputStream outputStream = httpURLConnection.getOutputStream();
                                            outputStream.write(data.getBytes());
                                            outputStream.flush();
                                            outputStream.close();
                                            int responseCode = httpURLConnection.getResponseCode();
//                            String sys_code = httpURLConnection.get;
                                            System.out.println("mainactivity data ===== : " + data);
                                            PrefsHelper.getmenucost(getActivity(), faretxt.getText().toString());
                                            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
                                            StringBuilder sb = new StringBuilder();
                                            String line = null;
                                            while ((line = reader.readLine()) != null) {
                                                sb.append(line + "\n");
                                            }

                                            System.out.println(" ==== responsecode" + sb);
                                            JSONObject jsonObject = new JSONObject(sb.toString());
                                            System.out.println("bookingcarselectfragment ===== :" + jsonObject.getString("sys_code").equals("200"));
                                            if (jsonObject.getString("sys_code").equals("200")) {
//                                showview.setVisibility(View.INVISIBLE);
                                                PrefsHelper.getclientorderid(getActivity(), jsonObject.getString("order_id").toString());
                                                new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        try {
                                                            Thread.sleep(500);

                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        } finally {
                                                            dialog.dismiss();
                                                            Message message = new Message();
                                                            message.what = SHOWVIEWINV;
                                                            viewhandler.sendMessage(message);
//
                                                            Intent intent = new Intent();
                                                            intent.setClass(getActivity(), WaitDriverActivtiy.class);
                                                            startActivity(intent);
                                                            getActivity().finish();
                                                        }
                                                    }
                                                }).start();
                                            } else {
                                                new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        try {
                                                            Thread.sleep(500);

                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        } finally {
                                                            dialog.dismiss();
                                                        }
                                                    }
                                                }).start();
                                                Looper.prepare();
                                                Toast.makeText(getActivity(), "sorry, Near no driver.plz change other car.", Toast.LENGTH_SHORT).show();
//

                                                Looper.loop();//


                                            }
//                                        System.out.println(" ==== data : " + "https://my.here2go.asia///api_booking/order_create?" + data);
                                        } catch (MalformedURLException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }).start();
                            }
                        }
                    }
                    if (PrefsHelper.setcashtype(getApplication()).equals("ambank")) {
                        dialog = ProgressDialog.show(getActivity(), "",
                                "please wait.", true);
                        dialog.show();
                        if (PrefsHelper.setgoreservatione(getActivity()) != null || PrefsHelper.setgoreservatione(getActivity()) == null) {

                            if (PrefsHelper.setgoreservatione(getActivity()).equals("0")) {
//                Intent intent = new Intent();
//                intent.setClass(getActivity(), WaitDriverActivtiy.class);
//                startActivity(intent);
                                System.out.println("bookingcarselectfragment ===== " + PrefsHelper.setphonenumber(getActivity()));
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
//                 booking = null;
                                        try {
                                            URL booking = new URL("https://my.here2go.asia///api_booking/order_create");
                                            httpURLConnection = (HttpURLConnection) booking.openConnection();
                                            httpURLConnection.setRequestMethod("POST");
                                            httpURLConnection.setDoOutput(true);
                                            httpURLConnection.setDoInput(true);
                                            String data = "member_id=" + PrefsHelper.setphonenumber(getActivity())
                                                    + "&class=" + PrefsHelper.setcarclass(getActivity())
                                                    + "&start_address=" + PrefsHelper.setstartadress(getActivity())
                                                    + "&end_address=" + PrefsHelper.setendaddress(getActivity())
                                                    + "&start_location=" + PrefsHelper.setstartlocation(getActivity())
                                                    + "&end_location=" + PrefsHelper.setendlocation(getActivity())
                                                    + "&distance=" + PrefsHelper.setkm(getActivity())
                                                    + "&times=" + PrefsHelper.settimes(getActivity())
                                                    + "&payment=" + PrefsHelper.setcashtype(getActivity())
                                                    + "&cost=" + PrefsHelper.setcost(getActivity())
                                                    + "&expected_time_onboard=" + PrefsHelper.setalltime(getActivity());
//                            String testdata = "email=testpppp@gmail.com&class=Budget&start_address=123&end_address=321&start_location=123&end_location=321&distance=1";
                                            OutputStream outputStream = httpURLConnection.getOutputStream();
                                            outputStream.write(data.getBytes());
                                            outputStream.flush();
                                            outputStream.close();
                                            int responseCode = httpURLConnection.getResponseCode();
//                            String sys_code = httpURLConnection.get;
                                            System.out.println("mainactivity data ===== : " + data);
                                            PrefsHelper.getmenucost(getActivity(), faretxt.getText().toString());
                                            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
                                            StringBuilder sb = new StringBuilder();
                                            String line = null;
                                            while ((line = reader.readLine()) != null) {
                                                sb.append(line + "\n");
                                            }

                                            System.out.println(" ==== responsecode" + sb);
                                            JSONObject jsonObject = new JSONObject(sb.toString());
                                            System.out.println("bookingcarselectfragment ===== :" + jsonObject.getString("sys_code").equals("200"));
                                            if (jsonObject.getString("sys_code").equals("200")) {
//                                showview.setVisibility(View.INVISIBLE);
                                                PrefsHelper.getclientorderid(getActivity(), jsonObject.getString("order_id").toString());
                                                new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        try {
                                                            Thread.sleep(500);

                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        } finally {
                                                            dialog.dismiss();
                                                            Message message = new Message();
                                                            message.what = SHOWVIEWINV;
                                                            viewhandler.sendMessage(message);
//
                                                            Intent intent = new Intent();
                                                            intent.setClass(getActivity(), WaitDriverActivtiy.class);
                                                            startActivity(intent);
                                                            getActivity().finish();
                                                        }
                                                    }
                                                }).start();
                                            } else {
                                                new Thread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        try {
                                                            Thread.sleep(500);

                                                        } catch (InterruptedException e) {
                                                            e.printStackTrace();
                                                        } finally {
                                                            dialog.dismiss();
                                                        }
                                                    }
                                                }).start();
                                                Looper.prepare();
                                                Toast.makeText(getActivity(), "sorry, Near no driver.plz change other car.", Toast.LENGTH_SHORT).show();
//

                                                Looper.loop();//


                                            }
//                                        System.out.println(" ==== data : " + "https://my.here2go.asia///api_booking/order_create?" + data);
                                        } catch (MalformedURLException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }).start();
                            }
                        }
                    }

                    if (PrefsHelper.setgoreservatione(getActivity()) != null || PrefsHelper.setgoreservatione(getActivity()) == null) {
                        if (PrefsHelper.setgoreservatione(getActivity()).equals("1")) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {

                                    try {
                                        URL booking = new URL("https://my.here2go.asia///api_booking/order_create");
                                        httpURLConnection = (HttpURLConnection) booking.openConnection();
                                        httpURLConnection.setRequestMethod("POST");
                                        httpURLConnection.setDoOutput(true);
                                        httpURLConnection.setDoInput(true);
                                        String data = "member_id=" + PrefsHelper.setphonenumber(getActivity())
                                                + "&class=" + PrefsHelper.setcarclass(getActivity())
                                                + "&start_address=" + PrefsHelper.setstartadress(getActivity())
                                                + "&end_address=" + PrefsHelper.setendaddress(getActivity())
                                                + "&start_location=" + PrefsHelper.setstartlocation(getActivity())
                                                + "&end_location=" + PrefsHelper.setendlocation(getActivity())
                                                + "&distance=" + PrefsHelper.setkm(getActivity())
                                                + "&times=" + PrefsHelper.settimes(getActivity())
                                                + "&payment=cash"
                                                + "&cost=" + PrefsHelper.setcost(getActivity())
                                                + "&expected_time_onboard=" + PrefsHelper.setalltime(getActivity());
//                            String testdata = "email=testpppp@gmail.com&class=Budget&start_address=123&end_address=321&start_location=123&end_location=321&distance=1";
                                        OutputStream outputStream = httpURLConnection.getOutputStream();
                                        outputStream.write(data.getBytes());
                                        outputStream.flush();
                                        outputStream.close();
                                        int responseCode = httpURLConnection.getResponseCode();
//                            String sys_code = httpURLConnection.get;
                                        System.out.println("mainactivity data ===== : " + data);
                                        PrefsHelper.getmenucost(getActivity(), faretxt.getText().toString());
                                        BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
                                        StringBuilder sb = new StringBuilder();
                                        String line = null;
                                        while ((line = reader.readLine()) != null) {
                                            sb.append(line + "\n");
                                        }

                                        System.out.println(" ==== responsecode" + sb);
                                        JSONObject jsonObject = new JSONObject(sb.toString());
                                        System.out.println("bookingcarselectfragment ===== :" + jsonObject.getString("sys_code").equals("200"));
                                        if (jsonObject.getString("sys_code").equals("200")) {
//                                showview.setVisibility(View.INVISIBLE);
                                            PrefsHelper.getclientorderid(getActivity(), jsonObject.getString("order_id").toString());
                                            new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    try {
                                                        Thread.sleep(500);

                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    } finally {
                                                        dialog.dismiss();
                                                        Message message = new Message();
                                                        message.what = SHOWVIEWINV;
                                                        viewhandler.sendMessage(message);
                                                        PrefsHelper.gethaved(getActivity(), "5");
                                                        Intent intent = getIntent();
                                                        finish();
                                                        startActivity(intent);
//                                                        cleanmap();
                                                    }
                                                }
                                            }).start();
                                        } else {
                                            new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    try {
                                                        Thread.sleep(500);

                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    } finally {
                                                        dialog.dismiss();

                                                    }
                                                }
                                            }).start();
                                            Looper.prepare();
                                            Toast.makeText(getActivity(), "sorry, Near no driver.plz change other car.", Toast.LENGTH_SHORT).show();
//

                                            Looper.loop();//


                                        }
//                                        System.out.println(" ==== data : " + "https://my.here2go.asia///api_booking/order_create?" + data);
                                    } catch (MalformedURLException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                        }
                    }
                    break;
                case R.id.officebookingbtn:
                    //                    dialog = ProgressDialog.show(getActivity(), "",
//                            "please wait.", true);
//                    dialog.show();
////                Intent intent = new Intent();
////                intent.setClass(getActivity(), WaitDriverActivtiy.class);
////                startActivity(intent);
//                    System.out.println("official bookingcarselectfragment ===== " + PrefsHelper.setphonenumber(getActivity()));
//                    new Thread(new Runnable() {
//
//
//                        @Override
//                        public void run() {
////                 booking = null;
//
//                            try {
//                                URL booking = new URL("https://my.here2go.asia///api_booking/order_create");
//                                httpURLConnection = (HttpURLConnection) booking.openConnection();
//                                httpURLConnection.setRequestMethod("POST");
//                                httpURLConnection.setDoOutput(true);
//                                httpURLConnection.setDoInput(true);
//                                String data = "member_id=" + PrefsHelper.setphonenumber(getActivity())
//                                        + "&class=" + PrefsHelper.setcarclass(getActivity())
//                                        + "&start_address=" + PrefsHelper.setstartadress(getActivity())
//                                        + "&end_address=" + PrefsHelper.setendaddress(getActivity())
//                                        + "&start_location=" + PrefsHelper.setstartlocation(getActivity())
//                                        + "&end_location=" + PrefsHelper.setendlocation(getActivity())
//                                        + "&distance=" + PrefsHelper.setkm(getActivity())
//                                        + "&times=" + PrefsHelper.settimes(getActivity())
//                                        + "&payment=cash"
//                                        + "&cost=" + faretxt.getText().toString()
//                                        + "&expected_time_onboard=" + PrefsHelper.setalltime(getActivity());
////                            String testdata = "email=testpppp@gmail.com&class=Budget&start_address=123&end_address=321&start_location=123&end_location=321&distance=1";
//                                OutputStream outputStream = httpURLConnection.getOutputStream();
//                                outputStream.write(data.getBytes());
//                                outputStream.flush();
//                                outputStream.close();
//                                int responseCode = httpURLConnection.getResponseCode();
////                            String sys_code = httpURLConnection.get;
//                                PrefsHelper.getmenucost(getActivity(), faretxt.getText().toString());
//                                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
//                                StringBuilder sb = new StringBuilder();
//                                String line = null;
//                                while ((line = reader.readLine()) != null) {
//                                    sb.append(line + "\n");
//                                }
//
//                                System.out.println(" ==== responsecode" + sb);
//                                JSONObject jsonObject = new JSONObject(sb.toString());
//                                System.out.println("bookingcarselectfragment ===== :" + jsonObject.getString("sys_code").equals("200"));
//                                if (jsonObject.getString("sys_code").equals("200")) {
////                                showview.setVisibility(View.INVISIBLE);
//                                    PrefsHelper.getclientorderid(getActivity(), jsonObject.getString("order_id").toString());
//                                    new Thread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            try {
//                                                Thread.sleep(500);
//
//                                            } catch (InterruptedException e) {
//                                                e.printStackTrace();
//                                            } finally {
//                                                dialog.dismiss();
//                                                Message message = new Message();
//                                                message.what = SHOWVIEWINV;
//                                                viewhandler.sendMessage(message);
////                                                FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
////                                                fragmentTransaction1.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
////                                                fragmentTransaction1.replace(R.id.container, new WaitingdriverFragment()).commit();
////                                                getActivity().getFragmentManager().beginTransaction().remove(new BookingCarSelectFragment1());
////                                                getActivity().getFragmentManager().beginTransaction().replace(R.id.container1, new WaitingdriverFragment()).commit();
//                                                Intent intent = new Intent();
//                                                intent.setClass(getActivity(), WaitDriverActivtiy.class);
//                                                startActivity(intent);
//                                                getActivity().finish();
//                                            }
//                                        }
//                                    }).start();
//
//                                }
//                                System.out.println(" ==== data : " + "https://my.here2go.asia///api_booking/order_create?" + data);
//                            } catch (MalformedURLException e) {
//                                e.printStackTrace();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }).start();
                    final List<String> liststring = new ArrayList<>();
                    liststring.add("Meeting");
                    liststring.add("Go to HQ");
                    liststring.add("Others");
                    LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                    final View v = inflater.inflate(R.layout.alertdiloguse, null);
                    new AlertDialog.Builder(MainActivity.this)
                            .

                                    setTitle("What Purpose?")
                            .

                                    setItems(liststring.toArray(new String[liststring.size()]), new DialogInterface.OnClickListener()

                                    {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            final String name = liststring.get(which);
//                                    Toast.makeText(getApplicationContext(),   name, Toast.LENGTH_SHORT).show();

                                            new AlertDialog.Builder(MainActivity.this)
                                                    .setTitle(name)
                                                    .setView(v)
                                                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {

                                                        }
                                                    })
                                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            final EditText editText = (EditText) (v.findViewById(R.id.editText));
//                                                    Toast.makeText(getApplicationContext(), "你的id是" +
//
//                                                            editText.getText().toString(), Toast.LENGTH_SHORT).show();

//                                                    dialog = ProgressDialog.show(getActivity(), "",
//                                                            "please wait.", true).show();
//                                                    dialog.show();
//                Intent intent = new Intent();
//                intent.setClass(getActivity(), WaitDriverActivtiy.class);
//                startActivity(intent);
                                                            progressDialog = ProgressDialog.show(getActivity(), "Please wait.",
                                                                    "", true);
                                                            System.out.println("official bookingcarselectfragment ===== " + PrefsHelper.setphonenumber(getActivity()));

                                                            new Thread(new Runnable() {


                                                                @Override
                                                                public void run() {
//                 booking = null;

                                                                    try {
                                                                        URL booking = new URL("https://my.here2go.asia///api_booking/order_create");
                                                                        httpURLConnection = (HttpURLConnection) booking.openConnection();
                                                                        httpURLConnection.setRequestMethod("POST");
                                                                        httpURLConnection.setDoOutput(true);
                                                                        httpURLConnection.setDoInput(true);
                                                                        String data = "member_id=" + PrefsHelper.setphonenumber(getActivity())
                                                                                + "&class=" + PrefsHelper.setcarclass(getActivity())
                                                                                + "&start_address=" + PrefsHelper.setstartadress(getActivity())
                                                                                + "&end_address=" + PrefsHelper.setendaddress(getActivity())
                                                                                + "&start_location=" + PrefsHelper.setstartlocation(getActivity())
                                                                                + "&end_location=" + PrefsHelper.setendlocation(getActivity())
                                                                                + "&distance=" + PrefsHelper.setkm(getActivity())
                                                                                + "&times=" + PrefsHelper.settimes(getActivity())
                                                                                + "&payment=official"
                                                                                + "&cost=" + faretxt.getText().toString()
                                                                                + "&official_info=" + editText.getText().toString()
                                                                                + "&official_subject=" + name
                                                                                + "&expected_time_onboard=" + PrefsHelper.setalltime(getActivity());
//                            String testdata = "email=testpppp@gmail.com&class=Budget&start_address=123&end_address=321&start_location=123&end_location=321&distance=1";
                                                                        OutputStream outputStream = httpURLConnection.getOutputStream();
                                                                        outputStream.write(data.getBytes());
                                                                        outputStream.flush();
                                                                        outputStream.close();
                                                                        int responseCode = httpURLConnection.getResponseCode();
//                            String sys_code = httpURLConnection.get;
                                                                        PrefsHelper.getmenucost(getActivity(), faretxt.getText().toString());
                                                                        BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
                                                                        StringBuilder sb = new StringBuilder();
                                                                        String line = null;
                                                                        while ((line = reader.readLine()) != null) {
                                                                            sb.append(line + "\n");
                                                                        }

                                                                        System.out.println(" ==== responsecode" + sb);
                                                                        JSONObject jsonObject = new JSONObject(sb.toString());
                                                                        System.out.println("bookingcarselectfragment ===== :" + jsonObject.getString("sys_code").equals("200"));
                                                                        if (jsonObject.getString("sys_code").equals("200")) {
//                                showview.setVisibility(View.INVISIBLE);
                                                                            PrefsHelper.getclientorderid(getActivity(), jsonObject.getString("order_id").toString());
                                                                            PrefsHelper.gethaved(getActivity(), "5");
                                                                            Intent intent = getIntent();
                                                                            finish();
                                                                            startActivity(intent);
//                                                                    cleanmap();
//                                                                    Toast.makeText(MainActivity.this, "Wait for order review.", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                        if (jsonObject.getString("sys_code").equals("500")) {
                                                                            Looper.prepare();
                                                                            Toast.makeText(MainActivity.this, "Must apply 45 minutes ago", Toast.LENGTH_SHORT).show();
//                                                                            cleanmap();
                                                                            Looper.loop();

                                                                        }
//                                                                        System.out.println(" ==== data : " + "https://my.here2go.asia///api_booking/order_create?" + data);
                                                                    } catch (MalformedURLException e) {
                                                                        e.printStackTrace();
                                                                    } catch (IOException e) {
                                                                        e.printStackTrace();
                                                                    } catch (JSONException e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                }
                                                            }).start();
                                                        }
                                                    }).show();
                                        }
                                    })
                            .show();
                    break;
                case R.id.teks1mcarebtn:
                    dialog = ProgressDialog.show(

                            getActivity(), "",
                            "please wait.", true);
                    dialog.show();
                    teks1mtxt.setText(R.string.teks1mtxtaction);
                    budgettxt.setText(R.string.budgettxt);
                    executivetxt.setText(R.string.exexutivetxt);
                    PrefsHelper.getcarclass(

                            getActivity(), "Teks1m");
                    t1mimg.setImageDrawable(

                            getResources().

                                    getDrawable(R.mipmap.choose_car_active));
                    budgetimg.setImageDrawable(

                            getResources().

                                    getDrawable(R.mipmap.choose_car));
                    extimg.setImageDrawable(

                            getResources().

                                    getDrawable(R.mipmap.choose_car));
                    //                System.out.println("bookingcarselect ===== " + PrefsHelper.setkm(getActivity()));
                    String url = "https://my.here2go.asia///api_booking/estimated_cost?distance=" + PrefsHelper.setkm(getActivity()) + "&h=" + PrefsHelper.sethtim(getActivity());
                    aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>()

                    {
                        @Override
                        public void callback(String url, JSONObject object, AjaxStatus status) {
                            super.callback(url, object, status);
                            System.out.println("bookingselectcar ==== " + object);
                            try {
//                            if (object.getString("sys_code").equals("200")) {
                                faretxt.setText("RM " + object.getJSONObject("price").getString("Teks1m").toString());
                                PrefsHelper.getcost(getActivity(), object.getJSONObject("price").getString("Budget").toString());
//                            }
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(500);

                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        } finally {
                                            dialog.dismiss();
                                        }
                                    }
                                }).start();
                                bookingbtn.setVisibility(View.VISIBLE);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

//                t1mtxt.setTextColor(R.color.colorPrimary);
                    break;
                case R.id.buggetbtn:
                    dialog = ProgressDialog.show(getActivity(), "", "please wait.", true);
                    dialog.show();
                    teks1mtxt.setText(R.string.teks1mtxt);
                    budgettxt.setText(R.string.budgettxtaction);
                    executivetxt.setText(R.string.exexutivetxt);
                    PrefsHelper.getcarclass(getActivity(), "Budget");
                    t1mimg.setImageDrawable(getResources().getDrawable(R.mipmap.choose_car));
                    budgetimg.setImageDrawable(

                            getResources().

                                    getDrawable(R.mipmap.choose_car_active));
                    extimg.setImageDrawable(

                            getResources().

                                    getDrawable(R.mipmap.choose_car));
                    String url1 = "https://my.here2go.asia///api_booking/estimated_cost?distance=" + PrefsHelper.setkm(getActivity()) + "&h=" + PrefsHelper.sethtim(getActivity());
                    aQuery.ajax(url1, null, JSONObject.class, new AjaxCallback<JSONObject>()

                    {
                        @Override
                        public void callback(String url, JSONObject object, AjaxStatus status) {
                            super.callback(url, object, status);
                            System.out.println("bookingselectcar ==== " + object);
                            try {
//                            if (object.getString("sys_code").equals("200")) {
                                faretxt.setText("RM " + object.getJSONObject("price").getString("Budget").toString());
                                PrefsHelper.getcost(getActivity(), object.getJSONObject("price").getString("Budget").toString());
//                            }
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(500);

                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        } finally {
                                            dialog.dismiss();
                                        }
                                    }
                                }).start();
                                bookingbtn.setVisibility(View.VISIBLE);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    break;
                case R.id.executivebtn:
                    teks1mtxt.setText(R.string.teks1mtxt);
                    budgettxt.setText(R.string.budgettxt);
                    executivetxt.setText(R.string.exexutivetxtaction);
                    dialog = ProgressDialog.show(

                            getActivity(), "",
                            "please wait.", true);
                    dialog.show();
                    PrefsHelper.getcarclass(

                            getActivity(), "Executive");
                    t1mimg.setImageDrawable(

                            getResources().

                                    getDrawable(R.mipmap.choose_car));
                    extimg.setImageDrawable(

                            getResources().

                                    getDrawable(R.mipmap.choose_car_active));
                    budgetimg.setImageDrawable(

                            getResources().

                                    getDrawable(R.mipmap.choose_car));
                    String url2 = "https://my.here2go.asia///api_booking/estimated_cost?distance=" + PrefsHelper.setkm(getActivity()) + "&h=" + PrefsHelper.sethtim(getActivity());
                    aQuery.ajax(url2, null, JSONObject.class, new AjaxCallback<JSONObject>()

                    {
                        @Override
                        public void callback(String url, JSONObject object, AjaxStatus status) {
                            super.callback(url, object, status);
                            System.out.println("bookingselectcar ==== " + object);
                            try {
//                            if (object.getString("sys_code").equals("200")) {
                                faretxt.setText("RM " + object.getJSONObject("price").getString("Executive").toString());
                                PrefsHelper.getcost(getActivity(), object.getJSONObject("price").getString("Budget").toString());
//                            }
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(500);

                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        } finally {
                                            dialog.dismiss();
                                        }
                                    }
                                }).start();
                                bookingbtn.setVisibility(View.VISIBLE);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    break;
            }
        }
    }

    Handler viewhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    showview.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    };

    //    public void dismissdialoglayout(){
//        bookingFragment = new BookingFragment();
//        bookingFragment.dismiss();
//
//    }

    public void mintip() {
        Toast.makeText(MainActivity.this, "you have a order lafter 30 min.", Toast.LENGTH_SHORT).show();
    }

    public void finddriverlayout() {
        container1.setVisibility(View.VISIBLE);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
        fragmentTransaction.remove(new BookSelectTimeFragment1());
        fragmentTransaction.replace(R.id.container1, new OrderHaveDFragement());
        fragmentTransaction.commit();


    }


//    public class GcmBrodacasReceiver extends BroadcastReceiver {
//        public static final int NOTIFICATION_ID = 0;
//        private int tab;
//        Context context;
//
//        public GcmBrodacasReceiver(Context context) {
//            this.context = context;
//        }
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Bundle extras = intent.getExtras();
//
//            final String orderid = extras.getString("gcm.notification.body");
//            GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
//            String messageType = gcm.getMessageType(intent);
//            System.out.println(" ==== gcm ===== :" + context.toString());
//            System.out.println(" ==== gcm msmtype == =:" + messageType.toString());
//            if (!extras.isEmpty()) {
//                if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
//                    Log.i(getClass() + "gcm error", extras.toString());
//                } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
//                    Log.i(getClass() + "gcm delete", extras.toString());
//                } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
//                    Log.i(getClass() + "gcm nessage", extras.toString());
//                    System.out.println(" ---- gcm message --- : " + extras.toString());
//                    System.out.println("====");
//                    //又把推播改回來首頁
//                    Intent i = new Intent();
////                PrefsHelper.getorderid(context, orderid);
//                    PrefsHelper.getclientorderid(context, orderid);
//                    System.out.println("gcm  orderid === " + orderid);
////                int tab =0;
////                Intent i = new Intent(context,TitlebarActivity.class);
////                i.putExtra("tab",tab);
//                    String msg = extras.toString();
////                String msg = extras.getString("message").toString();
//
////                String test = msg.substring(0, 1);
////                String showtxt = msg.substring(1, msg.length());
////                System.out.println(" ---- gcmmsg ---- : " + showtxt);
//                    System.out.println(" ---- gcm message ---- : " + extras.toString());
//
////                System.out.println(" ---- gcmmsgfirst ---- :" + test);
//                    i.setAction("android.intent.Main");
//                    i.addCategory("android.intent.action.LAUNCHER");
//                    if (extras.getString("gcm.notification.click_action").equals("2")) {
////                    IntentService.sendLocalNotification(context, NOTIFICATION_ID, R.mipmap.ic_launcher_, "乘客", "有司機前往你的位置", "", true, PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT));
//                        PrefsHelper.gethaved(context, "1");
//                        Intent intent1 = new Intent();
//                        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent1.setClass(context, MainActivity.class);
//                        context.startActivity(intent1);
//
//
//                    }
//                    if (extras.getString("gcm.notification.click_action").equals("3")) {
////                    IntentService.sendLocalNotification(context, NOTIFICATION_ID, R.mipmap.ic_launcher_, "乘客", "感謝妳的搭乘", "", true, PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT));
//                        PrefsHelper.gethaved(context, "2");
//                        Intent intent1 = new Intent();
//                        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent1.setClass(context, StartDriverActivity.class);
//                        context.startActivity(intent1);
//                    }
//                    if (extras.getString("gcm.notification.click_action").equals("4")) {
////                    IntentService.sendLocalNotification(context, NOTIFICATION_ID, R.mipmap.ic_launcher_, "乘客", "到達目的地", "", true, PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT));
//                        PrefsHelper.gethaved(context, "3");
////                    StartDriverActivity startDriverActivity = new StartDriverActivity();
////                    startDriverActivity.ratlayout();
//                        Intent intent1 = new Intent();
//                        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent1.setClass(context, StartDriverActivity.class);
//                        context.startActivity(intent1);
//                    }
//                    if (extras.getString("gcm.notification.click_action").equals("9")) {
////                    IntentService.sendLocalNotification(context, NOTIFICATION_ID, R.mipmap.ic_launcher_, "乘客", "到達目的地", "", true, PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT));
//                        PrefsHelper.gethaved(context, "7");
////                    StartDriverActivity startDriverActivity = new StartDriverActivity();
////                    startDriverActivity.ratlayout();
//                        Intent intent1 = new Intent();
//                        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent1.setClass(context, MainActivity.class);
//                        context.startActivity(intent1);
//                    }
//                    MainActivity mainActivity = new MainActivity();
//
//                    if (extras.getString("gcm.notification.click_action").equals("6")) {
////                    IntentService.sendLocalNotification(context, NOTIFICATION_ID, R.mipmap.ic_launcher_, "乘客", "到達目的地", "", true, PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT));
//                        PrefsHelper.gethaved(context, "6");
////                    StartDriverActivity startDriverActivity = new StartDriverActivity();
////                    startDriverActivity.ratlayout();
//                        Intent intent1 = new Intent();
//                        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent1.setClass(context, MainActivity.class);
//                        context.startActivity(intent1);
//
//                    }
//
//                }
//                setResultCode(Activity.RESULT_OK);
//            }
//        }
//    }
//    public class ClientSafelyFragment extends Fragment implements View.OnClickListener{
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.safelylayout,container,false);
//        return view;
//    }
//
//    @Override
//    public void onClick(View view) {
//
//    }
//}

}
