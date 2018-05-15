package com.nnosy.partner2go;

import java.util.List;

/**
 * Created by Nosy on 2017/1/24.
 */

public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}
