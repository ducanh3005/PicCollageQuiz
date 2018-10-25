package com.example.shadow.piccollagequiz;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        WebView web = findViewById(R.id.wv_quiz4);

        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.loadUrl("file:///android_asset/shadow.html");
    }
}
