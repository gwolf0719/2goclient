package com.nnosy.partner2go.ActivityFragment;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.nnosy.partner2go.BookingtypeItem;
import com.nnosy.partner2go.PrefsHelper;
import com.nnosy.partner2go.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Nosy on 2017/1/24.
 */

public class BookingFragment extends DialogFragment implements View.OnClickListener {
    TextView confirmbtn;
    TextView faretxt, paymenttypelayout;
    String test;
    ImageView bookingclosbtn, budgetbtn, exectuivebtn, nearestbtn, teks1mbtn;
    AQuery aQuery = new AQuery(getActivity());
    HttpURLConnection httpURLConnection;
    Spinner spinner;
    ArrayAdapter adapter;
    ArrayList<BookingtypeItem> arrayList = new ArrayList<>();
    ArrayList<String> stringArrayList = new ArrayList<String>();
    BookingItem bookingItem;
    String arrstring;
    ArrayList arrayList1 = new ArrayList();
    String typestting;
    String[] testtype = new String[]{"Payment type", "cash", "card", "enterprise"};
    Context context;

    //    BookingtypeItem bookingtypeItem;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newbookingfragment, container, false);
//        confirmbtn = (TextView) view.findViewById(R.id.confirmbtn);
//        confirmbtn.setOnClickListener(this);
//        kmtv = (TextView) view.findViewById(R.id.kmtextview);
//        kmtv.setText(PrefsHelper.setkm(getActivity()));
        aQuery = new AQuery(getActivity());
//        bookingtypeItem = new BookingtypeItem(getActivity());
//        adapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, testtype);\
//        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,new String[]{"紅茶","奶茶","綠茶"});

        bookingclosbtn = (ImageView) view.findViewById(R.id.bookingclosebtn);
        bookingclosbtn.setOnClickListener(this);
        budgetbtn = (ImageView) view.findViewById(R.id.budgetbtn);
        budgetbtn.setOnClickListener(this);
        nearestbtn = (ImageView) view.findViewById(R.id.nearestbtn);
        nearestbtn.setOnClickListener(this);
        exectuivebtn = (ImageView) view.findViewById(R.id.exectuivebtn);
        exectuivebtn.setOnClickListener(this);
        teks1mbtn = (ImageView) view.findViewById(R.id.teks1mbtn);
        teks1mbtn.setOnClickListener(this);
        faretxt = (TextView) view.findViewById(R.id.faretxt);
        confirmbtn = (TextView) view.findViewById(R.id.bookingconfirmbtn);
        confirmbtn.setOnClickListener(this);
        paymenttypelayout = (TextView) view.findViewById(R.id.textView23);
//
//        String url = "http://uat.fofo.tw/2go_be/api/get_payment?email=" + PrefsHelper.setmail(getActivity());
//        aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
//            @Override
//            public void callback(String url, JSONObject object, AjaxStatus status) {
//                super.callback(url, object, status);
//                System.out.println(" ===== object ===== " + object);
//                try {
//                    if (object.getString("sys_code").equals("200")) {
//                        System.out.println(" ===== object 200 booking ===== " + object);
////
//                        for (int i = 0; i <= object.getJSONArray("payments").length();
//                             i++) {
//                            System.out.println(" ==== for ==== " + object.getJSONArray("payments").get(i).toString());
////                            arrayList.add(new BookingtypeItem(object.getJSONArray("payments").get(i).toString()));
////                            testtype = object.getJSONArray("payments").get(i).toString();
//                            stringArrayList.add(object.getJSONArray("payments").get(i).toString());
//                            System.out.println(" ===== for out ======" + arrayList.toString());
//                        }
//
//
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        });
//        ArrayAdapter<String> adapter_option = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, new String[]{"123", "233"});
//        String[] str = {"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
//        ArrayAdapter<String> ad = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, stringArrayList);
//        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> lunchList = new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                testtype);

//        .setAdapter(ad);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        spinner.setAdapter(lunchList);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "你選的是" + testtype[position], Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        confirmbtn.setVisibility(View.GONE);
                        break;
                    case 1:
//                        System.out.println("1 ====== ");
                        confirmbtn.setVisibility(View.VISIBLE);
                        PrefsHelper.getpaymenttype(getActivity(),"cast");
                        break;
                    case 2:
                        confirmbtn.setVisibility(View.VISIBLE);
                        PrefsHelper.getpaymenttype(getActivity(),"card");
                        break;
                    case 3:
                        confirmbtn.setVisibility(View.VISIBLE);
                        PrefsHelper.getpaymenttype(getActivity(),"enterprise");
                        break;
                    case 4:
                        confirmbtn.setVisibility(View.VISIBLE);
                        break;
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        spinner.set
        //http://uat.fofo.tw/2go_be/api/get_payment?email=james@fofo.tw

