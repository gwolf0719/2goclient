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
import android.widget.ImageView;
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

public class newOTPFragment extends Fragment implements View.OnClickListener {
    EditText otpedittxt;
    TextView donebtn, numbertxt;
    AQuery aQuery;
    private ProgressDialog dialog;
    ImageView backbtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newotplayout, container, false);
        otpedittxt = (EditText) view.findViewById(R.id.otpedittxt);
        donebtn = (TextView) view.findViewById(R.id.donetxt);
        donebtn.setOnClickListener(this);
        numbertxt = (TextView) view.findViewById(R.id.numbertxt);
        numbertxt.setText(PrefsHelper.setphonenumber(getActivity()));
        backbtn = (ImageView) view.findViewById(R.id.backbtn);
        backbtn.setOnClickListener(this);
        aQuery = new AQuery(getActivity());
        return view;
    }

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.newotplayout);
//        initview();
//    }

    private void initview() {

    }

    public void otpurl() {
        String url = "https://my.here2go.asia///api_member/chk_otp?member_id=" + PrefsHelper.setphonenumber(getActivity()) + "&otp=" + otpedittxt.getText().toString();
        aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                System.out.println(" == newotpactivity ===== : " + object);
                try {
                    if (object.getString("sys_code").equals("200")) {
//                        Intent intent = new Intent();
//                        intent.setClass(newOTPFragment.this, newPhoneCreateMemberFragment.class);
//                        startActivity(intent);
                        FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
                        fragmentTransaction.replace(R.id.container, new newPhoneCreateMemberFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
//                        getActivity().getFragmentManager().beginTransaction().replace(R.id.container,new newPhoneCreateMemberFragment()).commit();
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
                        Toast.makeText(getActivity(), "otp err", Toast.LENGTH_SHORT).show();
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
            case R.id.donetxt:
                dialog = ProgressDialog.show(getActivity(), "",
                        "please wait.", true);
                dialog.show();
                otpurl();

                break;
            case R.id.backbtn:
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
                fragmentTransaction.replace(R.id.container, new PhoneCreateFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
//                getActivity().getFragmentManager().beginTransaction().replace(R.id.container,new PhoneCreateFragment()).commit();
                break;
        }
    }
}
