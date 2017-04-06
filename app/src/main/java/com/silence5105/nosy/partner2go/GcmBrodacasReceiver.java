package com.silence5105.nosy.partner2go;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.silence5105.nosy.partner2go.ActivityFragment.ClientSafelyActivity;
import com.silence5105.nosy.partner2go.ActivityFragment.OfficleReservationSActivity;
import com.silence5105.nosy.partner2go.ActivityFragment.newOrderHaveDriverActivity;

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
 * Created by Nosy on 2017/2/14.
 */
public class GcmBrodacasReceiver extends BroadcastReceiver {
    public static final int NOTIFICATION_ID = 0;
    private int tab;
    public Bundle extras;
    private HttpURLConnection httpURLConnection;

    @Override
    public void onReceive(final Context context, Intent intent) {
        extras = intent.getExtras();

        final String orderid = extras.getString("gcm.notification.body");
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
        String messageType = gcm.getMessageType(intent);
        System.out.println(" ==== gcm ===== :" + context.toString());
        System.out.println(" ==== gcm msmtype == =:" + messageType.toString());
        if (!extras.isEmpty()) {
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                Log.i(getClass() + "gcm error", extras.toString());
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                Log.i(getClass() + "gcm delete", extras.toString());
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                Log.i(getClass() + "gcm nessage", extras.toString());
                System.out.println(" ---- gcm message --- : " + extras.toString());
                System.out.println("====");
                //又把推播改回來首頁
                Intent i = new Intent();


//                PrefsHelper.getorderid(context, orderid);
                PrefsHelper.getclientorderid(context, orderid);
                System.out.println("gcm  orderid === " + orderid);
//                int tab =0;
//                Intent i = new Intent(context,TitlebarActivity.class);
//                i.putExtra("tab",tab);
                String msg = extras.toString();
//                String msg = extras.getString("message").toString();

//                String test = msg.substring(0, 1);
//                String showtxt = msg.substring(1, msg.length());
//                System.out.println(" ---- gcmmsg ---- : " + showtxt);
                System.out.println(" ---- gcm message ---- : " + extras.toString());

//                System.out.println(" ---- gcmmsgfirst ---- :" + test);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                 booking = null;
                        try {
                            URL booking = new URL("http://2go.ladesign.tw/api_booking/get_once");
                            httpURLConnection = (HttpURLConnection) booking.openConnection();
                            httpURLConnection.setRequestMethod("POST");
                            httpURLConnection.setDoOutput(true);
                            httpURLConnection.setDoInput(true);
                            String data = "order_id=" + orderid;
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
//                            if(PrefsHelper.sethav)
                            //order_type
                            JSONObject jsonObject = new JSONObject(String.valueOf(sb));
                            String cost = jsonObject.getJSONObject("order_info").getString("cost").toString();
                            PrefsHelper.getcost(context, cost);
                            String startlocation = jsonObject.getJSONObject("order_info").getString("start_address").toString();
                            PrefsHelper.getstartlocation(context, startlocation);
                            String endlocation = jsonObject.getJSONObject("order_info").getString("end_address").toString();
                            PrefsHelper.getendlocation(context, endlocation);
                            String rate = jsonObject.getJSONObject("order_info").getJSONObject("partner").getString("ranks").toString();
                            PrefsHelper.getrate(context, rate);
                            String gotime = jsonObject.getJSONObject("order_info").getString("go_time").toString();
                            PrefsHelper.getgotime(context, gotime);
                            String drivername = jsonObject.getJSONObject("order_info").getJSONObject("partner").getString("name").toString();
                            PrefsHelper.getdrivername(context, drivername);
                            String drivernumber = jsonObject.getJSONObject("order_info").getJSONObject("partner").getString("partner_id");
                            PrefsHelper.getdriverphonenumber(context, drivernumber);
                            String textnumber = jsonObject.getJSONObject("order_info").getJSONObject("partner").getString("texi_number");
                            PrefsHelper.gettextnumber(context, textnumber);
                            PrefsHelper.getdrivercarclass(context, jsonObject.getJSONObject("order_info").getString("class").toString());
                            PrefsHelper.getdriverlat(context, jsonObject.getJSONObject("order_info").getJSONObject("partner").getString("lat").toString());
                            PrefsHelper.getdriverlng(context, jsonObject.getJSONObject("order_info").getJSONObject("partner").getString("lng").toString());
                            PrefsHelper.getordertype(context,jsonObject.getJSONObject("order_info").getString("order_type"));

                            System.out.println(" gcm    ====== rate time mame number  " + rate + " " + drivername + " " + drivernumber + " " + gotime + " " + textnumber+ " "+jsonObject.getJSONObject("order_info").getString("order_type"));
                            if (jsonObject.getString("sys_code").equals("200")) {
                                PrefsHelper.getorderid(context, orderid);

                            }
//                                }
//                            }).start();

//                            Thread.sleep(1000);

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();

                i.setAction("android.intent.Main");
                i.addCategory("android.intent.action.LAUNCHER");
                if (extras.getString("gcm.notification.click_action").equals("2")) {
//                    IntentService.sendLocalNotification(context, NOTIFICATION_ID, R.mipmap.ic_launcher_, "乘客", "有司機前往你的位置", "", true, PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT));
                    PrefsHelper.gethaved(context, "1");
//                    Bundle bundle = new Bundle();
                    Intent intent1 = new Intent();
//                    intent.putExtra("1","1");
//                    bundle.putString("1","1");
//                    intent1.putExtra(bundle);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent1.setClass(context, newOrderHaveDriverActivity.class);
                    context.startActivity(intent1);
                }
                if (extras.getString("gcm.notification.click_action").equals("3")) {
//                    IntentService.sendLocalNotification(context, NOTIFICATION_ID, R.mipmap.ic_launcher_, "乘客", "感謝妳的搭乘", "", true, PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT));
                    PrefsHelper.gethaved(context, "2");
                    Intent intent1 = new Intent();
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent1.setClass(context, MainActivity.class);
                    context.startActivity(intent1);
                }

                if (extras.getString("gcm.notification.click_action").equals("8")) {
                    PrefsHelper.getlast30m(context,"1");
                    Intent intent1 = new Intent();
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent1.setClass(context, MainActivity.class);
                    context.startActivity(intent1);
                    System.out.println("gcm ====== 8 ");
                }
                if (extras.getString("gcm.notification.click_action").equals("5")) {
                    PrefsHelper.gethaved(context, "1");
//                    Bundle bundle = new Bundle();
                    Intent intent1 = new Intent();
//                    intent.putExtra("1","1");
//                    bundle.putString("1","1");
//                    intent1.putExtra(bundle);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent1.setClass(context, newOrderHaveDriverActivity.class);
                    context.startActivity(intent1);
                }
                if (extras.getString("gcm.notification.click_action").equals("7")) {
                    PrefsHelper.getlast5m(context,"1");
                    Intent intent1 = new Intent();
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent1.setClass(context, MainActivity.class);
                    context.startActivity(intent1);
                    System.out.println("gcm ====== 7 ");
                }

                if (extras.getString("gcm.notification.click_action").equals("4")) {
//                    IntentService.sendLocalNotification(context, NOTIFICATION_ID, R.mipmap.ic_launcher_, "乘客", "到達目的地", "", true, PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT));
                    PrefsHelper.gethaved(context, "3");
//                    StartDriverActivity startDriverActivity = new StartDriverActivity();
//                    startDriverActivity.ratlayout();
                    Intent intent1 = new Intent();
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent1.setClass(context, ClientSafelyActivity.class);
                    context.startActivity(intent1);
                }
                if (extras.getString("gcm.notification.click_action").equals("9")) {
//                    IntentService.sendLocalNotification(context, NOTIFICATION_ID, R.mipmap.ic_launcher_, "乘客", "到達目的地", "", true, PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT));
                    if (PrefsHelper.setdriverotw(context) != null) {
                        if (PrefsHelper.setdriverotw(context).equals("1")) {
                            PrefsHelper.gethaved(context, "9");
                        }
                    }
                    if (PrefsHelper.setdriverotw(context) != null ) {
                        if (!PrefsHelper.setdriverotw(context).equals("1")) {
                            PrefsHelper.gethaved(context, "7");
                        }
                    }
                        if (PrefsHelper.setdriverotw(context) == null){
                            PrefsHelper.gethaved(context, "7");
                        }
//                    StartDriverActivity startDriverActivity = new StartDriverActivity();
//                    startDriverActivity.ratlayout();
                    Intent intent1 = new Intent();
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent1.setClass(context, MainActivity.class);
                    context.startActivity(intent1);
                }
                MainActivity mainActivity = new MainActivity();

                if (extras.getString("gcm.notification.click_action").equals("6")) {
//                    IntentService.sendLocalNotification(context, NOTIFICATION_ID, R.mipmap.ic_launcher_, "乘客", "到達目的地", "", true, PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT));
                    PrefsHelper.gethaved(context, "6");
//                    StartDriverActivity startDriverActivity = new StartDriverActivity();
//                    startDriverActivity.ratlayout();
                    Intent intent1 = new Intent();
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent1.setClass(context, OfficleReservationSActivity.class);
                    context.startActivity(intent1);

                }

            }
            setResultCode(Activity.RESULT_OK);
        }
    }
}
