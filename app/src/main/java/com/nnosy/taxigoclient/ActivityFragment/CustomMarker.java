package com.nnosy.taxigoclient.ActivityFragment;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.nnosy.taxigoclient.R;

/**
 * Created by Nosy on 2017/3/30.
 */

public class CustomMarker implements GoogleMap.InfoWindowAdapter {
    Activity context;

    public CustomMarker(Activity context) {
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = context.getLayoutInflater().inflate(R.layout.custommarkerlayout,null);
        TextView startadresstxt = (TextView) view.findViewById(R.id.startadresstxt);
        startadresstxt.setText(marker.getTitle());
        return view;
    }
}
