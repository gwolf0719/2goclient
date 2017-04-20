package com.silence5105.nosy.partner2go.ActivityFragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.silence5105.nosy.partner2go.R;

/**
 * Created by Nosy on 2017/4/20.
 */

public class BookingDetailActivity extends Activity {
    TextView titletext;
    AQuery aQuery;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.bookinglayot);
        aQuery = new AQuery(this);

        titletext = (TextView) findViewById(R.id.titletext);

    }
}
