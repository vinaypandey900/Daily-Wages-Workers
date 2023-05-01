package com.knit.dailywagesworkers;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class admin extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        webView=findViewById(R.id.webView2);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://console.firebase.google.com/u/3/project/dailywagesworkers-3219a/authentication/users");
        webView.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }
    private class MywebClient extends WebViewClient{

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    @Override
    public void onBackPressed() {
        if(webView.isFocused() && webView.canGoBack()){
            webView.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
}