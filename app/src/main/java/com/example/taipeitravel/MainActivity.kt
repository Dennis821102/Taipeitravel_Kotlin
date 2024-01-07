package com.example.taipeitravel

import android.annotation.SuppressLint
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONObject

class MainActivity : AppCompatActivity() {



    private var lang: String = "zh-tw"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val newsFragment = NewsFragment.newInstance(lang)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, newsFragment)
        transaction.commit()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_button -> {
                showOptionsDialog()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun showOptionsDialog() {
        // 定義選項列表
        val options = arrayOf("繁體中文","简体中文","English","日本語","한국어","Español","Bahasa Indonesia","อักษรไทย","Tiếng Việt")

        // 創建一個 AlertDialog.Builder
        val builder = AlertDialog.Builder(this)
        builder.setTitle("選擇語言")

        // 設置選項列表和點擊監聽器
        builder.setItems(options) { dialog, which ->
            val selectedOption = options[which]
            if (which == 0){
                lang = "zh-tw"
            }else if (which == 1){
                lang = "zh-cn"
            }else if (which == 2){
                lang = "en"
            }else if (which == 3){
                lang = "ja"
            }
            else if (which == 4){
                lang = "ko"
            }
            else if (which == 5){
                lang = "es"
            }
            else if (which == 6){
                lang = "id"
            }else if (which == 7){
                lang = "th"
            }
            else if (which == 8){
                lang = "vi"
            }
            val newsFragment = NewsFragment.newInstance(lang)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, newsFragment)
            transaction.commit()
        }
        builder.show()
    }
    private fun showToast(message: String) {
        // 這裡可以實現顯示 Toast 的邏輯
    }

}