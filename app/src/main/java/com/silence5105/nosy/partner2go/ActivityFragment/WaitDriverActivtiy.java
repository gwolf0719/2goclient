package com.silence5105.nosy.partner2go.ActivityFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.silence5105.nosy.partner2go.MainActivity;
import com.silence5105.nosy.partner2go.PrefsHelper;
import com.silence5105.nosy.partner2go.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nosy on 2017/3/14.
 */

public class WaitDriverActivtiy extends Activity implements View.OnClickListener {
    TextView startadresstxt, endadresstxt, faretxt;
    ImageView teks1mimg, budgetimg, executive;
    RelativeLayout cancelbtn;
    AQuery aQuery;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waitdriverlayout);
        aQuery = new AQuery(this);
        startadresstxt = (TextView) findViewById(R.id.startadresstxt);
        startadresstxt.setText(PrefsHelper.setstartadress(getApplication()));
        endadresstxt = (TextView) findViewById(R.id.endadresstxt);
        endadresstxt.setText(PrefsHelper.setendaddress(getApplication()));
        teks1mimg = (ImageView) findViewById(R.id.teks1mimg);
        budgetimg = (ImageView) findViewById(R.id.budgetimg);
        executive = (ImageView) findViewById(R.id.exectuiveimg);
        faretxt = (TextView) findViewById(R.id.faretxt);
        cancelbtn = (RelativeLayout) findViewById(R.id.cancelbtn);
        cancelbtn.setOnClickListener(this);
        faretxt.setText(PrefsHelper.setmenucost(getApplication()));
        if (PrefsHelper.setcarclass(getApplication()).equals("Teks1m")) {
            teks1mimg.setVisibility(View.VISIBLE);
        }
        if (PrefsHelper.setcarclass(getApplication()).equals("Budget")) {
            budgetimg.setVisibility(View.VISIBLE);
        }
        if (PrefsHelper.setcarclass(getApplication()).equals("Executive")) {
            executive.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancelbtn:
                new AlertDialog.Builder(this)
                        .setTitle("")
                        .setMessage("order cancel?")
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

//                                System.exict(0);
//                                dialog = ProgressDialog.show(MapMainActivity.this, "",
//                                        "please wait.", true);
//                                dialog.show();
                                System.out.println("Cancel btn  == == = : " + PrefsHelper.setclientorderid(getApplication()));
                                String cancelurl = "https://my.here2go.asia/api_booking/cancel_order?order_id=" + PrefsHelper.setclientorderid(getApplication());
                                aQuery.ajax(cancelurl, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                                    @Override
                                    public void callback(String url, JSONObject object, AjaxStatus status) {
                                        super.callback(url, object, status);
                                        System.out.println("cancel btn ======== :" + object);

                                        try {
                                            if (object.getString("sys_code").equals("200")) {
                                                Intent intent = new Intent(WaitDriverActivtiy.this, MainActivity.class);
                                                startActivity(intent);
                                                finish();
//                                                Toast.makeText(getApplication(), "canceled", Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        }).show();
                break;
        }
    }
}
