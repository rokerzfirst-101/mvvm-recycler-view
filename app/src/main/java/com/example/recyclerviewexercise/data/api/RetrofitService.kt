package com.example.recyclerviewexercise.data.api

import com.example.recyclerviewexercise.data.model.MainActivityResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("/quotes")
    fun getQuotes() : Call<MainActivityResponse>

    companion object {

        private const val baseUrl = "https://customquote.free.beeceptor.com"
        var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}