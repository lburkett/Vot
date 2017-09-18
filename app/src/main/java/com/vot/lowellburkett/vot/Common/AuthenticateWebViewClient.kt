package com.vot.lowellburkett.vot.Common

import android.content.Context
import android.net.Uri
import android.preference.PreferenceManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.vot.lowellburkett.vot.R

class AuthenticateWebViewClient(finish: () -> Unit) : WebViewClient() {
    val finished = finish

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        overrideUrlLoading(view, Uri.parse(url))

        return true
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        overrideUrlLoading(view, request?.url!!)

        return true
    }

    private fun overrideUrlLoading(view: WebView?, uri: Uri) {
        if (uri.toString().contains(view?.context!!.getString(R.string.redirect_uri))) {
            var state = uri.getQueryParameters("state").first()
            var code = uri.getQueryParameters("code").first()

            PreferenceManager.getDefaultSharedPreferences(view.context).edit().putString("auth_token", code).apply()

            finished.invoke()
        }
        else
            view.loadUrl(uri.toString())
    }
}