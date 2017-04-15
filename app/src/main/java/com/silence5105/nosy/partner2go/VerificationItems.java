package com.silence5105.nosy.partner2go;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nosy on 2017/3/31.
 */

public class VerificationItems {
    public String update_datetime;
    public String official_info;
    public String official_id;

    public VerificationItems(JSONObject jsonObject) {
        //object.getJSONArray("data_list").getJSONObject(PrefsHelper.setlistselect(getApplication())).getString("official_subject").toString()
        try {
            this.update_datetime = jsonObject.getString("update_datetime").toString();
            this.official_info = jsonObject.getString("official_subject").toString();
            this.official_id = jsonObject.getString("official_id").toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
