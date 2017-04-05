package com.silence5105.nosy.partner2go;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nosy on 2017/3/4.
 */

public class BookingtypeItem {
    public String type;


    public BookingtypeItem(String jsonObject) {

            this.type = jsonObject.toString();


    }
    public JSONObject toJsonObject(){
        JSONObject json = new JSONObject();

        try {
            json.put("payments", type);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }
}
