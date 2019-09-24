package com.example.dailyfeed;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class ExploreNews extends AppCompatActivity {

   WebView webView;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.explore_news);

      Intent i = getIntent();
      String newsUrl = i.getStringExtra("newsUrl");
      webView = findViewById(R.id.webView);
      webView.loadUrl(newsUrl);
      webView.setWebViewClient(new WebViewClient() {

         @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
         @Override
         public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return false;
         }

      });

   }
}
