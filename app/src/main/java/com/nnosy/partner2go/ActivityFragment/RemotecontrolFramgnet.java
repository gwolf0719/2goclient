package com.nnosy.partner2go.ActivityFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nnosy.partner2go.R;

/**
 * Created by Nosy on 2017/1/24.
 */

public class RemotecontrolFramgnet extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.remotecontro,container,false);
        return view;
    }
}
