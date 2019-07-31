package com.example.ejemplowebview

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.*
import android.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Private

    private val BASE_URL = "https://www.agenciadediseño.cl/"
    private val SEARCH_PATH = "/search?q="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            //swiperefresh
        swiperefresh.setOnRefreshListener{
            webView.reload()
        }

            //Search
        searchView.setOnQueryTextListener( object : SearchView.OnQueryTextListener{

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                p0?.let {
                    if (URLUtil.isValidUrl(it)) {
                        //Es una URL
                        webView.loadUrl(it)
                    } else {
                        //No es una URl
                        webView.loadUrl("$BASE_URL$SEARCH_PATH$it")
                    }
                }

                return false
            }
        })
            //Webview
        webView.webChromeClient = object : WebChromeClient()
            {


            }

        webView.webViewClient   = object  : WebViewClient ()
            {

                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    return false
                }
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)

                    searchView.setQuery(url, false)
                    swiperefresh.isRefreshing =true

                }
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)

                    swiperefresh.isRefreshing = true
                }
            }


        val settings = webView.settings
        settings.javaScriptEnabled = true

        webView.loadUrl(BASE_URL)

    }
}
