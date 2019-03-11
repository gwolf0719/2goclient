package com.nnosy.taxigoclient.ActivityFragment;

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
import com.nnosy.taxigoclient.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nosy on 2017/2/10.
 */

public class ForgetPassWordActivity extends Activity implements View.OnClickListener {
    TextView backbtn;
    EditText mailedittxt, phoneedittxt;
    AQuery aQuery;
    Button nextbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpasswordlayout);
        aQuery = new AQuery(this);
        backbtn = (TextView) findViewById(R.id.fpwbackbtn);
        backbtn.setOnClickListener(this);
        mailedittxt = (EditText) findViewById(R.id.fpwmaileditText);
        phoneedittxt = (EditText) findViewById(R.id.fpwphoneedittxt);
        nextbtn = (Button) findViewById(R.id.fpwnextbtn);
        nextbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fpwbackbtn:
                this.finish();
                break;
            case R.id.fpwnextbtn:
                String url = "http://uat.fofo.tw/2go_be/api/member_forget_pw?" + "email=" + mailedittxt.getText().toString() + "&mobile=" + phoneedittxt.getText().toString();
                aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                    @Override
                    public void callback(String url, JSONObject object, AjaxStatus status) {
                        super.callback(url, object, status);
                        System.out.println(" ==== :" + object);
                        try {
                            if (object.getString("sys_code").toString().equals("200")) {
                                Intent i = new Intent();
//                                Intent i = new Intent();
//                                intent.putExtra("name", nameedittxt.getText().toString());
                                i.putExtra("mail", mailedittxt.getText().toString());
                                i.putExtra("phone", phoneedittxt.getText().toString());
//                                intent.putExtra("password", pwedittxt.getText().toString());
                                i.setClass(ForgetPassWordActivity.this, ForgetPWOTPActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(ForgetPassWordActivity.this, "資料有誤", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;

        }
    }
}
