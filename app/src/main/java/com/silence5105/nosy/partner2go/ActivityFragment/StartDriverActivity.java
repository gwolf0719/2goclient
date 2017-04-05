package com.silence5105.nosy.partner2go.ActivityFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.silence5105.nosy.partner2go.PrefsHelper;
import com.silence5105.nosy.partner2go.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Nosy on 2017/3/2.
 */

public class StartDriverActivity extends AppCompatActivity implements View.OnClickListener {
    TextView startadresstxt, endadresstxt, estimatedtimetxt, estimatedistancetxt, estimatefaretxt, helpbtn;
    AQuery aQuery;
    FrameLayout container;
    RelativeLayout ratbglayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startdrivrtlayout);
        initview();
        String driverurl = "http://2go.ladesign.tw/api_booking/get_once?order_id=" + PrefsHelper.setclientorderid(getApplication());
        aQuery.ajax(driverurl, null, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                System.out.println(" ==== object ==== " + object);
                try {
                    startadresstxt.setText(object.getJSONObject("order_info").getString("start_address").toString());
                    endadresstxt.setText(object.getJSONObject("order_info").getString("end_address").toString());
                    estimatedtimetxt.setText(object.getJSONObject("order_info").getString("times").toString());
                    int distancetxt = Integer.parseInt(object.getJSONObject("order_info").getString("distance")) / 1000;
                    System.out.println(" ===== distancetxt : " + distancetxt);
                    estimatedistancetxt.setText(distancetxt + " km");
                    estimatefaretxt.setText(object.getJSONObject("order_info").getString("cost").toString());
                    PrefsHelper.getmenucost(getApplication(),estimatefaretxt.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        if (PrefsHelper.sethaved(getApplication()).equals("3")) {
            ratlayout();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.helpbtn:
                SmsManager smsMgr = SmsManager.getDefault();
//                Intent sent = new Intent();
//                sent.setAction("com.send");
//                PendingIntent sentIntent = PendingIntent.getBroadcast(this, 0, sent, 0);
//                Intent delivery = new Intent();
//                delivery.setAction("com.delivery");
//                PendingIntent deliveryIntent = PendingIntent.getBroadcast(this, 0, delivery, 0);
                smsMgr.sendTextMessage("0920516317", null, "sos", null, null);
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.putExtra("sms_body", "default content");
                sendIntent.setType("vnd.android-dir/mms-sms");
                startActivity(sendIntent);
                Toast.makeText(StartDriverActivity.this, "sended sms.", Toast.LENGTH_SHORT).show();


                break;
        }

    }

    public void ratlayout() {
        ratbglayout.setVisibility(View.VISIBLE);
        getFragmentManager().beginTransaction().replace(R.id.container, new RatFragment()).commit();
    }

    public void initview() {
        aQuery = new AQuery(this);
        startadresstxt = (TextView) findViewById(R.id.startadresstxt);
        endadresstxt = (TextView) findViewById(R.id.endadresstxt);
        estimatedtimetxt = (TextView) findViewById(R.id.estimatedtimetxt);
        estimatedistancetxt = (TextView) findViewById(R.id.estimatedistancetxt);
        estimatefaretxt = (TextView) findViewById(R.id.estimatefaretxt);
        helpbtn = (TextView) findViewById(R.id.helpbtn);
        helpbtn.setOnClickListener(this);
        container = (FrameLayout) findViewById(R.id.container);
        ratbglayout = (RelativeLayout) findViewById(R.id.ratbglayout);
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
                            }
        }
        return false;
    }
}
