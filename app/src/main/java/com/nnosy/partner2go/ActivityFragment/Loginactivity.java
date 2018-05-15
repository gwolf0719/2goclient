package com.nnosy.partner2go.ActivityFragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.nnosy.partner2go.MainActivity;
import com.nnosy.partner2go.PrefsHelper;
import com.nnosy.partner2go.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nosy on 2017/1/24.
 */
public class Loginactivity extends AppCompatActivity implements View.OnClickListener {
    TextView loginbtn, newuserbtn, forgetpwbtn;
    EditText pwedittxt, mailedittxt;
    AQuery aQuery;
    CheckBox autocheck;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        aQuery = new AQuery(this);
        autocheck = (CheckBox) findViewById(R.id.autologincheckBox);
        pwedittxt = (EditText) findViewById(R.id.passwordeditText2);
        mailedittxt = (EditText) findViewById(R.id.emaileditText);
        if (PrefsHelper.setmail(getApplication()) != null) {
            mailedittxt.setText(PrefsHelper.setmail(getApplication()));
        }
        loginbtn = (TextView) findViewById(R.id.loginbutton);
        loginbtn.setOnClickListener(this);
        newuserbtn = (TextView) findViewById(R.id.newuserbutton);
        newuserbtn.setOnClickListener(this);
        forgetpwbtn = (TextView) findViewById(R.id.forgetpwbtn);
        forgetpwbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginbutton:
                dialog = ProgressDialog.show(Loginactivity.this, "",
                        "please wait.", true);
                dialog.show();
                PrefsHelper.getmail(getApplication(), mailedittxt.getText().toString());
                String url = "https://my.here2go.asia/2go_be/api/member_login?" + "email" + "=" + mailedittxt.getText() + "&" + "password=" + pwedittxt.getText();
//https://my.here2go.asia/
                aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                    @Override
                    public void callback(String url, JSONObject object, AjaxStatus status) {
                        super.callback(url, object, status);
//                            Log.e(Tag, object.toString());
                        System.out.println("url = : " + url);
                        System.out.println(" ---- lighteditfragment ---- object" + object);
                        System.out.print(" ---- lighteditfragment ----- status" + status);
                        try {
//                            JSONObject jsonObject = new JSONObject(object.getJSONObject("object").getString("sys_code").toString());
                            String test = object.getString("sys_code");
                            if (test.equals("200")) {
//                                autocheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                                    @Override
//                                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                                        if(autocheck.isChecked()){
//                                            PrefsHelper.getautologin(getApplication(),"1");
//                                        }
//                                    }
//                                });
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
//                                            Thread.sleep(1000);

                                        } finally {
                                            dialog.dismiss();
                                        }
                                    }
                                }).start();
                                if (autocheck.isChecked()) {
                                    PrefsHelper.getautologin(getApplication(), "1");
                                }
                                Intent intent = new Intent();
                                intent.setClass(Loginactivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(Loginactivity.this, "密碼錯誤", Toast.LENGTH_SHORT).show();
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
//                                            Thread.sleep(1000);

                                        } finally {
                                            dialog.dismiss();
                                        }
                                    }
                                }).start();
                            }
                            System.out.println("=====:" + test);

                        } catch (JSONException e) {
                            e.printStackTrace();
//                            if
                        }
                    }
                });
//                Intent intent = new Intent();
//                intent.setClass(Loginactivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
                break;
            case R.id.newuserbutton:
                Intent intent1 = new Intent();
                intent1.setClass(Loginactivity.this, CreateUserFragment.class);
                startActivity(intent1);
                break;
            case R.id.forgetpwbtn:
                Intent intent = new Intent();
                intent.setClass(Loginactivity.this, ForgetPassWordActivity.class);
                startActivity(intent);
                break;
        }
    }
}
