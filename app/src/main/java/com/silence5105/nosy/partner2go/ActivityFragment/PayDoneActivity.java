package com.silence5105.nosy.partner2go.ActivityFragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.silence5105.nosy.partner2go.PrefsHelper;
import com.silence5105.nosy.partner2go.R;

public class PayDoneActivity extends Activity {
    Handler viewhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Toast.makeText(PayDoneActivity.this, "失敗", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(PayDoneActivity.this, "成功", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paydonelayout);
        final WebView webView = (WebView) findViewById(R.id.webview);

        String url = "https://my.here2go.asia/api_booking/payment?" + "order_id=" + PrefsHelper.setclientorderid(getApplication());
        System.out.println("url = : " + url);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                System.out.println(" onjs prompt = : view = " + view + "url = " + url + " message = " + message + " dafaultvalue = : " + defaultValue + " result = " + result);
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
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
                if (redirected.contains("fail")) {
                    Message message = new Message();
                    message.what = 0;
                    viewhandler.sendMessage(message);
                    webView.loadUrl(url);
                }
                if (redirected.contains("success")) {
                    Message message = new Message();
                    message.what = 1;
                    viewhandler.sendMessage(message);


                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                System.out.println("pagestarted = : " + view + " " + url + " " + favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                System.out.println("shouldoverrideurlloading = : " + view + " " + url);
                return true;
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
    }


}
