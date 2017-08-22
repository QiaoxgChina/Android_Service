package com.qiaoxg.servicedemo.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.qiaoxg.servicedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OverviewActivity extends Activity {

    @BindView(R.id.overview_wv)
    WebView overviewWv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        ButterKnife.bind(this);

        overviewWv.loadUrl("https://developer.android.google.cn/guide/components/services.html?");

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");


        overviewWv.setWebChromeClient(new WebChromeClient());

        overviewWv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                dialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dialog.dismiss();
            }
        });
    }
}
