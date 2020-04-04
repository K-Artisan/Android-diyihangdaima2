package com.example.webviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.web_view);

        WebSettings webSettings =  webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

       //该方法解决的问题是打开浏览器不调用系统浏览器，直接用webview打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        }); //在WebView上显示网页
        webView.loadUrl("http://www.baidu.com");
    }
}
