package com.nnosy.partner2go.ActivityFragment;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.Timer;
import java.util.TimerTask;

public class CMapView extends MapView {
    public CMapView(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
    }
}
//    Then you should register event handlers in your MapActivity like this:

//public class YourMapActivity extends MapActivity {
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        mv = new EnhancedMapView(this, "<your Maps API key here>");
//
//        mv.setClickable(true);
//        mv.setBuiltInZoomControls(true);
//
//        mv.setOnZoomChangeListener(new EnhancedMapView.OnZoomChangeListener() {
//                                       @Override
//                                       public void onZoomChange(MapView view, int newZoom, int oldZoom) {
//                                           Log.d("test", "zoom changed from " + oldZoom + " to " + newZoom);
//                                       }
//                                   }
//                mv.setOnPanChangeListener(new EnhancedMapView.OnPanChangeListener() {
//                    public void onPanChange(MapView view, GeoPoint newCenter, GeoPoint oldCenter) {
//                        Log.d("test", "center changed from " + oldCenter.getLatitudeE6() + "," + oldCenter.getLongitudeE6() + " to " + newCenter.getLatitudeE6() + "," + newCenter.getLongitudeE6());
//                    }
//                }
//    }
