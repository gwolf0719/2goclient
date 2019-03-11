package com.nnosy.taxigoclient.ActivityFragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.crashlytics.android.Crashlytics;
import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.AppUpdaterUtils;
import com.github.javiersantos.appupdater.enums.AppUpdaterError;
import com.github.javiersantos.appupdater.enums.UpdateFrom;
import com.github.javiersantos.appupdater.objects.Update;
import com.nnosy.taxigoclient.MainActivity;
import com.nnosy.taxigoclient.PrefsHelper;
import com.nnosy.taxigoclient.R;

import io.fabric.sdk.android.Fabric;

import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Nosy on 2017/1/24.
 */

public class TitleActivity extends Activity {

    public static final int LOCATION_UPDATE_MIN_DISTANCE = 5;
    public static final int LOCATION_UPDATE_MIN_TIME = 3000;
    LocationManager locationManager;
    private AQuery aQuery;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 5) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED&&grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
//            Intent intent = new Intent();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (PrefsHelper.setcheckagree(getApplication()) != null) {
                            if (PrefsHelper.setcheckagree(getApplication()).equals("1")) {
                                if (PrefsHelper.setautologin(getApplication()) != null) {
                                    String check = PrefsHelper.setautologin(getApplication());
                                    if (check != null) {
                                        if (check.equals("1")) {
                                            if (PrefsHelper.setpushkey(getApplication()) != null) {

                                                String url = "https://www.mmas.biz///api_member/push_key?member_id=" + PrefsHelper.setphonenumber(getApplication()) + "&push_key=" + PrefsHelper.setpushkey(getApplication());
//        if (aQuery != null) {
                                                aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                                                    @Override
                                                    public void callback(String url, JSONObject object, AjaxStatus status) {
                                                        super.callback(url, object, status);

                                                    }
                                                });

                                            }
                                            System.out.println("title activity ==== 1 ");
                                            Intent i = new Intent();
                                            i.setClass(TitleActivity.this, MainActivity.class);
                                            startActivity(i);
                                            finish();
                                        }
                                    }

                                }
                                if (PrefsHelper.setautologin(getApplicationContext()) == null) {
                                    Intent intent = new Intent();
                                    intent.setClass(TitleActivity.this, LoginContainerActivity.class);
                                    startActivity(intent);
//                    getActivity().finish();
                                    finish();
                                }

                            } else {
                                Intent checkagreeintent = new Intent();
                                checkagreeintent.setClass(TitleActivity.this, CheckAgreeActivity.class);
                                startActivity(checkagreeintent);
                                finish();

                            }
                        } else {
                            Intent checkagreeintent = new Intent();
                            checkagreeintent.setClass(TitleActivity.this, CheckAgreeActivity.class);
                            startActivity(checkagreeintent);
                            finish();

                        }


                    }
                }, 2000);
            } else {
                Toast.makeText(TitleActivity.this, "Need permission to use", Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            finish();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.titleactivity);
        appUpdaterUtils = new AppUpdaterUtils(this);
        appUpdater = new AppUpdater(this);
        Fabric.with(this, new Crashlytics());
//        Fabric.with(this, new Crashlytics());
        initview();



        aQuery = new AQuery(this);
        System.out.println(" ===== titleactivity ===== :" + PrefsHelper.setautologin(getApplication()));
checkpermission();

    }

    public void initview() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

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
                        LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, mLocationListener);
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

            if (isGPSEnabled) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, mLocationListener);
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        }
//        Geocoder gc = new Geocoder(MainActivity.this, Locale.TRADITIONAL_CHINESE);
//        List<Address> lstAddress = null;
//        try {
//            lstAddress = gc.getFromLocation(nowlat, nowlng, 1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        if (location != null) {
//            nowlat = location.getLatitude();
//            nowlng = location.getLongitude();
//            String returnAddress = lstAddress.get(0).getAddressLine(0);
//            etOrigin.setText(returnAddress);
//        }


