package arche.phodal.com.arche

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.graphics.Bitmap
import android.support.v7.app.ActionBar
import com.wang.avi.AVLoadingIndicatorView

class ArcheWebViewFragment : Fragment() {
    private var mWebView: WebView? = null
    private var avi: AVLoadingIndicatorView? = null

    private var actionBar: ActionBar? = null

    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        actionBar?.show()
    }

    override fun onDetach() {
        super.onDetach()
        actionBar?.show()
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_webview, container, false)

        avi = view?.findViewById(R.id.avi)
        mWebView = view?.findViewById(R.id.webview)

        mWebView!!.loadUrl("file:///android_asset/www/index.html")

        val webSettings = mWebView!!.settings
        webSettings.javaScriptEnabled = true
        mWebView!!.webViewClient = WebViewClient()

        setLoadingProgress()

        actionBar = (this.activity as MainActivity).supportActionBar
        actionBar?.hide()

        return view
    }

    private fun setLoadingProgress() {
        mWebView!!.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                avi!!.show()
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                avi!!.hide()
                super.onPageFinished(view, url)
            }
        }
    }

}