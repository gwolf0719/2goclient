package com.silence5105.nosy.partner2go.ActivityFragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.silence5105.nosy.partner2go.R;

/**
 * Created by Nosy on 2017/4/5.
 */

public class Last5minFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.last5mlayout,container,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), NewBookingActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
