package com.silence5105.nosy.partner2go.ActivityFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

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
 * Created by Nosy on 2017/3/1.
 */

public class OrderHaveDriverActivity extends AppCompatActivity implements View.OnClickListener {
    TextView calldriverbtn, numbertxt, distancetxt, estimatedtimetxt;
    AQuery aQuery;
    private final static String CALL = "android.intent.action.CALL";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderhavedriverlayout);
        initview();
        String driverurl = "http://2go.ladesign.tw/api_booking/get_once?order_id=" + PrefsHelper.setclientorderid(getApplication());
        aQuery.ajax(driverurl, null, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                System.out.println(" ==== object ==== " + object);
                try {
                    numbertxt.setText(object.getJSONObject("order_info").getString("partner_id").toString());
                    estimatedtimetxt.setText(object.getJSONObject("order_info").getString("go_time").toString());
                    distancetxt.setText(object.getJSONObject("order_info").getString("go_distance").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void initview() {
        calldriverbtn = (TextView) findViewById(R.id.calldriverbtn);
        calldriverbtn.setOnClickListener(this);
        numbertxt = (TextView) findViewById(R.id.numbertxt);
        distancetxt = (TextView) findViewById(R.id.distancetxt);
        estimatedtimetxt = (TextView) findViewById(R.id.estimatedtimetxt);
        aQuery = new AQuery(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.calldriverbtn:
                Intent call = new Intent(CALL, Uri.parse("tel:" + numbertxt.getText().toString()));
                startActivity(call);
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
