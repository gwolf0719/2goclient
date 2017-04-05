package com.silence5105.nosy.partner2go.ActivityFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silence5105.nosy.partner2go.R;

/**
 * Created by Nosy on 2017/1/24.
 */

public class DropFravourites extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dropfravourites,container,false);
        return view;
    }
}
