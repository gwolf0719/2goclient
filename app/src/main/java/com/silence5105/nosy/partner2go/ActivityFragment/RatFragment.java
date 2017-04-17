package com.silence5105.nosy.partner2go.ActivityFragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.silence5105.nosy.partner2go.MainActivity;
import com.silence5105.nosy.partner2go.PrefsHelper;
import com.silence5105.nosy.partner2go.R;

import org.json.JSONObject;

/**
 * Created by Nosy on 2017/3/2.
 */

public class RatFragment extends Fragment implements View.OnClickListener {
    TextView ratebtn, faretxt;
    RatingBar ratingBar;
    float rate;
    AQuery aQuery;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ratfragmentlayout, container, false);
        aQuery = new AQuery(getActivity());
        faretxt = (TextView) view.findViewById(R.id.faretxt);
        faretxt.setText(PrefsHelper.setmenucost(getActivity()));
        ratebtn = (TextView) view.findViewById(R.id.ratebtn);
        ratebtn.setOnClickListener(this);
        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rate = v;
            }
        });
        return view;
    }

    public void pushrate() {
        String url = "http://2go.ladesign.tw///api_partner/score_for_partner?order_id=" + PrefsHelper.setclientorderid(getActivity()) + "&rank=" + rate + "&notice= ";
        System.out.println("ratfragment orderid ===== " + PrefsHelper.setclientorderid(getActivity()));
        aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                System.out.println("ratingfragment ===== : " + object);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ratebtn:
                pushrate();
                PrefsHelper.gethaved(getActivity(), "0");
//                System.exit(0);
                Toast.makeText(getActivity(), "rated, thank you", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(getActivity(), MainActivity.class);
                startActivity(intent);
                break;
        }


    }

}
