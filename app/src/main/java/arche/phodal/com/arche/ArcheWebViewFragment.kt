package arche.phodal.com.arche

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient


class ArcheWebViewFragment : Fragment() {
    private var mWebView: WebView? = null


    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_webview, container, false)
        mWebView = view?.findViewById(R.id.webview)
        mWebView!!.loadUrl("file:///android_asset/www/index.html")

        val webSettings = mWebView!!.settings
        webSettings.javaScriptEnabled = true
        mWebView!!.webViewClient = WebViewClient()

        return view
    }

}