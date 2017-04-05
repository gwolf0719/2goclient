package com.silence5105.nosy.partner2go;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nosy on 2017/3/23.
 */

public class MyBookingItems {
    String start_address;
    String end_address;
    String create_datetime;
    String payment;
    String order_status;


    public MyBookingItems(JSONObject obj) {
        try {
            this.start_address = obj.getString("start_address").toString();
            this.end_address = obj.getString("end_address").toString();
            this.create_datetime = obj.getString("create_datetime").toString();
            this.payment = obj.getString("payment").toString();
            this.order_status = obj.getString("order_status").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
