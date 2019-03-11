package com.nnosy.taxigoclient.ActivityFragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
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
 * Created by Nosy on 2017/1/24.
 */

public class MyprofileActivity extends Activity implements View.OnClickListener {
    EditText nameedittxt, phoneedittxt, newpwedittxt, oldedittxt;
    TextView mailedittxt;
    TextView pwtxt;
    AQuery aQuery;
    Button editpwbtn, donebtn, updatapwbtn;
    LinearLayout editpwlayout;
    CheckBox autologincheckbox;
    Switch checkpass;

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.myprofile, container, false);
//        aQuery = new AQuery(getActivity());
//        String mail = PrefsHelper.setmail(getActivity());
//        String url = "http://uat.fofo.tw/2go_be/api/member_info?" + "email" + "=" + mail;
//        System.out.println(" ==== mp ===== :" + url);
//        autologincheckbox = (CheckBox) view.findViewById(R.id.mpautologincheckbox);
//        aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
//            @Override
//            public void callback(String url, JSONObject object, AjaxStatus status) {
//                super.callback(url, object, status);
//                System.out.println(" ====== :" + object);
//                try {
//                    nameedittxt.setText(object.getJSONObject("data").getString("name").toString());
//                    mailedittxt.setText(object.getJSONObject("data").getString("email").toString());
//                    phoneedittxt.setText(object.getJSONObject("data").getString("mobile").toString());
////                    pwtxt.setText(object.getString("p"));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        nameedittxt = (EditText) view.findViewById(R.id.mpnameeditText);
//        mailedittxt = (EditText) view.findViewById(R.id.mpmaileditText);
//        phoneedittxt = (EditText) view.findViewById(R.id.mpphoneeditText);
//        pwtxt = (TextView) view.findViewById(R.id.mppwText);
//        editpwbtn = (Button) view.findViewById(R.id.editpwbtn);
//        editpwbtn.setOnClickListener(this);
//        editpwlayout = (LinearLayout) view.findViewById(R.id.editpwlayout);
//        editpwlayout.setVisibility(View.GONE);
//        donebtn = (Button) view.findViewById(R.id.mpdonebtn);
//        donebtn.setOnClickListener(this);
//        oldedittxt = (EditText) view.findViewById(R.id.oldpweditText);
//        newpwedittxt = (EditText) view.findViewById(R.id.mpnewpwedittxt);
//        updatapwbtn = (Button) view.findViewById(R.id.updatapwbtn);
//        updatapwbtn.setOnClickListener(this);
//        checkpass = (Switch) view.findViewById(R.id.ipasscheckBox);
//        String checkautologin = PrefsHelper.setautologin(getActivity());
//        if (checkautologin.equals("1")) {
//            autologincheckbox.setChecked(true);
//        }
//
//        return view;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile);
        aQuery = new AQuery(this);
        String mail = PrefsHelper.setmail(getApplication());
        String url = "http://uat.fofo.tw/2go_be/api/member_info?" + "email" + "=" + mail;
        System.out.println(" ==== mp ===== :" + url);
        autologincheckbox = (CheckBox) findViewById(R.id.mpautologincheckbox);
        aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                System.out.println(" ====== :" + object);
                try {
                    nameedittxt.setText(object.getJSONObject("data").getString("name").toString());
                    mailedittxt.setText(object.getJSONObject("data").getString("email").toString());
                    phoneedittxt.setText(object.getJSONObject("data").getString("mobile").toString());
//                    pwtxt.setText(object.getString("p"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        nameedittxt = (EditText) findViewById(R.id.mpnameeditText);
        mailedittxt = (TextView) findViewById(R.id.mpmaileditText);
        phoneedittxt = (EditText) findViewById(R.id.mpphoneeditText);
        pwtxt = (TextView) findViewById(R.id.mppwText);
        editpwbtn = (Button) findViewById(R.id.editpwbtn);
        editpwbtn.setOnClickListener(this);
        editpwlayout = (LinearLayout) findViewById(R.id.editpwlayout);
        editpwlayout.setVisibility(View.GONE);
        donebtn = (Button) findViewById(R.id.mpdonebtn);
        donebtn.setOnClickListener(this);
        oldedittxt = (EditText) findViewById(R.id.oldpweditText);
        newpwedittxt = (EditText) findViewById(R.id.mpnewpwedittxt);
        updatapwbtn = (Button) findViewById(R.id.updatapwbtn);
        updatapwbtn.setOnClickListener(this);
        checkpass = (Switch) findViewById(R.id.ipasscheckBox);
        String checkautologin = PrefsHelper.setautologin(getApplication());
//        if (checkautologin.equals("1")) {
//            autologincheckbox.setChecked(true);
//        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editpwbtn:
                editpwlayout.setVisibility(View.VISIBLE);
                break;
            case R.id.mpdonebtn:
//                if (checkpass.isChecked()) {
                new AlertDialog.Builder(this)
                        .setTitle("")
                        .setMessage("資料是否修改?")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String url = "http://uat.fofo.tw/2go_be/api/member_edit?" + "email=" + mailedittxt.getText().toString() + "&" + "name=" + nameedittxt.getText().toString() + "&" + "mobile=" + phoneedittxt.getText().toString();
                                aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                                    @Override
                                    public void callback(String url, JSONObject object, AjaxStatus status) {
                                        super.callback(url, object, status);
                                        System.out.println(" ===== done ==== :" + url);
                                        System.out.println(" ===== done ==== :" + object);
                                        try {
                                            if (object.getString("sys_code").toString().equals("200")) {
                                                Toast.makeText(MyprofileActivity.this, " 修改成功", Toast.LENGTH_SHORT).show();
                                                finish();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });
                                if (!autologincheckbox.isChecked()) {
                                    PrefsHelper.getautologin(getApplication(), "0");
                                }
                                if (autologincheckbox.isChecked()) {
                                    PrefsHelper.getautologin(getApplication(), "1");
                                }

                            }
                        }).show();
//                }
//                if (!checkpass.isChecked()) {
//                    Toast.makeText(getApplication(), "請勾選同意條款", Toast.LENGTH_SHORT).show();
//                }


//                getFragmentManager().beginTransaction().remove(this).commit();
                break;
            case R.id.updatapwbtn:
                String url = "http://uat.fofo.tw/2go_be/api/member_passwd?" + "email=" + mailedittxt.getText().toString() + "&" + "old=" + oldedittxt.getText().toString() + "&" + "new=" + newpwedittxt.getText().toString();
                aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                    @Override
                    public void callback(String url, JSONObject object, AjaxStatus status) {
                        super.callback(url, object, status);
                        System.out.println(" ===== done ==== :" + url);
                        System.out.println(" ===== done ==== :" + object);
                        try {
                            if (object.getString("sys_code").toString().equals("200")) {
                                Toast.makeText(getApplication(), " 密碼修改成功", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(getApplication(), "密碼輸入錯誤", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
                break;
        }

    }
}
