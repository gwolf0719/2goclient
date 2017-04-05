package com.silence5105.nosy.partner2go.ActivityFragment;

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
import android.support.v4.app.ActivityCompat;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.silence5105.nosy.partner2go.MainActivity;
import com.silence5105.nosy.partner2go.PrefsHelper;
import com.silence5105.nosy.partner2go.R;

/**
 * Created by Nosy on 2017/1/24.
 */

public class TitleActivity extends Activity {

    public static final int LOCATION_UPDATE_MIN_DISTANCE = 5;
    public static final int LOCATION_UPDATE_MIN_TIME = 3000;
    LocationManager locationManager;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
//            Intent intent = new Intent();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (PrefsHelper.setautologin(getApplication()) != null) {
                        String check = PrefsHelper.setautologin(getApplication());
                        if (check != null) {
                            if (check.equals("1")) {
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


                }
            }, 2000);
        } else {
            Toast.makeText(TitleActivity.this, "需要允許權限才可以使用", Toast.LENGTH_SHORT).show();
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
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.titleactivity);
        initview();
        System.out.println(" ===== titleactivity ===== :" + PrefsHelper.setautologin(getApplication()));
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    String check = PrefsHelper.setautologin(getApplication());
                    if (check != null) {
                        if (check.equals("1")) {
                            Intent i = new Intent();
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
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String check = PrefsHelper.setautologin(getApplication());
                        if (check != null) {
                            if (check.equals("1")) {
                                Intent i = new Intent();
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
            } else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS}, 0);
            }
        }
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
}
