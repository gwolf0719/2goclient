package com.nnosy.partner2go;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CWebViewClient extends WebViewClient {
    boolean timeout;

    public CWebViewClient() {
        timeout = true;
    }

    @Override
    public void onPageStarted(final WebView view, String url, Bitmap favicon) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (timeout) {
                    view.reload();
                }
            }
        }).start();
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        timeout = false;
    }
}
