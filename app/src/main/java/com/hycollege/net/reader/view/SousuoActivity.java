package com.hycollege.net.reader.view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hycollege.net.reader.R;

public class SousuoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sousuo);
        WebView webView = (WebView) findViewById(R.id.web_view);
        //设置浏览器的属性、调用setJavaScriptEnabled(true)支持JavaS脚本
        webView.getSettings().setJavaScriptEnabled(true);
        //让网页在当前WebView显示
        webView.setWebViewClient(new WebViewClient());
        //调用loadUrl(）并将网址传入、显示出来(必须要连接网络)
        webView.loadUrl("http://www.duokan.com");
    }
}