//        System.out.println(" ===== " + nowlng + " ===== lat" + nowlat);
    }

    public void checkpermission() {

                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                String check = PrefsHelper.setautologin(getApplication());
                                if (check != null) {
                                    if (check.equals("1")) {
                                        Intent i = new Intent();
                                        System.out.println("title activity ======== 1 : ");
                                        if (PrefsHelper.setpushkey(getApplication()) != null) {

                                            String url = "https://www.mmas.biz///api_member/push_key?member_id=" + PrefsHelper.setphonenumber(getApplication()) + "&push_key=" + PrefsHelper.setpushkey(getApplication());
//        if (aQuery != null) {
                                            aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                                                @Override
                                                public void callback(String url, JSONObject object, AjaxStatus status) {
                                                    super.callback(url, object, status);

                                                }
                                            });

                                        }
                                        i.setClass(TitleActivity.this, MainActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                    if (check.equals("0")) {
                                        Intent intent = new Intent();
                                        intent.setClass(TitleActivity.this, LoginContainerActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                                if (check == null) {
                                    Intent intent = new Intent();
                                    intent.setClass(TitleActivity.this, LoginContainerActivity.class);
                                    startActivity(intent);
//                    getActivity().finish();
                                    finish();
                                }
                            }
                        }, 2000);
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        System.out.println("登入 " + PrefsHelper.setcheckagree(getApplication()) + "\n" + PrefsHelper.setautologin(getApplication()));
                        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED ) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (PrefsHelper.setcheckagree(getApplication()) != null) {
                                        if (PrefsHelper.setcheckagree(getApplication()).equals("1")) {
                                            if (PrefsHelper.setautologin(getApplication()) != null) {
                                                String check = PrefsHelper.setautologin(getApplication());
                                                if (check != null) {
                                                    if (check.equals("1")) {
                                                        if (PrefsHelper.setpushkey(getApplication()) != null) {

                                                            String url = "https://www.mmas.biz///api_member/push_key?member_id=" + PrefsHelper.setphonenumber(getApplication()) + "&push_key=" + PrefsHelper.setpushkey(getApplication());
//        if (aQuery != null) {
                                                            aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                                                                @Override
                                                                public void callback(String url, JSONObject object, AjaxStatus status) {
                                                                    super.callback(url, object, status);

                                                                }
                                                            });

                                                        }
                                                        System.out.println("title activity ==== 1 ");
                                                        Intent i = new Intent();
                                                        i.setClass(TitleActivity.this, MainActivity.class);
                                                        startActivity(i);
                                                        finish();
                                                    }else {
                                                        startActivity(new Intent(TitleActivity.this,LoginContainerActivity.class));
                                                    }
                                                }

                                            }
                                            if (PrefsHelper.setautologin(getApplicationContext()) == null) {
                                                Intent intent = new Intent();
                                                intent.setClass(TitleActivity.this, LoginContainerActivity.class);
                                                startActivity(intent);
//                    getActivity().finish();
                                                finish();
                                            }

                                        } else {
                                            Intent checkagreeintent = new Intent();
                                            checkagreeintent.setClass(TitleActivity.this, CheckAgreeActivity.class);
                                            startActivity(checkagreeintent);
                                            finish();

                                        }
                                    } else {
                                        Intent checkagreeintent = new Intent();
                                        checkagreeintent.setClass(TitleActivity.this, CheckAgreeActivity.class);
                                        startActivity(checkagreeintent);
                                        finish();

                                    }
                                }
                            }, 2000);
                        } else {
                            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE}, 5);
                        }
                    }


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


    AppUpdaterUtils appUpdaterUtils;
    AppUpdater appUpdater;

    public void checkgoogleplay() {
        if (executeCommand() == true) {
            appUpdaterUtils.withListener(new AppUpdaterUtils.UpdateListener() {
                @Override
                public void onSuccess(Update update, Boolean aBoolean) {
                    System.out.println(" update " + update.getLatestVersion() + "\n" + update.getReleaseNotes() + "\n " + aBoolean);
                    if (aBoolean == true) {
                        appUpdater.setUpdateFrom(UpdateFrom.GOOGLE_PLAY)
                                .setContentOnUpdateAvailable("Be sure to update to the latest version to maintain the best experience")
                                .setTitleOnUpdateAvailable("Check to have a new version !")
//                .setContentOnUpdateNotAvailable("第三段")
                                .setButtonUpdate("goto google store")
                                .setButtonDismiss("")
                                .setButtonDoNotShowAgain("")
                                .setCancelable(false)
                                .start();
                    } else {
//                            new Thread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    try {
//                                        Thread.sleep(750);
//                                        Intent intent1 = new Intent();
//                                        intent1.setClass(TitleActivity.this, LoginActivity.class);
//                                        startActivity(intent1);
//                                        finish();
//                                    } catch (InterruptedException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }).start();
                        checkpermission();

                    }
                }

                @Override
                public void onFailed(AppUpdaterError appUpdaterError) {

                }
            }).start();
        } else {
            Message message = new Message();
            message.what = 0;
            viewhandler.sendMessage(message);
        }

    }

    Handler viewhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Toast.makeText(TitleActivity.this, "Please use it in a good environment", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
//        checkgoogleplay();
    }

    private boolean executeCommand() {
        System.out.println("executeCommand");
        Runtime runtime = Runtime.getRuntime();
        try {
            Process mIpAddrProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int mExitValue = mIpAddrProcess.waitFor();
            System.out.println(" mExitValue " + mExitValue);
            if (mExitValue == 0) {
                return true;
            } else {
                return false;
            }
        } catch (InterruptedException ignore) {
            ignore.printStackTrace();
            System.out.println(" Exception:" + ignore);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(" Exception:" + e);
        }
        return false;
    }
}
