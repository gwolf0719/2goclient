package com.silence5105.nosy.partner2go.ActivityFragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.silence5105.nosy.partner2go.R;

import java.util.ArrayList;
import java.util.List;

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
