package com.nnosy.taxigoclient.ActivityFragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.nnosy.taxigoclient.PrefsHelper;
import com.nnosy.taxigoclient.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nosy on 2017/3/14.
 */

public class PhoneCreateFragment extends Fragment implements View.OnClickListener {
    EditText phonenumberedittxt;
    AQuery aQuery;
    RelativeLayout nextbtn;
    TextView plusnumbertxt;
    private ProgressDialog dialog;
    ImageView backbtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.phonecreateactivity, container, false);
        aQuery = new AQuery(getActivity());
        phonenumberedittxt = (EditText) view.findViewById(R.id.phonenumbertxt);
        nextbtn = (RelativeLayout) view.findViewById(R.id.nexttxt);
        nextbtn.setOnClickListener(this);
        backbtn = (ImageView) view.findViewById(R.id.backbtn);
        backbtn.setOnClickListener(this);
        plusnumbertxt = (TextView) view.findViewById(R.id.plusnumbertxt);
        return view;
    }

    Handler viewhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Toast.makeText(getActivity(), "need input phonenumber", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    public void apimember() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "https://www.mmas.biz///api_member/register?member_id="
                        + plusnumbertxt.getText().toString()
                        + phonenumberedittxt.getText().toString();
                PrefsHelper.getotpphonenumber(getActivity(), plusnumbertxt.getText().toString() + phonenumberedittxt.getText().toString());
                aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                    @Override
                    public void callback(String url, JSONObject object, AjaxStatus status) {
                        super.callback(url, object, status);
                        System.out.println("= PHONECREATEACTIVITY RESULT ==== :" + object);
                        try {
                            if (object.getString("sys_code").equals("200")) {
                                PrefsHelper.getphonenumber(getActivity(), plusnumbertxt.getText().toString() + phonenumberedittxt.getText().toString());
//                        Intent intent = new Intent();
//                        intent.setClass(getActivity(), newOTPFragment.class);
//                        startActivity(intent);
                                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                                fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
                                fragmentTransaction.replace(R.id.container, new newOTPFragment());
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
//                        getActivity().getFragmentManager().beginTransaction().replace(R.id.container,new newOTPFragment()).commit();
                                PrefsHelper.getphonenumber(getActivity(), plusnumbertxt.getText().toString() + phonenumberedittxt.getText().toString());
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
                            }
                            if (object.getString("sys_code").equals("500")) {
                                Toast.makeText(getActivity(), "Account repeated.", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                            if (object.getString("sys_code").equals("501")) {
                                PrefsHelper.getphonenumber(getActivity(), phonenumberedittxt.getText().toString());
//                        Intent intent = new Intent();
//                        intent.setClass(getActivity(), newOTPFragment.class);
//                        startActivity(intent);
                                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                                fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
                                fragmentTransaction.replace(R.id.container, new newOTPFragment());
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
//                        getActivity().getFragmentManager().beginTransaction().replace(R.id.container,new newOTPFragment()).commit();
                                PrefsHelper.getphonenumber(getActivity(), plusnumbertxt.getText().toString() + phonenumberedittxt.getText().toString());
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
                            }
                            if (object.getString("sys_code").equals("502")) {
                                PrefsHelper.getphonenumber(getActivity(), phonenumberedittxt.getText().toString());
//                        Intent intent = new Intent();
//                        intent.setClass(getActivity(), newOTPFragment.class);
//                        startActivity(intent);
                                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                                fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
                                fragmentTransaction.replace(R.id.container, new newPhoneCreateMemberFragment());
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
//                        getActivity().getFragmentManager().beginTransaction().replace(R.id.container,new newPhoneCreateMemberFragment()).commit();
                                PrefsHelper.getphonenumber(getActivity(), plusnumbertxt.getText().toString() + phonenumberedittxt.getText().toString());
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
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
        //https://www.mmas.biz///api_member/register?member_id=

    }

    private void initview() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nexttxt:
                if (!phonenumberedittxt.getText().toString().equals("")) {
                    dialog = ProgressDialog.show(getActivity(), "",
                            "please wait.", true);
                    dialog.show();
                    apimember();
                } else {
                    Message message = new Message();
                    message.what = 0;
                    viewhandler.sendMessage(message);
                }
                break;
            case R.id.backbtn:
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
                fragmentTransaction.replace(R.id.container, new NewLoginFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
//                getActivity().getFragmentManager().beginTransaction().replace(R.id.container,new NewLoginFragment());
                break;
        }
    }
}
