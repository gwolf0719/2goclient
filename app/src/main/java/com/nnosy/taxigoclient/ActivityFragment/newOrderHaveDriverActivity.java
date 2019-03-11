package com.nnosy.taxigoclient.ActivityFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.nnosy.taxigoclient.MainActivity;
import com.nnosy.taxigoclient.PrefsHelper;
import com.nnosy.taxigoclient.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nosy on 2017/3/28.
 */

public class newOrderHaveDriverActivity extends Activity {
    AQuery aQuery;
    ImageView carimg;
    TextView numbertxt, distancetxt, estimatedtimetxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderhaveflayout);
        carimg = (ImageView) findViewById(R.id.carimg);
        numbertxt = (TextView) findViewById(R.id.ohdnametxt);
        distancetxt = (TextView) findViewById(R.id.distancetxt);
        estimatedtimetxt = (TextView) findViewById(R.id.estimatedtimetxt);
        if (PrefsHelper.setcarclass(getApplication()).equals("Budget")) {
            carimg.setImageDrawable(getResources().getDrawable(R.mipmap.car_img_red));

        }
        if (PrefsHelper.setcarclass(getApplication()).equals("Teks1m")) {
            carimg.setImageDrawable(getResources().getDrawable(R.mipmap.car_img_golden));
        }
        if (PrefsHelper.setcarclass(getApplication()).equals("Executive")) {
            carimg.setImageDrawable(getResources().getDrawable(R.mipmap.car_img_blue));
        }

        aQuery = new AQuery(this);
        String driverurl = "https://www.mmas.biz///api_booking/get_once?order_id=" + PrefsHelper.setclientorderid(getApplication());
        aQuery.ajax(driverurl, null, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                System.out.println(" ==== object ==== " + object);
                try {
                    if (object.getString("sys_code").equals("200")) {
                        estimatedtimetxt.setText(object.getJSONObject("order_info").getString("class").toString()+"．"+object.getJSONObject("order_info").getJSONObject("partner").getString("texi_number").toString());
                        distancetxt.setText(object.getJSONObject("order_info").getJSONObject("partner").getString("ranks").toString());
                        numbertxt.setText(object.getJSONObject("order_info").getJSONObject("partner").getString("name").toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent();
                intent.setClass(newOrderHaveDriverActivity.this, MainActivity.class);
                startActivity(intent);
//                    getActivity().finish();
                finish();

            }
        }, 3000);
    }


}
