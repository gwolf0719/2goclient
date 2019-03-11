package com.nnosy.taxigoclient.ActivityFragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.nnosy.taxigoclient.MainActivity;
import com.nnosy.taxigoclient.PrefsHelper;
import com.nnosy.taxigoclient.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nosy on 2017/3/14.
 */

public class BookingCarSelectFragment extends Fragment implements View.OnClickListener {
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
        ArrayAdapter<String> starttimelist = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                cashlist);
        sp.setAdapter(starttimelist);
        final ImageView cashimg = (ImageView) view.findViewById(R.id.cashimg);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                System.out.println("select = : " + parent + " " + view.getId() + " " + position + " " + id);
                if (position == 1) {
                    cashimg.setImageResource(R.mipmap.mcash);
                    PrefsHelper.getcashtype(getActivity(), "mcash");
                }
                if (position == 0) {
                    cashimg.setImageResource(R.mipmap.list_cash);
                    PrefsHelper.getcashtype(getActivity(), "cash");
                }
                if (position == 2) {
                    cashimg.setImageResource(R.mipmap.list_credit_card);
                    PrefsHelper.getcashtype(getActivity(), "ambank");
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
    public int SHOWVIEWINV = 0;
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bookingbtn:
                if (PrefsHelper.setcashtype(getActivity()).equals("cash")) {
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
                                        URL booking = new URL("https://www.mmas.biz///api_booking/order_create");
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
                                        System.out.println(" booking cost = : " + PrefsHelper.setcost(getActivity()));

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
//                                                        Message message = new Message();
//                                                        message.what = SHOWVIEWINV;
//                                                        viewhandler.sendMessage(message);
////
//                                                        Intent intent = new Intent();
//                                                        intent.setClass(getActivity(), WaitDriverActivtiy.class);
//                                                        startActivity(intent);
//                                                        getActivity().finish();
                                                        ((MainActivity)getActivity()).InvShowview();
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
//                                        System.out.println(" ==== data : " + "https://www.mmas.biz///api_booking/order_create?" + data);
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
                if (PrefsHelper.setcashtype(getActivity()).equals("mcash")) {
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
                                        URL booking = new URL("https://www.mmas.biz/api_booking/order_create");
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
//                                                        dialog.dismiss();
                                                        ((MainActivity) getActivity()).InvShowview();
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
//                                        System.out.println(" ==== data : " + "https://www.mmas.biz///api_booking/order_create?" + data);
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
                if (PrefsHelper.setcashtype(getActivity()).equals("ambank")) {
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
                                        URL booking = new URL("https://www.mmas.biz///api_booking/order_create");
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
//                                                        Message message = new Message();
//                                                        message.what = SHOWVIEWINV;
//                                                        viewhandler.sendMessage(message);
////
//                                                        Intent intent = new Intent();
//                                                        intent.setClass(getActivity(), WaitDriverActivtiy.class);
//                                                        startActivity(intent);
//                                                        getActivity().finish();
                                                        ((MainActivity)getActivity()).InvShowview();
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
//                                        System.out.println(" ==== data : " + "https://www.mmas.biz///api_booking/order_create?" + data);
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
                                    URL booking = new URL("https://www.mmas.biz///api_booking/order_create");
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
//                                                    Message message = new Message();
//                                                    message.what = SHOWVIEWINV;
//                                                    viewhandler.sendMessage(message);
                                                    PrefsHelper.gethaved(getActivity(), "5");
//                                                    Intent intent = getActivity().getIntent();
//                                                    getActivity().finish();
//                                                    startActivity(intent);
                                                    ((MainActivity) getActivity()).InvShowview();
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
//                                        System.out.println(" ==== data : " + "https://www.mmas.biz///api_booking/order_create?" + data);
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
//                                URL booking = new URL("https://www.mmas.biz///api_booking/order_create");
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
//                                System.out.println(" ==== data : " + "https://www.mmas.biz///api_booking/order_create?" + data);
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
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                final View v = inflater.inflate(R.layout.alertdiloguse, null);
                new AlertDialog.Builder(getActivity())
                        .setTitle("What Purpose?")
                        .setItems(liststring.toArray(new String[liststring.size()]), new DialogInterface.OnClickListener()

                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String name = liststring.get(which);
//                                    Toast.makeText(getApplicationContext(),   name, Toast.LENGTH_SHORT).show();

                                new AlertDialog.Builder(getActivity())
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

//
//                                                progressDialog = ProgressDialog.show(getActivity(), "Please wait.",
//                                                        "", true);
                                                System.out.println("official bookingcarselectfragment ===== " + PrefsHelper.setphonenumber(getActivity()));

                                                new Thread(new Runnable() {


                                                    @Override
                                                    public void run() {
//                 booking = null;
                                                        try {
                                                            URL booking = new URL("https://www.mmas.biz///api_booking/order_create");
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
                                                                Intent intent = getActivity().getIntent();
                                                                getActivity().finish();
                                                                startActivity(intent);
//                                                                    cleanmap();
//                                                                    Toast.makeText(MainActivity.this, "Wait for order review.", Toast.LENGTH_SHORT).show();
                                                            }
                                                            if (jsonObject.getString("sys_code").equals("500")) {
                                                                Looper.prepare();
                                                                Toast.makeText(getActivity(), "Must apply 45 minutes ago", Toast.LENGTH_SHORT).show();
//                                                                            cleanmap();
                                                                Looper.loop();

                                                            }
//                                                                        System.out.println(" ==== data : " + "https://www.mmas.biz///api_booking/order_create?" + data);
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
                        }).show();
                break;
            case R.id.teks1mcarebtn:
                dialog = ProgressDialog.show(getActivity(), "", "please wait.", true);
                dialog.show();
                teks1mtxt.setText(R.string.teks1mtxtaction);
                budgettxt.setText(R.string.budgettxt);
                executivetxt.setText(R.string.exexutivetxt);
                PrefsHelper.getcarclass(getActivity(), "Teks1m");
                t1mimg.setImageDrawable(getResources().getDrawable(R.mipmap.choose_car_active));
                budgetimg.setImageDrawable(getResources().getDrawable(R.mipmap.choose_car));
                extimg.setImageDrawable(getResources().getDrawable(R.mipmap.choose_car));
                //                System.out.println("bookingcarselect ===== " + PrefsHelper.setkm(getActivity()));
                String url = "https://www.mmas.biz/api_booking/estimated_cost?distance=" + PrefsHelper.setkm(getActivity()) + "&h=" + PrefsHelper.sethtim(getActivity());
                aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>()

                {
                    @Override
                    public void callback(String url, JSONObject object, AjaxStatus status) {
                        super.callback(url, object, status);
                        System.out.println("bookingselectcar ==== " + object+ "\n"+url);
                        try {
//                            if (object.getString("sys_code").equals("200")) {
                            faretxt.setText("RM " + object.getJSONObject("price").getString("Teks1m").toString());
                            PrefsHelper.getcost(getActivity(), object.getJSONObject("price").getString("Teks1m").toString());
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
                budgetimg.setImageDrawable(getResources().getDrawable(R.mipmap.choose_car_active));
                extimg.setImageDrawable(getResources().getDrawable(R.mipmap.choose_car));
                String url1 = "https://www.mmas.biz///api_booking/estimated_cost?distance=" + PrefsHelper.setkm(getActivity()) + "&h=" + PrefsHelper.sethtim(getActivity());
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
                dialog = ProgressDialog.show(getActivity(), "", "please wait.", true);
                dialog.show();
                PrefsHelper.getcarclass(getActivity(), "Executive");
                t1mimg.setImageDrawable(getResources().getDrawable(R.mipmap.choose_car));
                extimg.setImageDrawable(getResources().getDrawable(R.mipmap.choose_car_active));
                budgetimg.setImageDrawable(getResources().getDrawable(R.mipmap.choose_car));
                String url2 = "https://www.mmas.biz///api_booking/estimated_cost?distance=" + PrefsHelper.setkm(getActivity()) + "&h=" + PrefsHelper.sethtim(getActivity());
                aQuery.ajax(url2, null, JSONObject.class, new AjaxCallback<JSONObject>()

                {
                    @Override
                    public void callback(String url, JSONObject object, AjaxStatus status) {
                        super.callback(url, object, status);
                        System.out.println("bookingselectcar ==== " + object);
                        try {
//                            if (object.getString("sys_code").equals("200")) {
                            faretxt.setText("RM " + object.getJSONObject("price").getString("Executive").toString());
                            PrefsHelper.getcost(getActivity(), object.getJSONObject("price").getString("Executive").toString());
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
