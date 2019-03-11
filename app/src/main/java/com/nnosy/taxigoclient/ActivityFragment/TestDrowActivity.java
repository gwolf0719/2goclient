package com.nnosy.taxigoclient.ActivityFragment;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.nnosy.taxigoclient.R;

/**
 * Created by Nosy on 2017/4/20.
 */

public class TestDrowActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.testview);
    }

}
