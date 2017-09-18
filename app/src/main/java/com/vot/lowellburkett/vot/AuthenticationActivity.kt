package com.vot.lowellburkett.vot

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.vot.lowellburkett.vot.Common.AuthenticateWebViewClient

class AuthenticationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        var urlString = BuildUriString()
        var webView = findViewById<WebView>(R.id.authenticate_webview);

        webView.webViewClient = AuthenticateWebViewClient({finish()})
        webView.loadUrl(urlString)
    }

    private fun BuildUriString() : String {
        var builder = Uri.Builder().scheme(getString(R.string.scheme))
                .encodedAuthority(getString(R.string.base_url))
                .appendEncodedPath(getString(R.string.auth_segment))
                .appendQueryParameter("client_id", getString(R.string.client_id))
                .appendQueryParameter("response_type", getString(R.string.response_type))
                .appendQueryParameter("redirect_uri", getString(R.string.redirect_uri))
                .appendQueryParameter("duration", getString(R.string.duration))
                .appendQueryParameter("scope", getString(R.string.scope))
                .appendQueryParameter("state", getString(R.string.state))

        return builder.build().toString()
    }
}
