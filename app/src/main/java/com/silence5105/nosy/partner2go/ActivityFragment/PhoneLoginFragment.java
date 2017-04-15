package com.silence5105.nosy.partner2go.ActivityFragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.silence5105.nosy.partner2go.MainActivity;
import com.silence5105.nosy.partner2go.PrefsHelper;
import com.silence5105.nosy.partner2go.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nosy on 2017/3/13.
 */

public class PhoneLoginFragment extends Fragment implements View.OnClickListener {
    EditText mobilenumberedittxt, passwordedittxt;
    RelativeLayout loginbtn;
    AQuery aQuery;
    TextView plusnumbertxt;
    //    Button loginbtn;
    private ProgressDialog dialog;
    ImageView backbtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.phoneloginlayout, container, false);
        mobilenumberedittxt = (EditText) view.findViewById(R.id.phoneedittxt);
        passwordedittxt = (EditText) view.findViewById(R.id.pwedittxt);
        plusnumbertxt = (TextView) view.findViewById(R.id.plusnumber);
        loginbtn = (RelativeLayout) view.findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(this);
        backbtn = (ImageView) view.findViewById(R.id.backbtn);
        backbtn.setOnClickListener(this);
        aQuery = new AQuery(getActivity());
        return view;
    }

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.phoneloginlayout);
//        initview();
//
//    }

    private void initview() {

    }

    public void loginapi() {
        String url = "https://my.here2go.asia/api_member/login?member_id=" + plusnumbertxt.getText().toString() + mobilenumberedittxt.getText().toString()
                + "&password=" + passwordedittxt.getText().toString();
        System.out.println("Loginactivity ===== : " + url);
        aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                System.out.println("== phoneloginactivity ===== : " + object);
                try {
                    if (object.getString("sys_code").equals("200")) {
                        PrefsHelper.getofficaltype(getActivity(), object.getString("official").toString());
                        PrefsHelper.getcarclass(getActivity(),object.getString("partner_class").toString());
                        PrefsHelper.getusername(getActivity(),object.getString("member_name").toString());
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                        PrefsHelper.getautologin(getActivity(),"1");
                        PrefsHelper.getphonenumber(getActivity(), plusnumbertxt.getText().toString() + mobilenumberedittxt.getText().toString());
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
                                    Thread.sleep(500);

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } finally {
                                    dialog.dismiss();
                                }
                            }
                        }).start();
                        Toast.makeText(getActivity(), "pw err", Toast.LENGTH_SHORT).show();
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

            case R.id.backbtn:
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
                fragmentTransaction.replace(R.id.container, new LoginSelectFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
//                getActivity().getFragmentManager().beginTransaction().replace(R.id.container,new LoginSelectFragment()).commit();
                break;
            case R.id.loginbtn:
                dialog = ProgressDialog.show(getActivity(), "",
                        "please wait.", true);
                dialog.show();
                loginapi();
                break;
        }
    }
}
