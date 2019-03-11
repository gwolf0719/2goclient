package com.nnosy.taxigoclient.ActivityFragment;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.nnosy.taxigoclient.R;

/**
 * Created by Nosy on 2017/1/24.
 */

public class FaqsActivity extends Activity {
    WebView webView;

//    String url = "https://www.mmas.biz/help";
    String url = "http://taxigo.com.my/help.html";

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.faqs,null);
//        webView = (WebView) view.findViewById(R.id.webview);
//        WebSettings webSetting = webView.getSettings();
//        webSetting.setJavaScriptEnabled(true);
//        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
//        webSetting.setAllowFileAccess(true);
//        webSetting.setSupportZoom(true);
//        webSetting.setBuiltInZoomControls(true);
//        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
//        //webSetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        webSetting.setDomStorageEnabled(true);
//        webSetting.setDatabaseEnabled(true);
//        webSetting.setCacheMode(webSetting.LOAD_NO_CACHE);
//
//        webView.loadUrl(url.toString());
//        webView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
//                //Required functionality here
//                return super.onJsAlert(view, url, message, result);
//            }
//        });
//
//        return view;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faqs);
        webView = (WebView) findViewById(R.id.webview);
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        //webSetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSetting.setDomStorageEnabled(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setCacheMode(webSetting.LOAD_NO_CACHE);

        webView.loadUrl(url.toString());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                //Required functionality here
                return super.onJsAlert(view, url, message, result);
            }
        });

    }
}
