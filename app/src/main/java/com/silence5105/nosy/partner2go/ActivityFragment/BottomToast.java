package com.silence5105.nosy.partner2go.ActivityFragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.silence5105.nosy.partner2go.R;

/**
 * Created by Nosy on 2017/4/1.
 */

public class BottomToast extends Fragment implements View.OnClickListener {
    TextView ttext;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomtoastlayout, container, false);
        ttext = (TextView) view.findViewById(R.id.ttxt);
//        if (PrefsHelper.setlast30m(getActivity()) != null) {
//            if (PrefsHelper.setlast30m(getActivity()).equals("1")) {
//                ttext.setText("30min Later");
//            }
//        }
//        if (PrefsHelper.setlast5m(getActivity()) != null) {
//            if (PrefsHelper.setlast5m(getActivity()).equals("1")) {
//                ttext.setText("5min Later");
//            }
//        }
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

    @Override
    public void onClick(View v) {

    }
}
