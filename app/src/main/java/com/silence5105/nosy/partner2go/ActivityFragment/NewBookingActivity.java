package com.silence5105.nosy.partner2go.ActivityFragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.silence5105.nosy.partner2go.MainActivity;
import com.silence5105.nosy.partner2go.MyBookingAdapter;
import com.silence5105.nosy.partner2go.MyBookingItems;
import com.silence5105.nosy.partner2go.PrefsHelper;
import com.silence5105.nosy.partner2go.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Nosy on 2017/3/5.
 */

public class NewBookingActivity extends Activity implements View.OnClickListener {
    AQuery aQuery;
    RecyclerView rv;
    ArrayList<MyBookingItems> myBookingItemses = new ArrayList<MyBookingItems>();
    private ProgressDialog dialog;
    MyBookingAdapter myBookingAdapter;
    LinearLayout nowbtn, pastbtn;
    RelativeLayout backbtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aQuery = new AQuery(this);
//        loadlist();
        setContentView(R.layout.newmybookinglayout);
//        initview();

        dialog = ProgressDialog.show(this, "",
                "please wait.", true);
        dialog.show();
        PrefsHelper.getbookingtype(getApplication(), "now");
        nowbtn = (LinearLayout) findViewById(R.id.nowbtn);
        nowbtn.setOnClickListener(this);
        backbtn = (RelativeLayout) findViewById(R.id.backbtn);
        backbtn.setOnClickListener(this);
        nowbtn.setBackgroundColor(Color.parseColor("#33000000"));
        pastbtn = (LinearLayout) findViewById(R.id.pastbtn);
        pastbtn.setOnClickListener(this);
        rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication());
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(linearLayoutManager);
//        GatewayAdpter gatewayAdpter = new GatewayAdpter(getActivity(), arrayList);
        myBookingAdapter = new MyBookingAdapter(this, myBookingItemses);
        myBookingAdapter.setonitemclick(new MyBookingAdapter.Onitemclick() {
            @Override
            public void onitemclick(View view, int position) {
                Intent intent = new Intent();
                intent.setClass(NewBookingActivity.this, BookingDetailActivity.class);
                startActivity(intent);
                finish();
            }
        });
        loadlist1();

    }

    private void initview() {


    }

    public void loadlist1() {
        String url = "https://my.here2go.asia///api_booking/get_order_list?member_id=" + PrefsHelper.setphonenumber(getApplication());
        aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                System.out.println(" booking  object ===== : " + object);

//                try {
////                    JSONObject jsonObject = new JSONObject(object);
//                    for (int i = 0; i < object.getJSONArray("datalist").length(); i++) {
//                        myBookingItemses.add(new MyBookingItems(object.getJSONArray("datalist").getJSONObject(i)));
//////                        if (object.getJSONArray("datalist").getJSONObject(i).getString("order_status").equals("9")) {
////                            myBookingItemses.add(new MyBookingItems(jsonObject.getJSONArray("datalist").getJSONObject(i)));
//                            System.out.println("MY BOOKING OBJ = === :" + object.getJSONArray("datalist").getJSONObject(0).getString("start_address").toString());
//                        }
////                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                try {
                    if (object.getString("sys_code").equals("200")) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    //                                            Thread.sleep(1000);

                                } finally {
                                    dialog.dismiss();
                                }
                            }
                        }).start();
                        clearList(myBookingItemses);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    try {
//                    JSONObject jsonObject = new JSONObject(object);
                        for (int i = 0; i < object.getJSONArray("datalist").length(); i++) {
//                        myBookingItemses.add(new MyBookingItems(object.getJSONArray("datalist").getJSONObject(i)));
                            if (object.getJSONArray("datalist").getJSONObject(i).getString("order_status").equals("0") || object.getJSONArray("datalist").getJSONObject(i).getString("order_status").equals("1") || object.getJSONArray("datalist").getJSONObject(i).getString("order_status").equals("2") || object.getJSONArray("datalist").getJSONObject(i).getString("order_status").equals("5") || object.getJSONArray("datalist").getJSONObject(i).getString("order_status").equals("6") || object.getJSONArray("datalist").getJSONObject(i).getString("order_status").equals("7")) {
                                myBookingItemses.add(new MyBookingItems(object.getJSONArray("datalist").getJSONObject(i)));
                                System.out.println("MY BOOKING OBJ = === :" + object.getJSONArray("datalist").getJSONObject(i).toString());
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    rv.setAdapter(myBookingAdapter);
                }
            }
        });

    }

    public void clearList(List<MyBookingItems> f) {
        int size = f.size();
        if (size > 0) {
            f.removeAll(f);
            myBookingAdapter.notifyDataSetChanged();
        }
    }

    public void loadlist() {
        String url = "https://my.here2go.asia///api_booking/get_order_list?member_id=" + PrefsHelper.setphonenumber(getApplication());
        aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                System.out.println(" booking  object ===== : " + object);

//                try {
////                    JSONObject jsonObject = new JSONObject(object);
//                    for (int i = 0; i < object.getJSONArray("datalist").length(); i++) {
//                        myBookingItemses.add(new MyBookingItems(object.getJSONArray("datalist").getJSONObject(i)));
//////                        if (object.getJSONArray("datalist").getJSONObject(i).getString("order_status").equals("9")) {
////                            myBookingItemses.add(new MyBookingItems(jsonObject.getJSONArray("datalist").getJSONObject(i)));
//                            System.out.println("MY BOOKING OBJ = === :" + object.getJSONArray("datalist").getJSONObject(0).getString("start_address").toString());
//                        }
////                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                try {
                    if (object.getString("sys_code").equals("200")) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    //                                            Thread.sleep(1000);

                                } finally {
                                    dialog.dismiss();
                                }
                            }
                        }).start();
                        clearList(myBookingItemses);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    try {
//                    JSONObject jsonObject = new JSONObject(object);
                        for (int i = 0; i < object.getJSONArray("datalist").length(); i++) {
//                        myBookingItemses.add(new MyBookingItems(object.getJSONArray("datalist").getJSONObject(i)));
                            if (object.getJSONArray("datalist").getJSONObject(i).getString("order_status").equals("9") || object.getJSONArray("datalist").getJSONObject(i).getString("order_status").equals("3")) {
                                myBookingItemses.add(new MyBookingItems(object.getJSONArray("datalist").getJSONObject(i)));
//                                System.out.println("MY BOOKING OBJ = === :" + object.getJSONArray("datalist").getJSONObject(0).getString("start_address").toString());
                                System.out.println("MY BOOKING OBJ = === :" + object.getJSONArray("datalist").getJSONObject(i).toString());
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    myBookingAdapter.notifyDataSetChanged();
                    rv.setAdapter(myBookingAdapter);
                }
            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.nowbtn:
                PrefsHelper.getbookingtype(getApplication(), "now");
                dialog = ProgressDialog.show(this, "",
                        "please wait.", true);
                dialog.show();
                nowbtn.setBackgroundColor(Color.parseColor("#33000000"));
                pastbtn.setBackgroundResource(0);
                loadlist1();
                break;
            case R.id.pastbtn:
                PrefsHelper.getbookingtype(getApplication(), "past");
                dialog = ProgressDialog.show(this, "",
                        "please wait.", true);
                dialog.show();
                pastbtn.setBackgroundColor(Color.parseColor("#33000000"));
//                myBookingAdapter.
                nowbtn.setBackgroundResource(0);
                loadlist();
                break;
            case R.id.backbtn:
                Intent intent = new Intent();
                intent.setClass(NewBookingActivity.this, MainActivity.class);
                startActivity(intent);

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
