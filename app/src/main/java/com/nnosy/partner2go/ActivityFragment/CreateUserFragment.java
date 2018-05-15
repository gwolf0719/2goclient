package com.nnosy.partner2go.ActivityFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.nnosy.partner2go.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;


/**
 * Created by Nosy on 2017/2/6.
 */

public class CreateUserFragment extends Activity implements View.OnClickListener {
    EditText nameedittxt, mailedittxt, phoneedittxt, pwedittxt;
    Button creatbtn;
    AQuery aQuery;
    Switch ipasscheckbox;
    TextView backbtn, urltxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creatuserlayout);
        aQuery = new AQuery(this);
        nameedittxt = (EditText) findViewById(R.id.nameeditText);
        phoneedittxt = (EditText) findViewById(R.id.phoneeditText2);
        mailedittxt = (EditText) findViewById(R.id.maileditText3);
        pwedittxt = (EditText) findViewById(R.id.pweditText4);
        creatbtn = (Button) findViewById(R.id.createbtn);
        creatbtn.setOnClickListener(this);
        backbtn = (TextView) findViewById(R.id.cubackbtn);
        backbtn.setOnClickListener(this);
        urltxt = (TextView) findViewById(R.id.urltxt);
//        urltxt.setText();
        ipasscheckbox = (Switch) findViewById(R.id.ipasscheckBox);
//        autologincheckbox = (CheckBox) findViewById(R.id.crautologin);

    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.creatuserlayout,null);
////        aQuery = new AQuery(getActivity());
//
//        nameedittxt = (EditText) v.findViewById(R.id.nameeditText);
//        phoneedittxt = (EditText) v.findViewById(R.id.phoneeditText2);
//        mailedittxt = (EditText) v.findViewById(R.id.maileditText3);
//        pwedittxt = (EditText) v.findViewById(R.id.pweditText4);
//        creatbtn =(Button) v.findViewById(R.id.createbtn);
//        creatbtn.setOnClickListener(this);

    //
//        return v;
//    }
    public static String makePostRequest(String stringUrl, HashMap payload,
                                         Context context) throws IOException {
        URL url = new URL(stringUrl);
        HttpURLConnection uc = (HttpURLConnection) url.openConnection();
        String line;
        StringBuffer jsonString = new StringBuffer();
        jsonString.append(payload);
        byte[] postDataBytes = payload.toString().getBytes("UTF-8");

//        uc.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        uc.setRequestMethod("POST");
        uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        uc.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
//        OutputStream os = uc.getOutputStream();
//        os.write(postDataBytes);
        uc.setDoInput(true);
        uc.setInstanceFollowRedirects(false);
        uc.connect();
//        Set set =payload.entrySet();
//        Iterator iterator = set.iterator();

//        OutputStreamWriter writer = new OutputStreamWriter(uc.getOutputStream(), "UTF-8");
//        writer.write(payload);
        System.out.println("==== stringurl ===== " + stringUrl);
        System.out.println("===== payload =====:" + payload);
//        writer.close();


//        writer.wr


        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        uc.disconnect();
        return jsonString.toString();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.createbtn:
//                new AsyncTask<String, String, String>() {
//                    @Override
//                    protected String doInBackground(String... strings) {
//                        try {
//
//                            HashMap<String,String> hashMap = new HashMap<String, String>();
//                            hashMap.put("name","TEST");
//                            hashMap.put("email","TEST@gmail.com");
//                            hashMap.put("mobile","0290039");
//                            hashMap.put("password","test");
//                            String response = makePostRequest("http://uat.fofo.tw/2go_be/api/member_register", hashMap, getApplicationContext());
//                            System.out.println(" ==== respnese" + response);
//                            return "Success";
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                            return "";
//                        }
//                    }
//
//                }.execute("");
                final String url3 = "http://uat.fofo.tw/2go_be/api/member_register?";
                if (ipasscheckbox.isChecked()) {
                    final String url2 = "http://uat.fofo.tw/2go_be/api/member_register?" + "name" + "=" + nameedittxt.getText().toString()
                            + "&" + "email" + "=" + mailedittxt.getText().toString()
                            + "&" + "mobile" + "=" + phoneedittxt.getText().toString()
                            + "&" + "password" + "=" + pwedittxt.getText().toString();
                    //                    JSONObject jsonObject = new JSONObject(url2);
//                final HashMap<String, String> hashMap = new HashMap<String, String>();
//                hashMap.put("", url2);
                    aQuery.ajax(url2, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                        @Override
                        public void callback(String url, JSONObject object, AjaxStatus status) {
                            super.callback(url, object, status);
//                            Log.e(Tag, object.toString());
                            System.out.println(" ----- tst ----- :" + url2);
                            System.out.println(" ---- lighteditfragment ---- object" + object);
                            System.out.print(" ---- lighteditfragment ----- status" + status);
                            try {
//                            if (object.getString("sys_code").toString() == "200") {
                                if (object.getString("sys_code").toString().equals("200")) {
                                    Intent intent = new Intent();
                                    intent.putExtra("name", nameedittxt.getText().toString());
                                    intent.putExtra("mail", mailedittxt.getText().toString());
                                    intent.putExtra("phone", phoneedittxt.getText().toString());
                                    intent.putExtra("password", pwedittxt.getText().toString());
                                    intent.setClass(CreateUserFragment.this, OTPActivity.class);
                                    startActivity(intent);
//                                Toast.makeText(getActivity(), " 修改成功", Toast.LENGTH_SHORT).show();
                                }
                                if (object.getString("sys_code").toString().equals("501")) {
                                    Intent intent = new Intent();
                                    intent.putExtra("name", nameedittxt.getText().toString());
                                    intent.putExtra("mail", mailedittxt.getText().toString());
                                    intent.putExtra("phone", phoneedittxt.getText().toString());
                                    intent.putExtra("password", pwedittxt.getText().toString());
                                    intent.setClass(CreateUserFragment.this, OTPActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                if (object.getString("sys_code").toString().equals("500")) {
                                    Toast.makeText(CreateUserFragment.this, "帳號已重複", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(CreateUserFragment.this, "資料不足", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
                if (!ipasscheckbox.isChecked()) {
                    Toast.makeText(CreateUserFragment.this, "請勾選同意繼續註冊", Toast.LENGTH_SHORT).show();
                }
//                Bundle bundle = new Bundle();
//                bundle.putString("name",nameedittxt.getText().toString());
//                bundle.putString("mail",mailedittxt.getText().toString());
//                bundle.putString("phone",phoneedittxt.getText().toString());
//                bundle.putString("password",pwedittxt.getText().toString());


                break;
            case R.id.cubackbtn:
                this.finish();
                break;
        }

    }
}
