package com.nnosy.taxigoclient.ActivityFragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.nnosy.taxigoclient.PrefsHelper;
import com.nnosy.taxigoclient.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nosy on 2017/4/10.
 */

public class BusinessInfoActivity extends Activity implements View.OnClickListener {
    TextView bnametxt, jobtxt, enametxt, bmailtxt, bjobtxt, bcartxt;
    AQuery aQuery;
    RelativeLayout backbtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessinfolayout);
        bnametxt = (TextView) findViewById(R.id.bnametxt);
        jobtxt = (TextView) findViewById(R.id.bjobtxt);
        enametxt = (TextView) findViewById(R.id.eidtxt);
        bmailtxt = (TextView) findViewById(R.id.emailtxt);
        bjobtxt = (TextView) findViewById(R.id.jobtxt);
        bcartxt = (TextView) findViewById(R.id.bcartxt);
        backbtn = (RelativeLayout) findViewById(R.id.backbtn);
        backbtn.setOnClickListener(this);
        aQuery = new AQuery(this);
        String url = "https://www.mmas.biz///api_official/member_business_info?member_id=" + PrefsHelper.setphonenumber(getApplication());
        aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                System.out.println("Businessactivity ==== : " + object);
                try {
                    if (object.getString("sys_code").equals("200")) {
                        bnametxt.setText(object.getJSONObject("info").getString("name").toString());
                        jobtxt.setText(object.getJSONObject("info").getString("department").toString());
                        enametxt.setText(object.getJSONObject("info").getString("empoyee_id").toString());
                        bmailtxt.setText(object.getJSONObject("info").getString("email"));
                        bjobtxt.setText(object.getJSONObject("info").getString("goverment"));
                        bcartxt.setText(object.getJSONObject("info").getString("class"));
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
            case R.id.backbtn:
                this.finish();
                break;
        }
    }
}
