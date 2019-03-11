package com.nnosy.taxigoclient.ActivityFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.nnosy.taxigoclient.PrefsHelper;
import com.nnosy.taxigoclient.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Nosy on 2017/4/1.
 */

public class TobeVerifiedActivity extends Activity implements View.OnClickListener {
    AQuery aQuery;
    TextView nametxt, jobtxt, createtimetxt, updatatimetxt, startadresstxt, endadresstxt, infotxt;
    RelativeLayout agreebtn, refusebtn;
    String orderid, memberid, review;
    ImageView backbtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tobeverifiedactivity);
        nametxt = (TextView) findViewById(R.id.vnametxt);
        jobtxt = (TextView) findViewById(R.id.vjobtxt);
        createtimetxt = (TextView) findViewById(R.id.vtimetxt);
        updatatimetxt = (TextView) findViewById(R.id.updatatimetxt);
        startadresstxt = (TextView) findViewById(R.id.startadresstxt);
        endadresstxt = (TextView) findViewById(R.id.endadresstxt);
        infotxt = (TextView) findViewById(R.id.whatptxt);
        agreebtn = (RelativeLayout) findViewById(R.id.agreebtn);
        agreebtn.setOnClickListener(this);
        refusebtn = (RelativeLayout) findViewById(R.id.refusebtn);
        refusebtn.setOnClickListener(this);
        backbtn = (ImageView) findViewById(R.id.backbtn);
        backbtn.setOnClickListener(this);
        System.out.println("TOBEACTIVITY ===== " + PrefsHelper.setlistselect(getApplication()));
        aQuery = new AQuery(this);
        String url = "https://www.mmas.biz///api_official/pending_review?member_id=" + PrefsHelper.setphonenumber(getApplication());
        aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                try {
                    if (object.getString("sys_code").equals("200")) {
                        nametxt.setText(object.getJSONArray("data_list").getJSONObject(PrefsHelper.setlistselect(getApplication())).getString("member_name").toString());
                        jobtxt.setText(object.getJSONArray("data_list").getJSONObject(PrefsHelper.setlistselect(getApplication())).getString("official_subject").toString());
                        createtimetxt.setText(object.getJSONArray("data_list").getJSONObject(PrefsHelper.setlistselect(getApplication())).getString("create_datetime").toString());
                        updatatimetxt.setText(object.getJSONArray("data_list").getJSONObject(PrefsHelper.setlistselect(getApplication())).getString("update_datetime").toString());
                        startadresstxt.setText(object.getJSONArray("data_list").getJSONObject(PrefsHelper.setlistselect(getApplication())).getString("start_address").toString());
                        endadresstxt.setText(object.getJSONArray("data_list").getJSONObject(PrefsHelper.setlistselect(getApplication())).getString("end_address").toString());
                        infotxt.setText(object.getJSONArray("data_list").getJSONObject(PrefsHelper.setlistselect(getApplication())).getString("official_info").toString());
                        orderid = object.getJSONArray("data_list").getJSONObject(PrefsHelper.setlistselect(getApplication())).getString("order_id").toString();
                        review = object.getJSONArray("data_list").getJSONObject(PrefsHelper.setlistselect(getApplication())).getString("review").toString();
                        memberid = object.getJSONArray("data_list").getJSONObject(PrefsHelper.setlistselect(getApplication())).getString("member_id").toString();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.agreebtn:
                String url = "https://www.mmas.biz///api_official/review_order?member_id=" + memberid + "&review=" + PrefsHelper.setphonenumber(getApplication()) + "&order_id=" + orderid + "&order_status=5";
                aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                    @Override
                    public void callback(String url, JSONObject object, AjaxStatus status) {
                        super.callback(url, object, status);
                        System.out.println("agress ===== : " + object);
                        Intent intent = new Intent();
                        intent.setClass(TobeVerifiedActivity.this, VerifiedActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });
                break;
            case R.id.refusebtn:
                String url1 = "https://www.mmas.biz///api_official/review_order?member_id=" + memberid + "&review=" + PrefsHelper.setphonenumber(getApplication()) + "&order_id=" + orderid + "&order_status=9";
                aQuery.ajax(url1, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                    @Override
                    public void callback(String url, JSONObject object, AjaxStatus status) {
                        super.callback(url, object, status);
                        System.out.println(" refusebtnclick ====== : " + object);
                        Intent intent = new Intent();
                        intent.setClass(TobeVerifiedActivity.this, VerifiedActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                break;
            case R.id.backbtn:
//                this.finish();
                Intent intent = new Intent();
                intent.setClass(TobeVerifiedActivity.this, VerifiedActivity.class);
                startActivity(intent);
                finish();
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
