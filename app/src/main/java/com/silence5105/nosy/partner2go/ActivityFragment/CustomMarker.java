package com.silence5105.nosy.partner2go.ActivityFragment;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.silence5105.nosy.partner2go.R;

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
