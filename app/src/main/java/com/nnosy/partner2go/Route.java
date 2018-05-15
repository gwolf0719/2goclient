package com.nnosy.partner2go;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Nosy on 2017/1/24.
 */

public class Route {
    public Distance distance;
    public Duration duration;
    public String endAddress;
    public LatLng endLocation;
    public String startAddress;
    public LatLng startLocation;

    public List<LatLng> points;

}
