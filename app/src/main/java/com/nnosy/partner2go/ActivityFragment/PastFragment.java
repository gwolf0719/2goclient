package com.nnosy.partner2go.ActivityFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.nnosy.partner2go.PrefsHelper;
import com.nnosy.partner2go.PastAdapter;
import com.nnosy.partner2go.PastItem;
import com.nnosy.partner2go.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Nosy on 2017/3/5.
 */

public class PastFragment extends Fragment {
    AQuery aQuery;
    RecyclerView rc;
    ArrayList<PastItem> arrayList = new ArrayList<PastItem>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pastfragmentlayout, container, false);
        aQuery = new AQuery(getActivity());
        rc = (RecyclerView) view.findViewById(R.id.rc);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rc.setLayoutManager(linearLayoutManager);
        PastAdapter pastAdapter = new PastAdapter(getActivity(), arrayList);
        loadpastdata();
        return view;
    }

    public void loadpastdata() {
//        String url = "http://uat.fofo.tw/2go_be/api/member_order_list?email=james@fofo.tw";
        String url = "http://uat.fofo.tw/2go_be/api/member_order_list?email=" + PrefsHelper.setmail(getActivity());
        aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override

            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                System.out.println(" ==== object ===== " + object);
                try {
                    if (object.getString("sys_code").equals("200")) {
                        for (int i = 0; i <= object.getJSONObject("orders").getJSONArray("old").length(); i++) {
                            System.out.println(" ==== pastfragment ==== :" + object.getJSONArray("orders").getJSONObject(i).getString("update_datetime").toString());
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
