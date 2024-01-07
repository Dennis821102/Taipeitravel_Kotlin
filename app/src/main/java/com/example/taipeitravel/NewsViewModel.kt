package com.example.taipeitravel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class NewsViewModel : ViewModel() {
    val newsData = MutableLiveData<List<String>>()
    val AttractionsData = MutableLiveData<List<String>>()

    fun getNews(url: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val result = HttpGet().getJson(url)
            parseJson(result)
        }
    }
    fun getAttractions(url: String){
        GlobalScope.launch(Dispatchers.IO) {
            val result = HttpGet().getJson(url)
            parseJsonAtt(result)
        }
    }

    private suspend fun parseJson(jsonString: String) {
        withContext(Dispatchers.Main) {
            try {
                val newsList = mutableListOf<String>()
                val jsonObject = JSONObject(jsonString)
                val dataArray = jsonObject.getJSONArray("data")

                for (i in 0 until dataArray.length()) {
                    val dataObject = dataArray.getJSONObject(i)
                    val titleValue = dataObject.getString("title")
                    val descriptionValue = dataObject.getString("description");
                    val url = dataObject.getString("url");
                    newsList.add(titleValue+"*"+descriptionValue+"*"+url)
                }
                newsData.value = newsList
            } catch (e: Exception) {
                println("Error parsing JSON: ${e.message}")
            }
        }
    }
    private suspend fun parseJsonAtt(jsonString: String) {
        withContext(Dispatchers.Main) {
            try {
                val newsList = mutableListOf<String>()
                val jsonObject = JSONObject(jsonString)
                val dataArray = jsonObject.getJSONArray("data")

                for (i in 0 until dataArray.length()) {
                    val dataObject = dataArray.getJSONObject(i)
                    val titleValue = dataObject.getString("name")
                    val introduction = dataObject.getString("introduction")
                    val images = dataObject.getString("images")
                    val address = dataObject.getString("address")
                    val  tel = dataObject.getString("tel")
                    val open_time = dataObject.getString("open_time")
                    val official_site = dataObject.getString("official_site")
                    newsList.add(titleValue+"*"+introduction+"*"+images+"*"+address+"*"+tel+"*"+open_time+"*"+official_site)
                }
                AttractionsData.value = newsList
            } catch (e: Exception) {
                println("Error parsing JSON: ${e.message}")
            }
        }
    }
}