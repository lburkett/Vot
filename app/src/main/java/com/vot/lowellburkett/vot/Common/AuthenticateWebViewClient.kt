package com.vot.lowellburkett.vot.Common

import android.content.Context
import android.net.Uri
import android.preference.PreferenceManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
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
            var context = view.context

            var error = uri.getQueryParameters(context.getString(R.string.error)).firstOrNull()
            var code = uri.getQueryParameters(context.getString(R.string.code)).first()
            var state = uri.getQueryParameters(context.resources.getResourceEntryName(R.string.state)).first()

            if (error != null || state != context.getString(R.string.state)) {
                throw Exception(error)
            }

//            var accessTokenUrl = UrlHelper(context).acessTokenUrl
//            var postData = context.getString(R.string.token_post_data)
//                    .replace("AUTHORIZATION_CODE", "authorization_code")
//                    .replace("CODE", code)
//                    .replace("URI", context.getString(R.string.redirect_uri))
//
//            var request = StringRequest(Request.Method.POST, accessTokenUrl, Response.Listener {
//                response ->
//            }, Response.ErrorListener {
//                error ->
//            })

            PreferenceManager.getDefaultSharedPreferences(view.context).edit().putString("auth_token", code).apply()

            finished.invoke()
        }
        else
            view.loadUrl(uri.toString())
    }
}