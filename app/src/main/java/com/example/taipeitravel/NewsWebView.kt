package com.example.taipeitravel

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ImageView
import android.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewsWebView : AppCompatActivity() {
    private lateinit var web001: WebView
    private lateinit var gifImageView: ImageView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_web_view)
        web001 = findViewById(R.id.web001)
        gifImageView = findViewById(R.id.gifImageView)

        web001.visibility = View.GONE
        lifecycleScope.launch {
            Glide.with(this@NewsWebView)
                .load(R.drawable.l) // 替換成你的 GIF 資源
                .into(gifImageView)

            delay(3000) // 假設顯示 3 秒
            gifImageView.visibility = View.INVISIBLE
            web001.visibility = View.VISIBLE

            val dpWidth = 700
            val dpHeight = 700

            val scale = resources.displayMetrics.density
            val widthInPixel = (dpWidth * scale + 0.5f).toInt()
            val heightInPixel = (dpHeight * scale + 0.5f).toInt()

            val layoutParams = gifImageView.layoutParams
            layoutParams.width = widthInPixel
            layoutParams.height = heightInPixel
            gifImageView.layoutParams = layoutParams
        }
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val receivedValue = intent.getStringExtra("url")
        val title = intent.getStringExtra("title")
        supportActionBar?.title = title
        Log.i("",receivedValue.toString())
        web001.webChromeClient = WebChromeClient()

        // 設置 WebView 的屬性
        val webSettings = web001.settings
        webSettings.javaScriptEnabled = true // 啟用 JavaScript
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true
        web001.loadUrl(receivedValue.toString())
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}