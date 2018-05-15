package com.nnosy.partner2go.ActivityFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.nnosy.partner2go.MainActivity;
import com.nnosy.partner2go.PrefsHelper;
import com.nnosy.partner2go.R;

/**
 * Created by Nosy on 2017/4/5.
 */

public class OfficleReservationSActivity extends Activity {
    ImageView img;
    AQuery aQuery;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.officlereservationslayout);
        img = (ImageView) findViewById(R.id.img);
//        switch (PrefsHelper.setordertype(getApplication()).toString()) {
//            case "official":
        if (PrefsHelper.setordertype(getApplication()).equals("official")) {
            img.setImageDrawable(getResources().getDrawable(R.mipmap.successful_blue));
        }
//                break;
//            case "reservation":
        if (PrefsHelper.setordertype(getApplication()).equals("reservation")) {
            img.setImageDrawable(getResources().getDrawable(R.mipmap.successful_red));
        }
//                break;
//        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent();
                intent.setClass(OfficleReservationSActivity.this, MainActivity.class);
                startActivity(intent);
//                    getActivity().finish();
                finish();

            }
        }, 3000);
    }
}
