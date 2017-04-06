package com.silence5105.nosy.partner2go.ActivityFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.silence5105.nosy.partner2go.MainActivity;
import com.silence5105.nosy.partner2go.R;

/**
 * Created by Nosy on 2017/4/5.
 */

public class PWCangeOkActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pwchangoklayout);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent();
                intent.setClass(PWCangeOkActivity.this, MainActivity.class);
                startActivity(intent);
//                    getActivity().finish();
                finish();

            }
        }, 2000);
    }
}
