package com.silence5105.nosy.partner2go.ActivityFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.silence5105.nosy.partner2go.PrefsHelper;
import com.silence5105.nosy.partner2go.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nosy on 2017/3/23.
 */

public class OrderHaveDFragement extends Fragment {
    AQuery aQuery;
    ImageView carimg;
    TextView numbertxt, distancetxt, estimatedtimetxt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orderhaveflayout, container, false);
        carimg = (ImageView) view.findViewById(R.id.carimg);
        numbertxt = (TextView) view.findViewById(R.id.numbertxt);
        distancetxt = (TextView) view.findViewById(R.id.distancetxt);
        estimatedtimetxt = (TextView) view.findViewById(R.id.estimatedtimetxt);
        if (PrefsHelper.setcarclass(getActivity()).equals("Budget")) {
            carimg.setImageDrawable(getResources().getDrawable(R.mipmap.car_img_red));

        }
        if (PrefsHelper.setcarclass(getActivity()).equals("Teks1m")) {
            carimg.setImageDrawable(getResources().getDrawable(R.mipmap.car_img_golden));
        }
        if (PrefsHelper.setcarclass(getActivity()).equals("Executive")) {
            carimg.setImageDrawable(getResources().getDrawable(R.mipmap.car_img_blue));
        }
        System.out.println("orderhavedriver ====== : " + PrefsHelper.setcarclass(getActivity()));
        aQuery = new AQuery(getActivity());
        String driverurl = "https://my.here2go.asia/api_booking/get_once?order_id=" + PrefsHelper.setclientorderid(getActivity());
        aQuery.ajax(driverurl, null, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                System.out.println(" ==== object ==== " + object);
                try {
                    numbertxt.setText(object.getJSONObject("order_info").getString("partner_id").toString());
                    estimatedtimetxt.setText(object.getJSONObject("order_info").getString("go_time").toString());
                    distancetxt.setText(object.getJSONObject("order_info").getString("go_distance").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }
}
