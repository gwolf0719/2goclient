package com.silence5105.nosy.partner2go.ActivityFragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.silence5105.nosy.partner2go.PrefsHelper;
import com.silence5105.nosy.partner2go.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Nosy on 2017/3/14.
 */

public class BookingCarSelectFragment extends Fragment implements View.OnClickListener {
    TextView faretxt;
    LinearLayout teks1mbtn, budgetbtn, extbtn;
    ImageView t1mimg, budgetimg, extimg;
    RelativeLayout bookingbtn;
    AQuery aQuery;
    private ProgressDialog dialog;
    HttpURLConnection httpURLConnection;

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
        extbtn = (LinearLayout) view.findViewById(R.id.executivebtn);
        extbtn.setOnClickListener(this);
        t1mimg = (ImageView) view.findViewById(R.id.teks1mimg);
        budgetimg = (ImageView) view.findViewById(R.id.budgetimg);
        extimg = (ImageView) view.findViewById(R.id.exectuiveimg);
        faretxt = (TextView) view.findViewById(R.id.faretxt);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bookingbtn:
//                Intent intent = new Intent();
//                intent.setClass(getActivity(), WaitDriverActivtiy.class);
//                startActivity(intent);
                System.out.println("bookingcarselectfragment ===== " + PrefsHelper.setphonenumber(getActivity()));
                new Thread(new Runnable() {


                    @Override
                    public void run() {
//                 booking = null;
                        try {
                            URL booking = new URL("http://2go.ladesign.tw///api_booking/order_create");
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
                                    + "&cost=" + faretxt.getText().toString()
                                    + "&expected_time_onboard=" + PrefsHelper.setalltime(getActivity());
//                            String testdata = "email=testpppp@gmail.com&class=Budget&start_address=123&end_address=321&start_location=123&end_location=321&distance=1";
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

                            System.out.println(" ==== responsecode" + sb);
                            JSONObject jsonObject = new JSONObject(sb.toString());

                            System.out.println("bookingcarselectfragment ===== :" + jsonObject.getString("sys_code").equals("200"));
                            if (jsonObject.getString("sys_code").equals("200")) {

                            }
                            System.out.println(" ==== data : " + "http://2go.ladesign.tw///api_booking/order_create?" + data);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case R.id.teks1mcarebtn:
                dialog = ProgressDialog.show(getActivity(), "",
                        "please wait.", true);
                dialog.show();
                PrefsHelper.getcarclass(getActivity(), "Teks1m");
                t1mimg.setImageDrawable(getResources().getDrawable(R.mipmap.choose_car_active));
                budgetimg.setImageDrawable(getResources().getDrawable(R.mipmap.choose_car));
                extimg.setImageDrawable(getResources().getDrawable(R.mipmap.choose_car));
//                System.out.println("bookingcarselect ===== " + PrefsHelper.setkm(getActivity()));
                String url = "http://2go.ladesign.tw///api_booking/estimated_cost?distance=" + PrefsHelper.setkm(getActivity()) + "&h=" + PrefsHelper.sethtim(getActivity());
                aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                    @Override
                    public void callback(String url, JSONObject object, AjaxStatus status) {
                        super.callback(url, object, status);
                        System.out.println("bookingselectcar ==== " + object);
                        try {
//                            if (object.getString("sys_code").equals("200")) {
                            faretxt.setText(object.getJSONObject("price").getString("Teks1m").toString());
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
                dialog = ProgressDialog.show(getActivity(), "",
                        "please wait.", true);
                dialog.show();
                PrefsHelper.getcarclass(getActivity(), "Budget");
                t1mimg.setImageDrawable(getResources().getDrawable(R.mipmap.choose_car));
                budgetimg.setImageDrawable(getResources().getDrawable(R.mipmap.choose_car_active));
                extimg.setImageDrawable(getResources().getDrawable(R.mipmap.choose_car));
                String url1 = "http://2go.ladesign.tw///api_booking/estimated_cost?distance=" + PrefsHelper.setkm(getActivity()) + "&h=" + PrefsHelper.sethtim(getActivity());
                aQuery.ajax(url1, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                    @Override
                    public void callback(String url, JSONObject object, AjaxStatus status) {
                        super.callback(url, object, status);
                        System.out.println("bookingselectcar ==== " + object);
                        try {
//                            if (object.getString("sys_code").equals("200")) {
                            faretxt.setText(object.getJSONObject("price").getString("Budget").toString());
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
                dialog = ProgressDialog.show(getActivity(), "",
                        "please wait.", true);
                dialog.show();
                PrefsHelper.getcarclass(getActivity(), "Executive");
                t1mimg.setImageDrawable(getResources().getDrawable(R.mipmap.choose_car));
                extimg.setImageDrawable(getResources().getDrawable(R.mipmap.choose_car_active));
                budgetimg.setImageDrawable(getResources().getDrawable(R.mipmap.choose_car));
                String url2 = "http://2go.ladesign.tw///api_booking/estimated_cost?distance=" + PrefsHelper.setkm(getActivity()) + "&h=" + PrefsHelper.sethtim(getActivity());
                aQuery.ajax(url2, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                    @Override
                    public void callback(String url, JSONObject object, AjaxStatus status) {
                        super.callback(url, object, status);
                        System.out.println("bookingselectcar ==== " + object);
                        try {
//                            if (object.getString("sys_code").equals("200")) {
                            faretxt.setText(object.getJSONObject("price").getString("Executive").toString());
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
