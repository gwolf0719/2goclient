package com.nnosy.taxigoclient.ActivityFragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.nnosy.taxigoclient.MainActivity;
import com.nnosy.taxigoclient.PrefsHelper;
import com.nnosy.taxigoclient.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Handler;

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
        RelativeLayout forgetpw = (RelativeLayout) view.findViewById(R.id.forgetpwbtn);
        forgetpw.setOnClickListener(this);
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
        String url = "https://www.mmas.biz///api_member/login?member_id=" + plusnumbertxt.getText().toString() + mobilenumberedittxt.getText().toString()
                + "&password=" + passwordedittxt.getText().toString();
        PrefsHelper.getpassword(getActivity(),passwordedittxt.getText().toString());
        System.out.println("Loginactivity ===== : " + url);
        aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                System.out.println("== phoneloginactivity ===== : " + object);
                try {
                    if (object.getString("sys_code").equals("200")) {
                        PrefsHelper.getofficaltype(getActivity(), object.getString("official"));
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
    String phonenumberstring;
    String infostring;
    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.forgetpwbtn:
                LayoutInflater factory = LayoutInflater.from(getActivity());
                final View DialogView = factory.inflate(R.layout.forgetpweditdialog, null);
               AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                        .setTitle("")
                        .setMessage("please enter phone number")
                       .setView(DialogView)
//                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                            }
//                        })
                        .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText phonenumber = (EditText) DialogView.findViewById(R.id.phonenumberedittext);
                                phonenumberstring = phonenumber.getText().toString();
                                String url = "https://www.mmas.biz/api_member/forget_pw?member_id="+phonenumberstring;
                                aQuery.ajax(url,null,JSONObject.class,new AjaxCallback<JSONObject>(){
                                    @Override
                                    public void callback(String url, JSONObject object, AjaxStatus status) {
                                        super.callback(url, object, status);
                                        System.out.println("obj === : " + object);
                                        try {
                                            infostring = object.getString("sys_msg");
                                            Message message =new Message();
                                            message.what = 0;
                                            handler.sendMessage(message);

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        }).create();
               alertDialog.show();
                break;

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
    android.os.Handler handler = new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    Toast.makeText(getActivity(),infostring,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
