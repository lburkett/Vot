package com.vot.lowellburkett.vot.Common

import android.content.Context
import android.net.Uri
import com.vot.lowellburkett.vot.R

class UrlHelper(val context: Context) {

    val authenticationUrl: String get() {
        var builder = getApiUrl()
                .appendEncodedPath(getResourceValue(R.string.auth_segment))
                .appendQueryParameter(getResourceName(R.string.client_id), getResourceValue(R.string.client_id))
                .appendQueryParameter(getResourceName(R.string.response_type), getResourceValue(R.string.response_type))
                .appendQueryParameter(getResourceName(R.string.redirect_uri), getResourceValue(R.string.redirect_uri))
                .appendQueryParameter(getResourceName(R.string.duration), getResourceValue(R.string.duration))
                .appendQueryParameter(getResourceName(R.string.scope), getResourceValue(R.string.scope))
                .appendQueryParameter(getResourceName(R.string.state), getResourceValue(R.string.state))

        return builder.build().toString()
    }

    val acessTokenUrl: String get() {
        var builder = getApiUrl()
                .appendEncodedPath(getResourceValue(R.string.token_segment))

        return builder.build().toString()
    }

    private fun getApiUrl() : Uri.Builder {
        return Uri.Builder().scheme(getResourceValue(R.string.scheme))
                .encodedAuthority(getResourceValue(R.string.base_url))
                .appendEncodedPath(getResourceValue(R.string.api_segment))
    }

    private fun getResourceValue(resourceId: Int) : String {
        return context.getString(resourceId)
    }

    private fun getResourceName(resourceId: Int) : String {
        return context.resources.getResourceEntryName(resourceId)
    }
}