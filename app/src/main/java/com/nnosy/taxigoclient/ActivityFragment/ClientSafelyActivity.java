package com.nnosy.taxigoclient.ActivityFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.loopj.android.image.SmartImageView;
import com.nnosy.taxigoclient.PrefsHelper;
import com.nnosy.taxigoclient.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nosy on 2017/3/28.
 */

public class ClientSafelyActivity extends Activity implements View.OnClickListener {
    TextView startadresstxt, endadresstxt, faretxt;
    RelativeLayout savebtn;
    AQuery aQuery;
    SmartImageView driverimg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safelylayout);
        initview();
        startadresstxt.setText(PrefsHelper.setstartadress(getApplication()).toString());
        endadresstxt.setText(PrefsHelper.setendaddress(getApplication()).toString());
        faretxt.setText(PrefsHelper.setmenucost(getApplication()).toString());
        ImageView cashimg = (ImageView) findViewById(R.id.cashimg);
        TextView cashtxt = (TextView) findViewById(R.id.cashtxt);
        driverimg.setImageUrl(PrefsHelper.setavapic(getApplication()));
        if (PrefsHelper.setcashtype(getApplication()).equals("cash")) {
            cashimg.setImageResource(R.mipmap.list_cash);
            cashtxt.setText("CASH");
        }
        if (PrefsHelper.setcashtype(getApplication()).equals("mcash")) {
            cashimg.setImageResource(R.mipmap.mcash);
            cashtxt.setText("MCASH");
        }
        if (PrefsHelper.setcashtype(getApplication()).equals("ambank")) {
            cashimg.setImageResource(R.mipmap.list_credit_card);
            cashtxt.setText("amBank");
        }
    }

    public void initview() {
        driverimg = (SmartImageView) findViewById(R.id.driverimg);
        startadresstxt = (TextView) findViewById(R.id.startadresstxt);
        endadresstxt = (TextView) findViewById(R.id.endadresstxt);
        faretxt = (TextView) findViewById(R.id.faretxt);
        savebtn = (RelativeLayout) findViewById(R.id.savebtn);
        savebtn.setOnClickListener(this);
        aQuery = new AQuery(this);
    }

    public void savearrivedapi() {
        String url = "https://www.mmas.biz///api_booking/member_safe?order_id=" + PrefsHelper.setclientorderid(getApplication());
        aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                System.out.println("saverrivedapi obj  ===== : " + object);
                try {
                    if (object.getString("sys_code").equals("200")) {
                        Intent intent = new Intent();
                        intent.setClass(ClientSafelyActivity.this, RatdriverActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.savebtn:
                savearrivedapi();
                break;
        }
    }
}
