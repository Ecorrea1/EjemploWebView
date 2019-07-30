package com.example.ejemplowebview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Private

    private val BASE_URL = "https://google.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            //Webview

        webView.webChromeClient = object : WebChromeClient()
            {

            }

        webView.webViewClient   = object  : WebViewClient ()
            {

            }

        val settings = webView.settings
        settings.javaScriptEnabled = true

        webView.loadUrl(BASE_URL)

    }
}
