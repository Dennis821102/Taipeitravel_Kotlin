package com.example.taipeitravel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import java.io.IOException

class HttpGet {
    private val client = OkHttpClient()

//這邊新增
//2024120 05.13在增加 ---
    suspend fun getJson(url: String): String {
        return try {
            val request = Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .build()

            client.newCall(request).execute().use { response ->
                handleResponse(response)
            }
        } catch (e: IOException) {
            "Error: ${e.message}"
        }
    }

    private suspend fun handleResponse(response: okhttp3.Response): String {
        return if (response.isSuccessful) {
            response.body()?.string() ?: ""
        } else {
            "Error: ${response.code()}"
        }
    }
}