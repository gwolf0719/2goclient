package com.nnosy.taxigoclient.ActivityFragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
 * Created by Nosy on 2017/2/7.
 */

public class OTPActivity extends Activity implements View.OnClickListener {
    TextView nametxt, phonetxt, mailtxt, pwtxt, backbtn;
    EditText otpedittxt;
    Button donebtn;
    AQuery aQuery;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.optlayout);
        bundle = getIntent().getExtras();
        aQuery = new AQuery(this);
//        nametxt = (TextView) findViewById(R.id.nametxt);
//        nametxt.setText(bundle.getString("name"));
//        phonetxt = (TextView) findViewById(R.id.phonetxt);
//        phonetxt.setText(bundle.getString("phone"));
//        mailtxt = (TextView) findViewById(R.id.mailtxt);
//        mailtxt.setText(bundle.getString("mail"));
//        pwtxt = (TextView) findViewById(R.id.pwtxt);
//        pwtxt.setText(bundle.getString("password"));
        otpedittxt = (EditText) findViewById(R.id.otpeditText);

        donebtn = (Button) findViewById(R.id.donbtn);
        donebtn.setOnClickListener(this);
        backbtn = (TextView) findViewById(R.id.otpbackbtn);
        backbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.donbtn:
                String url = "http://uat.fofo.tw/2go_be/api/member_otp?" + "email" + "=" + bundle.getString("mail") + "&" + "otp=" + otpedittxt.getText().toString();

                aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                    @Override
                    public void callback(String url, JSONObject object, AjaxStatus status) {
                        super.callback(url, object, status);
//                            Log.e(Tag, object.toString());

                        System.out.println(" ---- lighteditfragment ---- object" + object);
                        System.out.print(" ---- lighteditfragment ----- status" + status);
                        try {
//                            JSONObject jsonObject = new JSONObject(object.getJSONObject("object").getString("sys_code").toString());
                            String test = object.getString("sys_code");
                            if (test.equals("200")) {
                                PrefsHelper.getmail(getApplication(), bundle.getString("mail").toString());
                                Intent intent = new Intent();
                                intent.setClass(OTPActivity.this, MainActivity.class);
                                startActivity(intent);
                                OTPActivity.this.finish();
                            } else {
                                new AlertDialog.Builder(OTPActivity.this)
                                        .setTitle("")
                                        .setMessage("OTP 錯誤")

                                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

//                                                finish();

                                            }
                                        }).show();
                            }

                            System.out.println("=====:" + test);

                        } catch (JSONException e) {
                            e.printStackTrace();
//                            if
                        }
                    }
                });
                break;
            case R.id.otpbackbtn:
                this.finish();
                break;

        }
    }
}
