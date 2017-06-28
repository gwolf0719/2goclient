package com.silence5105.nosy.partner2go.ActivityFragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.silence5105.nosy.partner2go.PrefsHelper;
import com.silence5105.nosy.partner2go.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nosy on 2017/3/14.
 */

public class newPhoneCreateMemberFragment extends Fragment implements View.OnClickListener {
    EditText nameedittxt, emailedittxt, pwedittxt, apwedittxt;
    AQuery aQuery;
    TextView numbertxt;
    RelativeLayout donebtn;
    private ProgressDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newphonecreatememberlayout,container,false);
        nameedittxt = (EditText) view.findViewById(R.id.nameeditText);
        emailedittxt = (EditText) view.findViewById(R.id.mailedittxt);
        pwedittxt = (EditText) view.findViewById(R.id.pwedittxt);
        numbertxt = (TextView) view.findViewById(R.id.numbertxt);
        numbertxt.setText(PrefsHelper.setphonenumber(getActivity()));
        apwedittxt = (EditText) view.findViewById(R.id.apwedittxt);
        donebtn = (RelativeLayout) view.findViewById(R.id.donebtn);
        donebtn.setOnClickListener(this);
        aQuery = new AQuery(getActivity());
        return view;
    }



    private void initview() {

    }

    public void memberlistapi() {
        String url = "http://2go.ladesign.tw///api_member/edit_member_first?member_id=" + PrefsHelper.setphonenumber(getActivity()) + "&email=" + emailedittxt.getText().toString()
                + "&name=" + nameedittxt.getText().toString() + "&password=" + apwedittxt.getText().toString();
        aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                System.out.println("==== newphonecreatememberactivity ==== : " + object);
                try {
                    if (object.getString("sys_code").equals("200")) {
//                        Intent intent = new Intent();
//                        intent.setClass(newPhoneCreateMemberFragment.this, SignupFragment.class);
//                        startActivity(intent);
                        FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
                        fragmentTransaction.replace(R.id.container, new SignupFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
//                        getActivity().getFragmentManager().beginTransaction().replace(R.id.container,new SignupFragment()).commit();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
//                                            Thread.sleep(1000);

                                } finally {
                                    dialog.dismiss();
                                }
                            }
                        }).start();
                    } else {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
//                                            Thread.sleep(1000);

                                } finally {
                                    dialog.dismiss();
                                }
                            }
                        }).start();
                        Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.donebtn:
                dialog = ProgressDialog.show(getActivity(), "",
                        "please wait.", true);
                dialog.show();
                if (pwedittxt.getText().toString() != null || apwedittxt != null) {
                    if (pwedittxt.getText().toString().equals(apwedittxt.getText().toString())) {
                        memberlistapi();
                    }
                } else {
                    Toast.makeText(getActivity(), "pw error", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
