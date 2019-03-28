package com.nnosy.taxigoclient.ActivityFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.loopj.android.image.SmartImageView;
import com.nnosy.taxigoclient.MainActivity;
import com.nnosy.taxigoclient.PrefsHelper;
import com.nnosy.taxigoclient.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nosy on 2017/3/28.
 */

public class RatdriverActivity extends Activity implements View.OnClickListener {
    RatingBar ratingBar;
    float rate;
    TextView drivernametxt;
    RelativeLayout ratebtn;
    AQuery aQuery;
    SmartImageView ratdriverimg;
    EditText reporttxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ratdriverlayout);
        initview();
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rate = v;
            }
        });
        ratdriverimg.setImageUrl(PrefsHelper.setavapic(getApplication()));
    }

    private void initview() {
        drivernametxt = (TextView) findViewById(R.id.drivernametxt);
        drivernametxt.setText(PrefsHelper.setdrivername(getApplication()));
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratebtn = (RelativeLayout) findViewById(R.id.ratebtn);
        ratebtn.setOnClickListener(this);
        reporttxt = (EditText) findViewById(R.id.reporttxt);
        ratdriverimg = (SmartImageView) findViewById(R.id.ratdriverimg);
        aQuery = new AQuery(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ratebtn:
                pushrate();
                break;
        }
    }

    public void pushrate() {
        String url = "https://www.mmas.biz///api_partner/score_for_partner?order_id=" + PrefsHelper.setclientorderid(getApplication()) + "&rank=" + rate + "&notice=" + reporttxt.getText().toString();
        System.out.println("ratingfragment url = " + url);
        System.out.println("ratfragment orderid ===== " + PrefsHelper.setclientorderid(getApplication()));
        aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                System.out.println("ratingfragment ===== : " + object);
                try {
                    if (object.getString("sys_code").equals("200")) {
                        reporttxt.setText("");
                        Intent intent = new Intent();
                        intent.setClass(RatdriverActivity.this, MainActivity.class);
                        startActivity(intent);

                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}