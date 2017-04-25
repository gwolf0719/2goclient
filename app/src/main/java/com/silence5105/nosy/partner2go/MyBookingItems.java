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
    String cost;
    String car;
    String official_subject;
    String official_info;
    String partner_name;
    String partner_id;
    String partner_ranks;
    String update_datetime;
    String texi_number;
    String order_id;


    public MyBookingItems(JSONObject obj) {
        try {
            this.start_address = obj.getString("start_address").toString();
            this.end_address = obj.getString("end_address").toString();
            this.create_datetime = obj.getString("create_datetime").toString();
            this.payment = obj.getString("payment").toString();
            this.order_status = obj.getString("order_status").toString();
            this.cost = obj.getString("cost").toString();
            this.car = obj.getString("class").toString();
            this.official_subject = obj.getString("official_subject").toString();
            this.official_info = obj.getString("official_info").toString();
            this.partner_name = obj.getString("partner_name").toString();
            this.partner_id = obj.getString("partner_id").toString();
            this.update_datetime = obj.getString("update_datetime").toString();
            this.partner_ranks = obj.getString("partner_ranks").toString();
            this.texi_number = obj.getString("texi_number").toString();
            this.order_id = obj.getString("order_id").toString();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
