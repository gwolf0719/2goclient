package com.nnosy.taxigoclient.ActivityFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nnosy.taxigoclient.R;

/**
 * Created by Nosy on 2017/1/25.
 */

public class PickupFravouritesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pickupfravouritesfragment,null);
        return view;
    }
}