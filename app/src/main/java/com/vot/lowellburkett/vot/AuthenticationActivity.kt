package com.vot.lowellburkett.vot

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.vot.lowellburkett.vot.Common.AuthenticateWebViewClient
import com.vot.lowellburkett.vot.Common.UrlHelper

class AuthenticationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        var webView = findViewById<WebView>(R.id.authenticate_webview);

        webView.webViewClient = AuthenticateWebViewClient({finish()})
        webView.loadUrl(UrlHelper(this).authenticationUrl)
    }
}
