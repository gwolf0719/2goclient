package com.silence5105.nosy.partner2go.ActivityFragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.silence5105.nosy.partner2go.PrefsHelper;
import com.silence5105.nosy.partner2go.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nosy on 2017/4/5.
 */

public class ChangePWFragment extends Fragment implements View.OnClickListener {
    EditText oldpwedittxt, newpwedittxt, cpwedittxt;
    RelativeLayout savebtn;
    AQuery aQuery;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.changepwlayout, container, false);
        oldpwedittxt = (EditText) view.findViewById(R.id.opweditText);
        newpwedittxt = (EditText) view.findViewById(R.id.newpwedittext);
        cpwedittxt = (EditText) view.findViewById(R.id.cpwedittext);
        savebtn = (RelativeLayout) view.findViewById(R.id.savebtn);
        savebtn.setOnClickListener(this);
        aQuery = new AQuery(getActivity());
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.savebtn:
                if (oldpwedittxt.getText().toString() != null) {
                    System.out.println("click ==== yes");
                    if (newpwedittxt.getText().toString().equals(cpwedittxt.getText().toString())) {
                        String url = "https://my.here2go.asia///api_member/passwd?member_id=" + PrefsHelper.setphonenumber(getActivity()) + "&old_password=" + oldpwedittxt.getText().toString() + "&new_password=" + newpwedittxt.getText().toString();
                        aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                            @Override
                            public void callback(String url, JSONObject object, AjaxStatus status) {
                                super.callback(url, object, status);
                                System.out.println("changpw ====== " + object);
                                try {
                                    if (object.getString("sys_code").equals("200")) {
                                        Intent intent = new Intent();
                                        intent.setClass(getActivity(), PWCangeOkActivity.class);
                                        startActivity(intent);
                                        getActivity().finish();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
//                                if (object)
                            }
                        });
                    } else {
                        Toast.makeText(getActivity(), "password error.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "please input old password.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
