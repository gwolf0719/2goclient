package com.silence5105.nosy.partner2go.ActivityFragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.silence5105.nosy.partner2go.MyBookingItems;
import com.silence5105.nosy.partner2go.PrefsHelper;
import com.silence5105.nosy.partner2go.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Nosy on 2017/4/20.
 */

public class BookingDetailActivity extends Activity implements View.OnClickListener {
    TextView titletext;
    AQuery aQuery;
    RelativeLayout bgcolor;
    TextView timetxt, bookingtypetxt;
    MyBookingItems myBookingItems;
    private final static String CALL = "android.intent.action.CALL";
    RelativeLayout detailcolor;

    private TextView detailtxt;
    private RelativeLayout bglayoutcolor;
    private TextView startadresstxt;
    private TextView endadresstxt;
    private ImageView redline;
    private ImageView blueline;
    private TextView faretxt;
    private TextView cartxt;
    private TextView subject;
    TextView infotxt;
    private TextView drivernametxt;
    private TextView carnnumbertxt;
    private TextView ranktxt;
    private RelativeLayout driverlayout;
    private RelativeLayout cancelbtn;
    private LinearLayout subjectlayout;
    private RelativeLayout callbtn;
    private ImageView backbtn;
    private RelativeLayout msnbtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookinglayot);
        System.out.println("bookingdetaillayout ==== : " + PrefsHelper.setlistselect(getApplication()));
        aQuery = new AQuery(this);
        subjectlayout = (LinearLayout) findViewById(R.id.subjetlayout);
        if (PrefsHelper.setbookingsubject(getApplication()).equals("null")) {
            subjectlayout.setVisibility(View.INVISIBLE);
        }
        msnbtn = (RelativeLayout) findViewById(R.id.msnbtn);
        callbtn = (RelativeLayout) findViewById(R.id.callbtn);
        callbtn.setOnClickListener(this);
        backbtn = (ImageView) findViewById(R.id.backbtn);
        backbtn.setOnClickListener(this);
        cancelbtn = (RelativeLayout) findViewById(R.id.cancelbtn);
        cancelbtn.setOnClickListener(this);
        driverlayout = (RelativeLayout) findViewById(R.id.driverlayout);
        drivernametxt = (TextView) findViewById(R.id.drivernametxt);
        drivernametxt.setText(PrefsHelper.setbookingdrivername(getApplication()));
        carnnumbertxt = (TextView) findViewById(R.id.carnnumbertxt);
        carnnumbertxt.setText(PrefsHelper.setbookingcar(getApplication()) + " ‧ " + PrefsHelper.setbookingtexinumber(getApplication()));
        ranktxt = (TextView) findViewById(R.id.ranktxt);
        ranktxt.setText(PrefsHelper.setbookingrank(getApplication()));
        infotxt = (TextView) findViewById(R.id.infotxt);
        infotxt.setText(PrefsHelper.setbookinginfo(getApplication()));
        if (PrefsHelper.setbookinginfo(getApplication()).equals("null")) {
            infotxt.setVisibility(View.INVISIBLE);
        }
        faretxt = (TextView) findViewById(R.id.faretxt);
        faretxt.setText("RM" + PrefsHelper.setbookingcost(getApplication()));
        cartxt = (TextView) findViewById(R.id.cartxt);
        cartxt.setText(PrefsHelper.setbookingcar(getApplication()));
        subject = (TextView) findViewById(R.id.subjettxt);
        subject.setText(PrefsHelper.setbookingsubject(getApplication()));
        startadresstxt = (TextView) findViewById(R.id.startadresstxt);
        startadresstxt.setText(PrefsHelper.setbookingstartadress(getApplication()));
        endadresstxt = (TextView) findViewById(R.id.endadresstxt);
        endadresstxt.setText(PrefsHelper.setbookingendadress(getApplication()));
        bgcolor = (RelativeLayout) findViewById(R.id.bglayout);
        blueline = (ImageView) findViewById(R.id.blueimg);
        redline = (ImageView) findViewById(R.id.redimg);
        titletext = (TextView) findViewById(R.id.titletext);
        detailcolor = (RelativeLayout) findViewById(R.id.detailcolor);
        detailtxt = (TextView) findViewById(R.id.detailtxt);
        timetxt = (TextView) findViewById(R.id.createtimetxt);
        bglayoutcolor = (RelativeLayout) findViewById(R.id.bglayout);
        bookingtypetxt = (TextView) findViewById(R.id.bookingtypetxt);

        if (PrefsHelper.setbookingtype(getApplication()).equals("now")) {

            timetxt.setText(PrefsHelper.setbookingupdatetime(getApplication()));
            titletext.setText("Now");
            if (PrefsHelper.setbookingdrivername(getApplication()).equals("")) {
                driverlayout.setVisibility(View.GONE);
            }
            if (PrefsHelper.setbookingpayment(getApplication()).equals("official")) {
                bookingtypetxt.setText("Official Booking");
                blueline.setVisibility(View.VISIBLE);
                redline.setVisibility(View.GONE);
                bglayoutcolor.setBackgroundColor(Color.parseColor("#0255a6"));
            }
            if (!PrefsHelper.setbookingpayment(getApplication()).equals("official")) {
                bookingtypetxt.setText("Personal Booking");
                blueline.setVisibility(View.GONE);
                redline.setVisibility(View.VISIBLE);
                bglayoutcolor.setBackgroundColor(Color.parseColor("#c63725"));
            }
            if (PrefsHelper.setbookingorderstauts(getApplication()).equals("5")) {
                detailtxt.setText("Pending");
                detailcolor.setBackgroundColor(Color.parseColor("#f6a623"));
            }

            if (!PrefsHelper.setbookingorderstauts(getApplication()).equals("5")) {
                detailtxt.setText("Detail");
                detailcolor.setBackgroundColor(Color.parseColor("#62b606"));
            }


        }
        if (PrefsHelper.setbookingtype(getApplication()).equals("past")) {
            titletext.setText("Past");
            cancelbtn.setVisibility(View.GONE);
            msnbtn.setVisibility(View.GONE);
            callbtn.setVisibility(View.GONE);
            timetxt.setText(PrefsHelper.setbookingupdatetime(getApplication()));
//            titletext.setText("Now");

            if (PrefsHelper.setbookingdrivername(getApplication()).equals("")) {
                driverlayout.setVisibility(View.GONE);
            }
            if (PrefsHelper.setbookingpayment(getApplication()).equals("official")) {
                bookingtypetxt.setText("Official Booking");
                blueline.setVisibility(View.VISIBLE);
                redline.setVisibility(View.GONE);
                bglayoutcolor.setBackgroundColor(Color.parseColor("#0255a6"));
            }
            if (!PrefsHelper.setbookingpayment(getApplication()).equals("official")) {
                bookingtypetxt.setText("Personal Booking");
                blueline.setVisibility(View.GONE);
                redline.setVisibility(View.VISIBLE);
                bglayoutcolor.setBackgroundColor(Color.parseColor("#c63725"));
            }
            if (PrefsHelper.setbookingorderstauts(getApplication()).equals("5")) {
                detailtxt.setText("Pending");
                detailcolor.setBackgroundColor(Color.parseColor("#f6a623"));
            }

            if (!PrefsHelper.setbookingorderstauts(getApplication()).equals("5")) {
                detailtxt.setText("Detail");
                detailcolor.setBackgroundColor(Color.parseColor("#62b606"));
            }
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.callbtn:
                Intent call = new Intent(CALL, Uri.parse("tel:" + PrefsHelper.setbookingdrivernumber(getApplication())));
                startActivity(call);
                break;
            case R.id.backbtn:
                Intent intent = new Intent();
                intent.setClass(BookingDetailActivity.this, NewBookingActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.cancelbtn:


                new AlertDialog.Builder(this)
                        .setTitle("")
                        .setMessage("do you want cancel this order?")
                        //
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String url = "http://2go.ladesign.tw/api_booking/cancel_order?order_id=" + PrefsHelper.setbookingorderid(getApplication());
                                aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                                    @Override
                                    public void callback(String url, JSONObject object, AjaxStatus status) {
                                        super.callback(url, object, status);
                                        try {
                                            if (object.getString("sys_code").equals("200")) {
                                                Toast.makeText(BookingDetailActivity.this, "canceled", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent();
                                                intent.setClass(BookingDetailActivity.this, NewBookingActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        }).show();
//                new
                break;
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

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (isExit == false) {
                isExit = true;

                if (!hasTask) {
                    timerExit.schedule(task, 1000);
                }
            } else {

            }
        }
        return false;
    }
}
