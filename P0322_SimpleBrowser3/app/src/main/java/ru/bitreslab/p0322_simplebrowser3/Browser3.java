package ru.bitreslab.p0322_simplebrowser3;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Browser3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser3);

        WebView webView = (WebView) findViewById(R.id.webView);
        Uri data = getIntent().getData();
        webView.loadUrl(data.toString());
    }
}
