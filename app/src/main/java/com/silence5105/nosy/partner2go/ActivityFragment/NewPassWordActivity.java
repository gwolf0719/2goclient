package com.silence5105.nosy.partner2go.ActivityFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.silence5105.nosy.partner2go.MainActivity;
import com.silence5105.nosy.partner2go.PrefsHelper;
import com.silence5105.nosy.partner2go.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nosy on 2017/2/10.
 */

public class NewPassWordActivity extends Activity implements View.OnClickListener {
    EditText pwedittxt, apwedittxy;
    TextView backbtn;
    Button donebtn;
    AQuery aQuery;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newpwlayout);
        aQuery = new AQuery(this);
        pwedittxt = (EditText) findViewById(R.id.newpwedittxt);
        backbtn = (TextView) findViewById(R.id.newpwbackbtn);
        backbtn.setOnClickListener(this);
        apwedittxy = (EditText) findViewById(R.id.newapwedittxt);
        donebtn = (Button) findViewById(R.id.nwepwdonebtn);
        donebtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.newpwbackbtn:
                this.finish();
                break;
            case R.id.nwepwdonebtn:

                if (pwedittxt.getText().toString().equals(apwedittxy.getText().toString())) {
                    String url = "http://uat.fofo.tw/2go_be/api/member_forget_pw_reset?" + "email=" + PrefsHelper.setmail(getApplication()).toString() + "&password=" + pwedittxt.getText().toString();
                    aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                        @Override
                        public void callback(String url, JSONObject object, AjaxStatus status) {
                            super.callback(url, object, status);
                            System.out.println(" ==== : " + object);
                            try {
                                if (object.getString("sys_code").toString().equals("200")) {
                                    Intent i = new Intent();
                                    i.setClass(NewPassWordActivity.this, MainActivity.class);
                                    startActivity(i);
                                } else {
                                    Toast.makeText(NewPassWordActivity.this, "密碼輸入有誤", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                    });
                }
                break;
        }
    }
}
