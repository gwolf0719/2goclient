package com.nnosy.partner2go.ActivityFragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.nnosy.partner2go.PrefsHelper;
import com.nnosy.partner2go.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Nosy on 2017/3/5.
 */

public class NewBookingDialogFragment extends DialogFragment {
    AQuery aQuery;
    ArrayList<String> stringArrayList = new ArrayList<>();
    Spinner spinner;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newbookingmenulayout, null);
        aQuery = new AQuery(getActivity());
        String url = "http://uat.fofo.tw/2go_be/api/get_payment?email=" + PrefsHelper.setmail(getActivity());
        aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                System.out.println(" ===== object ===== " + object);
                try {
                    if (object.getString("sys_code").equals("200")) {
                        System.out.println(" ===== object 200 booking ===== " + object);
//
                        for (int i = 0; i <= object.getJSONArray("payments").length();
                             i++) {
                            System.out.println(" ==== for ==== " + object.getJSONArray("payments").get(i).toString());
//                            arrayList.add(new BookingtypeItem(object.getJSONArray("payments").get(i).toString()));
//                            testtype = object.getJSONArray("payments").get(i).toString();
                            stringArrayList.add(object.getJSONArray("payments").get(i).toString());
                        }


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
        ArrayAdapter<String> lunchList = new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                stringArrayList);

//        .setAdapter(ad);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        spinner.setAdapter(lunchList);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "你選的是" + stringArrayList.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }
}
