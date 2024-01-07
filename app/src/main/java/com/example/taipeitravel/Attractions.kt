package com.example.taipeitravel

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.json.JSONArray

class Attractions : AppCompatActivity() {
    private lateinit var att_image001: ImageView
    private lateinit var att_txt001: TextView
    private lateinit var att_txt002: TextView
    private lateinit var att_txt003: TextView
    private lateinit var att_txt004: TextView
    private lateinit var att_txt005: TextView
    private lateinit var containerLayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attractions)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.atttoolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        att_image001 = findViewById<ImageView>(R.id.att_img001)
        att_txt001 = findViewById<TextView>(R.id.att_txt001)
        att_txt002 = findViewById<TextView>(R.id.att_txt002)
        att_txt003 = findViewById<TextView>(R.id.att_txt003)
        att_txt004 = findViewById<TextView>(R.id.att_txt004)
        att_txt005 = findViewById<TextView>(R.id.att_txt005)
        containerLayout = findViewById(R.id.containerLayout)

        val name = intent.getStringExtra("name")
        supportActionBar?.title = name
        val introduction = intent.getStringExtra("introduction")
        val imagesJsonArrayString = intent.getStringExtra("images")
        val imagesJsonArray = JSONArray(imagesJsonArrayString)

        if (imagesJsonArray.length() != 0) {
            val imageObject = imagesJsonArray.getJSONObject(0)
            val srcUrl = imageObject.optString("src")
            println(srcUrl)
            val address = intent.getStringExtra("address")
            val tel = intent.getStringExtra("tel")
            val open_time = intent.getStringExtra("open_time")
            val official_site = intent.getStringExtra("official_site")

            Picasso.get().load(srcUrl).into(att_image001)
            att_txt001.text = "開放時間: " + open_time
            att_txt002.text = "地址:" + address
            att_txt003.text = "聯絡電話: " + tel
            att_txt005.text = introduction

            if (official_site != null) {
                setClickableText(att_txt004, official_site)
            }
            for (i in 1 until imagesJsonArray.length()) {
                val imageObject = imagesJsonArray.getJSONObject(i)
                val srcUrl = imageObject.optString("src")

                val imageView = ImageView(this)
                Picasso.get().load(srcUrl).into(imageView)

                containerLayout.addView(imageView)
            }
        }else{
            val address = intent.getStringExtra("address")
            val tel = intent.getStringExtra("tel")
            val open_time = intent.getStringExtra("open_time")
            att_txt001.text = "開放時間: " + open_time
            att_txt002.text = "地址:" + address
            att_txt003.text = "聯絡電話: " + tel
            att_txt004.visibility = View.GONE
            att_txt005.text = introduction
        }
    }
    @SuppressLint("ResourceAsColor")
    private fun setClickableText(textView: TextView, fullText: String) {
        val spannableString = SpannableString(fullText)

        // 设置点击事件
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                web()
            }
        }

        spannableString.setSpan(clickableSpan, 0, fullText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()
        textView.highlightColor = android.R.color.transparent
        textView.setTextColor(resources.getColor(android.R.color.holo_blue_light))
    }
    fun web(){
        val title = intent.getStringExtra("name")
        val intent = Intent(this, NewsWebView::class.java)
        val bundle = Bundle()
        bundle.putString("url", att_txt004.text.toString())
        bundle.putString("title",title)
        intent.putExtras(bundle)
        startActivity(intent)
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}