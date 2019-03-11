package com.nnosy.taxigoclient;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Nosy on 2017/2/14.
 */

public class IntentService {
    //    AQuery aq = new AQuery(getActivity());
    AQuery aQuery = new AQuery(getActivity());
    public static String SENDER_ID = "350397581906";

    public enum PlayServicesState {
        SUPPROT, NEED_PLAY_SERVICE, UNSUPPORT;
    }

    public enum GCMState {
        PLAY_SERVICES_NEED_PLAY_SERVICE, PLAY_SERVICES_UNSUPPORT, NEED_REGISTER, AVAILABLE;
    }

    public interface MagicLenGCMListener {
        void gcmRegistered(boolean successfull, String regID);

        boolean gcmSendRegistrationIdToAppServer(String regID);
    }

    private static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";

    public final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    public static void sendLocalNotification(Context context, int notifyID,
                                             int drawableSmallIcon, String title, String msg, String info,
                                             boolean autoCancel, PendingIntent pendingIntent) {
        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                context).setSmallIcon(drawableSmallIcon).setContentTitle(title)
                .setContentText(msg).setAutoCancel(autoCancel)
                .setContentInfo(info).setDefaults(Notification.DEFAULT_ALL);

        if (msg.length() > 10) {
            mBuilder.setStyle(new NotificationCompat.BigTextStyle()
                    .bigText(msg));
        }
        mBuilder.setContentIntent(pendingIntent);
        mNotificationManager.notify(notifyID, mBuilder.build());
    }

    private Activity activity;
    private MagicLenGCMListener listener;

    public IntentService(Activity activity) {
        this(activity, null);
    }

    public IntentService(Activity activity, MagicLenGCMListener listener) {
        this.activity = activity;
        setMagicLenGCMListener(listener);
    }

    public Activity getActivity() {
        return activity;
    }

    public void setMagicLenGCMListener(MagicLenGCMListener listener) {
        this.listener = listener;
    }

    public GCMState startGCM() {
        return openGCM();
    }

    public GCMState openGCM() {
        switch (checkPlayServices()) {
            case SUPPROT:
//                thread.run();
                if (PrefsHelper.setpushkey(getActivity()) == null) {
                    String regid = getRegistrationId();

                    if (regid.isEmpty()) {
                        registerInBackground();
                        return GCMState.NEED_REGISTER;
                    } else {
                        return GCMState.AVAILABLE;
                    }
                }

            case NEED_PLAY_SERVICE:
                return GCMState.PLAY_SERVICES_NEED_PLAY_SERVICE;
            default:
                return GCMState.PLAY_SERVICES_UNSUPPORT;
        }
    }

    public String getRegistrationId() {
//        aq = new AQuery(getActivity());
        final SharedPreferences prefs = getGCMPreferences();
        final String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        System.out.println("====registrationid===" + registrationId);

//        PrefsHelper.getregiid(getActivity(), registrationId);
//        String url = "uat.fofo.tw/2go_be/api/member_push_key_setting?email=" + PrefsHelper.setmail(getActivity()) + "&os=android&push_key=" + registrationId;
//        String url = "http://event.fubon.name/backend/api/member_push_key_setting?key="+registrationId+"&os=android";

//        aq.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
//            @Override
//            public void callback(String url, JSONObject object, AjaxStatus status) {
//                super.callback(url, object, status);
//                System.out.println(" ==== key status ===== " + object);
//
//            }
//        });

        if (registrationId.isEmpty()) {
            return "";
        }

        int registeredVersion = prefs.getInt(IntentService.PROPERTY_APP_VERSION,
                Integer.MIN_VALUE);
        int currentVersion = getAppVersion();
        if (registeredVersion != currentVersion) {
            return "";
        }
        String url = "https://www.mmas.biz///api_member/push_key?member_id=" + PrefsHelper.setphonenumber(getActivity()) + "&push_key=" + registrationId;
//        if (aQuery != null) {
            aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                @Override
                public void callback(String url, JSONObject object, AjaxStatus status) {
                    super.callback(url, object, status);
                    System.out.println(" ==== key status ===== " + object);
                    System.out.println(" ==== url ==== " + url.toString());
                    if (object.equals("200")) {
                        PrefsHelper.getpushkey(getActivity(), registrationId);
                    }
                }
            });
//        }
        return registrationId;

    }

    public int getAppVersion() {
        try {
            PackageInfo packageInfo = activity.getPackageManager()
                    .getPackageInfo(activity.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {

            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    private SharedPreferences getGCMPreferences() {
        return activity.getSharedPreferences(activity.getClass()
                .getSimpleName(), Context.MODE_PRIVATE);
    }

    public PlayServicesState checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(activity);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, activity,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
                return PlayServicesState.NEED_PLAY_SERVICE;
            } else {
                return PlayServicesState.UNSUPPORT;
            }
        }
        return PlayServicesState.SUPPROT;
    }

    private void registerInBackground() {
        new AsyncTaskRegister().execute();
    }

    private final class AsyncTaskRegister extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            String regid = "";
            try {
                GoogleCloudMessaging gcm = GoogleCloudMessaging
                        .getInstance(activity);
                regid = gcm.register(SENDER_ID);

                if (regid == null || regid.isEmpty()) {
                    return "";
                }


                storeRegistrationId(regid);

                if (listener != null) {
                    if (!listener.gcmSendRegistrationIdToAppServer(regid)) {
                        storeRegistrationId("");
                        return "";
                    }
                }
            } catch (IOException ex) {

            }
            String url = "https://www.mmas.biz///api_member/push_key?member_id=" + PrefsHelper.setphonenumber(getActivity()) + "&push_key=" + regid;
            aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                @Override
                public void callback(String url, JSONObject object, AjaxStatus status) {
                    super.callback(url, object, status);
                    System.out.println(" ==== key status ===== " + object);
                    System.out.println(" ==== url ==== " + url.toString());
                    if (object.equals("200")) {

                    }
                }
            });
            PrefsHelper.getpushkey(getActivity(), regid);
            return regid;
        }

        @Override
        protected void onPostExecute(String msg) {
            if (listener != null) {
                listener.gcmRegistered(!msg.isEmpty(), msg.toString());
            }
        }
    }

    private void storeRegistrationId(String regId) {
        final SharedPreferences prefs = getGCMPreferences();
        int appVersion = getAppVersion();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_REG_ID, regId);
        editor.putInt(PROPERTY_APP_VERSION, appVersion);
        editor.commit();
    }
//    private int sum = 0;
//    private boolean running = true;
//    Thread thread = new Thread(new Runnable(){
//
//        @Override
//        public void run() {
////            while(running){
////                sum++;
////                Log.d("ServiceProject","ServiceSwe : "+sum);
//                Intent intent=new Intent();
//                intent.putExtra("Swe_sum", ""+sum);
//                intent.setAction("gcm.notification.click_action");
//                activity.sendBroadcast(intent);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    running = false;
//                    e.printStackTrace();
////                }
//            }
//        }});

}
