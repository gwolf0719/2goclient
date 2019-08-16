package com.nnosy.taxigoclient.ActivityFragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.webkit.JsPromptResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.nnosy.taxigoclient.PrefsHelper;
import com.nnosy.taxigoclient.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class PayDoneActivity extends Activity {
    Handler viewhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Toast.makeText(PayDoneActivity.this, "mcash fail", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    PaymentD();
                    Toast.makeText(PayDoneActivity.this, "success", Toast.LENGTH_SHORT).show();

                    break;
            }
        }
    };

    Handler checkpayment = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    System.out.println(" = check start = ");
                    String url = "http://my.here2go.asia/api_booking/chk_payment?order_id=" + PrefsHelper.setorderid(getApplication());
                    aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
                        @Override
                        public void callback(String url, JSONObject object, AjaxStatus status) {
                            super.callback(url, object, status);

                            try {
//                                System.out.println("obj = : " + object.getJSONObject("order_info").getString("pay_status").toString());
                                if (object.getString("pay_status") != null) {
//                                    if (object.getJSONObject("order_info").has("pay_status")) {
                                    System.out.println("payment = : " + object.getString("pay_status"));
                                    if (object.getString("pay_status").equals("2")) {
                                        if (PrefsHelper.setmcashdone(getApplication()).equals("0")) {
                                            PaymentD();
                                            Intent intent = new Intent();
                                            intent.setClass(PayDoneActivity.this, ClientSafelyActivity.class);
                                            startActivity(intent);
                                            PrefsHelper.getmcashdone(getApplication(), "1");
                                        }
                                        finish();

                                    }
//                                    if (object.getString("pay_status").equals("9")) {
//                                        finish();
//                                        Intent intent = new Intent();
//                                        intent.setClass(PayDoneActivity.this, PayDoneActivity.class);
//                                        startActivity(intent);
//                                    }
//                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    break;
            }
        }
    };
    AQuery aQuery;
    ProgressDialog dialog;

    public void checkcash() {
        String url = "http://my.here2go.asia/api_booking/get_once?order_id=" + PrefsHelper.setorderid(getApplication());
        aQuery.ajax(url, null, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);

                try {
                    System.out.println("payment obj = : " + object.getJSONObject("order_info").getJSONObject("pay_status").toString());
                    if (object.getJSONObject("order_info").getJSONObject("pay_status").equals("2")) {
//                                    Intent intent = new Intent();
//                                    intent.setClass(PayDoneActivity.this, ClientSafelyActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                        menulayout.setVisibility(View.GONE);
//                        PrefsHelper.gethaveg(getApplication(), "0");
//                        overlayout.setVisibility(View.INVISIBLE);
//                        driverlayout.setVisibility(View.GONE);
//                        driverbtn.setVisibility(View.INVISIBLE);
//                        cashcontainer.setVisibility(View.GONE);
//                        ratlayout.setVisibility(View.VISIBLE);
//                        nametxt.setText(PrefsHelper.setmembername(getApplication()));
//                        numbertxt.setText(PrefsHelper.setphonenumber(getApplication()));
//                        PrefsHelper.getmcashdon(getApplication(), "1");
//                        Message message = new Message();
//                        message.what = 0;
//                        toasthandler.sendMessage(message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paydonelayout);
        aQuery = new AQuery(this);
        WebView webView = (WebView) findViewById(R.id.webview);
        PrefsHelper.getmcashdone(getApplication(), "0");
        String url = "https://www.mmas.biz/api_booking/payment?" + "order_id=" + PrefsHelper.setclientorderid(getApplication());
//        System.out.println("url = : " + url);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
//                System.out.println(" onjs prompt = : view = " + view + "url = " + url + " message = " + message + " dafaultvalue = : " + defaultValue + " result = " + result);
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 0;

            }
        }).start();

        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String redirected;
                redirected = view.getUrl();
                redirected = Uri.decode(redirected);
                System.out.println(" redirected = : " + redirected);
//                if (redirected != null && redirected.equals(redirectUri)) {
//                    wvLogin.stopLoading();
//                    Intent mIntent = new Intent(LoginActivity.this, Update.class);
//                    startActivity(mIntent);
//                }

                if (redirected.contains("https://www.mmas.biz/mcash_fail?")) {
                    Message message = new Message();
                    message.what = 0;
                    viewhandler.sendMessage(message);
                    finish();
                    Intent intent = new Intent();
                    intent.setClass(PayDoneActivity.this, PayDoneActivity.class);
                    startActivity(intent);
//                    webView.loadUrl(url);
                }
                if (redirected.contains("https://www.mmas.biz/mcash_success?")) {
                    if (PrefsHelper.setcashtype(getApplication()).equals("ambank")) {
                    } else {
                        Message message = new Message();
                        message.what = 1;
                        PrefsHelper.getmcashdone(getApplication(), "1");
                        viewhandler.sendMessage(message);
                        Intent intent = new Intent();
                        intent.setClass(PayDoneActivity.this, ClientSafelyActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                if (PrefsHelper.setcashtype(getApplication()).equals("ambank")) {
//                    dialog.dismiss();
//                }
//                System.out.println("pagestarted = : " + view + " " + url + " " + favicon);

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                System.out.println("shouldoverrideurlloading = : " + view + " " + url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
                super.onReceivedSslError(view, handler, error);
            }


        });


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
////            webView.loadUrl("javascript:callJS()");
//            webView.evaluateJavascript(url, new ValueCallback<String>() {
//                @Override
//                public void onReceiveValue(String value) {
//                    System.out.println("receivevalue = " + value);
//                }
//            });
//        }
        webView.loadUrl(url);
        if (PrefsHelper.setcashtype(getApplication()).equals("ambank")) {

            new Thread(new Runnable() {
                @Override
                public void run() {

//                        Thread.sleep(4000);
                    Message message = new Message();
                    message.what = 0;
                    checkpayment.sendMessage(message);
                    checkpayment.postDelayed(this, 5000);


                }
            }).start();
        }

    }
    public void PaymentD(){
            String url = "https://www.mmas.biz/api_booking/payment?order_id=" + PrefsHelper.setorderid(getApplication());
            aQuery.ajax(url,null,JSONObject.class,new AjaxCallback<JSONObject>(){
                @Override
                public void callback(String url, JSONObject object, AjaxStatus status) {
                    super.callback(url, object, status);
                    System.out.println(" Client payment obj === : " + object + "\n url == :" + url);
                }
            });
    }

    private static Boolean isExit = false;
    private static Boolean hasTask = false;

    Timer timerExit = new Timer();
    TimerTask task = new TimerTask() {

        @Override
        public void run() {
            isExit = false;
            hasTask = true;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 判斷是否按下Back
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            relativeLayout.setVisibility(View.INVISIBLE);
            // 是否要退出


        }
        return false;
    }


}
