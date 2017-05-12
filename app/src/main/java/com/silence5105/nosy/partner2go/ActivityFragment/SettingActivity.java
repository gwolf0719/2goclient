package com.silence5105.nosy.partner2go.ActivityFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silence5105.nosy.partner2go.PrefsHelper;
import com.silence5105.nosy.partner2go.R;

/**
 * Created by Nosy on 2017/4/5.
 */

public class SettingActivity extends Activity implements View.OnClickListener {
    RelativeLayout changepw, businessbtn, container;
    TextView nametxt, mobilenumber, logout;
    RelativeLayout backbtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingactivitylayout);
        container = (RelativeLayout) findViewById(R.id.container);
        changepw = (RelativeLayout) findViewById(R.id.changpassword);
        changepw.setOnClickListener(this);
        businessbtn = (RelativeLayout) findViewById(R.id.businessbtn);
        businessbtn.setOnClickListener(this);
        nametxt = (TextView) findViewById(R.id.nametxt);
        nametxt.setText(PrefsHelper.setusername(getApplication()));
        mobilenumber = (TextView) findViewById(R.id.phonenumbertxt);
        mobilenumber.setText(PrefsHelper.setphonenumber(getApplication()));
        logout = (TextView) findViewById(R.id.logoutbtn);
        logout.setOnClickListener(this);
        backbtn = (RelativeLayout) findViewById(R.id.backbtn);
        backbtn.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logoutbtn:
                new AlertDialog.Builder(this)
                        .setTitle("")
                        .setMessage("do you want logout?")
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PrefsHelper.getautologin(getApplication(), "0");
//                                MainActivity mainActivity = new MainActivity();
//                                System.runFinalizersOnExit(true);
//                                System.exit(0);
                                Intent intent = new Intent();
                                intent.setClass(SettingActivity.this, LoginContainerActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        }).show();

                break;
            case R.id.changpassword:
                PrefsHelper.getlistselect(getApplication(), 1);
                container.setVisibility(View.VISIBLE);
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
////                fragmentTransaction.remove(new MainActivity.BookSelectTimeFragment1());
//                fragmentTransaction.replace(R.id.container, new ChangePWFragment());
//                fragmentTransaction.commit();
                getFragmentManager().beginTransaction().replace(R.id.container, new ChangePWFragment()).commit();
                break;
            case R.id.businessbtn:
                if (PrefsHelper.setofficaltype(getApplication()) != null) {
                    if (PrefsHelper.setofficaltype(getApplication()).equals("true")) {
                        Intent intent = new Intent();
                        intent.setClass(SettingActivity.this, BusinessInfoActivity.class);
                        startActivity(intent);
                    }
                }
                if (PrefsHelper.setofficaltype(getApplication()) != null) {
                    if (PrefsHelper.setofficaltype(getApplication()).equals("false")) {
                        new android.support.v7.app.AlertDialog.Builder(this)
                                .setTitle("")
                                .setMessage("This service is for Official Out of Office Business Travel/Meeting Services. If interested, kindly inform your employer to register or kindly email your request to below email and we will contact your employer for further details")
                                //
                                .setNegativeButton("Confirm", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .setPositiveButton("baharidris@2go.com.my", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
//                                    Intent intent = new Intent((Intent.ACTION_VIEW);
//                                    intent = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");

//                                    startActivity(intent);
                                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "baharidris@2go.com.my", null));
                                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                                        getApplication().startActivity(Intent.createChooser(emailIntent, null));
                                    }
                                }).show();
                    }
                }
                break;
            case R.id.backbtn:
                if (PrefsHelper.setlistselect(getApplication()) == 0) {
                    this.finish();
                }
                if (PrefsHelper.setlistselect(getApplication()) == 1) {
                    container.setVisibility(View.GONE);
                    PrefsHelper.getlistselect(getApplication(), 0);
                }
                break;
        }
    }
}