//        httpURLConnection = (HttpURLConnection)

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    //    private AdapterView.OnItemSelectedListener spnOnItemSelected
//            = new AdapterView.OnItemSelectedListener()
//    {
//        @Override
//        public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
//        {
//            // TODO Auto-generated method stub
//            System.out.println(" ==== spinner ==== :"+parent+" "+v+" "+position+" "+id);
//        }
//
//        @Override
//        public void onNothingSelected(AdapterView<?> arg0)
//        {
//            // TODO Auto-generated method stub
//        }
//    };

    static void arrayStringToIntArray(String arrayString) {

        // 將剛剛輸出之 array string 先作去頭去尾處理
        // 並用 split 來分開各個項目
        String[] items = arrayString.replaceAll("\\[", "")
                .replaceAll("\\]", "").split(",");

        // items.length 是所有項目的個數
        int[] results = new int[items.length];

        // 將結果放入 results，
        // 並利用 Integer.parseInt 來將整數字串轉換為 int
        for (int i = 0; i < items.length; i++) {
            results[i] = Integer.parseInt(items[i].trim());
        }

        // 此時已將字串轉換至 results 中，
        // 但為了檢查，我們還是要把 results 印出來。
        // 輸出結果：4, 2, 5, 1, 5, 2, 4, 3,
        for (int element : results) {
            System.out.print(" ===== bookingfragment == =:" + element + ", ");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bookingclosebtn:
                getFragmentManager().beginTransaction().remove(this).commit();

                break;
            case R.id.bookingconfirmbtn:
//                String bookingurl ="http://uat.fofo.tw/2go_be/api/order_create?emil="+PrefsHelper.setmail(getActivity())+"&class="+PrefsHelper.setcarclass(getActivity())+"&start_address="
//                String booking = "http://uat.fofo.tw/2go_be/api/order_create";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                 booking = null;
                        try {
                            URL booking = new URL("http://uat.fofo.tw/2go_be/api/order_create");
                            httpURLConnection = (HttpURLConnection) booking.openConnection();
                            httpURLConnection.setRequestMethod("POST");
                            httpURLConnection.setDoOutput(true);
                            httpURLConnection.setDoInput(true);
                            String data = "email=" + PrefsHelper.setmail(getActivity()) + "&class=" + PrefsHelper.setcarclass(getActivity()) + "&start_address=" + PrefsHelper.setstartadress(getActivity()) + "&end_address=" + PrefsHelper.setendaddress(getActivity()) + "&start_location=" + PrefsHelper.setstartlocation(getActivity()) + "&end_location=" + PrefsHelper.setendlocation(getActivity()) + "&distance=" + PrefsHelper.setkm(getActivity()) + "&times=" + PrefsHelper.settimes(getActivity())+"&payment="+PrefsHelper.setpaymenttype(getActivity());
//                            String testdata = "email=testpppp@gmail.com&class=Budget&start_address=123&end_address=321&start_location=123&end_location=321&distance=1";
                            OutputStream outputStream = httpURLConnection.getOutputStream();
                            outputStream.write(data.getBytes());
                            outputStream.flush();
                            outputStream.close();
                            int responseCode = httpURLConnection.getResponseCode();
//                            String sys_code = httpURLConnection.get;
                            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
                            StringBuilder sb = new StringBuilder();
                            String line = null;
                            while ((line = reader.readLine()) != null) {
                                sb.append(line + "\n");
                            }

                            System.out.println(" ==== responsecode" + sb);
                            System.out.println(" ==== data" + data);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().remove(this).commit();
//                fragmentManager.beginTransaction().replace(R.id.waitlayou,new WaitingdriverFragment()).commit();
                new WaitingdriverFragment().show(getFragmentManager(), "");
//                FragmentManager fragmentManager1 = getFragmentManager();
//                fragmentManager1.beginTransaction().replace(R.id.waitlayou, new WaitingdriverFragment()).commit();
//                getFragmentManager().beginTransaction().remove(new BookingFragment).commit();
                break;
            case R.id.budgetbtn:
                PrefsHelper.getcarclass(getActivity(), "Budget");
//                confirmbtn.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
                paymenttypelayout.setVisibility(View.VISIBLE);
                budgetbtn.setImageDrawable(getResources().getDrawable(R.mipmap.budget_hover));
                exectuivebtn.setImageDrawable(getResources().getDrawable(R.mipmap.executive));
                ;
                nearestbtn.setImageDrawable(getResources().getDrawable(R.mipmap.nearest));
                ;
                teks1mbtn.setImageDrawable(getResources().getDrawable(R.mipmap.teks1m));
                ;
                String url = "http://uat.fofo.tw/2go_be/api/order_estimated_cost?distance=" + PrefsHelper.setkm(getActivity());
                aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                    @Override
                    public void callback(String url, JSONObject object, AjaxStatus status) {
                        super.callback(url, object, status);
                        System.out.println(" ==== " + object);
                        try {
                            faretxt.setText(object.getJSONObject("price").getString("Budget").toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            case R.id.exectuivebtn:
                PrefsHelper.getcarclass(getActivity(), "Executive");
//                confirmbtn.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);

                paymenttypelayout.setVisibility(View.VISIBLE);
                budgetbtn.setImageDrawable(getResources().getDrawable(R.mipmap.budget));
                exectuivebtn.setImageDrawable(getResources().getDrawable(R.mipmap.executive_hover));
                ;
                nearestbtn.setImageDrawable(getResources().getDrawable(R.mipmap.nearest));
                ;
                teks1mbtn.setImageDrawable(getResources().getDrawable(R.mipmap.teks1m));
                ;
                String url1 = "http://uat.fofo.tw/2go_be/api/order_estimated_cost?distance=" + PrefsHelper.setkm(getActivity());
                aQuery.ajax(url1, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                    @Override
                    public void callback(String url, JSONObject object, AjaxStatus status) {
                        super.callback(url, object, status);
                        System.out.println(" ==== " + object);
                        try {
                            faretxt.setText(object.getJSONObject("price").getString("Executive").toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            case R.id.nearestbtn:
                PrefsHelper.getcarclass(getActivity(), "Nearest");
//                confirmbtn.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
                paymenttypelayout.setVisibility(View.VISIBLE);
                budgetbtn.setImageDrawable(getResources().getDrawable(R.mipmap.budget));
                exectuivebtn.setImageDrawable(getResources().getDrawable(R.mipmap.executive));
                ;
                nearestbtn.setImageDrawable(getResources().getDrawable(R.mipmap.nearest_hover));
                ;
                teks1mbtn.setImageDrawable(getResources().getDrawable(R.mipmap.teks1m));
                ;
                String url2 = "http://uat.fofo.tw/2go_be/api/order_estimated_cost?distance=" + PrefsHelper.setkm(getActivity());
                aQuery.ajax(url2, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                    @Override
                    public void callback(String url, JSONObject object, AjaxStatus status) {
                        super.callback(url, object, status);
                        System.out.println(" ==== " + object);
                        try {
                            faretxt.setText(object.getJSONObject("price").getString("Executive").toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            case R.id.teks1mbtn:
                PrefsHelper.getcarclass(getActivity(), "Teks1m");
//                confirmbtn.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
                paymenttypelayout.setVisibility(View.VISIBLE);
                budgetbtn.setImageDrawable(getResources().getDrawable(R.mipmap.budget));
                exectuivebtn.setImageDrawable(getResources().getDrawable(R.mipmap.executive));
                ;
                nearestbtn.setImageDrawable(getResources().getDrawable(R.mipmap.nearest));
                ;
                teks1mbtn.setImageDrawable(getResources().getDrawable(R.mipmap.teks1m_hover));
                ;
                String url3 = "http://uat.fofo.tw/2go_be/api/order_estimated_cost?distance=" + PrefsHelper.setkm(getActivity());
                aQuery.ajax(url3, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                    @Override
                    public void callback(String url, JSONObject object, AjaxStatus status) {
                        super.callback(url, object, status);
                        System.out.println(" ==== " + object);
                        try {
                            faretxt.setText(object.getJSONObject("price").getString("Teks1m").toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;


        }
    }

    public class BookingItem {
        public String type;


        public BookingItem(JSONObject jsonObject) {
            try {
                this.type = jsonObject.getString("payments").toString();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

//    static String intArrayToArrayString(JSONArray array) {
//
//        // 利用 Arrays.toString 可以超簡單輸出 array
//        // 輸出結果：[4, 2, 5, 1, 5, 2, 4, 3]
//        a = Arrays.toString(array);
////        System.out.println(arrayString);
//        return arrayString;
//    }

    public void layoutdismiss() {
//        this.dismiss();
        getFragmentManager().beginTransaction().remove(this).commit();
    }
}
