package com.nnosy.partner2go.ActivityFragment;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.nnosy.partner2go.R;

/**
 * Created by Nosy on 2017/1/24.
 */

public class FaqsActivity extends Activity {
    WebView webView;

    String url = "https://my.here2go.asia///help";

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
