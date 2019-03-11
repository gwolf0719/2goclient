package com.nnosy.taxigoclient.ActivityFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nnosy.taxigoclient.PrefsHelper;
import com.nnosy.taxigoclient.R;

public class CheckAgreeActivity extends Activity {
    RelativeLayout cantbtn, agreebtn;
    Handler viewhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Toast.makeText(CheckAgreeActivity.this, "need agree", Toast.LENGTH_SHORT);

                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkagresslayout);
        cantbtn = (RelativeLayout) findViewById(R.id.cantbtn);
        cantbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrefsHelper.getcheckagree(getApplication(), "0");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            Thread.sleep(2000);
                            System.exit(0);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        agreebtn = (RelativeLayout) findViewById(R.id.agreebtn);
        agreebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrefsHelper.getcheckagree(getApplication(), "1");
                Intent loginiintent = new Intent();
                loginiintent.setClass(CheckAgreeActivity.this, LoginContainerActivity.class);
                startActivity(loginiintent);
                finish();
            }
        });
    }
}
