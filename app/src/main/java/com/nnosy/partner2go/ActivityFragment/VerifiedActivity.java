package com.nnosy.partner2go.ActivityFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.nnosy.partner2go.PrefsHelper;
import com.nnosy.partner2go.VerificationItems;
import com.nnosy.partner2go.R;
import com.nnosy.partner2go.VerificationAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Nosy on 2017/3/5.
 */

public class VerifiedActivity extends Activity implements View.OnClickListener {
    RecyclerView rv;
    AQuery aQuery;
    ArrayList<VerificationItems> arrayList = new ArrayList<VerificationItems>();
    VerificationAdapter verificationAdapter;
    RelativeLayout backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newverifiedlayout);
        initview();
        loadverifiedlist();
        backbtn = (RelativeLayout) findViewById(R.id.backbtn);
        backbtn.setOnClickListener(this);
        rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication());
        rv.setLayoutManager(linearLayoutManager);
        verificationAdapter = new VerificationAdapter(this, arrayList);
        verificationAdapter.setOnItemClickLitener(new VerificationAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                PrefsHelper.getlistselect(getApplication(), position);
                Intent intent = new Intent();
                intent.setClass(VerifiedActivity.this,TobeVerifiedActivity.class);
                startActivity(intent);
                finish();
            }
        });
//        rv.setAdapter(verificationAdapter);
    }

    public void loadverifiedlist() {
        String url = "https://my.here2go.asia///api_official/pending_review?member_id=" + PrefsHelper.setphonenumber(getApplication());
        aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                System.out.println("verifiedactivity obj ====== : " + object);
                try {
                    if (object.getString("sys_code").equals("200")) {
//                        System.out.println("test obj = ===== : " + object.getJSONArray("data_list").getJSONObject(0).getString("order_status").toString());
                        for (int i = 0; i < object.getJSONArray("data_list").length(); i++) {
                            arrayList.add(new VerificationItems(object.getJSONArray("data_list").getJSONObject(i)));

                            System.out.println("test ====== = + " + object.getJSONArray("data_list").getJSONObject(i).toString());
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    verificationAdapter.notifyDataSetChanged();
                    rv.setAdapter(verificationAdapter);
                }
            }
        });
    }

    private void initview() {
        aQuery = new AQuery(this);

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

