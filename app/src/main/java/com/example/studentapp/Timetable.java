package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class Timetable extends AppCompatActivity {
    //declaration of variables/reference objects
    private WebView webView;
    private ImageView back, home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable_activity);
        back = findViewById(R.id.iv_timetable_back);
        home = findViewById(R.id.iv_timetable_home);
        webView = findViewById(R.id.wv_timetable);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://ttportalqalive.com/login.html");
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(Timetable.this, Dashboard.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timetable.super.onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }

    }
}